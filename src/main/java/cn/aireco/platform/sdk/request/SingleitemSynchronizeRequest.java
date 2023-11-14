/*
 * ©2018. etonbao.com. All rights reserved.
 * 河南易通跨境供应链有限公司 版权所有
 *
 */

package cn.aireco.platform.sdk.request;



/**
 * TOP API: yunji.esb.singleitem.synchronize request
 * 
 * @author top auto create
 * @since 1.0, 2016.07.21
 */
public class SingleitemSynchronizeRequest  {



	/**
	* 操作类型(两种类型：add|update)
	 */

	private String actionType;



	/**
	* null
	 */
	private Item item;

	/**
	* 货主编码
	 */
	private String ownerCode;

	/**
	* 供应商编码
	 */
	private String supplierCode;

	/**
	* 供应商名称
	 */
	private String supplierName;

	/**
	* 仓库编码(统仓统配等无需ERP指定仓储编码的情况填OTHER)
	 */
	private String warehouseCode;

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionType() {
		return this.actionType;
	}



	public void setItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return this.item;
	}

	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getOwnerCode() {
		return this.ownerCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierCode() {
		return this.supplierCode;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierName() {
		return this.supplierName;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getWarehouseCode() {
		return this.warehouseCode;
	}




	public static class ExtendProps{
		private String codeTs;
		private String itemRecordNo;
		private String originCountry;
		private String stockUnitCode;
		private String originCountryCode;

		public ExtendProps() {
		}

		public ExtendProps(String codeTs, String itemRecordNo, String originCountry, String stockUnitCode, String originCountryCode) {
			this.codeTs = codeTs;
			this.itemRecordNo = itemRecordNo;
			this.originCountry = originCountry;
			this.stockUnitCode = stockUnitCode;
			this.originCountryCode = originCountryCode;
		}

		public String getCodeTs() {
			return codeTs;
		}

		public void setCodeTs(String codeTs) {
			this.codeTs = codeTs;
		}

		public String getItemRecordNo() {
			return itemRecordNo;
		}

		public void setItemRecordNo(String itemRecordNo) {
			this.itemRecordNo = itemRecordNo;
		}

		public String getOriginCountry() {
			return originCountry;
		}

		public void setOriginCountry(String originCountry) {
			this.originCountry = originCountry;
		}

		public String getStockUnitCode() {
			return stockUnitCode;
		}

		public void setStockUnitCode(String stockUnitCode) {
			this.stockUnitCode = stockUnitCode;
		}

		public String getOriginCountryCode() {
			return originCountryCode;
		}

		public void setOriginCountryCode(String originCountryCode) {
			this.originCountryCode = originCountryCode;
		}
	}
	/**
	 * null
	 *
	 * @author top auto create
	 * @since 1.0, null
	 */
	public static class Item {
		/**
		 * 扩展属性
		 */
		private ExtendProps extendProps;
		/**
		 * 保质期临期预警天数
		 */
		private Long adventLifecycle;
		/**
		 * 批准文号
		 */
		private String approvalNumber;
		/**
		 * 条形码(可多个;用分号;隔开)
		 */
		private String barCode;
		/**
		 * 批次代码
		 */
		private String batchCode;
		/**
		 * 批次备注
		 */
		private String batchRemark;
		/**
		 * 品牌代码
		 */
		private String brandCode;
		/**
		 * 品牌名称
		 */
		private String brandName;
		/**
		 * 商品类别ID
		 */
		private String categoryId;
		/**
		 * 商品类别名称
		 */
		private String categoryName;
		/**
		 * 颜色
		 */
		private String color;
		/**
		 * 成本价
		 */
		private String costPrice;
		/**
		 * 创建时间(YYYY-MM-DD HH:MM:SS)
		 */
		private String createTime;
		/**
		 * 英文名
		 */
		private String englishName;
		/**
		 * 过期日期(YYYY-MM-DD)
		 */
		private String expireDate;
		/**
		 * 货号
		 */
		private String goodsCode;
		/**
		 * 毛重(单位：千克)
		 */
		private String grossWeight;
		/**
		 * 高(单位：厘米)
		 */
		private String height;
		/**
		 * 是否需要批次管理(Y/N ;默认为N)
		 */
		private String isBatchMgmt;

		/**
		 * 是否易碎品(Y/N ;默认为N)
		 */
		private String isFragile;
		/**
		 * 是否危险品(Y/N ;默认为N)
		 */
		private String isHazardous;
		/**
		 * 是否需要串号管理(Y/N ;默认为N)
		 */
		private String isSNMgmt;
		/**
		 * 是否需要保质期管理(Y/N ;默认为N)
		 */
		private String isShelfLifeMgmt;
		/**
		 * 是否sku(Y/N ;默认为N)
		 */
		private String isSku;
		/**
		 * 是否有效(Y/N ;默认为N)
		 */
		private String isValid;
		/**
		 * 商品编码
		 */
		private String itemCode;
		/**
		 * 仓储系统商品编码(该字段是WMS分配的商品编号;WMS如果分配了商品编码;则后续的商品操作都需要传该字段;如果WMS不使用;WMS可 以返回itemId=itemCode的值)
		 */
		private String itemId;
		/**
		 * 商品名称
		 */
		private String itemName;
		/**
		 * 商品类型(ZC=正常商品;FX=分销商品;ZH=组合商品;ZP=赠品;BC=包材;HC=耗材;FL=辅料;XN=虚拟品;FS=附属品;CC=残次品; OTHER=其它;只传英文编码)
		 */
		private String itemType;
		/**
		 * 长(单位：厘米)
		 */
		private String length;
		/**
		 * 保质期禁售天数
		 */
		private Long lockupLifecycle;
		/**
		 * 净重(单位：千克)
		 */
		private String netWeight;
		/**
		 * 商品的原产地
		 */
		private String originAddress;
		/**
		 * 包装代码
		 */
		private String packCode;
		/**
		 * 商品包装材料类型
		 */
		private String packageMaterial;
		/**
		 * 箱规
		 */
		private String pcs;
		/**
		 * 计价货类
		 */
		private String pricingCategory;
		/**
		 * 生产日期(YYYY-MM-DD)
		 */
		private String productDate;
		/**
		 * 采购价
		 */
		private String purchasePrice;
		/**
		 * 保质期禁收天数
		 */
		private Long rejectLifecycle;
		/**
		 * 备注
		 */
		private String remark;
		/**
		 * 零售价
		 */
		private String retailPrice;
		/**
		 * 安全库存
		 */
		private Long safetyStock;
		/**
		 * 季节编码
		 */
		private String seasonCode;
		/**
		 * 季节名称
		 */
		private String seasonName;
		/**
		 * 保质期(单位：小时)
		 */
		private Long shelfLife;
		/**
		 * 商品简称
		 */
		private String shortName;
		/**
		 * 尺寸
		 */
		private String size;
		/**
		 * 商品属性(如红色;XXL)
		 */
		private String skuProperty;
		/**
		 * 商品计量单位
		 */
		private String stockUnit;
		/**
		 * 吊牌价
		 */
		private String tagPrice;
		/**
		 * 渠道中的商品标题
		 */
		private String title;
		/**
		 * 更新时间(YYYY-MM-DD HH:MM:SS)
		 */
		private String updateTime;
		/**
		 * 体积(单位：升)
		 */
		private String volume;
		/**
		 * 宽(单位：厘米)
		 */
		private String width;
		public ExtendProps getExtendProps() {
			return extendProps;
		}

		public void setExtendProps(ExtendProps extendProps) {
			this.extendProps = extendProps;
		}
		public Long getAdventLifecycle() {
			return this.adventLifecycle;
		}
		public void setAdventLifecycle(Long adventLifecycle) {
			this.adventLifecycle = adventLifecycle;
		}
		public String getApprovalNumber() {
			return this.approvalNumber;
		}
		public void setApprovalNumber(String approvalNumber) {
			this.approvalNumber = approvalNumber;
		}
		public String getBarCode() {
			return this.barCode;
		}
		public void setBarCode(String barCode) {
			this.barCode = barCode;
		}
		public String getBatchCode() {
			return this.batchCode;
		}
		public void setBatchCode(String batchCode) {
			this.batchCode = batchCode;
		}
		public String getBatchRemark() {
			return this.batchRemark;
		}
		public void setBatchRemark(String batchRemark) {
			this.batchRemark = batchRemark;
		}
		public String getBrandCode() {
			return this.brandCode;
		}
		public void setBrandCode(String brandCode) {
			this.brandCode = brandCode;
		}
		public String getBrandName() {
			return this.brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getCategoryId() {
			return this.categoryId;
		}
		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}
		public String getCategoryName() {
			return this.categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getColor() {
			return this.color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getCostPrice() {
			return this.costPrice;
		}
		public void setCostPrice(String costPrice) {
			this.costPrice = costPrice;
		}
		public String getCreateTime() {
			return this.createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getEnglishName() {
			return this.englishName;
		}
		public void setEnglishName(String englishName) {
			this.englishName = englishName;
		}
		public String getExpireDate() {
			return this.expireDate;
		}
		public void setExpireDate(String expireDate) {
			this.expireDate = expireDate;
		}
		public String getGoodsCode() {
			return this.goodsCode;
		}
		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}
		public String getGrossWeight() {
			return this.grossWeight;
		}
		public void setGrossWeight(String grossWeight) {
			this.grossWeight = grossWeight;
		}
		public String getHeight() {
			return this.height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
		public String getIsBatchMgmt() {
			return this.isBatchMgmt;
		}
		public void setIsBatchMgmt(String isBatchMgmt) {
			this.isBatchMgmt = isBatchMgmt;
		}
		public String getIsFragile() {
			return this.isFragile;
		}
		public void setIsFragile(String isFragile) {
			this.isFragile = isFragile;
		}
		public String getIsHazardous() {
			return this.isHazardous;
		}
		public void setIsHazardous(String isHazardous) {
			this.isHazardous = isHazardous;
		}
		public String getIsSNMgmt() {
			return this.isSNMgmt;
		}
		public void setIsSNMgmt(String isSNMgmt) {
			this.isSNMgmt = isSNMgmt;
		}
		public String getIsShelfLifeMgmt() {
			return this.isShelfLifeMgmt;
		}
		public void setIsShelfLifeMgmt(String isShelfLifeMgmt) {
			this.isShelfLifeMgmt = isShelfLifeMgmt;
		}
		public String getIsSku() {
			return this.isSku;
		}
		public void setIsSku(String isSku) {
			this.isSku = isSku;
		}
		public String getIsValid() {
			return this.isValid;
		}
		public void setIsValid(String isValid) {
			this.isValid = isValid;
		}
		public String getItemCode() {
			return this.itemCode;
		}
		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}
		public String getItemId() {
			return this.itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public String getItemName() {
			return this.itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public String getItemType() {
			return this.itemType;
		}
		public void setItemType(String itemType) {
			this.itemType = itemType;
		}
		public String getLength() {
			return this.length;
		}
		public void setLength(String length) {
			this.length = length;
		}
		public Long getLockupLifecycle() {
			return this.lockupLifecycle;
		}
		public void setLockupLifecycle(Long lockupLifecycle) {
			this.lockupLifecycle = lockupLifecycle;
		}
		public String getNetWeight() {
			return this.netWeight;
		}
		public void setNetWeight(String netWeight) {
			this.netWeight = netWeight;
		}
		public String getOriginAddress() {
			return this.originAddress;
		}
		public void setOriginAddress(String originAddress) {
			this.originAddress = originAddress;
		}
		public String getPackCode() {
			return this.packCode;
		}
		public void setPackCode(String packCode) {
			this.packCode = packCode;
		}
		public String getPackageMaterial() {
			return this.packageMaterial;
		}
		public void setPackageMaterial(String packageMaterial) {
			this.packageMaterial = packageMaterial;
		}
		public String getPcs() {
			return this.pcs;
		}
		public void setPcs(String pcs) {
			this.pcs = pcs;
		}
		public String getPricingCategory() {
			return this.pricingCategory;
		}
		public void setPricingCategory(String pricingCategory) {
			this.pricingCategory = pricingCategory;
		}
		public String getProductDate() {
			return this.productDate;
		}
		public void setProductDate(String productDate) {
			this.productDate = productDate;
		}
		public String getPurchasePrice() {
			return this.purchasePrice;
		}
		public void setPurchasePrice(String purchasePrice) {
			this.purchasePrice = purchasePrice;
		}
		public Long getRejectLifecycle() {
			return this.rejectLifecycle;
		}
		public void setRejectLifecycle(Long rejectLifecycle) {
			this.rejectLifecycle = rejectLifecycle;
		}
		public String getRemark() {
			return this.remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getRetailPrice() {
			return this.retailPrice;
		}
		public void setRetailPrice(String retailPrice) {
			this.retailPrice = retailPrice;
		}
		public Long getSafetyStock() {
			return this.safetyStock;
		}
		public void setSafetyStock(Long safetyStock) {
			this.safetyStock = safetyStock;
		}
		public String getSeasonCode() {
			return this.seasonCode;
		}
		public void setSeasonCode(String seasonCode) {
			this.seasonCode = seasonCode;
		}
		public String getSeasonName() {
			return this.seasonName;
		}
		public void setSeasonName(String seasonName) {
			this.seasonName = seasonName;
		}
		public Long getShelfLife() {
			return this.shelfLife;
		}
		public void setShelfLife(Long shelfLife) {
			this.shelfLife = shelfLife;
		}
		public String getShortName() {
			return this.shortName;
		}
		public void setShortName(String shortName) {
			this.shortName = shortName;
		}
		public String getSize() {
			return this.size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getSkuProperty() {
			return this.skuProperty;
		}
		public void setSkuProperty(String skuProperty) {
			this.skuProperty = skuProperty;
		}
		public String getStockUnit() {
			return this.stockUnit;
		}
		public void setStockUnit(String stockUnit) {
			this.stockUnit = stockUnit;
		}
		public String getTagPrice() {
			return this.tagPrice;
		}
		public void setTagPrice(String tagPrice) {
			this.tagPrice = tagPrice;
		}
		public String getTitle() {
			return this.title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUpdateTime() {
			return this.updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		public String getVolume() {
			return this.volume;
		}
		public void setVolume(String volume) {
			this.volume = volume;
		}
		public String getWidth() {
			return this.width;
		}
		public void setWidth(String width) {
			this.width = width;
		}
	}
	

}