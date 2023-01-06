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
import com.lanou.yoyo.service.OrderService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.OrderServiceImpl;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/index/order")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	
	private OrderService orderService = new OrderServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user =(User)session.getAttribute("user");
		
		if(user==null) {
			response.sendRedirect("/YoyoShop/index/login");
		}else {
			int userId = user.getId();
			//根据用户id获取订单列表
			List<Order> orderList = orderService.getOrderListByUserId(userId);
			if(orderList.size()>0) {
				request.setAttribute("orderList", orderList);
			}
			
			
			
			request.setAttribute("flag", 3);
			List<Type> typeList=typeService.getTypeList();
			request.setAttribute("typeList", typeList);
			request.getRequestDispatcher("/index/order.jsp").forward(request, response);
		}
	}

	

}
