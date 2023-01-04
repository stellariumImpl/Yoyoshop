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

}
