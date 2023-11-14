package cn.aireco.platform.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.security.MessageDigest;
import java.util.LinkedHashMap;

public class GyySign {

    public static String generateSign(JSONObject data, String secret) {
        StringBuilder enValue = new StringBuilder();
        enValue.append(secret);
        enValue.append(JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue));
        enValue.append(secret);
        return encryptByMD5(enValue.toString());
    }


    public static String encryptByMD5(String data) {

        StringBuilder sign = new StringBuilder();

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] bytes = md.digest(data.getBytes("UTF-8"));

            for (int i = 0; i < bytes.length; i++) {

                String hex = Integer.toHexString(bytes[i] & 0xFF);

                if (hex.length() == 1) {

                    sign.append("0");

                }

                sign.append(hex.toUpperCase());

            }


        } catch (Exception e) {

            e.printStackTrace();

        }
        return sign.toString();
    }


    public static void main(String[] args){

        String str="{\"appkey\":\"117372\",\"sessionkey\":\"71faebefe1c543339e7233c671d8ffa7\",\"method\":\"gy.erp.warehouse.get\"}";

        String st = "{\"appkey\":\"117372\",\"sessionkey\":\"71faebefe1c543339e7233c671d8ffa7\",\"method\":\"gy.erp.warehouse.add\",\"warehouse_code\":\"1111111\",\"warehouse_name\":\"测试abcd\",\"contact_phone\":\"456789\",\"contact_mobile\":\"456789\",\"province\":\"浙江省\",\"city\":\"杭州市\",\"district\":\"上城区\",\"address\":\"1223\",\"note\":\"\"}";
        JSONObject warehouse = new JSONObject(new LinkedHashMap<>());;
        warehouse.put("appkey", "117372");
        warehouse.put("sessionkey", "71faebefe1c543339e7233c671d8ffa7");
        warehouse.put("method", "gy.erp.warehouse.add");
        warehouse.put("key", "sss");

        String secret="825435b90207420e8e9d4677fc75e6d6";
        //String result=encryptByMD5(secret+json.toString()+secret);
        StringBuilder enValue = new StringBuilder();
        enValue.append(secret);
        enValue.append(st);
        enValue.append(secret);

        System.out.println(st.toString());
        System.out.println(encryptByMD5(enValue.toString()));

    }





}
