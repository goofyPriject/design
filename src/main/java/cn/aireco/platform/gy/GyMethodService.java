package cn.aireco.platform.gy;

import cn.aireco.platform.util.HttpUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import cn.aireco.platform.util.GyySign;
import com.qimencloud.api.DefaultQimenCloudClient;
import com.qimencloud.api.scenetp8z6548i2.request.GyErpTradeDeliverysGetRequest;
import com.qimencloud.api.scenetp8z6548i2.request.GyErpTradeDeliverysHistoryGetRequest;
import com.qimencloud.api.scenetp8z6548i2.request.GyErpTradeGetRequest;
import com.qimencloud.api.scenetp8z6548i2.request.GyErpTradeHistoryGetRequest;
import com.qimencloud.api.scenetp8z6548i2.response.GyErpTradeDeliverysGetResponse;
import com.qimencloud.api.scenetp8z6548i2.response.GyErpTradeDeliverysHistoryGetResponse;
import com.qimencloud.api.scenetp8z6548i2.response.GyErpTradeGetResponse;
import com.qimencloud.api.scenetp8z6548i2.response.GyErpTradeHistoryGetResponse;
import com.taobao.api.ApiException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GyMethodService {
    /**
     * 奇门调用网关
     */
    public static final String URL = "http://tp8z6548i2.api.taobao.com/router/qm";

    public static final String APPKEY = "33979230";//商家的奇门appkey
    public static final String TARGET_APPKEY = "21226717";//管易的奇门appkey
    public static final String APP_SECRET = "1bcbf7b3f65550354116fd3a83dbbffa";//商家的奇门appSecret
    public static final String FORMAT = "JSON";
    public static final String APPKEY_GY = "165838";//管易的appkey
    public static final String SESSION_KEY = "d332407e15754865aefd0b891e752741";//管易的sessionkey

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
        gyErpTradeHistoryGetRequest.setPlatformCode(tradeCode);
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
        gyErpTradeGetRequest.setPageNo("1");
        gyErpTradeGetRequest.setPageSize("10");
        gyErpTradeGetRequest.setStartDate("2023-10-19 00:00:00");
        gyErpTradeGetRequest.setEndDate("2023-10-19 23:59:59");
        try {
            GyErpTradeGetResponse response = client.execute(gyErpTradeGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(body)
                    .getString("response")).getJSONArray("orders");
        } catch (ApiException e) {
            e.getMessage();
        }
        return orders;
    }

    public static JSONArray gyErpTradeDeliveryHistoryGet(String code) {
        JSONArray orders = new JSONArray();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysHistoryGetRequest gyErpTradeDeliverysHistoryGetRequest = new GyErpTradeDeliverysHistoryGetRequest();
        gyErpTradeDeliverysHistoryGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysHistoryGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysHistoryGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDeliverysHistoryGetRequest.setPageNo("1");
        gyErpTradeDeliverysHistoryGetRequest.setPageSize("10");
        gyErpTradeDeliverysHistoryGetRequest.setStartDeliveryDate("2023-10-19 00:00:00");
        gyErpTradeDeliverysHistoryGetRequest.setEndDeliveryDate("2023-10-19 23:59:59");
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

    public static JSONArray gyErpTradeDeliveryGet(String code) {
        JSONArray orders = new JSONArray();
        DefaultQimenCloudClient client = new DefaultQimenCloudClient(URL, APPKEY, APP_SECRET, FORMAT);
        GyErpTradeDeliverysGetRequest gyErpTradeDeliverysGetRequest = new GyErpTradeDeliverysGetRequest();
        gyErpTradeDeliverysGetRequest.setTargetAppKey(TARGET_APPKEY);//管易的奇门appkey
        gyErpTradeDeliverysGetRequest.setAppkey(APPKEY_GY);//管易的appkey
        gyErpTradeDeliverysGetRequest.setSessionkey(SESSION_KEY);//管易的sessionkey
        gyErpTradeDeliverysGetRequest.setPageNo("110");
        gyErpTradeDeliverysGetRequest.setPageSize("20");
        gyErpTradeDeliverysGetRequest.setStartDeliveryDate("2023-10-19 00:00:00");
        gyErpTradeDeliverysGetRequest.setEndDeliveryDate("2023-10-19 23:59:59");
        try {
            GyErpTradeDeliverysGetResponse response = client.execute(gyErpTradeDeliverysGetRequest);
            String body = JSONObject.parseObject(JSONObject.toJSONString(response))
                    .getString("body");
            orders = JSONObject.parseObject(JSONObject.parseObject(body)
                    .getString("response")).getJSONArray("deliverys");
        } catch (ApiException e) {
            e.getMessage();
        }
        return orders;
    }

    public static JSONObject  post() {
        JSONObject stock = new JSONObject(new LinkedHashMap<>());
        stock.put("appkey", "165838");
        stock.put("sessionkey", "d332407e15754865aefd0b891e752741");
        stock.put("method", "gy.erp.trade.return.get");
        stock.put("page_no", 1);
        stock.put("page_size", 100);
        stock.put("in_begin_time", "2023-10-24 00:00:00");//入库时间开始段
        stock.put("in_end_time", "2023-10-30 23:00:00");//入库时间结束段
        stock.put("cancel", 0);
        stock.put("platform_code", "231020-259690351441219");
        String sign = GyySign.generateSign(stock, "7b756458336540d2afa78e5bad493959");
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
        System.out.print(post());
    }


}
