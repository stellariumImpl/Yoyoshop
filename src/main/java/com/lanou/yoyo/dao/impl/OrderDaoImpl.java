package com.lanou.yoyo.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lanou.yoyo.bean.Order;
import com.lanou.yoyo.dao.OrderDao;
import com.lanou.yoyo.util.DBUtils;


public class OrderDaoImpl implements OrderDao {

	/**
	 * 添加订单，并获取订单id
	 * @param order
	 * @return
	 */
	@Override
	public int insertOrder(Order order) {
		
		Date date = order.getSystime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm::ss");
		
		String systime = sdf.format(date);
		
		String sql="insert into `order`(total,amount,status,paytype,systime,user_id)values(?,?,?,?,?,?)";
		int orderId = DBUtils.insertAndGetGeneratedKey(sql,order.getTotal(),order.getAmount(),order.getStatus(),order.getPaytype(),systime,order.getUserId());
		
		return orderId;
	}
	
	/**
	 * 根据订单id查询订单
	 * @param orderId 订单id
	 * @return 返回订单
	 */
	@Override
	public Order selectOrderById(int orderId) {
		String sql="select * from `order` where status!=0 and id=?";
		Order order= DBUtils.queryOne(sql, Order.class,orderId);
		return order;
	}

	/**
	 * 更新订单信息
	 * @param order 要更新的订单
	 * @return 如果更新成功 返回true
	 */
	@Override
	public int updateOrder(Order order) {
		String sql = "update `order` set status= ?, paytype= ? , name= ? , phone=? , address= ? where id=? and status!=0";
		int row = DBUtils.update(sql, order.getStatus(),order.getPaytype(),order.getName(),order.getPhone(),order.getAddress(),order.getId());
		return row;
	}

	/**
	 * 根据用户id查询订单列表
	 * @param userId
	 * @return 返回此用户的所有订单 不含被删除的订单
	 */
	@Override
	public List<Order> selectOrderListByUserId(int userId) {
		String sql = "select * from `order` where status!=0 and user_id = ?";
		List<Order> orderList = DBUtils.query(sql, Order.class,userId);
		return orderList;
	}

}
