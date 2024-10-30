/*
package cn.aireco.platform.demo;

import cn.aireco.platform.sdk.util.AKSKHead;
import cn.aireco.platform.util.HttpUtils;
import org.apache.tomcat.util.http.parser.Authorization;

import java.util.*;
import java.util.stream.*;

class Employee {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    String name;
    int age;

    public Employee(String alice, int id) {

    }

    // 构造函数和 getter 省略...

    public static void main(String[] args) {
        String method = "POST";   // 填method字段
        String url = "/o2o/v1/order/createExtOrder";       // 填path字段
//        String url = "/o2o/v1/product/queryExtDisplayProduct";       // 填path字段
//        String url = "/o2o/v1/order/queryExtOrderDetails";
        String ak = "XiO3EcovXDSfLuEHSKaWUI";                        // 填AK字段
        String sk = "bfccEb2sqES8ALU1wclsxRir0uWK0sko0Bry2c0RFNa1h"; // 填SK字段
//        String body =  "{ \"channelType\":2, \"storeID\":\"V9999990006\", \"companyCode\":\"\", \"country\":\"CN\", \"extOrderSource\":96, \"displayProductID\": \"10086318019079\" }";
        String body = "{ \"channelType\": \"2\", \"companyCode\": \"\", \"storeID\": \"V9999990006\", \"extOrderSource\": \"96\", \"extOrderCode\": \"202207261101\", \"orderItemReqArgList\": [{ \"itemId\": \"2601010206801\", \"itemType\": \"S0\", \"qty\": \"1\" }, { \"itemId\": \"2601010206802\", \"itemType\": \"S0\", \"qty\": \"1\" }, { \"itemId\": \"0086012601030016001\", \"itemType\": \"S0\", \"qty\": \"1\" } ], \"delivery\": { \"consignee\": \"魏祥国\", \"province\": \"广东省\", \"city\": \"深圳市\", \"district\": \"龙岗区\", \"street\": \"坂田街道\", \"address\": \"天安云谷\", \"mobile\": \"13632514321\" }, \"invoiceType\": \"3\", \"country\": \"CN\" }";
//        String body = "{ \"channelType\": \"2\", \"companyCode\": \"\", \"storeID\": \"V9999990006\", \"extOrderSource\": \"96\", \"orderCodeList\": [\"22350003433\",\"22350003432\"], \"country\": \"CN\" }";
        String appid = "";
        String deviceTimeStamp = String.valueOf(System.currentTimeMillis());
        String header = Optional.ofNullable(new AKSKHead(ak, sk)
                        .getAKSKHead(method, url, null, body, deviceTimeStamp))
                .orElse(new Authorization(null,null, null))
                .toString();
        System.out.println("在header中填写如下内容");
        System.out.println("===========================================================================");
        System.out.println("VmallAuthorization: " + header);
        System.out.println("===========================================================================");
        System.out.println("在body中填写如下内容");
        System.out.println("===========================================================================");
        System.out.println(body);
        System.out.println("===========================================================================");
        String url1 = "https://openapisit.test.vmall.com"+url;
        Map<String, String> header1 = new HashMap<>();
        header1.put("VmallAuthorization", header);
        header1.put("Content-Type", "text/plain");
        String post = HttpUtils.post(url1, header1, body);
        System.out.println("返回结果如下");
        System.out.println("=");
        System.out.println(post);
    }
}*/
