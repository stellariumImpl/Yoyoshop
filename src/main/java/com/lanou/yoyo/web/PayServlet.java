package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Item;
import com.lanou.yoyo.bean.Order;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.service.ItemService;
import com.lanou.yoyo.service.OrderService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;
import com.lanou.yoyo.service.impl.ItemServiceImpl;
import com.lanou.yoyo.service.impl.OrderServiceImpl;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class PayServlet
 */
@WebServlet("/index/pay")
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	private TypeService typeService = new TypeServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();
	private ItemService itemService = new ItemServiceImpl();
 	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect("/YoyoShop/index/login");
		}else {
			request.setCharacterEncoding("UTF-8");
			String orderIdStr = request.getParameter("id");
			String paytypeStr = request.getParameter("paytype");
			String name= request.getParameter("name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			int orderId = Integer.parseInt(orderIdStr);
			int paytype = Integer.parseInt(paytypeStr);
			Order order = orderService.getOrderById(orderId);
			order.setStatus(2);
			order.setPaytype(paytype);
			order.setPhone(phone);
			order.setAddress(address);
			boolean isSuccess = orderService.updateOrder(order);
			if(isSuccess) {
				List<Item> itemList = itemService.getItemListByOrderId(orderId);
				goodsService.updateGoodsStockForItemList(itemList);
				
				request.setAttribute("msg","恭喜你支付成功");
				
			}else {
				request.setAttribute("msg", "支付失败,收货人或者地址过长");
				
			}
			List<Type> typeList = typeService.getTypeList();
			request.setAttribute("typeList", typeList);
			request.getRequestDispatcher("/index/payok.jsp").forward(request, response);
		}
	}

}
