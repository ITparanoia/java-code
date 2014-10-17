package me.arthinking.encrypt.des;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**       
 * 使用DES加密与解�?可对byte[],String类型进行加密与解�? 
 * 密文可使用String,byte[]存储.   
 * 方法:  
 * void getKey(String   strKey)从strKey的字条生成一个Key     
 * String getEncString(String strMing)对strMing进行加密,返回String密文  
 * String getDesString(String strMi)对strMin进行解密,返回String明文  
 * byte[] getEncCode(byte[] byteS)byte[]型的加密  
 * byte[] getDesCode(byte[] byteD)byte[]型的解密  
 */      
public class Encrypt{
    private Key key;
    private byte[] byteMi = null;
    private byte[] byteMing = null;
    private String strMi= "";
    private String strM= "";
    //  根据参数生成KEY   
    public void setKey(String strKey){
        try{  
            KeyGenerator _generator = KeyGenerator.getInstance("DES");  
            _generator.init(new SecureRandom(strKey.getBytes()));  
            this.key = _generator.generateKey();  
            _generator=null;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    // 加密String明文输入,String密文输出  
    public void setEncString(String strMing){
        try {
            this.byteMing = strMing.getBytes("UTF8");  
            this.byteMi = this.getEncCode(this.byteMing);  
            this.strMi = Base64.encode(this.byteMi);
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            this.byteMing = null;  
            this.byteMi = null;
        }
    }  
    //加密以byte[]明文输入,byte[]密文输出    
    private byte[] getEncCode(byte[] byteS){
        byte[] byteFina = null;  
        Cipher cipher;  
        try {
            cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.ENCRYPT_MODE,key);  
            byteFina = cipher.doFinal(byteS);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        
        return byteFina;
    } 
    // 解密:以String密文输入,String明文输出   
    public void setDesString(String strMi){  
        try {
            this.byteMi = Base64.decode(strMi);  
            this.byteMing = this.getDesCode(byteMi);  
            this.strM = new String(byteMing,"UTF8");  
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            byteMing = null;  
            byteMi = null;
        }  
    }
    // 解密以byte[]密文输入,以byte[]明文输出    
    private byte[] getDesCode(byte[] byteD){
        Cipher cipher;  
        byte[] byteFina=null;  
        try{
            cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.DECRYPT_MODE,key);  
            byteFina = cipher.doFinal(byteD);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            cipher=null;
        }  
        return byteFina;
    } 
    //返回加密后的密文strMi  
    public String getStrMi() {
        return strMi;
    }
    //返回解密后的明文
    public String getStrM() {
        return strM;
    }
    
    public static void main(String[] args) {
        Encrypt encrypt = new Encrypt();
        encrypt.setKey("pcbest_pconline");
        encrypt.setEncString("Jason");
        encrypt.setDesString("7L3nMvqqQwo=");
        System.out.println(encrypt.getStrMi());
        System.out.println(encrypt.getStrM());
    }
}