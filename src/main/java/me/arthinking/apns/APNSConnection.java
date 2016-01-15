package me.arthinking.apns;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.security.KeyStore;
import java.util.logging.Logger;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509KeyManager;

/**
 * 功能描述：与APNS服务器的长连接
 * 
 */
public abstract class APNSConnection
{
	private static final String KS_TYPE = "PKCS12";
	private Logger logger = LoggerFactory.getLogger(getClass());

	private Socket socket;
	private OutputStream output;

	private APNSConnectInfo connectInfo;
	private APNSResponseHandler responseHandler;
	private APNSResponseReaderThread readerThread;

	public APNSConnection(APNSConnectInfo connectInfo,
			APNSResponseHandler responseHandler)
	{
		this.connectInfo = connectInfo;
		this.responseHandler = responseHandler;

		// 打开连接
		open();
	}

	/**
	 * 打开连接
	 */
	private void open()
	{
		try
		{
			long start = System.currentTimeMillis();
			logger.info("going to open connection to APNS Server: {}",
					connectInfo);

			ByteArrayInputStream keyBytes = new ByteArrayInputStream(
					connectInfo.getApnsCert());

			char[] pwd = connectInfo.getApnsSecret().toCharArray();
			// 证书存储器
			KeyStore ks = KeyStore.getInstance(KS_TYPE);
			ks.load(keyBytes, pwd);

			// 证书管理器
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509",
					"SunJSSE");
			kmf.init(ks, pwd);

			X509KeyManager xkm = null;

			for (KeyManager km : kmf.getKeyManagers())
			{
				if (km instanceof X509KeyManager)
				{
					xkm = (X509KeyManager) km;
					break;
				}
			}

			if (xkm == null)
			{
				throw new RuntimeException("X509KeyManager is Null.");
			}

			SSLContext sslContext = SSLContext.getInstance("TLS");

			sslContext.init(new KeyManager[] { xkm }, null, null);

			SSLSocketFactory ssf = sslContext.getSocketFactory();

			SSLSocket sslSocket = (SSLSocket) ssf.createSocket();
			sslSocket.setEnabledProtocols(new String[] { "TLSv1" });
			sslSocket.setKeepAlive(true);
			sslSocket.setTcpNoDelay(true);

			SocketAddress endpoint = new InetSocketAddress(
					connectInfo.getHost(), connectInfo.getPort());
			sslSocket.connect(endpoint);

			// debug
			debugSocket(sslSocket);

			// 保存socket
			socket = sslSocket;
			output = socket.getOutputStream();

			// 启动读线程
			readerThread = new APNSResponseReaderThread(socket);
			readerThread.start();

			long cost = System.currentTimeMillis() - start;
			logger.info("finish to connect to APNS Server. cost={}|{}", cost,
					connectInfo);
		}
		catch (Throwable e)
		{
			logger.warn(
					"error happen when opening connection to APNS Server.{}",
					connectInfo, e);

			shutdown();
		}
	}

	/**
	 * @param socket
	 * @throws SocketException
	 */
	private void debugSocket(SSLSocket socket) throws SocketException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SupportedCipherSuites=")
				.append(socket.getSupportedCipherSuites())
				.append("|EnabledCipherSuites=")
				.append(socket.getEnabledCipherSuites())
				.append("|SupportedProtocols=")
				.append(socket.getSupportedProtocols())
				.append("|EnabledProtocols=")
				.append(socket.getEnabledProtocols())
				.append("|EnableSessionCreation=")
				.append(socket.getEnableSessionCreation())
				.append("|KeepAlive=").append(socket.getKeepAlive())
				.append("|NeedClientAuth=").append(socket.getNeedClientAuth())
				.append("|UseClientMode=").append(socket.getUseClientMode())
				.append("|TcpNoDelay=").append(socket.getTcpNoDelay())
				.append("|SOLinger=").append(socket.getSoLinger())
				.append("|SoTimeout=").append(socket.getSoTimeout())
				.append("|ReceiveBufferSize=")
				.append(socket.getReceiveBufferSize())
				.append("|SendBufferSize=").append(socket.getSendBufferSize())
				.append("|ReuseAddress=").append(socket.getReuseAddress());

		logger.debug("SSLSocket: {}", sb);
	}

	/**
	 * 发送二进制协议数据
	 * 
	 * @param msgBytes
	 * @return
	 */
	public boolean send(byte[] msgBytes)
	{
		if (!checkConnection())
		{
			return false;
		}

		try
		{
			output.write(msgBytes);
			return true;
		}
		catch (IOException e)
		{
			logger.warn(
					"error happen when writing bytes to APNS. shutting down... {}",
					connectInfo, e);

			shutdown();
		}

		return false;
	}

	/**
	 * 验证连接
	 */
	public boolean checkConnection()
	{
		if (socket == null)
		{
			logger.warn("Socket to APNS Server is Null.{}", connectInfo);
			return false;
		}
		else
		{
			if (socket.isClosed() || !socket.isConnected())
			{
				logger.warn("Socket to APNS Server is Closed.{}", connectInfo);
				return false;
			}
		}

		return true;
	}

	/**
	 * 关闭连接
	 */
	public void shutdown()
	{
		logger.info("shutting down Socket to APNS: {}", connectInfo);
		try
		{
			// 关闭socket
			if (socket != null)
			{
				socket.close();
				socket = null;
			}

			// 关闭读线程
			if (readerThread != null)
			{
				readerThread.shutdown();
			}
		}
		catch (Exception e)
		{
			logger.warn("error happen when close socket.{}", connectInfo, e);
		}
	}

	/**
	 * 功能描述：APNS服务器结果读线程
	 * 
	 */
	private class APNSResponseReaderThread extends BaseLoopThread
	{
		private Socket socket;

		public APNSResponseReaderThread(Socket socket)
		{
			super("APNSResponseReaderThread");
			this.socket = socket;

			logger.info("starting APNSResponseReaderThread.");
		}

		@Override
		protected void doInLoop() throws Exception
		{
			if (socket.isClosed())
			{
				logger.warn("socket is closed. close ReaderThread.");
				shutdown();
			}
			else
			{
				try
				{
					readInput(socket.getInputStream());
				}
				catch (IOException e)
				{
					logger.warn(
							"IOException happen when read InputStream from Socket to APNS. Shutdown connection.",
							e);

					shutdown();
				}

			}
		}

		private void readInput(InputStream inputStream) throws IOException
		{
			// 6字节结果
			int bufLen = 6;
			byte[] buf = new byte[bufLen];
			int leave = bufLen;
			int readed = 0;
			while (true)
			{
				int len = inputStream.read(buf, readed, leave);
				logger.debug("read from InputStream, len={}", len);

				if (len == -1)
				{
					throw new IOException("Read the End of InputStream.");
				}

				readed += len;
				leave = bufLen - readed;

				if (leave == 0)
				{
					break;
				}
			}

			if (leave == 0)
			{
				responseHandler.onReceived(buf);
			}
		}
	}
}
