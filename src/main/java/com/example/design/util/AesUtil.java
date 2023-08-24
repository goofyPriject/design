package com.example.design.util;

import cn.hutool.crypto.symmetric.AES;

public class AesUtil {

    final static String mode = "ECB";
    final static String padding = "PKCS7Padding";

    public static String pkcs7Encode(String content,String key){
        AES aes = new AES(mode, padding,key.getBytes());
        return aes.encryptBase64(content);
    }

    public static String pkcs7Decode(String content,String key){
        AES aes = new AES(mode, padding,key.getBytes());
        return aes.decryptStr(content);
    }

}
