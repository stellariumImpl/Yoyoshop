package com.lanou.yoyo.service.impl;

import com.lanou.yoyo.bean.Order;
import com.lanou.yoyo.dao.OrderDao;
import com.lanou.yoyo.dao.impl.OrderDaoImpl;
import com.lanou.yoyo.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();

	/**
	 * 添加订单，并获取订单id
	 * @param order
	 * @return
	 */
	@Override
	public int addOrder(Order order) {
		int orderId = orderDao.insertOrder(order);
		return orderId;
	}
	
	/**
	 * 根据订单id 获取订单信息
	 * @param orderId
	 * @return
	 */
	@Override
	public Order getOrderById(int orderId) {
		Order order = orderDao.selectOrderById(orderId);
		return order;
	}

	/**
	 * 更新订单信息
	 * @param order 要更新的订单
	 * @return 如果更新成功 返回true
	 */
	@Override
	public boolean updateOrder(Order order) {
		int row = orderDao.updateOrder(order);
		if(row==1) {
			return true;
		}else{
			return false;
		}
		
	}

}
