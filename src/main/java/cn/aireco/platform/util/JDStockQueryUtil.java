package cn.aireco.platform.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDStockQueryUtil {

    private static final Logger logger = LoggerFactory.getLogger(JDStockQueryUtil.class);

    private static final String HEX_CHARACTERS = "0123456789ABCDEF";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Map<String, BigDecimal> doQuery() throws GeneralSecurityException, IOException {
        String requestConfigs = "{\"baseUrl\":\"https://api.jdl.com\", \"path\": \"/stock/queryStock\", \"appKey\": \"d579583a07a247e1b9dbcc478c25ba80\", \"appSecret\":\"889f01426ff34fd28689e9bbd56e7a11\", \"accessToken\":\"55dee03739cb4ad598b22f6345874ef8\", \"deptNo\":\"EBU4418055065265\",\"warehouseNo\":\"110017030\", \"LOP-DN\": \"IOWD\"}";
        JSONObject config = JSONObject.parseObject(requestConfigs);
        String appKey = config.getString("appKey");
        String appSecret = config.getString("appSecret");
        String accessToken = config.getString("accessToken");
        String baseUrl = config.getString("baseUrl");
        String path = config.getString("path");

        String body = getParamStr(config);

        String timestamp = DATE_TIME_FORMATTER.format(LocalDateTime.now());
        String content = String.join("", new String[]{appSecret, "access_token", accessToken, "app_key", appKey,
                "method", path, "param_json", body, "timestamp", timestamp, "v", "2.0", appSecret});

        String sign = sign(content.getBytes(StandardCharsets.UTF_8));

        Map<String, String> query = new HashMap<>();
        query.put("LOP-DN", config.getString("LOP-DN"));
        query.put("app_key", appKey);
        query.put("access_token", accessToken);
        query.put("timestamp", timestamp);
        query.put("v", "2.0");
        query.put("sign", sign);

        String result = doPost(baseUrl + path, query, body);
        return parseData(result);
    }

    private static Map<String, BigDecimal> parseData(String result) {
        try {
            Map<String, BigDecimal> map = new HashMap<>();
            JSONArray data = JSONArray.parseArray(result);
            for (int i = 0; i < data.size(); i++) {
                JSONObject item = data.getJSONObject(i);
                String skuCode = item.getString("isvSku");
                BigDecimal qty = item.getBigDecimal("totalNum");
                if (!map.containsKey(skuCode)) {
                    map.put(skuCode, BigDecimal.ZERO);
                }
                map.put(skuCode, map.get(skuCode).add(qty));
            }
            return map;
        } catch (Exception e) {
            JSONObject data = JSONObject.parseObject(result);
           return new HashMap<>();
        }
    }

    private static String doPost(String fullUrl, Map<String, String> query, String body) throws IOException {
        int offset = OffsetTime.now().getOffset().getTotalSeconds() / 3600;
        URL url = new URL(fullUrl + "?" + httpBuildQuery(query));

        Map<String, String> headers = new HashMap<>();
        headers.put("lop-tz", String.valueOf(offset));
        headers.put("User-Agent", "lop-http/java");

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(body.getBytes(StandardCharsets.UTF_8));
            }

            try (InputStream inputStream = connection.getInputStream()) {
                return new String(readAllBytes(inputStream), StandardCharsets.UTF_8);
            } catch (IOException e) {
                try (InputStream errorStream = connection.getErrorStream()) {
                    logger.error(new String(readAllBytes(errorStream), StandardCharsets.UTF_8));
                }
                return null;
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String sign(byte[] data) throws GeneralSecurityException {
        return bytesToHex(MessageDigest.getInstance("md5").digest(data));
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            stringBuilder.append(HEX_CHARACTERS.charAt((b >>> 4) & 0x0F));
            stringBuilder.append(HEX_CHARACTERS.charAt(b & 0x0F));
        }
        return stringBuilder.toString();
    }

    public static String httpBuildQuery(Map<String, String> query) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : query.entrySet()) {
            if (!first) {
                stringBuilder.append("&");
            } else {
                first = false;
            }
            stringBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
        }
        return stringBuilder.toString();
    }

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, n);
        }
        return outputStream.toByteArray();
    }

    private static String getParamStr(JSONObject config) {
        Map<String, String> params = new HashMap<>(2);
        params.put("deptNo", config.getString("deptNo"));
        params.put("warehouseNo", config.getString("warehouseNo"));
        List<Object> list = new ArrayList<>(2);
        list.add(params);
        list.add("pin");
        return  JSONObject.toJSONString(list);
    }
}
