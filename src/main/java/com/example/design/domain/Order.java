package com.example.design.domain;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private String code;

    private List<OrderDetail> orderDetail;

}
