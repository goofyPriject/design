package com.example.design.inventory.impl;

import com.example.design.inventory.proxy.AbstractInventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryImpl2 extends AbstractInventory {

    Logger logger = LoggerFactory.getLogger(InventoryImpl2.class);

    @Override
    public void doSomething(String type) {
        System.out.println("imp2");
    }
}
