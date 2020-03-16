package com.shan.cms.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhuzhenshan.cms.entity.Article;
import com.zhuzhenshan.cms.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleServiceImplTest {
	@Autowired
	private ArticleService articleService;
	
	@Test
	public void testInsert() {
		Article a = new Article();
		a.setTitle("aaaaaaaaa");
		a.setContent("aaaaabbbbbb");
		a.setChannelId(1);
		a.setCategoryId(1);
		a.setUserId(1);
		articleService.insert(a);
	}

	@Test
	public void testSelects() {
		articleService.selects(null, null, null);
	}

}
