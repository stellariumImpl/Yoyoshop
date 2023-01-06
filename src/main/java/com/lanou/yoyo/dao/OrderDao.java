package com.lanou.yoyo.dao;

import java.util.List;

import com.lanou.yoyo.bean.Order;

public interface OrderDao {
	
	/**
	 * 添加订单，并获取订单id
	 * @param order
	 * @return
	 */
	int insertOrder(Order order);
	
	/**
	 * 根据订单id查询订单
	 * @param orderId 订单id
	 * @return 返回订单
	 */
	Order selectOrderById(int orderId);
	
	/**
	 * 更新订单信息
	 * @param order 要更新的订单
	 * @return 如果更新成功 返回true
	 */
	int updateOrder(Order order);
	
	/**
	 * 根据用户id查询订单列表
	 * @param userId
	 * @return 返回此用户的所有订单 不含被删除的订单
	 */
	List<Order> selectOrderListByUserId(int userId);

}
