package com.zhuzhenshan.cms.dao;
/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: TODO
 * @author: 朱振山
 * @date: 2020年3月5日 上午10:06:43
 */

import java.util.List;

import com.zhuzhenshan.cms.entity.Category;
import com.zhuzhenshan.cms.entity.Channel;

public interface ChannelMapper {
	
	List<Channel> selects();
	
	List<Category> selectByChannelId(Integer channelId);
	
}
