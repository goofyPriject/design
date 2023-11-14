package cn.aireco.platform.inventory.impl;

import cn.aireco.platform.inventory.proxy.AbstractInventory;

public class InventoryImpl1 extends AbstractInventory {


    @Override
    public void doSomething(String type) {
        System.out.println("imp1");
    }
}
