package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class GoodsDetailServlet
 */
@WebServlet("/index/detail")
public class GoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Type> typeList = typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		
		String goodsIdStr = request.getParameter("goodid");
		int goodsId = Integer.parseInt(goodsIdStr);
		
		//根据商品id获取商品信息
		//select * from goods where id = ? and status = 1 
		Goods goods = goodsService.getGoodsById(goodsId);
		request.setAttribute("good", goods);
		
		request.getRequestDispatcher("/index/detail.jsp").forward(request, response);
	}

}
