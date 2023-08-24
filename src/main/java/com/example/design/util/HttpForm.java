package com.example.design.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpResponse;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HttpForm {


    public static void httpPost() throws Exception {
        String data = "body="+"data"+"headers="+"header";
        URL urlObj = new URL("http://ebc-test.zjmiec.cn/ierp/kapi/v2/wcys/im/confirm?openApiSign=UzB1WkZTeGh0VEh3NTZENTFrcFd5dThBcmRCX3BkVGcyOVYtNXNpMDMyND06MTUwMzc2MzAyMjM0OTMzODYyNA==&sign=123");
        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(data);
        writer.flush();
        int responseCode = conn.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        System.out.println("Response Code : " + responseCode);
        System.out.println("Response Data : " + response);

        //创建HttpClient对象
        //1.打开浏览器，创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址，发起请求创建HttpPost对象，设置url访问地址
        HttpPost httpPost = new HttpPost("http://ebc-test.zjmiec.cn/ierp/kapi/v2/wcys/im/confirm?openApiSign=UzB1WkZTeGh0VEh3NTZENTFrcFd5dThBcmRCX3BkVGcyOVYtNXNpMDMyND06MTUwMzc2MzAyMjM0OTMzODYyNA==");
        //声明List集合，封装表单中的参数
        List<NameValuePair> params = new ArrayList<>();
        //设置请求地址是://
        params.add(new BasicNameValuePair("body","1.0"));
        //创建表单的Entity对象，第一个参数就是封装好的表单数据，第二个参数就是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8");
        //设置表单的Entity对象到Post请求中
        httpPost.setEntity(formEntity);
        CloseableHttpResponse responseA = httpClient.execute(httpPost);
//        int statusCode = response.getStatusLine().getStatusCode();
//        int scOk = HttpStatus.SC_OK;
        HttpEntity entity = responseA.getEntity();
        String content = EntityUtils.toString(entity,"utf8");
        System.out.println(content);
    }

    public static void main(String[] args) {
        JSONObject order = new JSONObject(new LinkedHashMap<>());
        order.put("appkey", "appKey");
        order.put("sessionkey", "sessionKey");
        order.put("method", "gy.erp.trade.add");
        String s = JSONObject.toJSONString(order);
        System.out.println(s);
    }
}
