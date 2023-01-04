package com.lanou.yoyo.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.Item;

public class OrderDao {
	public void insertOrder(Connection con, Cart cart) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "insert into `order`(total,amount,status,paytype,name,phone,address,systime,user_id)values(?,?,?,?,?,?,?,?,?)";
		r.update(con, sql, cart.getTotal(), cart.getAmount(), cart.getStatus(), cart.getPaytype(), cart.getName(),
				cart.getPhone(), cart.getAddress(), cart.getSystime(), cart.getUser().getId());
	}

	public void insertOrderItem(Connection con, Item item) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "insert into item(price,amount,good_id,order_id)values(?,?,?,?)";
		r.update(con, sql, item.getPrice(), item.getAmount(), item.getGoodId(), item.getCart().getId());
	}

	public int getLastInsertId(Connection con) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "select last_insert_id()";
		BigInteger bi = r.query(con, sql, new ScalarHandler<BigInteger>());
		return Integer.parseInt(bi.toString());
	}
}
