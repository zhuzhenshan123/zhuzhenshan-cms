package com.zhuzhenshan.cms.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: Category 
 * @Description: 栏目的子分类
 * @author: 朱振山
 * @date: 2020年3月3日 上午11:24:33
 */
public class Category implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//主键
	private String name;
	private Integer channelId;//所属栏目ID
	private Integer sorted;//排序
	
	private Channel channel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getSorted() {
		return sorted;
	}

	public void setSorted(Integer sorted) {
		this.sorted = sorted;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Integer id, String name, Integer channelId, Integer sorted, Channel channel) {
		super();
		this.id = id;
		this.name = name;
		this.channelId = channelId;
		this.sorted = sorted;
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", channelId=" + channelId + ", sorted=" + sorted
				+ ", channel=" + channel + "]";
	}
	
	
}
