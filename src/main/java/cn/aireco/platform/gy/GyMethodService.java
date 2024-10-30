package cn.aireco.platform.gy;

import cn.aireco.platform.adapt.Demo1;
import cn.aireco.platform.util.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.aireco.platform.util.GyySign;
import com.qimencloud.api.DefaultQimenCloudClient;
import com.qimencloud.api.scenetp8z6548i2.request.*;
import com.qimencloud.api.scenetp8z6548i2.response.*;
import com.taobao.api.ApiException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class GyMethodService {
    /**
     * 奇门调用网关
     */
    public static final String URL = "http://tp8z6548i2.api.taobao.com/router/qm";

    public static final String TARGET_APPKEY = "21226717";//管易的奇门appkey
    public static final String APP_SECRET = "1bcbf7b3f65550354116fd3a83dbbffa";//商家的奇门appSecret
    public static final String FORMAT = "JSON";
    public static final String APPKEY = "33979230";//商家的奇门appkey

    public static final String SESSION_KEY = "f2ebb7880eaf4654961747d72c27bb09";//管易的sessionkey
    public static final String APPKEY_GY = "127712";//管易的appkey

//    // 旧
//    public static final String SESSION_KEY = "d332407e15754865aefd0b891e752741";//管易的sessionkey
//    public static final String APPKEY_GY = "165838";//管易的appkey



    public static JSONArray queryDeliverysHistory(String pageNo, String pageSize, Integer days) {
        JSONArray jsonArray = new JSONArray();
        //调用接口查询数据
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysHistoryGetRequest gyErpTradeDeliverysHistoryGetRequest = new GyErpTradeDeliverysHistoryGetRequest();
        gyErpTradeDeliverysHistoryGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysHistoryGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysHistoryGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDeliverysHistoryGetRequest.setStartDeliveryDate("2023-09-02 00:00:00");
        gyErpTradeDeliverysHistoryGetRequest.setEndDeliveryDate("2023-09-02 23:53:45");
        gyErpTradeDeliverysHistoryGetRequest.setPageNo("1");
        gyErpTradeDeliverysHistoryGetRequest.setPageSize("20");
        try {
            GyErpTradeDeliverysHistoryGetResponse response1 = client.execute(gyErpTradeDeliverysHistoryGetRequest);
            String body =  JSONObject.parseObject(JSONObject.toJSONString(response1))
                    .getString("body");
            JSONObject jsonObject = JSONObject.parseObject(body);
            JSONObject response = jsonObject.getJSONObject("response");
            return response.getJSONArray("deliverys");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONArray queryDeliverys() {
        JSONArray jsonArray = new JSONArray();
        //调用接口查询数据
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysGetRequest gyErpTradeDeliverysGetRequest = new GyErpTradeDeliverysGetRequest();
        gyErpTradeDeliverysGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDeliverysGetRequest.setStartDeliveryDate("2023-09-02 00:00:00");
        gyErpTradeDeliverysGetRequest.setEndDeliveryDate("2023-09-02 23:53:45");
        gyErpTradeDeliverysGetRequest.setPageNo("1");
        gyErpTradeDeliverysGetRequest.setPageSize("20");
        gyErpTradeDeliverysGetRequest.setDelivery("1");
        try {
            GyErpTradeDeliverysGetResponse response1 = client.execute(gyErpTradeDeliverysGetRequest);
            String body =  JSONObject.parseObject(JSONObject.toJSONString(response1))
                    .getString("body");
            JSONObject jsonObject = JSONObject.parseObject(body);
            JSONObject response = jsonObject.getJSONObject("response");
            return response.getJSONArray("deliverys");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONArray gyErpTradeHistoryGet(String tradeCode) {
        JSONArray orders = new JSONArray();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeHistoryGetRequest gyErpTradeHistoryGetRequest = new GyErpTradeHistoryGetRequest();
        gyErpTradeHistoryGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeHistoryGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeHistoryGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
//        gyErpTradeHistoryGetRequest.setPlatformCode(tradeCode);
        gyErpTradeHistoryGetRequest.setCode(tradeCode);
        try {
            GyErpTradeHistoryGetResponse response = client.execute(gyErpTradeHistoryGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(body)
                    .getString("response")).getJSONArray("orders");
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static JSONArray gyErpTradeGet(String tradeCode) {
        JSONArray orders = new JSONArray();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeGetRequest gyErpTradeGetRequest = new GyErpTradeGetRequest();
        gyErpTradeGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeGetRequest.setCode(tradeCode);
//        gyErpTradeGetRequest.setPlatformCode(tradeCode);
//        gyErpTradeGetRequest.setStartDate("2024-02-28 00:00:00");
//        gyErpTradeGetRequest.setEndDate("2024-02-29 00:00:00");
        try {
            GyErpTradeGetResponse response = client.execute(gyErpTradeGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(body)
                    .getString("response")).getJSONArray("orders");
        } catch (ApiException e) {
            e.getMessage();
        }
        List<JSONObject> codes = orders.stream()
                .map(row -> gyErpTradeDetailGet(((JSONObject) row).getString("code")))
                .collect(Collectors.toList());
        orders = JSON.parseArray(JSON.toJSONString(codes));
        return orders;
    }


    public static JSONObject gyErpTradeDetailGet(String tradeCode) {
        JSONObject orders = new JSONObject();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDetailGetRequest gyErpTradeDetailGetRequest = new GyErpTradeDetailGetRequest();
        gyErpTradeDetailGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDetailGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDetailGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDetailGetRequest.setCode(tradeCode);
//        gyErpTradeGetRequest.setStartDate("2024-02-28 00:00:00");
//        gyErpTradeGetRequest.setEndDate("2024-02-29 00:00:00");
        try {
            GyErpTradeDetailGetResponse response = client.execute(gyErpTradeDetailGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(body)
                    .getString("response")).getJSONObject("orderDetail");
        } catch (ApiException e) {
            e.getMessage();
        }
        return orders;
    }

    public static JSONArray gyErpTradeDeliveryHistoryGet(String code) {
        JSONArray orders = new JSONArray();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysHistoryGetRequest gyErpTradeDeliverysHistoryGetRequest = new GyErpTradeDeliverysHistoryGetRequest();
        GyErpTradeDeliverysDetailHistoryGetRequest gyErpTradeDeliverysDetailHistoryGetRequest = new GyErpTradeDeliverysDetailHistoryGetRequest();

        gyErpTradeDeliverysHistoryGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysHistoryGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysHistoryGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDeliverysHistoryGetRequest.setStartDeliveryDate("2024-04- 00:00:00");
        gyErpTradeDeliverysHistoryGetRequest.setEndDeliveryDate("2024-02-27 23:59:00");
        gyErpTradeDeliverysHistoryGetRequest.setPageNo("1");
        gyErpTradeDeliverysHistoryGetRequest.setPageSize("10");
        try {
            GyErpTradeDeliverysHistoryGetResponse responseHistory = client.execute(gyErpTradeDeliverysHistoryGetRequest);
            String bodyHistory = JSONObject.parseObject(JSONObject.toJSONString(responseHistory))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(bodyHistory)
                    .getString("response")).getJSONArray("deliverys");
        } catch (Exception e) {
            e.getMessage();
        }
        return orders;
    }

    public static JSONArray GyErpTradeDeliverysDetailHistoryGetRequest(String code) {
        JSONArray orders = new JSONArray();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysDetailHistoryGetRequest gyErpTradeDeliverysDetailHistoryGetRequest = new GyErpTradeDeliverysDetailHistoryGetRequest();
        gyErpTradeDeliverysDetailHistoryGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysDetailHistoryGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysDetailHistoryGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDeliverysDetailHistoryGetRequest.setCode("2024-02-27 00:00:00");

        try {
            GyErpTradeDeliverysDetailHistoryGetResponse responseHistory = client.execute(gyErpTradeDeliverysDetailHistoryGetRequest);
            String bodyHistory = JSONObject.parseObject(JSONObject.toJSONString(responseHistory))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(bodyHistory)
                    .getString("response")).getJSONArray("deliverys");
        } catch (Exception e) {
            e.getMessage();
        }
        return orders;
    }

    public static JSONArray gyErpTradeDeliveryGet(String code) {
        JSONArray orders = new JSONArray();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysGetRequest gyErpTradeDeliverysGetRequest = new GyErpTradeDeliverysGetRequest();
        gyErpTradeDeliverysGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDeliverysGetRequest.setPageNo("1");
        gyErpTradeDeliverysGetRequest.setPageSize("100");
        gyErpTradeDeliverysGetRequest.setDelivery("1");
        gyErpTradeDeliverysGetRequest.setCode(code);
        gyErpTradeDeliverysGetRequest.setStartDeliveryDate("2024-06-18 00:00:00");
        gyErpTradeDeliverysGetRequest.setEndDeliveryDate("2024-06-18 23:59:59");
        try {
            GyErpTradeDeliverysGetResponse response = client.execute(gyErpTradeDeliverysGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(body)
                    .getString("response")).getJSONArray("deliverys");
        } catch (ApiException e) {
            e.getMessage();
        }
        List<JSONObject> codes = new ArrayList<>();
                orders.stream()
                .forEach(row -> codes.add(gyErpTradeDeliveryDetailGet(((JSONObject) row).getString("code"))));
        orders = JSON.parseArray(JSON.toJSONString(codes));
        return orders;
    }

    public static JSONObject gyErpTradeDeliveryDetailGet(String code) {
        JSONObject orders = new JSONObject();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysDetailGetRequest gyErpTradeDeliverysDetailGetRequest = new GyErpTradeDeliverysDetailGetRequest();
        gyErpTradeDeliverysDetailGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysDetailGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysDetailGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
//        gyErpTradeDeliverysGetRequest.setPageNo("1");
//        gyErpTradeDeliverysGetRequest.setPageSize("20");
        gyErpTradeDeliverysDetailGetRequest.setCode(code);
//        gyErpTradeDeliverysGetRequest.setStartDeliveryDate("2023-10-19 00:00:00");
//        gyErpTradeDeliverysGetRequest.setEndDeliveryDate("2023-10-19 23:59:59");
        try {
            GyErpTradeDeliverysDetailGetResponse response = client.execute(gyErpTradeDeliverysDetailGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(body)
                    .getString("response")).getJSONObject("delivery");
        } catch (ApiException e) {
            e.getMessage();
        }
        return orders;
    }

    public static String gyErpTradeReturnGet(String code) {
        String orders = "";
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeReturnGetRequest gyErpTradeReturnGetRequest = new GyErpTradeReturnGetRequest();
        gyErpTradeReturnGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeReturnGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeReturnGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        try {
            GyErpTradeReturnGetResponse response = client.execute(gyErpTradeReturnGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(body)
                    .getString("response");
        } catch (ApiException e) {
            e.getMessage();
        }
        return orders;
    }

    public static String gyErpTradeReturnDetailGet(String code) {
        String orders = "";
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeReturnDetailGetRequest gyErpTradeReturnDetailGetRequest = new GyErpTradeReturnDetailGetRequest();
        gyErpTradeReturnDetailGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeReturnDetailGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeReturnDetailGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeReturnDetailGetRequest.setCode("SDO707184098630");
        try {
            GyErpTradeReturnDetailGetResponse response = client.execute(gyErpTradeReturnDetailGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(body)
                    .getString("response");
        } catch (ApiException e) {
            e.getMessage();
        }
        return orders;
    }

    public static JSONObject  post(int i) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(formatter);

        JSONObject stock = new JSONObject(new LinkedHashMap<>());
        stock.put("appkey", "127712");
        stock.put("sessionkey", "f2ebb7880eaf4654961747d72c27bb09");
        stock.put("method", "gy.erp.new.stock.get");
        stock.put("page_no", i);
        stock.put("page_size", 100);
        stock.put("cancel", 0);
        stock.put("start_date", "2024-07-15 00:00:00");
        stock.put("end_date", format);
//        stock.put("item_code","6942103126536");
        String sign = GyySign.generateSign(stock, "41691fe3dea341d5ae9457d3e580f786");
//        stock.put("appkey", "165838"); PAO738318149056
//        stock.put("sessionkey", "d332407e15754865aefd0b891e752741");
//        stock.put("method", "gy.erp.trade.history.detail.get");
//        stock.put("code", "SO689863673069");
//        stock.put("page_no", "1");
//        stock.put("page_size", "10");
//        stock.put("start_date", "2024-01-03 00:00:00");
//        stock.put("end_date", "2024-01-03 23:00:00");
//        String sign = GyySign.generateSign(stock, "7b756458336540d2afa78e5bad493959");
        stock.put("sign", sign);
        Map<String, String> maps = new HashMap<>();
        String encodeData = null;
        try {
            encodeData = URLEncoder.encode(JSONObject.toJSONString(stock), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.getMessage();
        }
        maps.put("Content-Type", "text/plain");
        String post = HttpUtils.post("http://v2.api.guanyierp.com/rest/erp_open", maps, encodeData);
        JSONObject returnResult = JSONObject.parseObject(post);
       return returnResult;
    }

    public static void main(String[] args) {
//        System.out.println(gyErpTradeDeliveryGet("SDO745308996661"));
//        System.out.println(gyErpTradeHistoryGet("SDO738172103005"));
//        System.out.println(gyErpTradeGet("SDO746059081057"));
//        for (int i = 0; i < 23; i++) {
//            System.out.println(i);
//            System.out.println(post(i));
//            System.out.println(post(i).toJSONString().contains("6942103126536"));
//        }
        // 获取当前日期
        LocalDate today = LocalDate.now();

        // 获取当前日期的最小时间
        LocalDateTime startOfDay = today.atStartOfDay();

        // 获取当前日期的最大时间
        LocalDateTime endOfDay = LocalDateTime.of(today, LocalTime.MAX);

        // 转换为 ZonedDateTime
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime startZoned = startOfDay.atZone(zoneId);
        ZonedDateTime endZoned = endOfDay.atZone(zoneId);

        // 转换为 java.util.Date
        Date startDate = Date.from(startZoned.toInstant());
        Date endDate = Date.from(endZoned.toInstant());

        // 输出结果
        System.out.println("Start of the day as Date: " + startDate);
        System.out.println("End of the day as Date: " + endDate);

    }

    public static boolean idNowDate() {

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        return 1== calendar.get(Calendar.DAY_OF_MONTH);
    }


}
