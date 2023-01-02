package com.lanou.yoyo.dao.impl;

import java.util.List;

import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.dao.TypeDao;
import com.lanou.yoyo.util.DBUtils;

public class TypeDaoImpl implements TypeDao {

	/**
	 * 查询所有商品大类（status为1的）
	 * @return 返回商品大类
	 */
	@Override
	public List<Type> selectTypeList() {
		String sql="SELECT * FROM type WHERE status=1";
		List<Type> typeList=DBUtils.query(sql, Type.class);
		
		return typeList;
	}
	
	/**
	 * 根据商品大类的id 查询商品大类信息
	 * @param id 商品大类的id
	 * @return 商品大类信息
	 */

	@Override
	public Type selectTypeById(int id) {
		String sql ="SELECT * FROM type WHERE id=? AND status=1";
		Type type = DBUtils.queryOne(sql, Type.class, id);
		return type;
	}

}
