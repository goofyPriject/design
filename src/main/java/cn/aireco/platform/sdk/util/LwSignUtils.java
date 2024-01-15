package cn.aireco.platform.sdk.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class LwSignUtils {
    // log
    private final static Logger logger = LoggerFactory.getLogger(LwSignUtils.class);
    /**
     * 加密方式，先MD5再BASE64
     *
     * @param plain
     * @return
     */
    public static String encrypt(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b1 = md.digest(plain.getBytes("UTF-8"));
            // 将等长字节利用Base64算法转换成字符串
            Base64 base64 = new Base64();
            return base64.encodeAsString(b1);

        } catch (Exception e) {
            logger.error("加密失败！", e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String clientId = "60";
        String clientKey = "4aed69cb88a74288970d172b7be01b88";
        String salt = "123";
        System.out.println(encrypt(clientId + clientKey + salt));
    }

}
