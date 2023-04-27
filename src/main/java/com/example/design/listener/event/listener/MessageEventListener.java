package com.example.design.listener.event.listener;

import com.example.design.listener.LotteryResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageEventListener implements EventListener{
    @Override
    public void doEvent(LotteryResult result) {
        log.info("给⽤户 {} 发送短信通知(短信)：{}", result.getUId(), result.getLottery());
    }
}
