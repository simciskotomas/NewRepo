package cz.sde.DatabaseControl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
/**
 Aes encryption
 */
public class AES
{

    private static SecretKeySpec secretKey ;
    private static byte[] key ;

    private static String decryptedString;
    private static String encryptedString;

    public static void setKey(String myKey){


        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            System.out.println(key.length);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
            System.out.println(key.length);
            System.out.println(new String(key,"UTF-8"));
            secretKey = new SecretKeySpec(key, "AES");


        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    public static String getDecryptedString() {
        return decryptedString;
    }
    public static void setDecryptedString(String decryptedString) {
        AES.decryptedString = decryptedString;
    }
    public static String getEncryptedString() {
        return encryptedString;
    }
    public static void setEncryptedString(String encryptedString) {
        AES.encryptedString = encryptedString;
    }
    public static String encrypt(String strToEncrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);


            setEncryptedString(Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));

        }
        catch (Exception e)
        {

            System.out.println("Error while encrypting: "+e.toString());
        }
        return null;
    }
    public static String decrypt(String strToDecrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            setDecryptedString(new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt))));

        }
        catch (Exception e)
        {

            System.out.println("Error while decrypting: "+e.toString());
        }
        return null;
    }
    public static void main(String args[])
    {
        //final String strToEncrypt = "jdbc:sqlserver://flologic.database.windows.net:1433;database=FloLogicTest;user=FloLogicAdmin@flologic;password=*sdeSWdev9061700;encrypt=true;trustServerCertificate=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        final String strPssword = "encryptor key";
        AES.setKey(strPssword);

        //AES.encrypt(strToEncrypt.trim());

        //System.out.println("String to Encrypt: " + strToEncrypt);
        //System.out.println("Encrypted: " + AES.getEncryptedString());

        final String strToDecrypt =  "QXXK4sWUXl1JmtQnL3SlogBt7PX9nIHSU0bwTfbAhzOYn8g4OUS9cm8X2Rz9Z6E" +
                "/iHpGyaIR/Zjt6Mcad6YIjPFkyYdeoW7Z+Ac02qhNqjpUtgMB1T7BxbvogzHjHeWClSJNWgZ3T9dhixl43+jG" +
                "fbtxlUWXg57xOnhJai0M4s53la+5ufgeeyNkjlHBOqWs28/bbpGjzE86TSduaDf2tf4pZPwtUB0RNMATf1s8zg+sKs" +
                "36NC4h8/yjAppqKUOpFIlPp7c5J1VsfYIRsFpnj5W7x70JFik8ah6tguBtM0Q33A14bGczonwk3F7EGXgi";

        AES.decrypt(strToDecrypt.trim());

        System.out.println("String To Decrypt : " + strToDecrypt);
        System.out.println("Decrypted : " + AES.getDecryptedString());

    }

    public static String decryptMyString(String encrypted)
    {

        final String strPssword = "rRP!,.6gy}L9[mx-";
        AES.setKey(strPssword);




        final String strToDecrypt = encrypted;

        AES.decrypt(strToDecrypt.trim());

        System.out.println("String To Decrypt : " + strToDecrypt);
        System.out.println("Decrypted : " + AES.getDecryptedString());

        return AES.getDecryptedString();

    }

    public static String encryptMyString(String stringToEnctypt)
    {

        final String strToEncrypt = stringToEnctypt;
        final String strPssword = "rRP!,.6gy}L9[mx-";
        AES.setKey(strPssword);

        AES.encrypt(strToEncrypt.trim());

        System.out.println("String to Encrypt: " + strToEncrypt);
        System.out.println("Encrypted: " + AES.getEncryptedString());

        return AES.getEncryptedString();

    }

}