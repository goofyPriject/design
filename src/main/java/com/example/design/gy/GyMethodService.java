package com.example.design.gy;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qimencloud.api.DefaultQimenCloudClient;
import com.qimencloud.api.scenetp8z6548i2.request.GyErpTradeDeliverysGetRequest;
import com.qimencloud.api.scenetp8z6548i2.request.GyErpTradeDeliverysHistoryGetRequest;
import com.qimencloud.api.scenetp8z6548i2.response.GyErpTradeDeliverysGetResponse;
import com.qimencloud.api.scenetp8z6548i2.response.GyErpTradeDeliverysHistoryGetResponse;

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

    public static void main(String[] args) {
        System.out.print(queryDeliverys());
    }


}
