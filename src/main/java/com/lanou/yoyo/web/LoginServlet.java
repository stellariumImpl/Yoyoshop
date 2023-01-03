package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.RepaintManager;

import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.UserService;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;
import com.lanou.yoyo.service.impl.TypeServiceImpl;
import com.lanou.yoyo.service.impl.UserServiceImpl;
import com.lanou.yoyo.util.SafeUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/index/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	
	private UserService userService = new UserServiceImpl();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("flag", 6);
		
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);

		request.getRequestDispatcher("/index/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String pwd = SafeUtil.encode(password);
		
//		System.out.println(username);
//		System.out.println(pwd);
		
		//select * from user where status = 1 and username='?' and password = '?'
		
		// 根据用户名和密码查找
		User user = userService.getUserByUsernameAndPassword(username, pwd);
		
		if(user == null) {
			request.setAttribute("msg", "用户名或者密码错误");
			request.setAttribute("flag", 6);
			List<Type> typeList = typeService.getTypeList();
			request.setAttribute("typeList", typeList);
			
			//返回登陆页面
			request.getRequestDispatcher("/index/login.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			
//			request.getRequestDispatcher("/index/index").forward(request, response);
			//使用重定向技术，请求/index/index
			response.sendRedirect("/YoyoShop/index/index");
		}
		

	}


}
