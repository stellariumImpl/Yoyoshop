package com.lanou.yoyo.dao;

import com.lanou.yoyo.bean.User;

/**
 * 这是操作数据库user表的接口，声明我们要对user表做哪些操作
 * @author FYGOD
 *
 */
public interface UserDao {
	
	User selectUserByUsername(String username);
	
	/**
	 * 让user表中添加一个用户
	 * @param user 要添加的用户
	 * @return 返回添加数据的条数
	 */
	int insertUser(User user);
	
	/**
	 * 根据密文和用户名查询用户
	 * @param username
	 * @param password （密文）
	 * @return
	 */
	User selectUserByUsernameAndPassword(String username,String password);
	
	/**
	 * 更新用户
	 * @param user
	 * @return 更新成功  1 反之 0
	 */
	int updateUser(User user);
	
	/**
	 * 根据用户id 查询用户
	 * @param userId
	 * @return 查询到的用户
	 */
	User selectUserById(int userId);
}
