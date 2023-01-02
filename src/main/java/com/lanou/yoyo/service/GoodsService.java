package com.lanou.yoyo.service;

import java.util.List;

import com.lanou.yoyo.bean.Goods;

public interface GoodsService {
	
	/**
	 * 根据排行榜类型获取商品列表
	 * @param topType 排行榜类型 1 今日推荐 2 热销 3 新品
	 * @return 返回榜单对应的商品列表
	 */
	List<Goods> getGoodsListByTopType(int topType);
	
	/**
	 * 根据商品分类的id获取商品列表
	 * @param typeId 商品分类的id
	 * @param page 当前页码
	 * @param size 每页条数
	 * @return 查询到的商品列表
	 */
	List<Goods> getGoodsListByTypeId(int typeId,int page, int size);
	
	/**
	 * 根据商品大类的id获取大类下商品的数量
	 * @param typeId 商品大类的id
	 * @return 返回大类下商品的条数
	 */
	long getGoodsCountByTypeId(int typeId);
	
	/**
	 * 根据排行榜类型及分页信息查询商品列表
	 * @param typeId 排行榜类型 1 今日推荐 2 热销 3 新品
	 * @param page 当前页面
	 * @param size 每页条数
	 * @return
	 */
	List<Goods> getGoodsListByTopType(int topType,int page, int size);
	
	/**
	 * 根据排行榜类型查询商品总量
	 * @param topType 排行榜类型 1 今日 2 ...
	 * @return 返回该榜下的数量
	 */
	long getGoodsCountByTopType(int topType);
	
	/**
	 * 根据关键字获取商品列表
	 * @param keyword 商品关键字 
 	 * @return 商品列表
	 */
	List<Goods> getGoodsListByKeyword(String keyword,int page,int size); 
	
	/**
	 * 根据商品关键字 获取商品数量
	 * @param keyword
	 * @return
	 */
	long getGoodsCountByKeyword(String keyword);
	
	/**
	 * 根据商品id获取商品信息
	 * @param goodsId
	 * @return
	 */
	Goods getGoodsById(int goodsId);

}
