package me.arthinking.encrypt.des;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Des {
    private static byte[] iv = { 10, 12, 6, 4, 5, 1, 18, 9 };

    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64.encode(encryptedData);
    }
    
    public static String decryptDES(String decryptString, String decryptKey) throws Exception {
        byte[] byteMi = Base64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);
    }
    
    public static void parseToken(String encTokenString){
        try {
            String tokenString = decryptDES(encTokenString, "pconline");
            if(!tokenString.equals("")){
                String[] tokenStringArr = tokenString.split("-");
                if(tokenStringArr.length >= 2){
                    int time = Integer.parseInt(tokenStringArr[1]);
                    if(time > System.currentTimeMillis()/1000 - 10000){
                        System.out.println(tokenStringArr[0]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("SubscriptionSecondService.parseToken fail: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        try {
            // System.out.println(decryptDES("OgmuiE+q2n2PN4XvB6G4V0L6+xysA1fEoToDlO3Fk83lUwLj+P4XchJ4etS1 p8Dt", "pconline"));
            System.out.println(encryptDES("abc-" + (System.currentTimeMillis()/1000 + 60*10), "pconline"));
            parseToken("OgmuiE+q2n2PN4XvB6G4V0L6+xysA1fEoToDlO3Fk83Li37cpx8e7RPs3DVc N0t0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}