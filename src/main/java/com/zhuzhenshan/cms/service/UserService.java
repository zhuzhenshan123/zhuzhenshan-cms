package com.zhuzhenshan.cms.service;

import com.github.pagehelper.PageInfo;
import com.zhuzhenshan.cms.entity.User;

public interface UserService {
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 用户列表查询
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(User user,Integer page,Integer pageSize);

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
	/**
	 * 
	 * @Title: login 
	 * @Description: 登录
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);
	
}
