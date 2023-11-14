package cn.aireco.platform.listener.event.listener;

import cn.aireco.platform.listener.LotteryResult;

public interface EventListener {
    void doEvent(LotteryResult result);
}
