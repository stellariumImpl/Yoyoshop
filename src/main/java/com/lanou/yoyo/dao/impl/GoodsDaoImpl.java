package com.lanou.yoyo.dao.impl;

import java.util.List;
import java.util.Map;

import com.lanou.yoyo.bean.Goods;
import com.lanou.yoyo.dao.GoodsDao;
import com.lanou.yoyo.util.DBUtils;

public class GoodsDaoImpl implements GoodsDao {

	@Override
	public List<Goods> selectGoodsListByTopType(int topType) {
		String sql="SELECT goods.* FROM top JOIN goods ON top.good_id=goods.id WHERE type=?";
		List<Goods> goodsList=DBUtils.query(sql, Goods.class, topType);
		
		return goodsList;
		
		/**
		 * 根据商品分类id 获取商品列表
		 * @param typeId 商品分类id
		 * @return 返回查询到的商品列表
		 */
		
		
	}

	/**
	 * 根据id查询商品信息
	 */
	@Override
	public List<Goods> selectGoodsListByTypeId(int typeId,int page,int size) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM goods WHERE type_id=? AND status=1 limit ?,?";
		List<Goods> goodsList=DBUtils.query(sql, Goods.class, typeId,(page-1)*size,size);
		return goodsList;
	}
	
	/**
	 * 根据商品大类的id，查询此商品大类下商品的数量
	 * @param typeId 商品大类的id
	 * @return 返回此商品大类下商品的数量
	 */
	@Override
	public long selectGoodsCountByTypeId(int typeId) {
		String sql="SELECT count(*) AS count FROM goods WHERE type_id = ? AND status =1 ";
		Map<String,Object> map = DBUtils.queryOne(sql, typeId);
		long count=(long)map.get("count");
		return count;
	}

	@Override
	public List<Goods> selectGoodsListByTopType(int topType, int page, int size) {
		// TODO 在这就可以写sql了
		String sql = "SELECT goods.* from top join goods on top.good_id = goods.id where type=? LIMIT ?,?";
		List<Goods> goodsList= DBUtils.query(sql, Goods.class, topType, (page-1)*size, size);
		return goodsList;
	}

	/**
	 * 根据排行榜类型查询商品总量
	 * @param topType 排行榜类型 1 今日 2 ...
	 * @return 返回该榜下的数量
	 */
	@Override
	public long selectGoodsCountByTopType(int topType) {
		String sql = "SELECT count(*) AS count FROM top JOIN goods ON top.good_id = goods.id WHERE type =?";
		Map<String,Object> map = DBUtils.queryOne(sql, topType);
		long count = (Long)map.get("count");
		return count;
		
	}

	/**
	 * 根据关键字查询列表
	 * @param keyword
	 * @return 
	 */
	@Override
	public List<Goods> selectGoodsListByKeyword(String keyword,int page,int size) {
		// 可以写sql啦！
		String sql = "select * from goods where status=1 and name like '%" + keyword + "%' limit ?,?";
		List<Goods> goodsList = DBUtils.query(sql, Goods.class,(page-1)*size,size);
		return goodsList;
	}

	/**
	 * 关键字查数量
	 * @param keyword
	 * @return
	 */
	@Override
	public long selectGoodsCountByKeyword(String keyword) {
		String sql = "select count(*) as count from goods where status=1 and name like '%" + keyword + "%'";
		Map<String,Object> map =DBUtils.queryOne(sql);
		long count = (long)map.get("count");
		return count;
	}
	
	
	/**
	 * 根据商品id获取商品信息
	 * @param goodsId
	 * @return
	 */
	@Override
	public Goods selectGoodsById(int goodsId) {
		// TODO 写sql喽！
		String sql ="select * from goods where id = ? and status = 1";
		Goods goods = DBUtils.queryOne(sql, Goods.class,goodsId);
		return goods;
	}

	

	
	
	

}
