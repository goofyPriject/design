/*
 * ©2018. etonbao.com. All rights reserved.
 * 河南易通跨境供应链有限公司 版权所有
 *
 */

package cn.aireco.platform.sdk.model;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 在库商品查询 请求类.
 *
 * @author zww
 * @Date: 2022-03-18
 */
@Data
public class ProductQueryRequest {


    /**
     * 店铺代码
     */
    @NotBlank(message = "shopCode 店铺信息不能为空")
    private String shopCode;

    /**
     * 仓库编码
     */
    private String warehouseCode;


}