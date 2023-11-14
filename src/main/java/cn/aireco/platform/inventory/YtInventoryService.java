package cn.aireco.platform.inventory;


import java.io.UnsupportedEncodingException;

import cn.aireco.platform.sdk.client.OpenClient;
import cn.aireco.platform.sdk.model.ProductQueryRequest;
import cn.aireco.platform.sdk.request.CommonRequest;
import cn.aireco.platform.sdk.util.SignUtil;
import cn.hutool.core.net.URLEncodeUtil;
import com.alibaba.fastjson.JSONObject;

import javax.accessibility.AccessibleRelation;
import java.net.URLEncoder;
import java.util.*;

import static cn.hutool.crypto.SecureUtil.md5;

public class YtInventoryService {


    public static void main(String[] args) throws UnsupportedEncodingException {
        String appkey = "ZZZJWC";
        String secret = "6bcede75d6560c245468734730a78f11";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("warehouseCode", "ZZZB");
        jsonObject.put("shopCode", "ZZZJWC");
        JSONObject item = new JSONObject();
        item.put("itemCode", "11123121");
        item.put("inventoryType", "ZP");
        item.put("ownerCode", "ZZZJWC");
        item.put("warehouseCode", "ZZZB");
        item.put("itemId", "11123121");
        JSONObject criteriaList = new JSONObject();
        criteriaList.put("criteriaList", Arrays.asList(item));
        String encode = URLEncodeUtil.encode(jsonObject.toString());
        System.out.println(encode);
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", "");
        map.put("app_key", appkey);
        map.put("data", encode);
        map.put("format", "json");
        map.put("name", "product.query");
        map.put("version", "");
        map.put("timestamp", "2023-11-09 03:25:47");
        System.out.println(generateSign(map, secret));
//
//        OpenClient openClient = new OpenClient("https://open.aireco.cn/api", "ZZZJWC", "6bcede75d6560c245468734730a78f11");
//        CommonRequest commonRequest = new CommonRequest("product.query");
//        ProductQueryRequest request = new ProductQueryRequest();
//        request.setShopCode("ZZZJWC");
//        request.setWarehouseCode("ZZZB");
//        commonRequest.setData(request);
//        String execute = openClient.execute(commonRequest);
//        System.out.println(execute);

    }

    private static String generateSign(Map<String,Object> paramsMap, String secret) {
        Set<String> keySet = paramsMap.keySet();
        List<String> paramNames = new ArrayList<String>(keySet); // 1. Collections.so
        Collections.sort(paramNames);
        StringBuilder paramNameValue = new StringBuilder(); // 2.
        for (String paramName : paramNames) {
            paramNameValue.append(paramName).append(paramsMap.get(paramName));
        } // 3.
        String source = secret + paramNameValue.toString() + secret; // 4.& 5.
        return md5(source).toUpperCase(); // 6. paramsMap.put("sign",sign);
    }

}
