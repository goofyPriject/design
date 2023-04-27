package com.example.design.listener.event.listener;

import com.example.design.listener.LotteryResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MQEventListener implements EventListener{
    @Override
    public void doEvent(LotteryResult result) {
        log.info("记录⽤户 {} 摇号结果(MQ)：{}", result.getUId(), result.getLottery());
    }
}
