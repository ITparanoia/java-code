package me.arthinking.encrypt.rsa.impl;

public class SignProvider {
	private SignProvider() {

	}

	/**
	 * 
	 * Description:У������ǩ��,�˷��������׳������쳣,�ɹ�����true,ʧ�ܷ���false,Ҫ��ȫ����������Ϊ��
	 * 
	 * @param pubKeyText
	 *            ��Կ,base64����
	 * @param plainText
	 *            ����
	 * @param signTest
	 *            ����ǩ��������,base64����
	 * @return У��ɹ�����true ʧ�ܷ���false
	 * @author ���ڼ�
	 * @since��2007-12-27 ����09:33:55
	 */
	public static boolean verify(byte[] pubKeyText, String plainText, byte[] signText) {
		try {
			// ������base64����Ĺ�Կ,������X509EncodedKeySpec����
			java.security.spec.X509EncodedKeySpec bobPubKeySpec = new java.security.spec.X509EncodedKeySpec(
					Base64.decode(pubKeyText));
			// RSA�ԳƼ����㷨
			java.security.KeyFactory keyFactory = java.security.KeyFactory
					.getInstance("RSA");
			// ȡ��Կ�׶���
			java.security.PublicKey pubKey = keyFactory
					.generatePublic(bobPubKeySpec);
			// ������base64���������ǩ��
			byte[] signed = Base64.decode(signText);
			java.security.Signature signatureChecker = java.security.Signature
					.getInstance("MD5withRSA");
			signatureChecker.initVerify(pubKey);
			signatureChecker.update(plainText.getBytes());
			// ��֤ǩ���Ƿ�����
			if (signatureChecker.verify(signed))
				return true;
			else
				return false;
		} catch (Throwable e) {
			System.out.println("У��ǩ��ʧ��");
			e.printStackTrace();
			return false;
		}
	}
}
