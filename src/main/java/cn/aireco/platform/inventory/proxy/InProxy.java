package cn.aireco.platform.inventory.proxy;

import cn.aireco.platform.inventory.InventoryService;
import cn.aireco.platform.inventory.impl.InventoryImpl1;
import cn.aireco.platform.inventory.impl.InventoryImpl2;

/**
 * 分发请求
 */
public class InProxy extends AbstractInventory {

    @Override
    public void dispatchingRequests(String type) {
        // 查询配置表  多选下拉框， code, 权限判断，key
        InventoryService inventoryService;
        if (type.equals("1")) {
            inventoryService = new InventoryImpl1();
            inventoryService.doSomething(type);
        }else if(type.equals("2")) {
            inventoryService = new InventoryImpl2();
            inventoryService.doSomething(type);
        }else {

            System.out.println("Dispatching Requests");
        }
    }



}
