package com.lanou.yoyo.service.impl;

import java.util.List;

import com.lanou.yoyo.bean.Type;
import com.lanou.yoyo.dao.TypeDao;
import com.lanou.yoyo.dao.impl.TypeDaoImpl;
import com.lanou.yoyo.service.TypeService;

public class TypeServiceImpl implements TypeService {
	
	private TypeDao typeDao= new TypeDaoImpl();

	@Override
	public List<Type> getTypeList() {
		// TODO 查询数据库
		List<Type> typeList = typeDao.selectTypeList();
		return typeList;
	}

	@Override
	public Type getTypeById(int id) {
		Type type = typeDao.selectTypeById(id);
		return type;
	}
	
	

}
