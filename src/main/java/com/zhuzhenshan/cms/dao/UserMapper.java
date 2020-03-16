package com.zhuzhenshan.cms.dao;

import java.util.List;

import com.zhuzhenshan.cms.entity.User;

public interface UserMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表查询
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(User user);

	int update(User user);
	/**
	 * 
	 * @Title: insert 
	 * @Description: 注册用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int insert(User user);
	/**
	 * 
	 * @Title: selectByUsername 
	 * @Description: 检查注册用户是否已存在
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByUsername(String username);
	
}
