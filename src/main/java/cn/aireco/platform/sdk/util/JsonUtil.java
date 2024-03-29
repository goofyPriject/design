package cn.aireco.platform.sdk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
    
    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj) {
        if(obj == null) {
            return "{}";
        }
        return JSON.toJSONString(obj);
    }

    /**
     * json转对象
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    public static JSONObject parseJSONObject(String json) {
        return JSON.parseObject(json);
    }
}
