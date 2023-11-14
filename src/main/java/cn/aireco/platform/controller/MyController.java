package cn.aireco.platform.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import cn.aireco.platform.domain.CommodityNo;
import cn.aireco.platform.domain.Model;
import cn.aireco.platform.util.AesUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

    @Scheduled(cron = "0 0 3,4,5 * * ?")
    public void myendpoint() {
        try {
            String mes = "客户代码, 100070, S10186,客户名称, 河南集成-浙江物产, 中心仓-物产";
            String url = "http://wms.jcgyl.net/outu/wms/api/Values/GetStock";
            String token = "6CE14835-C490-4735-A074-8593B98A9717";
            String secret = "D5B415E777654F548F2449FC1B614B82";
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Map<String, String> headers = new HashMap<>();
            headers.put("access_token", token);
            String timestamp = LocalDateTime.now().format(dateTimeFormatter);
            headers.put("timestamp", timestamp);
            String sign = "POST" + System.lineSeparator() + timestamp + System.lineSeparator() + "/api/Values/GetStock";
            sign = AesUtil.pkcs7Encode(sign, secret);
            headers.put("sign", sign);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("StoreNo", "110008125");
            jsonObject.put("OwnerNo", "410166K00K");
            jsonObject.put("SupplierNo", "100070");
            CommodityNo commodityNo = CommodityNo.of("8809803511230");
            jsonObject.put("commodityNos", Arrays.asList(commodityNo));
            String body = JSONUtil.toJsonStr(jsonObject);
            String encrypt =  AesUtil.pkcs7Encode(body, secret);
            Model model = new Model();
            model.setJson(encrypt);
            String jsonBodyStr = JSONUtil.toJsonStr(model);
            String result = HttpUtil.createPost(url).contentType("application/json").headerMap(headers, true).body(jsonBodyStr).timeout(-1).execute().body();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
