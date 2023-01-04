package com.lanou.yoyo.service.impl;

import java.util.List;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.service.CartService;

public class CartServiceImpl implements CartService {

	/**
	 * 把商品加入到购物车中
	 */
	@Override
	public void addGoodsToCart(Goods goods, Cart cart) {
		// 获取要加入购物车的商品id
		Integer goodsId = goods.getId();
		// 获取购物车中购买项列表
		List<Item> itemList = cart.getItemList();

		// 买过否？
		boolean isGoodsInCart = false;

		// 遍历购买项列表
		for (int i = 0; i < itemList.size(); i++) {
			// 获取购买项
			Item item = itemList.get(i);
			// 查看要加入购物车的商品id是否和已经加入购物车的商品id一致
			if (item.getGoodId() == goodsId) { // 如果已经买过了，就不新建购买项，值+1即可
				item.setAmount(item.getAmount() + 1);
				// 购物车中商品总数+1
				cart.setAmount(cart.getAmount() + 1);
				cart.setTotal(item.getPrice() + cart.getTotal());
				isGoodsInCart = true;
				break;
			}
		}

		if (isGoodsInCart == false) {
			Item item = new Item();
			item.setPrice(goods.getPrice());
			// 设置购买项的数量为1
			item.setAmount(1);
			// 设置商品id
			item.setGoodId(goodsId);

			// 设置购买的商品
			item.setGoods(goods);
			// 购买项加入购买项列表
			itemList.add(item);
			// 购物车商品总数量+1
			cart.setAmount(cart.getAmount() + 1);
			// 购物车总金额增加
			cart.setTotal(cart.getTotal() + goods.getPrice());
		}

	}
	
	/**
	 * 扣减订单,true,交给外层servlet删除order
	 * 自己写的 不一定对
	 */
	@Override
	public boolean lessenGoodsFromCartByGoodsId(int goodsId, Cart cart) {
		
		List<Item> itemList = cart.getItemList();
		
		for(int i=0;i<itemList.size();i++) {
			Item item = itemList.get(i);
			if (item.getGoodId() == goodsId) {
				item.setAmount(item.getAmount()-1);	
				cart.setAmount(cart.getAmount() - 1);
				cart.setTotal(cart.getTotal() - item.getPrice());
				if(item.getAmount()==0) {
					itemList.remove(i);
				}
				break;
			}
		}
		
		//购买项数量为0了，删掉order
		if(itemList.size()==0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 根据商品id删东西
	 * @param goods
	 * @param cart
	 * 自己写的不一定对
	 */
	@Override
	public boolean deleteGoodsFromCartByGoodsId(int goodsId, Cart cart) {
		List<Item> itemList = cart.getItemList();
		for(int i=0;i<itemList.size();i++) {
			Item item = itemList.get(i);
			if(item.getGoodId()==goodsId) {
				//把item从itemList中删除掉
				itemList.remove(i);
				//总数量还是得改的
				cart.setAmount(cart.getAmount()-item.getAmount());
				cart.setTotal(cart.getTotal()-item.getAmount()*item.getPrice());
				break;
			}
		}
		if(itemList.size()==0) {
			return true;
		}else {
			return false;
		}
	}

}
