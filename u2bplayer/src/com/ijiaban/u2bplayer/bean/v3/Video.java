package com.ijiaban.u2bplayer.bean.v3;

import java.util.Date;
/**
 * 视频的详细包括： id, snippet(视频的基本信息), contentDetails（比如视频格式）, fileDetails（无法操作）, 
 * liveStreamingDetails（无法操作）, player（播放网址等）, processingDetails（无法操作）, 
 * recordingDetails, statistics（统计信息）, status（状态）, 
 * suggestions（无法操作）, and topicDetails（话题包括相关的话题）.
 * @author Administrator
 *
 */
public class Video {

	 public String id;  //id
	  public String kind;  //种类 
	  public Date publishedAt;  //发布日期 
	  public String channelId;  //频道id
	  public String title;     //视频名称
	  public String description;  //视频描述 
	  public String channelTitle;  //频道名称 
	  public String categoryId;    //分类id
	  public Thumbnails thumbnails;  //缩略图 
	  
	  public Statistics statistics ; //统计数据
	  
	  

	  public Video() {
		// TODO 
	  } 
	  public Video(String id,String kind,Date publishedAt,String channelId,String title,String description,String channelTitle,String categoryId,Thumbnails thumbnails) {
			// TODO 初始化
		  this.id = id;
		  this.kind = kind;
		  this.channelId = channelId;
		  this.publishedAt = publishedAt;
		  this.title = title;
		  this.description = description;
		  this.channelTitle = channelTitle; 
		  this.categoryId = categoryId;
		  this.thumbnails = thumbnails;  
	  } 
	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public Thumbnails getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(Thumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	} 
	
}
