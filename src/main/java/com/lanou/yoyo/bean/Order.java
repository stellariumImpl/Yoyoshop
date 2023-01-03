package com.lanou.yoyo.bean;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
	private int id;
	private double total;
	private int amount;
	private int status;
	private int paytype;
	private String name;
	private String phone;
	private String address;
	private Date systime;
	private User user;
	private Map<Integer,OrderItem> itemMap = new HashMap<Integer,OrderItem>();
	
	
	public void addGoods(Goods g) {
		if(itemMap.containsKey(g.getId())) {
			OrderItem item = itemMap.get(g.getId());
			item.setAmount(item.getAmount()+1);
			amount++;
			total+=g.getPrice();
		}else {
			OrderItem item = new OrderItem(g.getPrice(),1, g, this);
			itemMap.put(g.getId(), item);
		}
	}
	
	public void lessen(int goodsid) {
		if(itemMap.containsKey(goodsid)) {
			OrderItem item = itemMap.get(goodsid);
			item.setAmount(item.getAmount()-1);
			amount--;
			total-=item.getPrice();
			if(item.getAmount()<=0) {
				itemMap.remove(goodsid);
			}
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
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
	public Date getSystime() {
		return systime;
	}
	public void setSystime(Date systime) {
		this.systime = systime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Order() {
		super();
	}
	
}
