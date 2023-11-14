package cn.aireco.platform.strategy.spring;

import org.springframework.stereotype.Component;

@Component
public class StrategyA implements ShopRankHandler{

    @Override
    public String getType() {
        return "ST_A";
    }

    @Override
    public String calculate() {
        System.out.println("测试实现spring策略1");
        return null;
    }
}
