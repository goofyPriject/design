package com.example.design.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 吉客云美妆库存数据
 */
public class JkyMzInventoryImpl {

    private final static String GATEWAY = "https://open.jackyun.com/open/openapi/do";

    public static void syncInventory() {
        String key = "54562476";
        String secret = "7a17078597fa47ba9613b85edc457ec4";
        String wareCode = "150,149,142,141,140,137,135,134,132,133,130,129,128,127,126,124,123,122,202211230004";
        String[] split = wareCode.split(",");
        JSONArray jsonArray = new JSONArray();
        AtomicInteger page = new AtomicInteger(1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageIndex", ""+page);
        jsonObject.put("pageSize", "100");
        try {
            String post = post("erp.stockquantity.get", "v1.0", jsonObject.toString(), key, secret);
            JSONObject object = JSONObject.parseObject(post);
            String code = object.getString("code");
            if ("200".equals(code)) {
                String result = object.getString("result");
                JSONObject object1 = JSONObject.parseObject(result);
                String data = object1.getString("data");
                JSONObject object2 = JSONObject.parseObject(data);
                JSONArray goodsStockQuantity = object2.getJSONArray("goodsStockQuantity");
                jsonArray.addAll(goodsStockQuantity);
                while (!goodsStockQuantity.isEmpty()) {
                    jsonObject.put("pageIndex", page.getAndIncrement());
                    jsonObject.put("pageSize", "100");
                     post = post("erp.stockquantity.get", "v1.0", jsonObject.toString(), key, secret);
                     object = JSONObject.parseObject(post);
                     code = object.getString("code");
                    if ("200".equals(code)) {
                         result = object.getString("result");
                         object1 = JSONObject.parseObject(result);
                         data = object1.getString("data");
                         object2 = JSONObject.parseObject(data);
                         goodsStockQuantity = object2.getJSONArray("goodsStockQuantity");
                         if (!goodsStockQuantity.isEmpty()) {
                             jsonArray.addAll(goodsStockQuantity);
                         }
                    }
                }
            }
            jsonArray.removeIf(row -> {
                JSONObject json = (JSONObject) row;
                return Arrays.stream(split).anyMatch(s -> s.contains(json.getString("warehouseCode")));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonArray);
    }


    public static String post(String method, String version, String jsonObject, String appkey, String appsecret) throws Exception {
        DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SortedMap<String, String> sortedMap = new TreeMap<>();
        sortedMap.put("method", method);
        sortedMap.put("appkey", appkey);
        sortedMap.put("version", version);
        sortedMap.put("contenttype", "json");
        sortedMap.put("timestamp", DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now()));
        sortedMap.put("bizcontent", jsonObject);

        // 构建待签名的字符串。
        StringBuilder sbSignData = new StringBuilder(appsecret);
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sbSignData.append(entry.getKey()).append(entry.getValue());
        }
        sbSignData.append(appsecret);
        // 生成签名。
        sortedMap.put("sign", md5Encrypt(sbSignData.toString().toLowerCase(), "UTF-8"));

        // 构造请求参数(name1=value1&name2=value2格式)
        StringBuilder sbPostData = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sbPostData.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        String postDataStr = sbPostData.substring(1);
        return postData(GATEWAY, postDataStr);
    }


    /**
     * MD5哈希加密。
     *
     * @param text     原文本
     * @param encoding 编码格式
     * @return 加密后的文本
     */
    static String md5Encrypt(String text, String encoding) throws Exception {
        if (encoding == null || encoding.isEmpty()) {
            encoding = "UTF-8";
        }
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] resultByte = text.getBytes(encoding);
        byte[] md5Bytes = md5.digest(resultByte);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = (md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * Post方式请求
     *
     * @param url      路径
     * @param postData 发送的数据(name1=value1&name2=value2)格式
     * @return HTTP-Post返回结果
     */
    public static String postData(String url, String postData) throws IOException {
        return postData(url, postData, "UTF-8", 300000);
    }

    /**
     * Post方式请求
     *
     * @param url      路径
     * @param postData 发送的数据(name1=value1&name2=value2)格式
     * @param encoding 编码格式
     * @param timeOut  超时时间
     * @return HTTP-Post返回结果
     */
    public static String postData(String url, String postData, String encoding, int timeOut) throws IOException {
        if (encoding == null || encoding.isEmpty()) {
            encoding = "UTF-8";
        }
        if (timeOut <= 0) {
            timeOut = 300000;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        StringBuffer resultBuffer = null;
        URLConnection urlConnection = null;

        // 发送Post请求
        try {
            URL postMethodUrl = new URL(url);
            // 打开连接
            urlConnection = postMethodUrl.openConnection();
            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");

            urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setConnectTimeout(timeOut);
            // 设置url连接可以用于输入输出
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            printWriter = new PrintWriter(urlConnection.getOutputStream());
            // 发送请求参数
            printWriter.print(postData);
            // flush输出流的缓冲
            printWriter.flush();
            // 定义BufferedReader输入流来读取URL的响应
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            resultBuffer = new StringBuffer();
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return resultBuffer.toString();
    }
}
