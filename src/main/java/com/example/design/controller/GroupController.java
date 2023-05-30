package com.example.design.controller;

import com.example.design.domain.ErrorTemplate;
import com.example.design.domain.UserInfo;
import com.example.design.domain.UserRequest;
import com.example.design.door.annotation.DoDoor;
import com.example.design.strategy.generic.GenericInterface;
import com.example.design.strategy.generic.HandlerFactory;
import com.example.design.strategy.generic.StrategyInterfaceA;
import com.example.design.strategy.spring.ShopRankHandler;
import com.example.design.strategy.spring.ShopRankHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class GroupController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShopRankHandlerFactory rankHandlerFactory;

    @Autowired
    private HandlerFactory<String, StrategyInterfaceA> handlerFactory;

    /**
     * 策略实例容器
     */
    @Autowired
    private Map<String, ShopRankHandler> GET_SHOP_RANK_STRATEGY_MAP;

    @DoDoor(key = "UserRequest")
    @PostMapping(value = "/v1/selectUserInfo")
    public ErrorTemplate selectUserInfo(@RequestBody UserRequest userRequest) {
        return ErrorTemplate.success(new UserInfo("nihao" + userRequest.getUserId(), 1, "杭州市"));
    }

    @GetMapping(value = "/v1/openUrl")
    public String openUrl(HttpServletResponse resp) throws IOException {
//        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();//注意getResource("")里面是空字符串
//        String str = path+"com/example/design/util/东疆备案表.xlsx";
//        File file = new File(str);
//        FileInputStream fileInputStream = new FileInputStream(file);
//        System.out.println(path);
        return "redirect:http://www.baidu.com/";
    }


    @GetMapping(value = "/v1/invoke")
    public @ResponseBody
    String invoke(HttpServletResponse resp) throws Exception {
        ShopRankHandler strategy = rankHandlerFactory.getStrategy("ST_A");
        ShopRankHandler shopRankHandler = GET_SHOP_RANK_STRATEGY_MAP.get("strategyA");
        shopRankHandler.calculate();
        strategy.calculate();
        System.out.println("测试泛型");
        StrategyInterfaceA stA = handlerFactory.getStrategy("ST_A");
        stA.handle();
        StrategyInterfaceA stB = handlerFactory.getStrategy("ST_B");
        stB.handle();
        return "success";
    }
}
