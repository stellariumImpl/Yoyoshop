package com.lanou.yoyo.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.dao.OrderDao;
import com.lanou.yoyo.util.DBUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	public void addOrder(Cart cart) {
		Connection con = null;
		try {
			con = DBUtils.getConnection();
			con.setAutoCommit(false);
			
			orderDao.insertOrder(con, cart);
			
			int id =orderDao.getLastInsertId(con);
			
			//订单id
			cart.setId(id);
			//订单项
			for(Item item:cart.getItemList()) {
				orderDao.insertOrderItem(con, item);
			}
			
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(con!=null)
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
}
