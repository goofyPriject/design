package com.example.design.door.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

public class StarterService {

    private  String userId;

    public StarterService(String userId) {
        this.userId = userId;
    }

    public String [] split(String separatorChar) {
        return StringUtils.split(this.userId,separatorChar);
    }
}
