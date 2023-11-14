package cn.aireco.platform.domain;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private String code;

    private List<OrderDetail> orderDetail;

}
