package com.example.design.door.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Configuration
//@ConditionalOnClass(StarterService.class)
//@EnableConfigurationProperties(StarterServiceProperties.class)
@Component
public class StarterAutoConfigure {

    @Autowired
    private StarterServiceProperties starterServiceProperties;

//    @Bean
//    @ConditionalOnBean
//    @ConditionalOnProperty(prefix = "door", value = "enable", havingValue = "true")
    public StarterService starterService() {
        return new StarterService(starterServiceProperties.getIp());
    }

}
