package com.example.design.inventory.impl;

import com.example.design.inventory.proxy.AbstractInventory;

public class InventoryImpl1 extends AbstractInventory {


    @Override
    public void doSomething(String type) {
        System.out.println("imp1");
    }
}
