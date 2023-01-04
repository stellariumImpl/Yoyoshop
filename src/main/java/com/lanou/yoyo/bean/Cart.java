package com.lanou.yoyo.bean;


import java.util.Date;
import java.util.List;

public class Cart {
	private Integer id;
	
	private Date systime;
	
	private User user;
	
	private Integer paytype;
	private String name;
	private String phone;
	private String address;
	
	public Cart() {
		super();
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Integer status;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getSystime() {
		return systime;
	}

	public void setSystime(Date systime) {
		this.systime = systime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Double total; // 购物车总金额
	private Integer amount; // 订单数量
	private Integer userId; // 即将下单用户的id
	private List<Item> itemList; // 购买项列表

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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
		return "Cart [id=" + id + ", systime=" + systime + ", user=" + user + ", status=" + status + ", total=" + total
				+ ", amount=" + amount + ", userId=" + userId + ", itemList=" + itemList + "]";
	}

}
