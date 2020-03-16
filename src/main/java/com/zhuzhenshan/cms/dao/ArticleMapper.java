package com.zhuzhenshan.cms.dao;

import java.util.List;

import com.zhuzhenshan.cms.entity.Article;

public interface ArticleMapper {
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
	List<Article> selects(Article article);
	
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
