package com.lanou.yoyo.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

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

}
