package com.lanou.yoyo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.util.PageUtil;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.service.TypeService;
import com.lanou.yoyo.service.impl.GoodsServiceImpl;
import com.lanou.yoyo.service.impl.TypeServiceImpl;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/index/goods")
public class GoodsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TypeService typeService = new TypeServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//选中商品分类
		request.setAttribute("flag", 2);
		
		//获取商品分类 列表
		List<Type> typeList=typeService.getTypeList();
		request.setAttribute("typeList", typeList);
		
		
		//获取请求参数
		String typeIdStr = request.getParameter("typeid");
		String pageStr = request.getParameter("page");
		String sizeStr = request.getParameter("size");
		
		//把字符串转换为整型
		int typeId = Integer.parseInt(typeIdStr);
		int page = Integer.parseInt(pageStr);
		int size = Integer.parseInt(sizeStr);
		
		
		//根据商品分类id获取分类信息
		Type type= typeService.getTypeById(typeId);
		//把type存入request，供商品分类页面使用
		request.setAttribute("type", type);
		
		//根据商品大类的id，查询商品列表
		List<Goods> goodsList = goodsService.getGoodsListByTypeId(typeId,page,size);
		//存入request，供页面使用
		request.setAttribute("goodList", goodsList);
		
		//根据商品大类id 获取这个商品分类下商品的总条数
		long count=goodsService.getGoodsCountByTypeId(typeId);
		
		//分页信息
		String pageInfo = PageUtil.getPageTool(request, count, page, size);
		//分页信息存入request
		request.setAttribute("pageTool", pageInfo);
		
		// 请转 goods页面
		request.getRequestDispatcher("/index/goods.jsp").forward(request, response);
	}

}
