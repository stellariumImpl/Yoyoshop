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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/index/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CartService cartService = new CartServiceImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.getWriter().append("login");
		} else {
			Cart cart = (Cart) session.getAttribute("order");

			String goodsIdStr = request.getParameter("goodid");
			int goodsId = Integer.parseInt(goodsIdStr);

			boolean isEmpty = cartService.deleteGoodsFromCartByGoodsId(goodsId, cart);
			if (isEmpty) {
				session.removeAttribute("order");
			}
			response.getWriter().append("ok");
		}
	}

}
