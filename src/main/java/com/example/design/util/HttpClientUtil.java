package com.example.design.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpClientUtil {
    public static String sendPostRequest(String url, Map<String, String> headers, String json) {
        try {
            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setDoOutput(true);
            // 设置请求方法和请求头
            connection.setRequestMethod("POST");

            // 设置请求头和参数
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // 发送请求
            connection.connect();
            connection.getOutputStream().write(json.getBytes("UTF-8"));

            // 获取响应
            int statusCode = connection.getResponseCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send POST request : " + e.getMessage(), e);
        }
    }
}
