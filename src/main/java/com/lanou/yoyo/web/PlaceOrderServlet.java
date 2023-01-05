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
import com.lanou.yoyo.service.OrderService;
import com.lanou.yoyo.service.ItemService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;
import com.lanou.yoyo.service.impl.OrderServiceImpl;
import com.lanou.yoyo.service.impl.TypeServiceImpl;


/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/index/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TypeService typeService = new TypeServiceImpl();
	
	private GoodsService goodsService = new GoodsServiceImpl();
	
	private OrderService orderService = new OrderServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user==null) {
			request.setAttribute("msg", "请登录后再提交订单！");
			response.sendRedirect("/fygod_shop/index/login");
		}else {
			Cart cart =(Cart)session.getAttribute("order");
			List<Item> itemList = cart.getItemList();
			for(int i=0;i<itemList.size();i++) {
				//获取购买项
				Item item = itemList.get(i);
				int amount = item.getAmount();
				int goodsId = item.getGoodId();
				Goods goods = goodsService.getGoodsById(goodsId);
				int stock = goods.getStock();
				if(amount>stock) {
					request.setAttribute("msg", "下单失败，商品 [" + goods.getName() + "] 库存不足，库存余量为 " + stock + " 件");
					
					List<Type> typeList1 = typeService.getTypeList();
					request.setAttribute("typeList", typeList1);
					request.getRequestDispatcher("/index/cart.jsp").forward(request, response);
					return;
					
				}
			}
			
			//库存充足
			Order order = new Order();
			order.setTotal(cart.getTotal());
			order.setAmount(cart.getAmount());
			order.setStatus(1);
			order.setPaytype(0);
			order.setSystime(new Date());
			order.setUserId(cart.getUserId());
			
			//添加订单
			int orderId = orderService.addOrder(order);
			
			System.out.println("订单的id是: " + orderId);
      //to be continued
			//把订单id设置给订单对象
			order.setId(orderId);
			//把订单中的商品itemList加入到数据库的item表中
			for(int i=0;i<itemList.size();i++) {
				Item item = itemList.get(i);
				//给购买项添加订单id
				item.setOrderId(orderId);//此时的购买项已经有了订单id
			}
			//把订单中的商品itemList
			itemService.addItemList(itemList);
			
			//清空购物车
			session.removeAttribute("order");
			
			session.setAttribute("order", order);
			request.getRequestDispatcher("/index/pay.jsp").forward(request, response);
		}
	}

}
