package com.zhuzhenshan.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuzhenshan.cms.dao.ChannelMapper;
import com.zhuzhenshan.cms.entity.Category;
import com.zhuzhenshan.cms.entity.Channel;
import com.zhuzhenshan.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService{
	@Autowired
	private ChannelMapper mapper;

	@Override
	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return mapper.selects();
	}

	@Override
	public List<Category> selectByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return mapper.selectByChannelId(channelId);
	}
	
	
}
