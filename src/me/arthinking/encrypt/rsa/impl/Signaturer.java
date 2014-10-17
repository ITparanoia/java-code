package me.arthinking.encrypt.rsa.impl;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public class Signaturer {
	/**
	 * 
	 * Description:����ǩ��
	 * 
	 * @param priKeyText
	 * @param plainText
	 * @return
	 * @author ���ڼ�
	 * @since��2007-12-27 ����10:51:48
	 */
	public static byte[] sign(byte[] priKeyText, String plainText) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(priKeyText));
			KeyFactory keyf = KeyFactory.getInstance("RSA");
			PrivateKey prikey = keyf.generatePrivate(priPKCS8);

			// ��˽Կ����Ϣ��������ǩ��
			java.security.Signature signet = java.security.Signature
					.getInstance("MD5withRSA");
			signet.initSign(prikey);
			signet.update(plainText.getBytes());
			byte[] signed = Base64.encodeToByte(signet.sign());
			return signed;
		} catch (java.lang.Exception e) {
			System.out.println("ǩ��ʧ��");
			e.printStackTrace();
		}
		return null;
	}
}
