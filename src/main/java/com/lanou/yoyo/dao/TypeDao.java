package com.lanou.yoyo.dao;

import java.util.List;

import com.lanou.yoyo.bean.Type;

public interface TypeDao {
	/**
	 * 查询所有商品大类（status为1的）
	 * @return 返回商品大类
	 */
		List<Type> selectTypeList();
	
	/**
	 * 根据商品大类的id 查询商品大类信息
	 * @param id 商品大类的id
	 * @return 商品大类信息
	 */
		Type selectTypeById(int id);
}
