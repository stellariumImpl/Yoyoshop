package com.lanou.yoyo.dao;

import java.util.List;

import com.lanou.yoyo.bean.Goods;

/**
 * 数据库goods表的接口，声明我们要对goods表做哪些操作
 * @author FYGOD
 *
 */

public interface GoodsDao {
	
	/**
	 * 根据榜单的类型 获取商品列表
	 * @param topType 榜单类型 1 今日 2 3
	 * @return 返回查询到的商品列表
	 */
	List<Goods> selectGoodsListByTopType(int topType);
	/**
	 * 根据商品分类id 获取商品列表
	 * @param typeId 商品分类id
	 * @param page 当前页码
	 * @param size 每页条数
	 * @return 返回查询到的商品列表
	 */
	List<Goods> selectGoodsListByTypeId(int typeId,int page,int size);
	
	/**
	 * 根据商品大类的id，查询此商品大类下商品的数量
	 * @param typeId 商品大类的id
	 * @return 返回此商品大类下商品的数量
	 */
	long selectGoodsCountByTypeId(int typeId);
	
	/**
	 * 根据排行榜类型及分页信息查询商品列表
	 * @param typeId 排行榜类型 1 今日推荐 2 热销 3 新品
	 * @param page 当前页面
	 * @param size 每页条数
	 * @return
	 */
	List<Goods> selectGoodsListByTopType(int topType,int page,int size);
	
	/**
	 * 根据排行榜类型查询商品总量
	 * @param topType 排行榜类型 1 今日 2 ...
	 * @return 返回该榜下的数量
	 */
	long selectGoodsCountByTopType(int topType);
	/**
	 * 根据关键字查询列表
	 * @param keyword
	 * @return 
	 */
	List<Goods> selectGoodsListByKeyword(String keyword,int page,int size);
	
	/**
	 * 关键字查数量
	 * @param keyword
	 * @return
	 */
	long selectGoodsCountByKeyword(String keyword);
	
	/**
	 * 根据商品id获取商品信息
	 * @param goodsId
	 * @return
	 */
	Goods selectGoodsById(int goodsId);
}
