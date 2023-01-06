package com.lanou.yoyo.service.impl;

import java.util.List;

import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.dao.ItemDao;
import com.lanou.yoyo.dao.impl.ItemDaoImpl;
import com.lanou.yoyo.service.ItemService;

public class ItemServiceImpl implements ItemService {
	
	private ItemDao itemDao =new ItemDaoImpl();
	
	/**
	 * 把购买项添加到item列表中
	 * @author FYGOD
	 *
	 */
	@Override
	public void addItemList(List<Item> itemList) {
		//遍历itemList
		for(int i=0;i<itemList.size();i++) {
			//获取购买项
			Item item = itemList.get(i);
			//把购买项加入到数据库中
			itemDao.insertItem(item);
			
		}

	}

	@Override
	public List<Item> getItemListByOrderId(int orderId) {
		//to be continued
		
		return null;
	}

}
