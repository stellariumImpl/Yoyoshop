package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Order;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.service.OrderService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;
import com.lanou.yoyo.service.impl.OrderServiceImpl;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class ToPayServlet
 */
@WebServlet("/index/topay")
public class ToPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService = new OrderServiceImpl();

	private TypeService typeService = new TypeServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/YoyoShop/index/login");
		} else {
			String orderIdStr = request.getParameter("orderid");
			int orderId = Integer.parseInt(orderIdStr);

			Order order = orderService.getOrderById(orderId);
			request.setAttribute("order", order);

			request.getRequestDispatcher("/index/pay.jsp").forward(request, response);
		}

		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
	}

}
