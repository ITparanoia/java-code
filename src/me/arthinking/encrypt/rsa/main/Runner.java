package me.arthinking.encrypt.rsa.main;

import me.arthinking.encrypt.rsa.impl.KeyGenerater;
import me.arthinking.encrypt.rsa.impl.SignProvider;
import me.arthinking.encrypt.rsa.impl.Signaturer;

public class Runner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KeyGenerater keyGen = new KeyGenerater();
		keyGen.generater();
		
		byte[] priKey = keyGen.getPriKey();
		byte[] pubKey = keyGen.getPubKey();
		
		String plainText = "signatureDemo";
		
		byte[] afterSigned = Signaturer.sign(priKey, plainText);
		String signature = new String(afterSigned);
		System.out.println("your signature is: " + signature);
		
		boolean verifyResult = SignProvider.verify(pubKey, plainText, afterSigned);
		System.out.println("verify result: " + verifyResult);
		
	}

}