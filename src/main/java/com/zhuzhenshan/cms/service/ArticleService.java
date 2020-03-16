package com.zhuzhenshan.cms.service;

import com.github.pagehelper.PageInfo;
import com.zhuzhenshan.cms.entity.Article;

public interface ArticleService {
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int insert(Article article);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 文章列表
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	
	
	PageInfo<Article> selects(Article article,Integer page,Integer pageSize );
	

	/**
	 * 
	 * @Title: articleDetail 
	 * @Description: 返回单个文章
	 * @param id
	 * @return
	 * @return: Article
	 */
	Article articleDetail(Integer id);
	/**
	 * 
	 * @Title: update 
	 * @Description: 更新文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int update(Article article);
	
	Article select(Integer id);
	
	
	
	
	
	
}
