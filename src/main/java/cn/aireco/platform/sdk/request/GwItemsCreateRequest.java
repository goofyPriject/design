package cn.aireco.platform.sdk.request;

import lombok.Data;

/**
 * Created by Hsp
 * CreateTime: 2020/5/26
 */
@Data
public class GwItemsCreateRequest {
    private String  itemId;
    private String  shopCode;
    private String  warehouseCode;
}
