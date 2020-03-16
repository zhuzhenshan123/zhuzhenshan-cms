package com.zhuzhenshan.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhuzhenshan.cms.dao.SlideMapper;
import com.zhuzhenshan.cms.entity.Slide;
import com.zhuzhenshan.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {
	@Autowired
	private SlideMapper slideMapper;

	@Override
	public List<Slide> slectes() {
		// TODO Auto-generated method stub
		return slideMapper.selects();
	}
	
	
}
