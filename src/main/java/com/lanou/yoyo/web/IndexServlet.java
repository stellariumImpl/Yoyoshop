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
import com.lanou.yoyo.util.DBUtils;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("flag", 1);
		
		List<Type> typeList=typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		
		//今日
		List<Goods> goodsList1=goodsService.getGoodsListByTopType(1);
		request.setAttribute("top1List", goodsList1);
		
		//热销
		List<Goods> goodsList2=goodsService.getGoodsListByTopType(2);
		request.setAttribute("top2List", goodsList2);
		
		//新品
		List<Goods> goodsList3=goodsService.getGoodsListByTopType(3);
		request.setAttribute("top3List", goodsList3);
		
		request.getRequestDispatcher("./index.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
