package com.lanou.yoyo.service;

import java.util.List;

import com.lanou.yoyo.bean.Item;

/**
 * 把购买项添加到item列表中
 * @author FYGOD
 *
 */
public interface ItemService {
	void addItemList(List<Item> itemList);
}
