package com.example.design.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
public class UserInfo {

    private String name;

    private int age;

    private String info;

}
