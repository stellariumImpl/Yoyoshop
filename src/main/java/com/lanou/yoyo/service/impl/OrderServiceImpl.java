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

}
