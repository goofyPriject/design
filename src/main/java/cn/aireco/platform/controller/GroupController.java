package cn.aireco.platform.controller;

import cn.aireco.platform.domain.ErrorTemplate;
import cn.aireco.platform.domain.UserInfo;
import cn.aireco.platform.domain.UserRequest;
import cn.aireco.platform.door.annotation.DoDoor;
import cn.aireco.platform.strategy.generic.HandlerFactory;
import cn.aireco.platform.strategy.generic.StrategyInterfaceA;
import cn.aireco.platform.strategy.spring.ShopRankHandler;
import cn.aireco.platform.strategy.spring.ShopRankHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
