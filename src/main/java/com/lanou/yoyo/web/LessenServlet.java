package com.lanou.yoyo.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Order;

/**
 * Servlet implementation class LessenServlet
 */
@WebServlet("/index/lessen")
public class LessenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		Order o = (Order)session.getAttribute("order");
		
		int goodsid = Integer.parseInt(request.getParameter("goodid"));
		
		o.lessen(goodsid);
		response.getWriter().print("ok");

		if (session.getAttribute("order") != null) {
			o = (Order) session.getAttribute("order");
		} else {
			o = new Order();
			session.setAttribute("order", o);
		}
		
		
	}

}
