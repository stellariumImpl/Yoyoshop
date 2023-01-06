package com.lanou.yoyo.service.impl;

import java.util.List;

import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.bean.Order;
import com.lanou.yoyo.dao.GoodsDao;
import com.lanou.yoyo.dao.ItemDao;
import com.lanou.yoyo.dao.OrderDao;
import com.lanou.yoyo.dao.impl.GoodsDaoImpl;
import com.lanou.yoyo.dao.impl.ItemDaoImpl;
import com.lanou.yoyo.dao.impl.OrderDaoImpl;
import com.lanou.yoyo.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private OrderDao orderDao = new OrderDaoImpl();
	private ItemDao itemDao = new ItemDaoImpl();
	private GoodsDao goodsDao = new GoodsDaoImpl();

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

	
	/**
	 * 根据用户id获取订单列表
	 * @param userId
	 * @return 返回此用户的所有订单 不含被删除的订单
	 */
	@Override
	public List<Order> getOrderListByUserId(int userId) {
		//此时订单列表不含 购买项详情
		List<Order> orderList = orderDao.selectOrderListByUserId(userId);
		for(int i=0;i<orderList.size();i++) {
			//获取订单
			Order order = orderList.get(i);
			int orderId = order.getId();
			//根据订单id获取购买项列表（不含商品具体信息，只含单价，数量）
			List<Item> itemList = itemDao.selectItemListByOrderId(orderId);
			//遍历itemList
			for(int j=0;j<itemList.size();j++) {
				//获取购买项
				Item item =itemList.get(j);
				//获取购买项id
				int goodsId = item.getGoodId();
				//根据购买项id获取商品
				Goods goods = goodsDao.selectGoodsById(goodsId);
				item.setGoods(goods);
			}
			//把购买项设置给订单
			order.setItemList(itemList);
		}
		
		return orderList;
	}

}
