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
import com.lanou.yoyo.util.SafeUtil;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/index/my")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TypeService typeService = new TypeServiceImpl();

	private UserService userService = new UserServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) { // 没有登陆 不能进入个人页面
			response.sendRedirect("/YoyoShop/index/login");
		} else {

			request.setAttribute("flag", 4);
			List<Type> typeList = typeService.getTypeList();
			request.setAttribute("typeList", typeList);

			request.getRequestDispatcher("/index/my.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		request.setCharacterEncoding("UTF-8");

		String userIdStr = request.getParameter("id");
		String typeStr = request.getParameter("type");

		int userId = Integer.parseInt(userIdStr);
		int type = Integer.parseInt(typeStr); // 1 修改个人信息 2 修改密码

		User user = userService.getUserById(userId);

		if (type == 1) {
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");

			// 修改user姓名...
			user.setName(name);
			user.setPhone(phone);
			user.setAddress(address);

			boolean isSuccess = userService.updateUser(user);
			if (isSuccess) {// 更新成功
				request.setAttribute("msg", "个人信息修改成功！");

				// 更新session中的user信息（默认显示）
				HttpSession session = request.getSession();
				session.setAttribute("user", user); // 替换拿最新的user作替换

			} else {// 如果更新失败
				request.setAttribute("msg", "个人信息更新失败！用户名或地址过长？");
			}
		} else {
			String password = request.getParameter("password");
			String passwordNew = request.getParameter("passwordNew");

			// 更具id查询用户
			String findedUserPwd = user.getPassword();

			String passwordOld = SafeUtil.encode(password);

			if (findedUserPwd.equals(passwordOld)) {
				// 加密新密码
				String passwordNewEncry = SafeUtil.encode(passwordNew);
				user.setPassword(passwordNewEncry);
				userService.updateUser(user);// 一定成功 不用再做判断了

				HttpSession session = request.getSession();
				session.setAttribute("user", user); // 替换拿最新的user作替换
				request.setAttribute("msg", "恭喜修改成功！");

			} else {
				request.setAttribute("msg", "原密码不对！请再次输入");
			}
		}

		request.setAttribute("flag", 4);
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		request.getRequestDispatcher("/index/my.jsp").forward(request, response);
	}

}
