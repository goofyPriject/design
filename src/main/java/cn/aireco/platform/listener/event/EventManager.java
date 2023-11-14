package cn.aireco.platform.listener.event;

import cn.aireco.platform.listener.event.listener.EventListener;
import cn.aireco.platform.listener.LotteryResult;

import java.util.*;

public class EventManager {

    Map<Enum<EventType>, List<cn.aireco.platform.listener.event.listener.EventListener>> listeners = new HashMap<>();
    @SafeVarargs
    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public enum EventType {
        MQ, Message
    }
    /**
     * 订阅
     * @param eventType 事件类型
     * @param listener 监听
     */
    public void subscribe(Enum<EventType> eventType, cn.aireco.platform.listener.event.listener.EventListener
            listener) {
        List<cn.aireco.platform.listener.event.listener.EventListener> users = listeners.get(eventType);
        users.add(listener);
    }
    /**
     * 取消订阅
     * @param eventType 事件类型
     * @param listener 监听
     */
    public void unsubscribe(Enum<EventType> eventType, cn.aireco.platform.listener.event.listener.EventListener
            listener) {
        List<cn.aireco.platform.listener.event.listener.EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }
    /**
     * 通知
     * @param eventType 事件类型
     * @param result 结果
     */
    public void notify(Enum<EventType> eventType, LotteryResult result) {
        List<cn.aireco.platform.listener.event.listener.EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.doEvent(result);
        }
    }
}
