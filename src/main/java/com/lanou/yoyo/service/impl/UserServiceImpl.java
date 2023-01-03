package com.lanou.yoyo.service.impl;

import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.dao.UserDao;
import com.lanou.yoyo.dao.impl.UserDaoImpl;
import com.lanou.yoyo.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userDao.selectUserByUsername(username);
		return user;
	}
	
	/**
	 * 添加用户
	 * @param user 用户
	 * @return 如果添加成功 返回true，失败 false
	 */
	@Override
	public boolean addUser(User user) {

		int row = userDao.insertUser(user);
		if(row==1) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 根据用户名和密码获取用户
	 * @param username
	 * @param password （密文）
	 * @return
	 */
	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		User user = userDao.selectUserByUsernameAndPassword(username, password);
		return user;
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@Override
	public boolean updateUser(User user) {
		// 更新数据库
		int row = userDao.updateUser(user);
		if(row==1) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return 返回用户
	 */
	@Override
	public User getUserById(int userId) {
		
		User user = userDao.selectUserById(userId);
		return user;
	}
}
