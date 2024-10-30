/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package cn.aireco.platform.sdk.util;

import cn.aireco.platform.util.HttpUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * AKSKHead
 *
 * @since 2021-10-30
 */
public class AKSKHead {
    private String ak;

    private String sk;

    private static final String APPID = "appid";

    private static final String TIMESTAMP = "timestamp";

    private static final String SIGNATURE = "signature";

    private static final String ALGORITHM_HMAC_SHAR256 = "HmacSHA256";

    public AKSKHead() {
    }

    public AKSKHead(String ak, String sk) {
        this.ak = ak;
        this.sk = sk;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String method = "POST";   // 填method字段
        String url = "/o2o/v1/order/createExtOrder";       // 填path字段
//        String url = "/o2o/v1/product/queryExtDisplayProduct";       // 填path字段
//        String url = "/o2o/v1/order/queryExtOrderDetails";
        String ak = "XiO3EcovXDSfLuEHSKaWUI";                        // 填AK字段
        String sk = "bfccEb2sqES8ALU1wclsxRir0uWK0sko0Bry2c0RFNa1h"; // 填SK字段
//        String body =  "{ \"channelType\":2, \"storeID\":\"V9999990006\", \"companyCode\":\"\", \"country\":\"CN\", \"extOrderSource\":96, \"displayProductID\": \"10086318019079\" }";
        String body = "{ \"channelType\": \"2\", \"companyCode\": \"\", \"storeID\": \"V9999990006\", \"extOrderSource\": \"96\", \"extOrderCode\": \"202207261101\", \"orderItemReqArgList\": [{ \"itemId\": \"2601010206801\", \"itemType\": \"S0\", \"qty\": \"1\" }, { \"itemId\": \"2601010206802\", \"itemType\": \"S0\", \"qty\": \"1\" }, { \"itemId\": \"0086012601030016001\", \"itemType\": \"S0\", \"qty\": \"1\" } ], \"delivery\": { \"consignee\": \"魏祥国\", \"province\": \"广东省\", \"city\": \"深圳市\", \"district\": \"龙岗区\", \"street\": \"坂田街道\", \"address\": \"天安云谷\", \"mobile\": \"13632514321\" }, \"invoiceType\": \"3\", \"country\": \"CN\" }";
//        String body = "{ \"channelType\": \"2\", \"companyCode\": \"\", \"storeID\": \"V9999990006\", \"extOrderSource\": \"96\", \"orderCodeList\": [\"22350003433\",\"22350003432\"], \"country\": \"CN\" }";
        String appid = "";
        String deviceTimeStamp = String.valueOf(System.currentTimeMillis());
        String header = Optional.ofNullable(new AKSKHead(ak, sk)
                        .getAKSKHead(method, url, null, body, deviceTimeStamp))
                .orElse(new Authorization(null,null, null))
                .toString();
        System.out.println("在header中填写如下内容");
        System.out.println("===========================================================================");
        System.out.println("VmallAuthorization: " + header);
        System.out.println("===========================================================================");
        System.out.println("在body中填写如下内容");
        System.out.println("===========================================================================");
        System.out.println(body);
        System.out.println("===========================================================================");
        String url1 = "https://openapisit.test.vmall.com"+url;
        Map<String, String> header1 = new HashMap<>();
        header1.put("VmallAuthorization", header);
        header1.put("Content-Type", "text/plain");
        String post = HttpUtils.post(url1, header1, body);
        System.out.println("返回结果如下");
        System.out.println("=");
        System.out.println(post);
    }

    /**
     * getAKSKHead
     *
     * @param method method
     * @param path path
     * @param query query
     * @param body body
     * @param deviceTimeStamp deviceTimeStamp
     * @return Authorization Authorization
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws InvalidKeyException InvalidKeyException
     */
    public Authorization getAKSKHead(String method, String path, String query, String body, String deviceTimeStamp)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String timeStamp =
                (deviceTimeStamp == null || deviceTimeStamp.isEmpty()) ?
                        String.valueOf(System.currentTimeMillis()): deviceTimeStamp;
        Supplier<String> getStringToSign = () -> {
            StringBuilder sb = new StringBuilder();
            sb.append("method=")
                    .append(method)
                    .append("&path=")
                    .append(path)
                    .append("&query=")
                    .append(Optional.ofNullable(query).orElse(""))
                    .append("&body=")
                    .append(Optional.ofNullable(body).orElse(""))
                    .append("&appid=")
                    .append(ak)
                    .append("&timestamp=")
                    .append(timeStamp);
            return sb.toString();
        };
        return new Authorization(ak, timeStamp, buildSignature(getStringToSign, sk));
    }

    public static String buildSignature(Supplier<String> getStringToSign, String sk)
            throws NoSuchAlgorithmException, InvalidKeyException {
        return java.util.Base64.getEncoder().encodeToString(hmacSha256(getStringToSign.get(), sk));
    }

    private static byte[] hmacSha256(String message, String secKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(ALGORITHM_HMAC_SHAR256);
        SecretKeySpec secretKeySpec =
                new SecretKeySpec(secKey.getBytes(StandardCharsets.UTF_8), ALGORITHM_HMAC_SHAR256);
        mac.init(secretKeySpec);
        return mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * AKSK鉴权头
     */
    static class Authorization {

        private String appid;

        private String timestamp;

        private String signature;


        Authorization(String appid, String timestamp, String signature) {
            this.signature = signature;
            this.appid = appid;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("sign_method=")
                    .append(ALGORITHM_HMAC_SHAR256)
                    .append(",appid=")
                    .append(appid)
                    .append(",timestamp=")
                    .append(timestamp)
                    .append(",signature=")
                    .append(signature)
                    .toString();
        }
    }
}
