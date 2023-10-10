package com.example.design.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class DtSignUtil {

    public static String generateSign() {
        String appId="1688";
        String serviceName="order.cancel";
        String bizData="W3siY2l0eSI6IuadreW3njEiLCJjb3VudCI6M31d";
        String v="4.0";
        String secret="1234";
        Map<String, String> map = Maps.newHashMap();
        map.put("appId", appId);
        map.put("serviceName", serviceName);
        map.put("bizData", bizData);
        map.put("v",v);
        Set<String> keySet = map.keySet();
        String[] keyArray = keySet.toArray(new String[0]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals("sign")) {  //防止参数中有sign，一般不会执行
                continue;
            }
            if(map.containsKey(k) && StringUtils.hasText(map.get(k))) {
                sb.append(k).append("=").append(map.get(k));
            }
        }
        sb.append(secret);
        String sign = Hashing.md5().newHasher().putString(sb.toString(), Charsets.UTF_8).hash().toString().toLowerCase();
        return sign;
    }

    public static void main(String[] args) {
        System.out.println(generateSign());
    }

}
