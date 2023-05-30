package com.example.design.strategy.generic;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {
    @Bean
    public HandlerFactory<String, StrategyInterfaceA> strategyInterfaceAFactory(){
        return new HandlerFactory<>(StrategyInterfaceA.class);
    }
}
