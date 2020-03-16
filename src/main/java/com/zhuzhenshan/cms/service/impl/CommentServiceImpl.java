package com.zhuzhenshan.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuzhenshan.cms.dao.CommentMapper;
import com.zhuzhenshan.cms.entity.Article;
import com.zhuzhenshan.cms.entity.Comment;
import com.zhuzhenshan.cms.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public int insert(Comment comment) {
		try {
			// 增加评论
			commentMapper.insert(comment);
			//改变文章的评论数据
			commentMapper.updateArticle(comment.getArticleId());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public PageInfo<Comment> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Comment> list = commentMapper.selects(article);
		return new PageInfo<Comment>(list);
	}

	@Override
	public PageInfo<Article> selectsByCommentNum(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> list = commentMapper.selectsByCommentNum();
		return new PageInfo<Article>(list);
	}

}
