package com.lanou.yoyo.service;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.Goods;

public interface CartService {

	/**
	 * 把商品加入购物车
	 * 
	 * @param goods
	 * @param cart
	 */
	void addGoodsToCart(Goods goods, Cart cart);
	
	/**
	 * 扣减订单
	 * 自己写的 不一定对
	 */
	boolean lessenGoodsFromCartByGoodsId(int goods,Cart cart);
}
