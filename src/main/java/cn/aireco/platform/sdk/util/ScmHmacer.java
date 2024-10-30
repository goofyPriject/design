package cn.aireco.platform.sdk.util;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public final class ScmHmacer {
    private static final Logger LOG = Logger.getLogger(ScmHmacer.class);
    private static final Integer MAX_AGE_OFFSET = -100;
    private static final Integer MAX_AGE = 300;
    private static final String ALGORITHM = "HmacSHA1";
    public static final String SIGNATURE_KEY = "_signature_";
    public static final String TIMESTAMP_KEY = "_timestamp_";

    public ScmHmacer() {
    }

    public static String getTimestamp() {
        return String.valueOf((new Date()).getTime() / 1000L);
    }

    public static Map<String, String> convertRequest(Map<String, String[]> stringMap) {
        Map<String, String> params = Maps.newHashMap();
        Iterator i = stringMap.keySet().iterator();

        while(i.hasNext()) {
            String key = (String)i.next();
            String[] values = (String[])stringMap.get(key);
            if (values != null && values.length > 0) {
                params.put(key, values[0]);
            }
        }

        return params;
    }

    /**
     *
     * @param secretKey 系统为接口调用方分配的app_secret
     * @param params
     * @return
     */
    public static String signRequest(String secretKey, Map<String, String> params) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
            mac.init(keySpec);
            mac.update(sortParam(params).getBytes());
            byte[] encryptedBytes = mac.doFinal();
            return Base64.encodeBase64URLSafeString(encryptedBytes);
        } catch (Exception var5) {
            LOG.error("无法对请求签名");
            return null;
        }
    }

    /**
     * 参数排序
     * @param params
     * @return
     */
    public static String sortParam(Map<String, String> params) {
        List<String> sortedParams = new ArrayList();
        Iterator i$ = params.keySet().iterator();

        while(i$.hasNext()) {
            String key = (String)i$.next();
            sortedParams.add(key.toLowerCase() + "=" + (String)params.get(key));
        }

        Collections.sort(sortedParams);
        String sortedUrl = null;
        boolean first = true;
        Iterator i = sortedParams.iterator();

        while(i.hasNext()) {
            String param = (String)i.next();
            if (first) {
                sortedUrl = param;
                first = false;
            } else {
                sortedUrl = sortedUrl + "&" + param;
            }
        }

        return sortedUrl;
    }

    public static String authenticate(String secretKey, Map<String, String> params) {
        String signature = params.get(SIGNATURE_KEY);
        params.remove(SIGNATURE_KEY);
        if (signature==null || "".equals(signature)) {
            LOG.error("参数中没有找到签名或签名为空");
            return "参数中没有找到签名或签名为空";
        } else {
            long timestamp;
            try {
                timestamp = Long.parseLong(params.get(TIMESTAMP_KEY));
            } catch (NumberFormatException var7) {
                LOG.error("时间戳格式错误");
                return "时间戳格式错误";
            }

            if (timestamp <= 0L) {
                LOG.warn("参数中没有找到时间戳或者时间戳为小于0");
                return "参数中没有找到时间戳或者时间戳为小于0";
            } else {
                long reqAge = (new Date()).getTime() / 1000L - timestamp;
                if (reqAge < (long)MAX_AGE_OFFSET) {
                    LOG.error("时间戳超出允许的范围");
                    return "时间戳超出允许的范围";
                } else if (reqAge > (long)MAX_AGE) {
                    LOG.error("签名已过期");
                    return "签名已过期";
                } else {
                    return signature.equals(signRequest(secretKey, params))?"":"签名校验不通过";
                }
            }
        }
    }

    /**
     * 生成secretKey
     * @return
     */
    public static String generateSecretKey() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            SecretKey key = generator.generateKey();
            return Base64.encodeBase64URLSafeString(key.getEncoded());
        } catch (NoSuchAlgorithmException var2) {
            LOG.warn("生成SecretKey出现异常", var2);
            return null;
        }
    }

}

