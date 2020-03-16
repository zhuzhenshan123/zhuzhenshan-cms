package com.zhuzhenshan.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shan.common.utils.StringUtil;
import com.zhuzhenshan.cms.dao.CollectMapper;
import com.zhuzhenshan.cms.entity.Collect;
import com.zhuzhenshan.cms.service.CollectService;
import com.zhuzhenshan.cms.util.CMSException;
@Service
public class CollectServiceImpl implements CollectService {
	@Autowired
	private CollectMapper collectMapper;
	
	@Override
	public int insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl())) {
			throw new CMSException("不是合法的URL");
		}
		return collectMapper.insert(collect);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.delete(id);
	}



	@Override
	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return collectMapper.selectByTitleAndUserId(title, userId);
	}

	@Override
	public PageInfo<Collect> selects(Integer userId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Collect> list = collectMapper.selects(userId);
		return new PageInfo<Collect>(list);
	}

}
