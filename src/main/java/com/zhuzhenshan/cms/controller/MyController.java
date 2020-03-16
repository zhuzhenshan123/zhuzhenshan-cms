package com.zhuzhenshan.cms.controller;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.zhuzhenshan.cms.entity.Article;
import com.zhuzhenshan.cms.entity.Collect;
import com.zhuzhenshan.cms.entity.User;
import com.zhuzhenshan.cms.service.ArticleService;
import com.zhuzhenshan.cms.service.CollectService;
/**
 * 
 * @ClassName: MyController 
 * @Description: 个人中心
 * @author: 朱振山
 * @date: 2020年3月4日 上午10:50:54
 */
@RequestMapping("my")
@Controller
public class MyController {
	 @Autowired
	private ArticleService service;
	 @Autowired
	private CollectService collectService;
	 
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入个人中心的首页
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		
		return "my/index";
	}
	/**
	 * 
	 * @Title: articles 
	 * @Description: 我的文章
	 * @return
	 * @return: String
	 */
	@RequestMapping("articles")
	public String articles(Model m,HttpSession session,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		Article article = new Article();
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//只显示当前登录人的文章
		PageInfo<Article> info=service.selects(article, page, pageSize);
		m.addAttribute("info", info);
		return "my/articles";
	}
	/**
	 * 
	 * @Title: publish 
	 * @Description: 去发布文章
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {
		return "my/publish";
	}
	
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(MultipartFile file,Article article,HttpSession session) {
		//文件上传
		if(null!=file && !file.isEmpty()) {
			String path ="d:/pic/";
			//文件原始名
			String filename = file.getOriginalFilename();
			//为了防止文件重名，改名
			String newFilename = UUID.randomUUID().toString()+filename.substring(filename.lastIndexOf("."));
			File f = new File(path,newFilename);
			try {
				file.transferTo(f);
				article.setPicture(newFilename);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());//目前没有登录
		article.setCreated(new Date());
		article.setHits(0);//默认点击0
		article.setDeleted(0);//默认删除状态 0正常
		article.setHot(0);//默认非热门
		article.setStatus(0);//默认待审核
		return service.insert(article)>0;
		 
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
		
		return service.articleDetail(id);
	}
	
	/**
	 * 
	 * @Title: collect 
	 * @Description: 我的收藏
	 * @param m
	 * @param session
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("collect")
	public String collect(Model m,HttpSession session,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize) {
		
		User user = (User) session.getAttribute("user");
		Integer userId=user.getId();
		PageInfo<Collect> info = collectService.selects(userId, page, pageSize);
		
		m.addAttribute("info", info);
		return "my/collect";
	}
	
}
