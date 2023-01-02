package com.lanou.yoyo.service;

import java.util.List;

import com.lanou.yoyo.bean.Type;

/**
 * 商品大类相关的业务逻辑，获取商品分类列表
 * @author FYGOD
 *
 */

public interface TypeService {
	List<Type> getTypeList();
	
	/**
	 * 根据商品大类的id获取商品大类的信息
	 * @param id 商品分类的id
	 * @return 分类信息
	 */
	Type getTypeById(int id);
}
