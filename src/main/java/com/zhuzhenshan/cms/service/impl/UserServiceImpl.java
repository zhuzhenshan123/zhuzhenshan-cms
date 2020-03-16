package com.zhuzhenshan.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.common.utils.StringUtil;
import com.zhuzhenshan.cms.dao.UserMapper;
import com.zhuzhenshan.cms.entity.User;
import com.zhuzhenshan.cms.service.UserService;
import com.zhuzhenshan.cms.util.CMSException;
import com.zhuzhenshan.cms.util.Md5Util;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	
	
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> list= userMapper.selects(user);
		return new PageInfo<User>(list);
	}



	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}


	//注册user
	@Override
	public int insert(User user) {
		//通过自定义校验规则对注册用户进行校验
		//1.用户名不为空，长度2-10 ，用户名不能重复
		if(!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		if(!(user.getUsername().length()>=2 && user.getUsername().length()<=10)){
			throw new CMSException("用户名的长度在2-10之间");
		}
		User findUser = this.selectByUsername(user.getUsername());
		if(findUser!=null) {
			throw new CMSException("用户名已经存在");
		}
		
		if(!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		if(!(user.getPassword().length()>=6 && user.getPassword().length()<=10)){
			throw new CMSException("密码的长度在2-10之间");
		}
		if(!StringUtil.hasText(user.getRepassword())) {
			throw new CMSException("确认密码不能为空");
		}
		if(!(user.getRepassword().equals(user.getPassword()))){
			throw new CMSException("两次密码输入不一致");
		}
		
		//对用户密码进行加密处理
		user.setPassword(Md5Util.encode(user.getPassword()));
		user.setCreated(new Date());//注册时间
		user.setNickname(user.getUsername());//默认昵称为用户名
		user.setLocked("0");//默认用户状态是可用的
		return userMapper.insert(user);
	}



	@Override
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.selectByUsername(username);
	}
	
	
	public User login(User user) {
		//1.校验用户名是否为空
		if(!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		//2.判断用户名是否存在
		User u = this.selectByUsername(user.getUsername());
		if(u==null) {
			throw new CMSException("用户名不存在");
		}
		//比较密码是否一致/数据库存储的是加密后的密码
		//对登陆的密码再次加密，判断是否一致
		if(!Md5Util.encode(user.getPassword()).equals(u.getPassword())) {
			throw new CMSException("密码不正确，请重新录入");
		}
		if(u.getLocked().equals("1")) {
			throw new CMSException("当前账号已被禁用，不能登录");
		}
		
		return u;
		
	}

}
