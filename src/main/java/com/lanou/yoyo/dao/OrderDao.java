package com.lanou.yoyo.dao;

import com.lanou.yoyo.bean.Order;

public interface OrderDao {
	
	/**
	 * 添加订单，并获取订单id
	 * @param order
	 * @return
	 */
	int insertOrder(Order order);

}
