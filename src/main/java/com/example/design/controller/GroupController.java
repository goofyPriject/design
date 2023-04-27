package com.example.design.controller;

import com.example.design.domain.ErrorTemplate;
import com.example.design.domain.UserInfo;
import com.example.design.domain.UserRequest;
import com.example.design.door.annotation.DoDoor;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
public class GroupController {

    @DoDoor(key = "UserRequest")
    @PostMapping(value = "/v1/selectUserInfo")
    public ErrorTemplate selectUserInfo(@RequestBody UserRequest userRequest) {
        return ErrorTemplate.success(new UserInfo("nihao"+userRequest.getUserId(),1,"杭州市"));
    }

    @GetMapping(value = "/v1/openUrl")
    public String openUrl(HttpServletResponse resp) throws IOException {
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();//注意getResource("")里面是空字符串
        String str = path+"com/example/design/util/东疆备案表.xlsx";
        File file = new File(str);
        FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println(path);
        return "redirect:http://www.baidu.com/";
    }


    @GetMapping(value = "/v1/invoke")
    public void invoke(HttpServletResponse resp) throws Exception{
//        String data = "body="+"data"+"headers="+"header";
//        URL urlObj = new URL("http://ebc-test.zjmiec.cn/ierp/kapi/v2/wcys/im/confirm?openApiSign=UzB1WkZTeGh0VEh3NTZENTFrcFd5dThBcmRCX3BkVGcyOVYtNXNpMDMyND06MTUwMzc2MzAyMjM0OTMzODYyNA==&sign=123");
//        HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
//        conn.setDoOutput(true);
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
//        writer.write(data);
//        writer.flush();
//        int responseCode = conn.getResponseCode();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line;
//        StringBuilder response = new StringBuilder();
//        while ((line = reader.readLine()) != null) {
//            response.append(line);
//        }
//        reader.close();
//        System.out.println("Response Code : " + responseCode);
//        System.out.println("Response Data : " + response);

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
        CloseableHttpResponse response = httpClient.execute(httpPost);
//        int statusCode = response.getStatusLine().getStatusCode();
//        int scOk = HttpStatus.SC_OK;
        HttpEntity entity = response.getEntity();
        String content = EntityUtils.toString(entity,"utf8");
        System.out.println(content);
    }
}
