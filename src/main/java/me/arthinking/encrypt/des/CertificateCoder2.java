package me.arthinking.encrypt.des;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.crypto.Cipher;

public abstract class CertificateCoder2 {  
   
    public static final String CERT_TYPE = "X.509";  
   
    public static PrivateKey getPrivateKeyByKeyStore(String keyStorePath, String alias, String password) throws Exception {  
        KeyStore keyStore = getKeyStore(keyStorePath, password);  
        return (PrivateKey) keyStore.getKey(alias, password.toCharArray());  
    }  
   
    public static PublicKey getPublicKeyByKeyStore(String certificatePath) throws Exception {  
        Certificate certificate = getCertificate(certificatePath);  
        return certificate.getPublicKey();  
    }  
   
    public static Certificate getCertificate(String certificatePath) throws Exception {  
        CertificateFactory certificateFactory = CertificateFactory.getInstance(CERT_TYPE);  
        FileInputStream fileInputStream = new FileInputStream(new File(certificatePath));  
        Certificate certificate =  certificateFactory.generateCertificate(fileInputStream);  
        fileInputStream.close();  
        return certificate;  
    }  
   
    public static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception {  
        KeyStore keyStore = getKeyStore(keyStorePath, password);  
        return keyStore.getCertificate(alias);  
    }  
   
    public static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {  
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());  
        FileInputStream fileInputStream = new FileInputStream(new File(keyStorePath));  
        keyStore.load(fileInputStream, password.toCharArray());  
        fileInputStream.close();  
        return keyStore;  
    }  

    public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception {  
        PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, alias, password);  
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  
        return cipher.doFinal(data);  
    }  
   
    public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) throws Exception {  
        PrivateKey privateKey = getPrivateKeyByKeyStore(keyStorePath, alias, password);  
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);  
        return cipher.doFinal(data);  
    }  
   
    public static byte[] encryptByPublicKey(byte[] data, String certificatePath) throws Exception {  
        PublicKey publicKey = getPublicKeyByKeyStore(certificatePath);  
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        return cipher.doFinal(data);  
    }  
   
    public static byte[] decryptByPublicKey(byte[] data, String certificatePath) throws Exception {  
        PublicKey publicKey = getPublicKeyByKeyStore(certificatePath);  
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);  
        return cipher.doFinal(data);  
    }  
   
    public static byte[] sign(byte[] data, String keyStorePath, String alias, String password) throws Exception {  
        Signature signature = Signature.getInstance("SHA1withRSA");  
        signature.initSign(getPrivateKeyByKeyStore(keyStorePath, alias, password));  
   
        signature.update(data);  
        return signature.sign();  
    }  
   
    public static boolean verify(byte[] data, byte[] sign, String certificatePath) throws Exception {  
        X509Certificate x509Certificate = (X509Certificate) getCertificate(certificatePath);  
   
        Signature signature = Signature.getInstance("SHA1withRSA");  
        signature.initVerify(x509Certificate);  
        signature.update(data);  
        return signature.verify(sign);  
    }  
}  