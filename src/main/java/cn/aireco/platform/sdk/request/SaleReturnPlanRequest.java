package cn.aireco.platform.sdk.request;

import lombok.Data;

/**
 * Created by Hsp
 * CreateTime: 2020/7/1
 */
@Data
public class SaleReturnPlanRequest {
    private String   orderCode;
    private String   warehouseCode;
    private String   goodsOwner;
}
