package com.lanou.yoyo.service;

import java.util.List;

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
	
	/**
	 * 根据用户id获取订单列表
	 * @param userId
	 * @return 返回此用户的所有订单 不含被删除的订单
	 */
	List<Order> getOrderListByUserId(int userId);

}
