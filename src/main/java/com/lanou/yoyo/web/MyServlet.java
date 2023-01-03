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
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/index/my")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		User user =(User)session.getAttribute("user");
		
		if(user==null) { // 没有登陆 不能进入个人页面
			response.sendRedirect("/Yoyoshop/index/login");
		}else{
			request.setAttribute("flag", 4);

			List<Type> typeList = typeService.getTypeList();
			request.setAttribute("typeList", typeList);
			
			request.getRequestDispatcher("/index/my.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
