package cn.aireco.platform.sdk.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SvcTest {

    public static void main(String[] args) {

//        List<String[]> time = time("2024-10-08 00:00:00", "2024-10-08 00:00:00");
//        for (String[] strings : time) {
//            String startTime = strings[0];
//            String endTime = strings[1];
            try {
                Map<String, String> params = new HashMap<>();
                Map<String, String> reqMap = new HashMap<>();
                reqMap.put("startCreated", "2024-10-28 00:00:00");
                reqMap.put("endCreated", "2024-10-29 00:00:00");
                reqMap.put("searchType", "1");
                reqMap.put("pageNo", "1");
                reqMap.put("pageSize", "1");
//                reqMap.put("tradeNo", "4072371876668078030");
                params.put("message", JSONObject.toJSONString(reqMap));
                params.put("appKey", "560334490168");
                params.put("v", "1.0");


//            String str = httpPost("http://127.0.0.1:9008/partChangeInvoice/"+"queryPartChangeInvoice", params);
                String str = httpPost("http://eop.hbdm.ehaier.com/partChangeInvoice/" + "queryPartChangeInvoice", params);

                System.out.println(str);
            } catch (Exception e) {
                e.printStackTrace();
            }





    }

    public static List<String[]> time(String startStr, String endStr) {
        // 定义时间范围的开始和结束
//        String startStr = "2024-10-09 00:00:00";
//        String endStr = "2024-10-12 00:00:00";

        // 使用 DateTimeFormatter 来格式化日期字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 将字符串转换为 LocalDateTime 对象
        LocalDateTime startDateTime = LocalDateTime.parse(startStr, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endStr, formatter);

        // 计算开始时间和结束时间之间的天数差
        long daysBetween = ChronoUnit.DAYS.between(startDateTime.toLocalDate(), endDateTime.toLocalDate()) + 1;

        // 创建时间段列表
        List<String[]> timeRanges = new ArrayList<>();

        // 循环每一天，创建时间段
        for (long i = 0; i < daysBetween; i++) {
            // 当天的开始时间
            LocalDateTime startOfDay = startDateTime.plusDays(i).withHour(0).withMinute(0).withSecond(0).withNano(0);
            // 下一天的开始时间（即当前天的结束时间）
            LocalDateTime endOfDay = startOfDay.plusDays(1);

            // 格式化日期字符串并添加到列表
//            timeRanges.add(startOfDay.format(formatter) + "到" + endOfDay.minusSeconds(1).format(formatter));
            timeRanges.add(new String[]{startOfDay.format(formatter), endOfDay.minusSeconds(1).format(formatter)});
        }

        return timeRanges;

//        // 打印时间段
//        for (String[] range : timeRanges) {
//            System.out.println(range[0]+"到"+range[1]);
//        }
    }

    public static String httpPost(String url, Map<String, String> params) throws IOException {
        //整理参数的字符编码
        List<NameValuePair> nameValues = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
            String value = params.get(key);
            nameValues.add(new BasicNameValuePair(key, value));
        }
        Long dt = System.currentTimeMillis() / 1000;
        params.put("timeStamp", dt + "");

        String value = ScmHmacer.signRequest("nkmoUbKmDnT8F3AgusU6N9ilt8CD-Eqo2_9UMim3F9F5hPlxLhBYmvUMQX4pJQfyn8nYP2SUUH4lGgcVmn5Tkw", params);
        nameValues.add(new BasicNameValuePair("timeStamp", "" + dt));
        nameValues.add(new BasicNameValuePair("sign", value));

        HttpPost httpRequest = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(90000).setConnectTimeout(90000).build();// 设置请求和传输超时时间
        httpRequest.setConfig(requestConfig);
        HttpEntity httpEntity = new UrlEncodedFormEntity(nameValues, "UTF-8");
        httpRequest.setEntity(httpEntity);

        return request(httpRequest);

    }

    private static String request(HttpUriRequest request) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        String result = EntityUtils.toString(response.getEntity());
        response.close();
        httpClient.close();
        return result;
    }
}

