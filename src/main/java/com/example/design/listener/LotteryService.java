package com.example.design.listener;

import com.example.design.listener.event.EventManager;
import com.example.design.listener.event.listener.MQEventListener;
import com.example.design.listener.event.listener.MessageEventListener;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class LotteryService {

    private EventManager eventManager;
    public LotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ,
                EventManager.EventType.Message);
        eventManager.subscribe(EventManager.EventType.MQ, new
                MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new
                MessageEventListener());
    }
    public LotteryResult draw(String uId) {
        LotteryResult lotteryResult = doDraw(uId);
        // 需要什么通知就给调⽤什么⽅法
        eventManager.notify(EventManager.EventType.MQ, lotteryResult);
        eventManager.notify(EventManager.EventType.Message,
                lotteryResult);
        return lotteryResult;
    }

    public abstract LotteryResult doDraw(String uId);


}
