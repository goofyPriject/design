package com.example.design.strategy.generic;

import org.springframework.stereotype.Component;

@Component
public class StrategyGB implements StrategyInterfaceA{
    @Override
    public String getType() {
        return "ST_B";
    }

    @Override
    public String handle() {
        System.out.println("测试泛型B");
        return null;
    }
}
