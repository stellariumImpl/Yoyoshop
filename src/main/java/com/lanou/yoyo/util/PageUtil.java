package com.lanou.yoyo.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具类
 */
public class PageUtil {
	
	/**
	 * 获取分页代码
	 * @param total 总记录数
	 * @param page 当前页面
	 * @param size 每页数量
	 * @return
	 */
	public static String getPageTool(HttpServletRequest request, long total, int page, int size){
		long pages = total % size ==0 ? total/size : total /size + 1;
		pages = pages==0 ? 1 : pages;
		String url = request.getRequestURL().toString();
		StringBuilder queryString = new StringBuilder();
		Enumeration<String> enumeration = request.getParameterNames();
		try { // 拼装请求参数
			while (enumeration.hasMoreElements()) {
				String element = (String) enumeration.nextElement();
				if(!element.contains("page")) { // 跳过page参数
					queryString.append("&").append(element).append("=").append(java.net.URLEncoder.encode(request.getParameter(element),"UTF-8"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 拼装分页代码
		StringBuilder buf = new StringBuilder();
		buf.append("<div style='text-align:center;'>\n");
		if (page <= 1) {
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' disabled >首页</a>\n");
		}else{
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' href='").append(url).append("?page=").append(1).append(queryString).append("'>首页</a>\n");
		}
		if (page <= 1) {
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' disabled >上一页</a>\n");
		}else {
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' href='").append(url).append("?page=").append(page>1 ? page-1 : 1).append(queryString).append("'>上一页</a>\n");
		}
		buf.append("<h2 style='display:inline;'>[当前第").append(page).append("页，共").append(pages).append("页]</h2>\n");
		buf.append("<h2 style='display:inline;'>[共").append(total).append("条数据，每页").append(size).append("条]</h2>\n");
		if (page >= pages) {
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' disabled >下一页</a>\n");
		}else {
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' href='").append(url).append("?page=").append(page<pages ? page+1 : pages).append(queryString).append("'>下一页</a>\n");
		}
		if (page >= pages) {
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' disabled >尾页</a>\n");
		}else {
			buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' href='").append(url).append("?page=").append(pages).append(queryString).append("'>尾页</a>\n");
		}
		buf.append("<input  type='number' min='1'  class='layui-btn layui-btn-sm layui-btn-primary' style='display:inline;width:60px;' value='1'/>");
		buf.append("<a class='layui-btn layui-btn-sm layui-btn-primary' href='javascript:void(0);' onclick='location.href=\"").append(url).append("?page=").append("\"+(this.previousSibling.value)+\"").append(queryString).append("\"'>GO</a>\n");
		buf.append("</div>\n");
		return buf.toString();
	}

}
