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
import com.lanou.yoyo.util.PageUtil;

/**
 * Servlet implementation class TopServlet
 */
@WebServlet("/index/top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取热销页面请求参数 typeid page size
		String typeIdStr = request.getParameter("typeid");
		String pageStr = request.getParameter("page");
		String sizeStr = request.getParameter("size");
		
//		System.out.println(typeIdStr);
//		System.out.println(pageStr);
//		System.out.println(sizeStr);
		
		int typeId = Integer.parseInt(typeIdStr); 
		int page = Integer.parseInt(pageStr); 
		int size = Integer.parseInt(sizeStr); 
		
		
		request.setAttribute("flag", typeId+5);
		
		//获取商品分类列表
		List<Type> typeList =  typeService.getTypeList();
		//存入request
		request.setAttribute("typeList", typeList);
		
		//设置typeid 热销。。。
		request.setAttribute("typeid", typeId); // 特么的怎么能写死
		
		//热销页面
		List<Goods> goodsList = goodsService.getGoodsListByTopType(typeId,page,size);
		request.setAttribute("goodList", goodsList);
		
		//获取条数
		long count = goodsService.getGoodsCountByTopType(typeId);
		
		//设置分页信息 100 -> SELECT count(*) AS count from top join goods on top.good_id = goods.id where type=2 LIMIT 0,4 
		String pageInfo = PageUtil.getPageTool(request, count, page, size);
		request.setAttribute("pageTool", pageInfo);
		
		request.getRequestDispatcher("/index/goods.jsp").forward(request, response);
	}

}
