package com.lanou.yoyo.service;

import com.lanou.yoyo.bean.User;

public interface UserService {
	/**
	 * 通过用户名查询用户
	 * @param username
	 * @return
	 */
	User getUserByUsername(String username);
	
	/**
	 * 添加用户
	 * @param user 用户
	 * @return 如果添加成功 返回true，失败 false
	 */
	boolean addUser(User user);
	
	/**
	 * 根据用户名和密码获取用户
	 * @param username
	 * @param password
	 * @return
	 */
	User getUserByUsernameAndPassword(String username,String password);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	boolean updateUser(User user);
	
	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return 返回用户
	 */
	User getUserById(int userId);
}
