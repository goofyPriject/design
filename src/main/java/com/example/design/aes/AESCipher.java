package com.example.design.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import com.google.common.io.BaseEncoding;

public class AESCipher {
    private static final String ALGORITHM_AES256 = "AES/CBC/PKCS5Padding";// "AES/CBC/PKCS7Padding";
    private static SecretKeySpec secretKeySpec = null;
    private static final String CHARSET = "UTF-8";
    private static final String DEFAULT_IV = "iv is default value";
    private static Cipher cipher;
    private static IvParameterSpec iv;


    public AESCipher(String key) {
        this(key, DEFAULT_IV);
    }

    public AESCipher(String key, String iv) {
        this(Numeric.hexStringToByteArray(key), Numeric.hexStringToByteArray(iv));
    }

    private AESCipher(byte[] key, byte[] iv) {
        // Security.addProvider(new BouncyCastleProvider());
        if (null == key || key.length != 32) {
            throw new RuntimeException("input params key must be 32bit bytes array");
        }
        if (null == iv || iv.length != 16) {
            throw new RuntimeException("input params iv must be 16bit bytes array");
        }
        this.secretKeySpec = new SecretKeySpec(key, "AES");
        this.iv = new IvParameterSpec(iv);
        try {
            this.cipher = Cipher.getInstance(ALGORITHM_AES256);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("instantiation objects Cipher exception");
        }
    }

    /**
     * AES Encrypt algorithm
     *
     * @param encryptSource
     *            not null string
     * @return after AES encrypt result , the type of the string
     */
    public static String getEncryptedMessage(final String encryptSource) {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
        byte[] encryptedTextBytes = null;
        try {
            encryptedTextBytes = cipher.doFinal(encryptSource.getBytes(CHARSET));
        } catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            throw new RuntimeException("AES encrypt exception");
        }
        return Encode.baseEncode(encryptedTextBytes);
    }

    /**
     * AES decrypt algorithm
     *
     * @param decryptSource
     *            AES encrypted cipher, type of String
     * @return decrypted plaintext, type of string
     */
    public String getDecryptMessage(String decryptSource) {

        Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
        byte[] encryptedTextBytes = null;
        String decryptResult = null;
        try {
            encryptedTextBytes = cipher.doFinal(BaseEncoding.base64().decode(decryptSource));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("AES decrypt exception");
        }
        try {
            decryptResult = new String(encryptedTextBytes, CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("bytes array convert into string exception");
        }
        return decryptResult;
    }

    private static Cipher getCipher(int encryptMode) {
        try {
            cipher.init(encryptMode, secretKeySpec, iv);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new RuntimeException("init objects Cipher exception");
        }
        return cipher;
    }
}