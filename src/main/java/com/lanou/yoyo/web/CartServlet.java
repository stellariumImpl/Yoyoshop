package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.UserService;
import com.lanou.yoyo.service.impl.TypeServiceImpl;
import com.lanou.yoyo.service.impl.UserServiceImpl;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/index/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	
	private UserService userService = new UserServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		HttpSession session = request.getSession();
		// 从session中获取user
		User user = (User) session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("/fygod_shop/index/login");
		}else {
			
			
			request.getRequestDispatcher("/index/cart.jsp").forward(request, response);
		}

	}

}
