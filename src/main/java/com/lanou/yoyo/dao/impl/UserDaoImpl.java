package com.lanou.yoyo.dao.impl;

import com.lanou.yoyo.bean.User;
import com.lanou.yoyo.dao.UserDao;
import com.lanou.yoyo.util.DBUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User selectUserByUsername(String username) {
		String sql = "select * from user where status = 1 and username =? ";
		User user =DBUtils.queryOne(sql, User.class,username);
		return user;
	}
	
	/**
	 * 让user表中添加一个用户
	 * @param user 要添加的用户
	 * @return 返回添加数据的条数
	 */
	@Override
	public int insertUser(User user) {
		String sql = "insert into user(username,password,name,phone,address)VALUES(?,?,?,?,?)";
		
		int row = DBUtils.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getPhone(),user.getAddress());
		
		return row;
	}

	@Override
	public User selectUserByUsernameAndPassword(String username, String password) {
		String sql = "select * from user where status = 1 and username=? and password = ?";
		User user = DBUtils.queryOne(sql, User.class,username,password);
		return user;
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@Override
	public int updateUser(User user) {
		//sql！
		String sql = "update user set password=? , name=? , phone=? , address=? where id=? and status=1";
		int row = DBUtils.update(sql, user.getPassword(),user.getName(),user.getPhone(),user.getAddress(),user.getId());
		
		return row;
	}
	
	/**
	 * 根据用户id 查询用户
	 * 
	 * @param userId
	 * @return 查询到的用户
	 */
	@Override
	public User selectUserById(int userId) {
		String sql = "select * from user where status = 1 and id = ?";
		User user = DBUtils.queryOne(sql, User.class, userId);
		return user;
	}
}
