package com.ijiaban.u2bplayer.bean;

public class Author {
	
	/**
	 * 用户名称
	 */
	public String name; 
	
	/**
	 * 用户详细地址
	 */
	public String uri;
	
	/**
	 * 用户id
	 */
	public String userid;
	
	/**
	 * 用户头像
	 */
	public String thumbnail;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	} 
}
