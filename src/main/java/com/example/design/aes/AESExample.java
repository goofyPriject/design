package com.example.design.aes;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;

public class AESExample {
    private static final String ALGORITHM = "AES/ECB/PKCS7Padding";

    public static void main(String[] args) throws Exception {
//        Security.addProvider(new BouncyCastleProvider());
//
//        String plaintext = "Hello, World!";
//        String key = "D5B415E777654F548F2449FC1B614B82";
//        byte[] encrypted = encrypt(plaintext, key);
//        String decrypted = decrypt(encrypted, key);
//
//        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
//        System.out.println("Decrypted: " + decrypted);
    }

    public static byte[] encrypt(String plaintext, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(byte[] ciphertext, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
