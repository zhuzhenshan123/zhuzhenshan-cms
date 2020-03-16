package com.zhuzhenshan.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhuzhenshan.cms.entity.Article;
import com.zhuzhenshan.cms.entity.User;
import com.zhuzhenshan.cms.service.ArticleService;
import com.zhuzhenshan.cms.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	
	
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入管理员中心
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "admin/index";
	}
	/**
	 * 
	 * @Title: articles 
	 * @Description: 进入文章列表页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model model,Article article,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize) {
		PageInfo<Article> info =articleService.selects(article,page,pageSize);
		model.addAttribute("info", info);
		model.addAttribute("article", article);
		return "admin/articles";
	}
	@ResponseBody
	@RequestMapping("update")
	public boolean update(Article article) {
		return articleService.update(article)>0;
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 单个文章详情
	 * @return
	 * @return: Article
	 */
	@ResponseBody
	@RequestMapping("articleDetail")
	public Article articleDetail(Integer id) {
		
		return articleService.articleDetail(id);
	}
	/**
	 * 
	 * @Title: users 
	 * @Description: 用户列表
	 * @return
	 * @return: String
	 */
	@RequestMapping("users")
	public String users(Model model,User user,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "18")Integer pageSize) {
		PageInfo<User> info = userService.selects(user, page, pageSize);	
		model.addAttribute("info", info);
		model.addAttribute("user", user);
		return "admin/users";
		
	}
	@ResponseBody
	@RequestMapping("updateUser")
	public boolean updateUser(User user) {
		
		return userService.update(user)>0;
	}
	
}
