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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/index/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//utf 8
		request.setCharacterEncoding("UTF-8");
		
		//选中商品分类
		request.setAttribute("flag", 2);
		
		//获取商品分类 列表
		List<Type> typeList=typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		//显示搜索结果 几个字
		request.setAttribute("typeid", 4);
		
		String keyword = request.getParameter("name");
//		System.out.println(keyword);
		request.setAttribute("keyword", keyword);
		
		
		
		//获取请求参数
		String name = request.getParameter("name");
		String pageStr = request.getParameter("page");
		String sizeStr = request.getParameter("size");
		
		int page = Integer.parseInt(pageStr);
		int size = Integer.parseInt(sizeStr);
		
		List<Goods> goodsList = goodsService.getGoodsListByKeyword(name,page,size);
		//存
		request.setAttribute("goodList", goodsList);
		
		
		long count = goodsService.getGoodsCountByKeyword(name);
		
		//分页 100->select count(*) as count from goods where status=1 and name like '% "+ keyword + "%'
		String pageInfo = PageUtil.getPageTool(request, count, page, size);
		request.setAttribute("pageTool", pageInfo);
		
		
		request.getRequestDispatcher("/index/goods.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
