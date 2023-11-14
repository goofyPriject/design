package cn.aireco.platform.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256HashingTool {

    public static String calculateSHA256Hash(String input) {
        try {
            // 获取SHA-256算法的MessageDigest实例
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // 计算哈希值
            byte[] hash = md.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public static void main(String[] args) {
        String APIKeyID = "hwNwquSruvCuvR1FeFeQkZIO9MXt1YGM";
        String APIKey = "ogYDzn16Dqmg3EsdSNrlk5H1MVGBUrbRF3f526KHIagRFokLazVZnfNyuMqugkgt";
        long timestamp= System.currentTimeMillis();
        String input = APIKeyID+APIKey+timestamp;
        String hash = calculateSHA256Hash(input);
        System.out.println("SHA256 hash of " + input + ": " + hash);
    }
}
