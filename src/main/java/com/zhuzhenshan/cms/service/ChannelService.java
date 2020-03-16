package com.zhuzhenshan.cms.service;

import java.util.List;

import com.zhuzhenshan.cms.entity.Category;
import com.zhuzhenshan.cms.entity.Channel;

public interface ChannelService {
	List<Channel> selects();
	
	List<Category> selectByChannelId(Integer channelId);
	
}
