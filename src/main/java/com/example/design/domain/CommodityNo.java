package com.example.design.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommodityNo implements Serializable {

    private String commodityNo;

    public static CommodityNo of(String commodityNo) {
        return new CommodityNo(commodityNo);
    }

}
