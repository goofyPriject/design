package cn.aireco.platform.sdk.util;

import java.io.IOException;
import java.util.*;

public class SignUtil {

    private static final String MD5 = "MD5";
    private static final String ZERO = "0";

    /**
     * 构建签名
     * 
     * @param paramsMap
     *            参数
     * @param secret
     *            密钥
     * @return
     * @throws IOException
     */
    public static String createSign(Map<String, ?> paramsMap, String secret) {
        Set<String> keySet = paramsMap.keySet();
        List<String> paramNames = new ArrayList<String>(keySet);

        Collections.sort(paramNames);

        StringBuilder paramNameValue = new StringBuilder();

        for (String paramName : paramNames) {
            paramNameValue.append(paramName).append(paramsMap.get(paramName));
        }

        String source = secret + paramNameValue.toString() + secret;

        return MD5Util.encryptUpper(source);
    }

}
