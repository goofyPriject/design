package cn.aireco.platform.aes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {

    /**
     * 编码
     */
    private static final String ENCODING = "UTF-8";
    /**
     * 算法定义
     */
    private static final String AES_ALGORITHM = "AES";
    /**
     * 指定填充方式
     */
    private static final String CIPHER_PADDING = "AES/ECB/PKCS5Padding";
    private static final String CIPHER_CBC_PADDING = "AES/ECB/PKCS7Padding";

    public static void main(String[] args) throws Exception {
        String token = "6CE14835-C490-4735-A074-8593B98A9717";
        String secret = "D5B415E777654F548F2449FC1B614B82";
        System.out.println(encrypt(token, secret));

    }


    public static String encrypt(String plaintext, String key) throws Exception {
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance(CIPHER_CBC_PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] ciphertextBytes = cipher.doFinal(plaintextBytes);
        return Base64.getEncoder().encodeToString(ciphertextBytes);
    }

    public static String decrypt(String ciphertext, String key) throws Exception {
        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance(CIPHER_CBC_PADDING);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] plaintextBytes = cipher.doFinal(ciphertextBytes);
        return new String(plaintextBytes, StandardCharsets.UTF_8);
    }

    /**
     * AES加密
     * @param content 待加密内容
     * @param aesKey  密码
     * @return
     */
    public static String encrypt1(String content, String aesKey){
        if(StringUtils.isBlank(content)){
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) && aesKey.length() == 16){
            try {
                //对密码进行编码
                byte[] bytes = aesKey.getBytes(ENCODING);
                //设置加密算法，生成秘钥
                SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_CBC_PADDING);
                //选择加密
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                //根据待加密内容生成字节数组
                byte[] encrypted = cipher.doFinal(content.getBytes(ENCODING));
                //返回base64字符串
                return Base64Utils.encodeToString(encrypted);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else {
            return null;
        }
    }
//    public static void main(String[] args) {
//        try {
//            String originalText = "Hello AES!";
//            String key = "D5B415E777654F548F2449FC1B614B82";
//            byte[] encryptedBytes, decryptedBytes;
//
//            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
//
//            // Generate a new random IV (Initialization Vector)
//            byte[] iv = new byte[16];
//            IvParameterSpec ivSpec = new IvParameterSpec(iv);
//
//            // Encrypt the original text
//            encryptedBytes = encrypt(originalText.getBytes(), secretKeySpec, ivSpec);
//
//            // Decrypt the encrypted text
//            decryptedBytes = decrypt(encryptedBytes, secretKeySpec, ivSpec);
//
//            String decryptedText = new String(decryptedBytes);
//
//            System.out.println("Original Text: " + originalText);
//            System.out.println("Encrypted Text: " + Base64.getEncoder().encodeToString(encryptedBytes));
//            System.out.println("Decrypted Text: " + decryptedText);
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }

    static byte[] encrypt(byte[] data, SecretKeySpec secretKeySpec, IvParameterSpec ivSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
        return cipher.doFinal(data);
    }

    static byte[] decrypt(byte[] encryptedData, SecretKeySpec secretKeySpec, IvParameterSpec ivSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
        return cipher.doFinal(encryptedData);
    }
}
