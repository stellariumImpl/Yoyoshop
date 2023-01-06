package com.lanou.yoyo.dao;

import java.util.List;

import com.lanou.yoyo.bean.Item;

public interface ItemDao {
	
	/**
	 * 把购买项加入到item表中
	 * @param item 购买项
	 * @return 返回影响的条数，添加失败返回0，成功1
	 */
	int insertItem(Item item);
	
	/**
	 * 根据订单id获取购买项列表
	 * @param orderId 订单id
	 * @return 返回购买项列表
	 */
	List<Item> selectItemListByOrderId(int orderId);

}
