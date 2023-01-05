package com.lanou.yoyo.dao;

import com.lanou.yoyo.bean.Item;

public interface ItemDao {
	
	/**
	 * 把购买项加入到item表中
	 * @param item 购买项
	 * @return 返回影响的条数，添加失败返回0，成功1
	 */
	int insertItem(Item item);

}
