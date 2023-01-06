package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lanou.yoyo.bean.Cart;
import com.lanou.yoyo.bean.Goods;
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
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/index/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodsService goodsService = new GoodsServiceImpl();
	private TypeService typeService = new TypeServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	private ItemService itemService = new ItemServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect("YoyoShop/index/login");
		}else {
			Cart cart = (Cart)session.getAttribute("order");
			List<Item> itemList = cart.getItemList();
			for(int i = 0; i < itemList.size(); i++) {
				Item item = itemList.get(i);
				int amount = item.getAmount();
				int goodsId = item.getGoodId();
				Goods goods = goodsService.getGoodsById(goodsId);
				int stock = goods.getStock();
				if(amount > goods.getStock()) {
					request.setAttribute("msg", "下单失败,商品[" + goods.getName() + "]库存不足");
				    List<Type> typeList = typeService.getTypeList();
				    request.setAttribute("typeList", typeList);
					request.getRequestDispatcher("index/cart.jsp").forward(request, response);
				    return;
				}
			}
			Order order = new Order();
			order.setTotal(cart.getTotal());
			order.setAmount(cart.getAmount());
			order.setStatus(1);
			order.setPaytype(0);
			order.setSystime(new Date());
			order.setUserId(cart.getUserId());
			int orderId = orderService.addOrder(order);
			order.setId(orderId);
			for(int i = 0; i< itemList.size(); i++) {
				Item item = itemList.get(i);
				item.setOrderId(orderId);
			}
			itemService.addItemList(itemList);
			session.removeAttribute("order");
			request.setAttribute("order", order);
			List<Type> typeList = typeService.getTypeList();
			request.setAttribute("typeList", typeList);
			request.getRequestDispatcher("/index/pay.jsp").forward(request, response);
		}
		
	}

}
