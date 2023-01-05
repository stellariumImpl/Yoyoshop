package com.lanou.yoyo.service;

import com.lanou.yoyo.bean.Order;

public interface OrderService {
	
	/**
	 * 添加订单，并获取订单id
	 * @param order
	 * @return
	 */
	int addOrder(Order order);
	
	/**
	 * 根据订单id 获取订单信息
	 * @param orderId
	 * @return
	 */
	Order getOrderById(int orderId);
	
	/**
	 * 更新订单信息
	 * @param order 要更新的订单
	 * @return 如果更新成功 返回true
	 */
	boolean updateOrder(Order order);

}
