package com.zhuzhenshan.cms.dao;

import java.util.List;

import com.zhuzhenshan.cms.entity.Article;
import com.zhuzhenshan.cms.entity.Comment;

public interface CommentMapper {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据文章查询文章评论
	 * @param article
	 * @return
	 * @return: List<Comment>
	 */
	List<Comment> selects(Article article);
	/**
	 * 
	 * @Title: selectsByCommentNum 
	 * @Description: 按评论数量排序
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selectsByCommentNum();
	
	
	int updateArticle(Integer articleId);
	
}
