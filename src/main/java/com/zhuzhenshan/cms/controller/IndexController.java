package com.zhuzhenshan.cms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhuzhenshan.cms.entity.Article;
import com.zhuzhenshan.cms.entity.Category;
import com.zhuzhenshan.cms.entity.Channel;
import com.zhuzhenshan.cms.entity.Collect;
import com.zhuzhenshan.cms.entity.Comment;
import com.zhuzhenshan.cms.entity.Slide;
import com.zhuzhenshan.cms.entity.User;
import com.zhuzhenshan.cms.service.ArticleService;
import com.zhuzhenshan.cms.service.ChannelService;
import com.zhuzhenshan.cms.service.CollectService;
import com.zhuzhenshan.cms.service.CommentService;
import com.zhuzhenshan.cms.service.SlideService;

/**
 * 
 * @ClassName: IndexController 
 * @Description: 系统首页入口
 * @author: 朱振山
 * @date: 2020年3月9日 上午11:20:42
 */
@Controller
public class IndexController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectService collectService;
	
	@RequestMapping(value = {"","/","index"})
	public String index(Model model,Article article,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		article.setStatus(1);//只显示审核过的文章
		article.setDeleted(0);//只显示未删除的文章
		model.addAttribute("article", article);
		//查询左侧栏目
		List<Channel> channels =channelService.selects();
		model.addAttribute("channels", channels);
		//如果栏目ID不为空则查询
		if(article.getChannelId()!=null) {
			List<Category> categorys = channelService.selectByChannelId(article.getChannelId());
			model.addAttribute("categorys", categorys);
		}
		if(article.getChannelId()==null) {
			article.setHot(1);
			List<Slide> slides = slideService.slectes();
			model.addAttribute("slides", slides);
		}
		//查询所有的文章
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		model.addAttribute("info", info);
		//在右侧显示10篇文章
		Article article2 = new Article();
		article2.setStatus(1);
		article2.setDeleted(0);
		PageInfo<Article> lastArticles = articleService.selects(article2, 1, 10);
		model.addAttribute("lastArticles", lastArticles);
		
		return "index/index";
	}
	
	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 文章详情
	 * @return
	 * @return: String
	 */
	@RequestMapping("articleDetail")
	public String articleDetail(HttpSession session,Model model,Integer id,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer pageSize) {
		Article article=articleService.select(id);
		model.addAttribute("article", article);
		//查询当前文章评论
		PageInfo<Comment> info = commentService.selects(article, page, pageSize);
		//查询所有文章评论
		PageInfo<Article> info2 = commentService.selectsByCommentNum(1, 10);
		
		model.addAttribute("info", info);
		model.addAttribute("info2", info2);
		
		//查询该文章是否被收藏过
		User user=(User) session.getAttribute("user");
		Collect collect =null;
		if(null!=user) {
			collect=collectService.selectByTitleAndUserId(article.getTitle(), user.getId());
		}
		model.addAttribute("collect", collect);
		
		return "index/articleDetail";
	}
	/**
	 * 
	 * @Title: collect 
	 * @Description: 收藏文章
	 * @param collect
	 * @param session
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("collect")
	public boolean collect(Collect collect,HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(null==user) {
			return false;
		}
		collect.setUser(user);
		collect.setCreated(new Date());
		return collectService.insert(collect)>0;
	}
	/**
	 * 
	 * @Title: coolect 
	 * @Description: 取消收藏
	 * @param id
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("deleteCollect")
	public boolean colect(Integer id) {
		return collectService.delete(id)>0;
	}
	
	
	
	/**
	 * 
	 * @Title: addComment 
	 * @Description: 增加评论
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("addComment")
	public boolean addComment(Comment comment,Integer articleId,HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(null==user) {
			return false;//没有登录的用户不能评论
		}
		comment.setUserId(user.getId());
		comment.setArticleId(articleId);
		comment.setCreated(new Date());
		
		return commentService.insert(comment)>0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
