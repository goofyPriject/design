
package com.example.design;

import com.example.design.demo.DemoB;
import com.example.design.listener.LotteryResult;
import com.example.design.listener.LotteryService;
import com.example.design.listener.LotteryServiceImpl;
import com.example.design.strategy.generic.GenericInterface;
import com.example.design.strategy.generic.HandlerFactory;
import com.example.design.strategy.spring.ShopRankHandler;
import com.example.design.strategy.spring.ShopRankHandlerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.PatternMatchUtils;

import java.util.ArrayList;
import java.util.List;

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
    public void invokeStrategy() {

    }



    public static void main(String[] args) {
        String s = "p1";
        List<String> list = new ArrayList<>();
        list.add("p2");
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


}
