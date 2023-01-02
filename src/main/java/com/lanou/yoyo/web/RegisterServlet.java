package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.UserService;
import com.lanou.yoyo.service.impl.TypeServiceImpl;
import com.lanou.yoyo.service.impl.UserServiceImpl;
import com.lanou.yoyo.util.SafeUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/index/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	
	private UserService userService = new UserServiceImpl();
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setAttribute("flag", 5);
		
		
		
		List<Type> typeList = typeService.getTypeList();
		
		request.setAttribute("typeList", typeList);
		
		
		
		
		request.getRequestDispatcher("/index/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 获取请求参数 username,password,name,phone,address
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(name);
//		System.out.println(phone);
//		System.out.println(address);
		
		//select * from user where username ='?'
		
		User user = userService.getUserByUsername(username);
		
		//用户是否存在
		if(user==null) {//不存在存入数据库
			
			//加密
			String pwd = SafeUtil.encode(password);
			
//			System.out.println(pwd);
			
			User newUser = new User();
			
			newUser.setUsername(username);
			newUser.setPassword(pwd);
			newUser.setName(name);
			newUser.setPhone(phone);
			newUser.setAddress(address);
			
			// insert into user(username,password,name,phone,address)VALUES('?','?','?','?','?')
			
			boolean isSuccess = userService.addUser(newUser);
			if(isSuccess) {//添加成功，意为注册成功
				request.setAttribute("msg", "恭喜注册成功，请前往登陆页面登录");
			}else {//添加失败，意味着注册失败
				request.setAttribute("msg", "注册失败，请输入有效注册信息");
			}
			
		}else {//存在返回注册页面
			request.setAttribute("msg", "用户名已存在，请换个用户名");
			
//			request.setAttribute("flag", 5);
//			//获取商品大类列表
//			List<Type> typeList = typeService.getTypeList();
//			request.setAttribute("typeList", typeList);
//			
//			
//			
//			request.getRequestDispatcher("/index/register.jsp").forward(request, response);
		}
		request.setAttribute("flag", 5);
		//获取商品大类列表
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		request.getRequestDispatcher("/index/register.jsp").forward(request, response);
	}
}
