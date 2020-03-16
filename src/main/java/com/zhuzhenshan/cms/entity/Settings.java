package com.zhuzhenshan.cms.entity;

import java.io.Serializable;
/**
 * 
 * @ClassName: Settings 
 * @Description: 系统配置表
 * @author: 朱振山
 * @date: 2020年3月3日 下午1:02:03
 */
public class Settings implements Serializable{
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String siteDomain;
	private String siteName;
	private Integer article_list_size;//文章每页显示的条目
	private Integer slide_size;//显示多少个广告
	private String adminUsername;
	private String admin_password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getArticle_list_size() {
		return article_list_size;
	}
	public void setArticle_list_size(Integer article_list_size) {
		this.article_list_size = article_list_size;
	}
	public Integer getSlide_size() {
		return slide_size;
	}
	public void setSlide_size(Integer slide_size) {
		this.slide_size = slide_size;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public Settings() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Settings(Integer id, String siteDomain, String siteName, Integer article_list_size, Integer slide_size,
			String adminUsername, String admin_password) {
		super();
		this.id = id;
		this.siteDomain = siteDomain;
		this.siteName = siteName;
		this.article_list_size = article_list_size;
		this.slide_size = slide_size;
		this.adminUsername = adminUsername;
		this.admin_password = admin_password;
	}
	@Override
	public String toString() {
		return "Settings [id=" + id + ", siteDomain=" + siteDomain + ", siteName=" + siteName + ", article_list_size="
				+ article_list_size + ", slide_size=" + slide_size + ", adminUsername=" + adminUsername
				+ ", admin_password=" + admin_password + "]";
	}
	
}
