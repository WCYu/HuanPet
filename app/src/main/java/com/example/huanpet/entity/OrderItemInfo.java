package com.example.huanpet.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @Description:(订单详情信息实体)
 * @ClassName: OrderItemInfo
 * @author zf zhangfeng@tctogether.com
 * @date 2016-03-17 17:52:54
 *       <p>
 *       北京天成合科�??有限公司版权�??�??
 *       </p>
 */
public class OrderItemInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String orderId;// 订单id
	private String petId;// 宠物id
	private String petName;// 宠物名称
	private String petImage;// 宠物图片
	private int petSex;// 宠物性别
	private String petInfo;// 宠物�??�??
	private String petPricingCode;// 宠物定价code
	private String petPricingName;// 宠物定价名称
	private int petDuration;// 寄养时长
	private BigDecimal price;// 金额
	private Date createTime;// 创建时间
	private Date updateTime;// 修改时间
	private String serviceCode;// 服务code
	private String serviceName;// 服务名称
	private BigDecimal servicePrice;// 服务定价
	private String petPricingPrice;// 宠物定价
	private String serviceCount;// 服务�??
	private int isService;// 是否是服�?? 1:是服�?? 2:是宠�??
	private String orderPetInfo;// 订单宠物信息
	private String orderState;// 订单状�??
	private String unit;// 单位

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getIsService() {
		return isService;
	}

	public void setIsService(int isService) {
		this.isService = isService;
	}

	public String getOrderPetInfo() {
		return orderPetInfo;
	}

	public void setOrderPetInfo(String orderPetInfo) {
		this.orderPetInfo = orderPetInfo;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getPetPricingName() {
		return petPricingName;
	}

	public String getPetPricingPrice() {
		return petPricingPrice;
	}

	public void setPetPricingPrice(String petPricingPrice) {
		this.petPricingPrice = petPricingPrice;
	}

	public String getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(String serviceCount) {
		this.serviceCount = serviceCount;
	}

	public void setPetPricingName(String petPricingName) {
		this.petPricingName = petPricingName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPetId() {
		return petId;
	}

	public void setPetId(String petId) {
		this.petId = petId;
	}

	public String getPetPricingCode() {
		return petPricingCode;
	}

	public void setPetPricingCode(String petPricingCode) {
		this.petPricingCode = petPricingCode;
	}

	public int getPetDuration() {
		return petDuration;
	}

	public void setPetDuration(int petDuration) {
		this.petDuration = petDuration;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public BigDecimal getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getPetImage() {
		return petImage;
	}

	public void setPetImage(String petImage) {
		this.petImage = petImage;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getPetSex() {
		return petSex;
	}

	public void setPetSex(int petSex) {
		this.petSex = petSex;
	}

	public String getPetInfo() {
		return petInfo;
	}

	public void setPetInfo(String petInfo) {
		this.petInfo = petInfo;
	}

	@Override
	public String toString() {
		return "OrderItemInfo [id=" + id + ", orderId=" + orderId + ", petId="
				+ petId + ", petName=" + petName + ", petImage=" + petImage
				+ ", petSex=" + petSex + ", petInfo=" + petInfo
				+ ", petPricingCode=" + petPricingCode + ", petPricingName="
				+ petPricingName + ", petDuration=" + petDuration + ", price="
				+ price + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", serviceCode=" + serviceCode
				+ ", serviceName=" + serviceName + ", servicePrice="
				+ servicePrice + ", petPricingPrice=" + petPricingPrice
				+ ", serviceCount=" + serviceCount + ", isService=" + isService
				+ ", orderPetInfo=" + orderPetInfo + ", orderState="
				+ orderState + ", unit=" + unit + "]";
	}

}
