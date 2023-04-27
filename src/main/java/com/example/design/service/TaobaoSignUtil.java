package com.example.design.service;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.Constants;
import com.taobao.api.internal.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TaobaoSignUtil {

    private static String ZTAPP_KEY = "UzB1WkZTeGh0VEh3NTZENTFrcFd5dThBcmRCX3BkVGcyOVYtNXNpMDMyND06MTUwMzc2MzAyMjM0OTMzODYyNA";


    /**
     *
     *
     * @param params
     *            所有字符型的TOP请求参数（这个是除了sign以外的所有字段）
     * @param body
     *            请求主体内容
     * @param secret
     *            签名密钥
     * @param signMethod
     *            签名方法，目前支持：空（老md5)、md5, hmac_md5三种
     * @return 签名
     */
    public static String signTopRequest(Map<String, String> params, String body, String secret, String signMethod)
            throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
            query.append(secret);
        }
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.areNotEmpty(key, value) && !"sign".equals(key) ) {
                query.append(key).append(value);
            }
        }

        // 第三步：把请求主体拼接在参数后面
        if (body != null) {
            query.append(body);
        }

        // 第四步：使用MD5/HMAC加密
        byte[] bytes;
        if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), secret);
        } else if (Constants.SIGN_METHOD_HMAC_SHA256.equals(signMethod)) {
            bytes = encryptHMACSHA256(query.toString(), secret);
        }  else {
            query.append(secret);
            System.out.println("签名字符串："+query);
            bytes = encryptMD5(query.toString());
        }

        // 第五步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    private static byte[] encryptHMAC(String data, String secret) throws IOException {
        Object var2 = null;

        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(data.getBytes("UTF-8"));
            return bytes;
        } catch (GeneralSecurityException var5) {
            throw new IOException(var5.toString());
        }
    }

    private static byte[] encryptHMACSHA256(String data, String secret) throws IOException {
        Object var2 = null;

        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(data.getBytes("UTF-8"));
            return bytes;
        } catch (GeneralSecurityException var5) {
            throw new IOException(var5.toString());
        }
    }

    public static byte[] encryptMD5(String data) throws IOException {
        return encryptMD5(data.getBytes("UTF-8"));
    }

    public static byte[] encryptMD5(byte[] data) throws IOException {
        Object var1 = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(data);
            return bytes;
        } catch (GeneralSecurityException var3) {
            throw new IOException(var3.toString());
        }
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();

        for(int i = 0; i < bytes.length; ++i) {
            String hex = Integer.toHexString(bytes[i] & 255);
            if (hex.length() == 1) {
                sign.append("0");
            }

            sign.append(hex.toUpperCase());
        }

        return sign.toString();
    }

    public static Map<String, String> getQueryMap(String httpQueryString, String charset) throws IOException {
        Map<String, String> queryMap = new HashMap();
        String[] params = httpQueryString.split("&");
        for(int i = 0; i < params.length; ++i) {
            String[] kv = params[i].split("=");
            String key;
            if (kv.length == 2) {
                key = URLDecoder.decode(kv[0], charset);
                String value = URLDecoder.decode(kv[1], charset);
                queryMap.put(key, value);
            } else if (kv.length == 1) {
                key = URLDecoder.decode(kv[0], charset);
                queryMap.put(key, "");
            }
        }

        return queryMap;
    }

    public static void main(String[] args) throws IOException {
        String s = "t=ebc-test&app_key=23558957&method=entryorder.confirm&v=3.0&format=xml&sign_method=md5&sign=887E86C146026DD41485FBC237963D76&timestamp=2023-04-24+15%3A15%3A03";
        Map<String, String> stringStringMap = parseUrl(s);
        String timestamp = stringStringMap.get("timestamp");
        stringStringMap.put("timestamp", URLDecoder.decode(timestamp, "UTF-8"));
//        String str1 = "{\"openApiSign\":\"UzB1WkZTeGh0VEh3NTZENTFrcFd5dThBcmRCX3BkVGcyOVYtNXNpMDMyND06MTUwMzc2MzAyMjM0OTMzODYyNA\",\"app_key\":\"23558957\",\"method\":\"entryorder.confirm\",\"v\":\"3.0\",\"format\":\"xml\",\"sign_method\":\"md5\",\"sign\":\"103522D8D5534812A04AF573DAD8FE21\",\"timestamp\":\"2023-04-21 09:09:24\"}";
//        String str = "{\"app_key\":\"23558957\",\"openApiSign\":\"UzB1WkZTeGh0VEh3NTZENTFrcFd5dThBcmRCX3BkVGcyOVYtNXNpMDMyND06MTUwMzc2MzAyMjM0OTMzODYyNA\",\"method\":\"entryorder.confirm\",\"v\":\"3.0\",\"format\":\"xml\",\"sign_method\":\"md5\",\"sign\":\"9E98D06963BD3E428E9692924BF4B576\",\"timestamp\":\"2023-04-24 11:16:21\"}";
//        Map map = JSONObject.parseObject(str, Map.class);
        String data = "<entryOrder><senderInfo><remark>备注</remark></senderInfo><receiverInfo><remark>备注</remark></receiverInfo><relatedOrders><remark>备注</remark></relatedOrders><orderCode>订单编码</orderCode><orderId>后端订单id</orderId><orderType>订单类型</orderType><warehouseName>仓库名称</warehouseName><totalOrderLines>12</totalOrderLines><entryOrderCode>E1234</entryOrderCode><ownerCode>O1234</ownerCode><purchaseOrderCode>C123455</purchaseOrderCode><warehouseCode>W1234</warehouseCode><entryOrderId>E1234</entryOrderId><entryOrderType>SCRK</entryOrderType><outBizCode>O1234</outBizCode><confirmType>0</confirmType><status>NEW</status><operateTime>2017-09-09 12:00:00</operateTime><remark>备注信息</remark><subOrderType>hss</subOrderType><responsibleDepartment>财务部</responsibleDepartment><shopNick>旗舰店</shopNick><shopCode>ssss</shopCode></entryOrder>";
        String localSign = TaobaoSignUtil.signTopRequest(stringStringMap, data, "1bcbf7b3f65550354116fd3a83dbbffa", "md5");
        System.out.println(localSign);


    }

    private static Map<String, String> parseUrl(String queryString) {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        String str = "t=ebc-test&app_key=23558957&method=entryorder.confirm&v=3.0&format=xml&sign_method=md5&sign=BD78ABFE26C436FADB456233F6F06AFC×tamp=2023-04-24+10%3A40%3A29";
        String[] r = queryString.split("&");
        for (String s : r) {
            String[] split = s.split("=");
            objectObjectHashMap.put(split[0], split[1]);
        }
        return objectObjectHashMap;
    }

    public void str() {
        String s = "1bcbf7b3f65550354116fd3a83dbbffaapp_key23558957formatxmlmethodentryorder.confirmsign_methodmd5timestamp2023-04-24 15:15:03v3.0<entryOrder><senderInfo><remark>备注</remark></senderInfo><receiverInfo><remark>备注</remark></receiverInfo><relatedOrders><remark>备注</remark></relatedOrders><orderCode>订单编码</orderCode><orderId>后端订单id</orderId><orderType>订单类型</orderType><warehouseName>仓库名称</warehouseName><totalOrderLines>12</totalOrderLines><entryOrderCode>E1234</entryOrderCode><ownerCode>O1234</ownerCode><purchaseOrderCode>C123455</purchaseOrderCode><warehouseCode>W1234</warehouseCode><entryOrderId>E1234</entryOrderId><entryOrderType>SCRK</entryOrderType><outBizCode>O1234</outBizCode><confirmType>0</confirmType><status>NEW</status><operateTime>2017-09-09 12:00:00</operateTime><remark>备注信息</remark><subOrderType>hss</subOrderType><responsibleDepartment>财务部</responsibleDepartment><shopNick>旗舰店</shopNick><shopCode>ssss</shopCode></entryOrder>1bcbf7b3f65550354116fd3a83dbbffa\n";
        String a = "1bcbf7b3f65550354116fd3a83dbbffaapp_key23558957formatxmlmethodentryorder.confirmsign_methodmd5tebc-testtimestamp2023-04-24 15:15:03v3.0<entryOrder><senderInfo><remark>备注</remark></senderInfo><receiverInfo><remark>备注</remark></receiverInfo><relatedOrders><remark>备注</remark></relatedOrders><orderCode>订单编码</orderCode><orderId>后端订单id</orderId><orderType>订单类型</orderType><warehouseName>仓库名称</warehouseName><totalOrderLines>12</totalOrderLines><entryOrderCode>E1234</entryOrderCode><ownerCode>O1234</ownerCode><purchaseOrderCode>C123455</purchaseOrderCode><warehouseCode>W1234</warehouseCode><entryOrderId>E1234</entryOrderId><entryOrderType>SCRK</entryOrderType><outBizCode>O1234</outBizCode><confirmType>0</confirmType><status>NEW</status><operateTime>2017-09-09 12:00:00</operateTime><remark>备注信息</remark><subOrderType>hss</subOrderType><responsibleDepartment>财务部</responsibleDepartment><shopNick>旗舰店</shopNick><shopCode>ssss</shopCode></entryOrder>1bcbf7b3f65550354116fd3a83dbbffa";
    }
}

