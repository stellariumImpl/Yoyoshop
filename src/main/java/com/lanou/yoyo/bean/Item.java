package com.lanou.yoyo.bean;

public class Item {
	private Integer id;
	private Double price;
	private Integer amout;
	private Integer goodId;
	private Integer orderId;
	
	private Goods goods;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getAmout() {
		return amout;
	}
	public void setAmout(Integer amout) {
		this.amout = amout;
	}
	public Integer getGoodId() {
		return goodId;
	}
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", price=" + price + ", amout=" + amout + ", goodId=" + goodId + ", orderId="
				+ orderId + ", goods=" + goods + "]";
	}
	
	
}
