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
	private ItemService itemService = new ItemServiceImpl();
	
	//减库存
	private GoodsService goodsService = new GoodsServiceImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null) {
			response.sendRedirect("/fygod_shop/index/login");
		}else {
			request.setCharacterEncoding("UTF-8");
			//获取请求参数id,paytype,name,phone,address
			String orderIdStr = request.getParameter("id");
			String paytypeStr = request.getParameter("paytype");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			
			int orderId = Integer.parseInt(orderIdStr);
			int paytype = Integer.parseInt(paytypeStr);
			
			Order order = orderService.getOrderById(orderId);
			/**
			 * 实际情况还需要
			 */
			//设置订单状态为2 已付款
			order.setStatus(2);
			
			//设置支付方式
			order.setPaytype(paytype);
			//个人信息
			order.setName(name);
			order.setPhone(phone);
			order.setAddress(address);
			
			//更新订单信息
			boolean isSuccess = orderService.updateOrder(order);
			
			if(isSuccess) {//如果订单更新成功
				//根据订单id获取购买项列表
				List<Item> itemList = itemService.getItemListByOrderId(orderId);
				//
				goodsService.updateGoodsStockForItemList(itemList);
				
				
				//设置成功的提示信息
				request.setAttribute("msg", "支付成功！");
			}else {//订单更新失败
				request.setAttribute("msg", "支付失败了，请检查收货人以及收货地址");
			}
			List<Type> typeList=typeService.getTypeList();
			request.setAttribute("typeList", typeList);
			
			request.getRequestDispatcher("/index/payok.jsp").forward(request, response);
		}
	}

}
