package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.impl.UserServiceImpl;
import com.lanou.yoyo.util.SafeUtil;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.UserService;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class ForgetPasswordServlet
 */
@WebServlet("/index/forget")
public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();

	private UserService userService = new UserServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 进入忘记密码页面
		
		request.setAttribute("flag", 6);
		
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		request.getRequestDispatcher("/index/forget.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 进入修改密码页面
		
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username"); 
		String phone = request.getParameter("phone");
		
//		System.out.println(username);
//		System.out.println(phone);
		
		User user = userService.getUserByUsername(username);
		
		if(user==null) {
			request.setAttribute("msg", "重置密码失败，用户不存在");
		}else {
			//查询到的用户手机号，数据库中查到的手机号
			String findedPhone =user.getPhone();
			
			if(findedPhone.equals(phone)==true) { //如果手机号一致，允许重置密码
				
				String pwd = SafeUtil.encode("123456");
				//修改被查询到的用户密码
				user.setPassword(pwd);
				
				//update user set password = '123456' where id 
				
				boolean isSuccess = userService.updateUser(user);
				if(isSuccess==true) { //密码修改成功 ，修改成了默认值
					request.setAttribute("msg", "密码重置成功，重置后的密码是123456");

				}
			}else {
				//提示错误信息
				request.setAttribute("msg", "这个手机号与用户名不匹配！");
			}
		}
		
		//跳转到找回页面
		request.setAttribute("flag", 6);
		
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		request.getRequestDispatcher("/index/forget.jsp").forward(request, response);
		
	}
}
