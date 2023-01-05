package com.lanou.yoyo.service;

import com.lanou.yoyo.bean.Order;

public interface OrderService {
	
	/**
	 * 添加订单，并获取订单id
	 * @param order
	 * @return
	 */
	int addOrder(Order order);

}
