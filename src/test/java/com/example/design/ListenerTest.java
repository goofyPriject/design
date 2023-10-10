
package com.example.design;

import com.alibaba.fastjson.JSONObject;
import com.example.design.demo.DemoB;
import com.example.design.listener.LotteryResult;
import com.example.design.listener.LotteryService;
import com.example.design.listener.LotteryServiceImpl;
import com.example.design.util.JDStockQueryUtil;
import com.example.design.util.JkyMzInventoryImpl;
import com.example.design.util.SFStockQueryUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ListenerTest {


    @Test
    public void select() {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult draw = lotteryService.draw("2765789109876");
        System.out.println(draw.getLottery());
    }
    @Test
    public void testExtend() {
        DemoB demoB = new DemoB();
        demoB.getA();
    }

    @Test
    public void invokeStrategy() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(1);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }



    public static void main(String[] args) {
        String s = "p1";
        List<String> list = new ArrayList<>();
        list.add("p1");
        list.add("p1,p2");
        list.add("p1,p2,p3");
        boolean b =list.stream().anyMatch(a-> PatternMatchUtils.simpleMatch(s.split(","),a));
        System.out.println(b);

    }

    public static boolean predicate(String a, String t) {
        String[] stream = a.split(",");
        for (String str: stream) {
            if (t.contains(str)) return true;
        }
        return false;
    }

    @Test
    public  void selectWare() throws Exception {
        JkyMzInventoryImpl.syncInventory();
    }


}
