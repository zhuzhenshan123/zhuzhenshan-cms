package com.zhuzhenshan.cms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuzhenshan.cms.entity.User;
import com.zhuzhenshan.cms.service.UserService;
import com.zhuzhenshan.cms.util.CMSException;
import com.zhuzhenshan.cms.util.Result;

/**
 * 
 * @ClassName: PassportController 
 * @Description: 注册登录入口
 * @author: 朱振山
 * @date: 2020年3月11日 上午10:36:35
 */
@Controller
@RequestMapping("passport")
public class PassportController {
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 去注册
	 * @return
	 * @return: String
	 */
	@GetMapping("reg")
	public String reg() {
		
		return "passport/reg";
	}
	/**
	 * 
	 * @Title: reg 
	 * @Description: 执行注册
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("reg")
	public Result<User> reg(User user,Model model) {
		Result<User> result = new Result<User>();
		try {
			if(userService.insert(user)>0) {
				result.setCode(200);
				result.setMsg("注册成功");
			}
		} catch (CMSException e) {//如果是自定义异常
			e.printStackTrace();
			result.setCode(300);
			result.setMsg("注册失败:"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();//把异常信息打印在控制台，一遍程序员找错(BUG)
			result.setCode(500);
			result.setMsg("注册失败:系统出现不可预知异常,请联系管理员");//给用户看
		}
		return result;
	} 
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 普通用户去登陆
	 * @return
	 * @return: String
	 */
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	/**
	 * 
	 * @Title: login 
	 * @Description: 执行登录
	 * @param formUser
	 * @param model
	 * @param session
	 * @return
	 * @return: Result<User>
	 */
	@ResponseBody
	@PostMapping("login")
	public Result<User> login(User formUser,Model model,HttpSession session){
		Result<User> result = new Result<User>();
		try {
			//去登陆，成功后返回用户的基本信息
			User user = userService.login(formUser);
			if(null!=user) {//普通用户
				result.setCode(200);
				result.setMsg("登录成功");
				if(user.getRole()==0) {
					session.setAttribute("user", user);//登陆成功，用户信息存入session
				}else {//管理员
					session.setAttribute("admin", user);//登陆成功，用户信息存入session
				}
				
			}
			
		} catch (CMSException e) {//如果是自定义异常
			e.printStackTrace();
			result.setCode(300);
			result.setMsg("登录失败:"+e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();//把异常信息打印在控制台，一遍程序员找错(BUG)
			result.setCode(500);
			result.setMsg("登录失败:系统出现不可预知异常,请联系管理员");//给用户看
		}
		return result;
		
		
	}
	
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 管理员-去登陆
	 * @return
	 * @return: String
	 */
	@GetMapping("adminLogin")
	public String adminLogin() {
		return "passport/adminLogin";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @Title: logout 
	 * @Description:注销用户
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpSession session) {
		
		//清除session
		session.invalidate();
		
		return "redirect:/";//返回首页
		
	}
	
	/**
	 * 
	 * @Title: checkName 
	 * @Description: 检查用户是否存在
	 * @param username
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("checkName")
	public boolean checkName(String username) {
		return userService.selectByUsername(username)==null;
	}
	
	
	
	
}
