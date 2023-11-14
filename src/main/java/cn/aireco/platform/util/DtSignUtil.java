package cn.aireco.platform.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class DtSignUtil {

    public static String generateSign() {
        String appId="YSNHRX";
        String serviceName="goods.query";
        String bizData="eyJhY2Nlc3NDb2RlIjoiSkhTZTI0MmZkWVdXQ1RCWVRPQyIsIndoQ29kZSI6IkpIU2UyNDJmZFlXV0NUQllUT0MiLCJwYWdlIjoiMSIsInNpemUiOiIxMDAifQ";
        String v="3.0";
        String secret="dea92fba7f8add0b0f12eabbc76d8660";
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

    public static String encodeData() {
        String json = "{\"accessCode\":\"JHSe242fdYWWCTBYTOC\",\"whCode\":\"JHSe242fdYWWCTBYTOC\",\"page\":\"1\",\"size\":\"100\"}";
        return BaseEncoding.base64().encode(json.getBytes());
    }

    public static String test() {
        String str = "1";
        try {
            throw new Exception("---");
        } catch (Exception e) {
            str = "2";
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(generateSign());
    }

}
