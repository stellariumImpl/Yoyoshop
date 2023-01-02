package com.lanou.yoyo.service.impl;

import java.util.List;

import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.dao.GoodsDao;
import com.lanou.yoyo.dao.TypeDao;
import com.lanou.yoyo.dao.impl.GoodsDaoImpl;
import com.lanou.yoyo.dao.impl.TypeDaoImpl;
import com.lanou.yoyo.service.GoodsService;
import com.lanou.yoyo.util.DBUtils;

public class GoodsServiceImpl implements GoodsService {
	
	private GoodsDao goodsDao = new GoodsDaoImpl();
	private TypeDao typeDao=new TypeDaoImpl();
	/**
	 * 根据排行榜类型获取商品列表
	 * @param topType 排行榜类型 1 今日推荐 2 热销 3 新品
	 * @return 返回榜单对应的商品列表
	 */
	@Override
	public List<Goods> getGoodsListByTopType(int topType) {
		//获取商品列表，但是此时的商品没有商品大类的信息，只有商品大类的id（typeId）
		List<Goods> goodsList= goodsDao.selectGoodsListByTopType(topType);
		//遍历goodsList，找到每一个商品，给商品设置商品大类的信息
		
		for(int i=0;i<goodsList.size();i++) {
			//获取商品
			Goods goods= goodsList.get(i);
			//获取商品的typeId
			Integer typeId=goods.getTypeId();
			//根据typeId获取，商品大类信息
			Type type = typeDao.selectTypeById(typeId);
			//把商品大类信息设置给商品
			goods.setType(type);
		}
		return goodsList;
	}
	
	/**
	 * 根据商品分类的id获取商品列表
	 * @param typeId 商品分类的id
	 * @return 查询到的商品列表
	 */
	@Override
	public List<Goods> getGoodsListByTypeId(int typeId,int page,int size) {
		//此时的商品列表只有商品分类的id，没有商品分类的具体信息
		List<Goods> goodsList = goodsDao.selectGoodsListByTypeId(typeId,page,size);
		return goodsList;
	}
	
	/**
	 * 根据商品大类的id获取大类下商品的数量
	 * @param typeId 商品大类的id
	 * @return 返回大类下商品的条数
	 */
	@Override
	public long getGoodsCountByTypeId(int typeId) {
		// TODO 查询数据库
		
		long count = goodsDao.selectGoodsCountByTypeId(typeId);
		return count;
	}

	/**
	 * 根据排行榜类型及分页信息查询商品列表
	 * @param typeId 排行榜类型 1 今日推荐 2 热销 3 新品
	 * @param page 当前页面
	 * @param size 每页条数
	 * @return
	 */
	@Override
	public List<Goods> getGoodsListByTopType(int topType, int page, int size) {
		// TODO 查询数据库 ， 不查 让 GoodsDao查 , 他也不查 让 他的impl查
		List<Goods> goodsList = goodsDao.selectGoodsListByTopType(topType,page,size);
		return goodsList;
	}

	/**
	 * 根据排行榜类型查询商品总量  ,  service层只是使用dao层结果
	 * @param topType 排行榜类型 1 今日 2 ...
	 * @return 返回该榜下的数量
	 */
	@Override
	public long getGoodsCountByTopType(int topType) {
		long count = goodsDao.selectGoodsCountByTopType(topType);
		return count;
	}
	
	/**
	 * 根据关键字获取商品列表
	 * @param keyword 商品关键字 
 	 * @return 商品列表
	 */
	@Override
	public List<Goods> getGoodsListByKeyword(String keyword,int page,int size) {
		List<Goods> goodsList = goodsDao.selectGoodsListByKeyword(keyword,page,size);
		return goodsList;
	}

	/**
	 * 根据商品关键字 获取商品数量
	 * @param keyword
	 * @return
	 */
	@Override
	public long getGoodsCountByKeyword(String keyword) {
		long count = goodsDao.selectGoodsCountByKeyword(keyword);
		return count;
	}
	
	/**
	 * 根据商品id获取商品信息
	 * @param goodsId
	 * @return
	 */
	@Override
	public Goods getGoodsById(int goodsId) {
		//只有id 无信息
		Goods goods = goodsDao.selectGoodsById(goodsId);
		
		//获取商品分类id
		Integer typeId= goods.getTypeId();
		
		Type type = typeDao.selectTypeById(typeId);
		
		goods.setType(type);
		
		return goods;
	}

}
