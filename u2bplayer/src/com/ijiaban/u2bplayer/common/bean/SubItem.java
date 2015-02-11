package com.ijiaban.u2bplayer.common.bean;

import java.util.Date;

public class SubItem {
	public String id; //播放列表的id
	public String kind; //种类 
	public Date publishedAt;//发布日期
	public String channelId; //总频道id
	public String description; //列表描述 
	public String title; //列表名称
	public Thumbnails thumbnails; //缩略图
	public ResourceId resourceid; //资源id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Date getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Thumbnails getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(Thumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}
	public ResourceId getResourceid() {
		return resourceid;
	}
	public void setResourceid(ResourceId resourceid) {
		this.resourceid = resourceid;
	}
	public SubItem(String id, String kind, Date publishedAt, String channelId,
			String description, String title, Thumbnails thumbnails,
			ResourceId resourceid) {
		super();
		this.id = id;
		this.kind = kind;
		this.publishedAt = publishedAt;
		this.channelId = channelId;
		this.description = description;
		this.title = title;
		this.thumbnails = thumbnails;
		this.resourceid = resourceid;
	}
	public SubItem() {
		super();
	}
}
