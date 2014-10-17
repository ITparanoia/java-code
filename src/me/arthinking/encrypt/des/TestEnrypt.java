package me.arthinking.encrypt.des;


public class TestEnrypt {
    private static String password = "123456";  
    private static final String alias = "CN=pconline, OU=pconline, O=pconline, L=guangzhou, ST=guangdong, C=cn";  
    private static final String certificatePath = "keystore.p7b";  
    private static final String keyStorePath = "D://.keystore";  
   
    static String orgText = "plainText";  
   
    static String sign_base64 = "SgU63H9NH6fUY9aJ91RTzhR1t3BD6zVmDHXxKf6Ib5RnkiUAq23m7+RWkiDSpVl80WUzwLTOiBwoUYpc98tBlSqiSbfEy5t7QZzso7Fa7KSDZG34VTJ6Yiw7IJvhlkh5eSjyUTw7kfPE0rhw2oTEGu3kRP/nfvklRtTasxVVQkY=";  // iOS客户端加签后的字符串base64编码  
   
    public static String enryptedText = "YWAOP2yS6vqOjT73bHSdE/yC8JRhm5Q2qZSSo4yij+uzY6zX9dDN3qzZfNZ2uX1n0nZ+0XdUVT0CVlgTnatABaGdunKIfThkEJygF0BCaL2MsVwYSzbwDHL8+Xt/9yY32w4kH8To9i/+vaBlkzfWTtCmpD63TJMoBa3ikX6u/qY=";// iOS客户端加密后的字符串base64编码  
   
    static String org = "Hello World!";   //加签的原文  
    
    /**  
     * @param args  
     * @throws Exception   
     */  
    public static void main(String[] args) throws Exception {  
        // boolean isSuc = CertificateCoder2.verify(org.getBytes(), Base64.decodeBase64(sign_base64), certificatePath);  
        // System.out.println(isSuc);  
   
        byte[] decryted = CertificateCoder2.decryptByPrivateKey(Base64.decode(enryptedText), keyStorePath, alias, password);  
        System.out.println(new String(decryted));  
    }  
}