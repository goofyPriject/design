package com.example.design.util;

import com.taobao.api.internal.util.TaobaoUtils;
import org.apache.commons.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @program: node-debug-mservice
 * @description: 顺丰仓接口
 * @author: ma jun chao
 * @create: 2022-10-16 15:00
 */
public class SfRequestUtils {
    private static final String URL = "https://portal-gateway.sf-express.com/scc-portal-api-service/omsPortalService/sendRequest";

    private static final String KEY = "29d4323922ba4885d4bbf694f73ddb0e";

//    public String getUrl() {
//        return url;
//    }

//    @Value("${sf.service.url}")
//    public void setUrl(String url) {
//        SfRequestUtils.url = url;
//    }

    public static String sendPost(String xml, String url, String key) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            String sign = sign(xml, key);
            String params = "logistics_interface=" + URLEncoder.encode(xml, "utf-8") + "&data_digest=" + URLEncoder.encode(sign, "utf-8");
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    public static String sign(String content, String key) throws IOException {
        content = content + key;
        return Base64.encodeBase64String(TaobaoUtils.encryptMD5(content));
    }
}
