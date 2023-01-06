package com.lanou.yoyo.dao.impl;

import java.util.List;

import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.dao.ItemDao;
import com.lanou.yoyo.util.DBUtils;

public class ItemDaoImpl implements ItemDao {

	/**
	 * 把购买项加入到item表中
	 * @param item 购买项
	 * @return 返回影响的条数，添加失败返回0，成功1
	 */
	@Override
	public int insertItem(Item item) {
		String sql = "insert into item(price,amount,good_id,order_id)values(?,?,?,?)";
		int row = DBUtils.update(sql, item.getPrice(),item.getAmount(),item.getGoodId(),item.getOrderId());
		return row;
	}

	/**
	 * 根据订单id获取购买项列表
	 * @param orderId 订单id
	 * @return 返回购买项列表
	 */
	@Override
	public List<Item> selectItemListByOrderId(int orderId) {
		String sql = "select * from item where order_id=?";
		List<Item> itemList =  DBUtils.query(sql, Item.class, orderId);
		return itemList;
	}

}
