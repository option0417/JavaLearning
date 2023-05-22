package tw.com.wd.secure;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncryptor {

    private static final String AES_KEY = "mitake!@86136982";
    private static final String AES_IV = "2563999986136982";
    private static final String AES_MODE = "AES/CBC/PKCS5Padding";

    public static String encrypt(String plaintext) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        IvParameterSpec aesIV = new IvParameterSpec(AES_IV.getBytes());

        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, aesIV);

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        IvParameterSpec aesIV = new IvParameterSpec(AES_IV.getBytes());

        Cipher cipher = Cipher.getInstance(AES_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, aesIV);

        byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) throws Exception {
        String plaintext = "86136982";
        String encryptedtext = AESEncryptor.encrypt(plaintext);

        System.out.printf("Plaintext: %s\n", plaintext);
        System.out.printf("Encryptedtext: %s\n", encryptedtext);
    }
}
