package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.CartService;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.service.impl.CartServiceImpl;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/index/addToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsService goodsService = new GoodsServiceImpl();
	
	private CartService cartService = new CartServiceImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 从session中获取user
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.getWriter().append("login");
		} else {// 已登录 就可以购物了
				// 获取goodid参数
			String goodsIdStr = request.getParameter("goodid");
			int goodsId = Integer.parseInt(goodsIdStr);

			// 根据商品id获取商品信息
			Goods goods = goodsService.getGoodsById(goodsId);
			// 获取库存
			Integer stock = goods.getStock();
			if (stock <= 0) {// 说明库存不足，返回empty
				response.getWriter().append("empty");
			} else {// 库存充足，可以加入购物车
					// 先把商品加到购物车再返回ok
				//2023.1.4
				Cart cart = (Cart) session.getAttribute("order");
				System.out.println(cart);
				// 如果为首次从session中获取购物车信息order，获取不到，cart为null
				if (cart == null) {// 首次从session中获取order，往session中存一个空的购物车，不含键值对
					cart = new Cart();
					cart.setTotal(0.0);
					// 商品总数为0，用户id
					cart.setAmount(0);
					cart.setUserId(user.getId());
					// 创建一个购买项 列表
					List<Item> itemList = new ArrayList<>();
					// 把空的购买项列表设置到购物车中，表示一种商品也没买，以后买的时候，再往列表里加
					cart.setItemList(itemList);
					// 把购物车存入session中，存入以后，下次就能从session获取购物车信息了
					session.setAttribute("order", cart);
				}

				cartService.addGoodsToCart(goods, cart);

				response.getWriter().append("ok");
			}

		}
	}

}
