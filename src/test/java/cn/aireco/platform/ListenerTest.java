
package cn.aireco.platform;

import cn.aireco.platform.demo.DemoB;
import cn.aireco.platform.listener.LotteryResult;
import cn.aireco.platform.listener.LotteryService;
import cn.aireco.platform.listener.LotteryServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StopWatch;

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
    public static String test() {
        String str = "1";
        try {
            throw new Exception("---");
        } catch (Exception e) {
            str = "2";
        }
        return str;
    }


    @Test
    public  void selectWare() throws Exception {

    }


}
