package com.zhuzhenshan.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuzhenshan.cms.entity.Category;
import com.zhuzhenshan.cms.entity.Channel;
import com.zhuzhenshan.cms.service.ChannelService;

@Controller
@RequestMapping("channel")
public class ChannelController {
	@Autowired
	private ChannelService service;
	
	@ResponseBody
	@RequestMapping("channels")
	public List<Channel> channels(){
		return service.selects();
	}
	
	@ResponseBody
	@RequestMapping("selectByChannelId")
	public List<Category> selectByChannelId(Integer channelId){
		return service.selectByChannelId(channelId);
	}
	
}
