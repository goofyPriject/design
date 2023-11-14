package cn.aireco.platform.strategy.generic;

import org.springframework.stereotype.Component;

@Component
public class StrategyGA implements StrategyInterfaceA{

    @Override
    public String getType() {
        return "ST_A";
    }

    @Override
    public String handle() {
        System.out.println("测试策略A");
        return null;
    }
}
