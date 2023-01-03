package com.lanou.yoyo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/index/addToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsService goodsService = new GoodsServiceImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order o = null;
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("order")!=null) {
			o = (Order)session.getAttribute("order");
		}else {
			o = new Order();
			session.setAttribute("order", o);
		}
		
		User user = (User) session.getAttribute("user");
		
		if(user==null) {
			response.getWriter().append("login");
		}else {
			String goodsIdStr = request.getParameter("goodid");
			int goodsId = Integer.parseInt(goodsIdStr);
			
			Goods goods = goodsService.getGoodsById(goodsId);
			
			Integer stock = goods.getStock();
			
			if (stock <= 0) {// 说明库存不足，返回empty
				response.getWriter().append("empty");
			} else {// 库存充足，可以加入购物车
					// 先把商品加到购物车再返回ok
				o.addGoods(goods);
				System.out.println(o.getName());
				response.getWriter().append("ok");
			}
		}
		
		
// 		HttpSession session = request.getSession();
// 		// 从session中获取user
// 		User user = (User) session.getAttribute("user");
// 		if (user == null) {
// 			response.getWriter().append("login");
// 		} else {// 已登录 就可以购物了
// 				// 获取goodid参数
// 			String goodsIdStr = request.getParameter("goodid");
// 			int goodsId = Integer.parseInt(goodsIdStr);

// 			// 根据商品id获取商品信息
// 			Goods goods = goodsService.getGoodsById(goodsId);
// 			// 获取库存
// 			Integer stock = goods.getStock();
// 			if (stock <= 0) {// 说明库存不足，返回empty
// 				response.getWriter().append("empty");
// 			} else {// 库存充足，可以加入购物车
// 					// 先把商品加到购物车再返回ok
// 					// 未完待续
// 				response.getWriter().append("ok");
// 			}

// 		}
	}

}
