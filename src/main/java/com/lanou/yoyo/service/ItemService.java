package com.lanou.yoyo.service;

import java.util.List;

import com.lanou.yoyo.bean.Item;

public interface ItemService {
	/**
	 * 把购买项添加到item列表中
	 * @author FYGOD
	 *
	 */
	void addItemList(List<Item> itemList);
	
	/**
	 * 根据订单id获取购买项列表
	 * @param orderId 订单id
	 * @return 返回购买项列表
	 */
	List<Item> getItemListByOrderId(int orderId);
}
