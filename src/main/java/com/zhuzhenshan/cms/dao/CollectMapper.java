package com.zhuzhenshan.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhuzhenshan.cms.entity.Collect;

public interface CollectMapper {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 收藏
	 * @param collect
	 * @return
	 * @return: int
	 */
	int insert(Collect collect);
	
	/**
	 * 
	 * @Title: delete 
	 * @Description: 取消收藏
	 * @param id
	 * @return
	 * @return: int
	 */
	int delete (Integer id);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 显示我的收藏
	 * @param userId
	 * @return
	 * @return: List<Collect>
	 */
	List<Collect> selects(Integer userId);
	/**
	 * 
	 * @Title: selectByTitleAndUserId 
	 * @Description: 根据title和userId查询文章是否被收藏
	 * @param title
	 * @param userId
	 * @return
	 * @return: Collect
	 */
	Collect selectByTitleAndUserId(@Param("title")String title,@Param("userId")Integer userId);
	
}
