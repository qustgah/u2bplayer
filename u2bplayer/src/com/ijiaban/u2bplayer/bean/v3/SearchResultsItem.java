package com.ijiaban.u2bplayer.bean.v3;

import java.util.Date;

/**
 * 咱们随时补充
 * @author Administrator
 *
 */
public class SearchResultsItem {
	
	  public String itemId;  
	  public String kind;
	  public Date publishedAt;
	  public String title;
	  public String description;
	  public String channelTitle;
	  
	  public Thumbnails thumbnails;

	  public SearchResultsItem() {
		// TODO Auto-generated constructor stub
	  }
	  public SearchResultsItem(String itemId,String kind,Date publishedAt,String title,String description,String channelTitle,Thumbnails thumbnails) {
			// TODO 初始化
		  this.itemId = itemId;
		  this.kind = kind;
		  this.publishedAt = publishedAt;
		  this.title = title;
		  this.description = description;
		  this.channelTitle = channelTitle; 
		  this.thumbnails = thumbnails;
		  
	  } 

	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	  
}
