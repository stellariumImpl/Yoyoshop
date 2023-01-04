package com.lanou.yoyo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.CartService;
import com.lanou.yoyo.service.impl.CartServiceImpl;

/**
 * Servlet implementation class LessenServlet
 */
@WebServlet("/index/lessen")
public class LessenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = new CartServiceImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		if (user == null) {// 长时间不做操作，session 失效了
			response.getWriter().append("login");
		} else {
			Cart cart = (Cart) session.getAttribute("order");

			String goodsIdStr = request.getParameter("goodid");
			int goodsId = Integer.parseInt(goodsIdStr);

			boolean isEmpty = cartService.lessenGoodsFromCartByGoodsId(goodsId, cart);

			if (isEmpty) {
				session.removeAttribute("order");
			}

			response.getWriter().append("ok");
		}

	}

}
