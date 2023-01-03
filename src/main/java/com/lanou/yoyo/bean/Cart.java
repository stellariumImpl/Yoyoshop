package com.lanou.yoyo.bean;

import java.util.List;

public class Cart {
	private Double total; // 购物车总金额
	private Integer amout; // 订单数量
	private Integer userId; // 即将下单用户的id
	private List<Item> itemList; // 购买项列表

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getAmout() {
		return amout;
	}

	public void setAmout(Integer amout) {
		this.amout = amout;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "Cart [total=" + total + ", amout=" + amout + ", userId=" + userId + ", itemList=" + itemList + "]";
	}

}
