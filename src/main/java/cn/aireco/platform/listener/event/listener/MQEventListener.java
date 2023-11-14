package cn.aireco.platform.listener.event.listener;

import cn.aireco.platform.listener.LotteryResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MQEventListener implements EventListener{
    @Override
    public void doEvent(LotteryResult result) {
        log.info("记录⽤户 {} 摇号结果(MQ)：{}", result.getUId(), result.getLottery());
    }
}
