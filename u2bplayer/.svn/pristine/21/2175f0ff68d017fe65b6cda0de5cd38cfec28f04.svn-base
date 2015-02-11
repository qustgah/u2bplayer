package com.ijiaban.u2bplayer.dao.v2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.ijiaban.u2bplayer.bean.Author;
import com.ijiaban.u2bplayer.bean.Category;
import com.ijiaban.u2bplayer.bean.Video;
import com.ijiaban.u2bplayer.bean.v3.Comments;
import com.ijiaban.u2bplayer.common.bean.XmlPageBean;
import com.ijiaban.u2bplayer.common.dao.XmlCommonDao;

public class CommentsDao {
	
	
	
	public static XmlPageBean getCommentsByUrl(String path) throws IOException, XmlPullParserException{
		List<Comments> comments = null;
		XmlPageBean pageBean = null;
		Comments comment = null;
		Author author = null;
		XmlPullParser xmlParser = XmlCommonDao.getXmlFromU2B(path);
		int evtType = xmlParser.getEventType();
		while(evtType != XmlPullParser.END_DOCUMENT){
			switch (evtType) {
			case XmlPullParser.START_TAG:
				String tag = xmlParser.getName();
				if(tag.equalsIgnoreCase(XmlCommonDao.FEED)){
					pageBean = new XmlPageBean();
					comments = new ArrayList<Comments>(); 
				}else if (pageBean!=null){
					if (tag.equalsIgnoreCase(XmlCommonDao.UPDATED)&&comment==null) {
						pageBean.setUpdated(xmlParser.nextText());
					} else if(tag.equalsIgnoreCase(XmlCommonDao.ID)&&comment==null){
						pageBean.setId(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.TOTALRESULTS)&&comment==null){
						pageBean.setTotalResults(xmlParser.next());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.ITEMSPERPAGE)&&comment==null){
						pageBean.setItemsPerPage(xmlParser.next());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.LINK)&&comment==null){
						if(xmlParser.getAttributeValue(null, "rel").equalsIgnoreCase("self")){
							pageBean.setLink(xmlParser.getAttributeValue(null, "href"));
						}else if(xmlParser.getAttributeValue(null, "rel").equalsIgnoreCase("previous")){
							pageBean.setPrevious(xmlParser.getAttributeValue(null, "href"));
						}else if (xmlParser.getAttributeValue(null, "rel").equalsIgnoreCase("next")) {
							pageBean.setNext(xmlParser.getAttributeValue(null, "href"));
						}
					}else if (tag.equalsIgnoreCase(XmlCommonDao.ENTRY)) {
						comment = new Comments();
					}else if(tag.equalsIgnoreCase(XmlCommonDao.ID)&&comment!=null){
						comment.setId(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.PUBLISHED)&&comment!=null){
						comment.setPublished(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.UPDATED)&&comment!=null){
						comment.setUpdated(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.TITLE)&&comment!=null){
						comment.setTitle(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.CONTENT)&&comment!=null){
						comment.setContent(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.LINK)&&comment!=null){
						if(xmlParser.getAttributeValue(null, "rel").equalsIgnoreCase("self")){
							comment.setLink(xmlParser.getAttributeValue(null, "href"));
						}
					}else if(tag.equalsIgnoreCase(XmlCommonDao.AUTHOR)&&comment!=null){
						author = new Author(); 
					}else if(tag.equalsIgnoreCase(XmlCommonDao.NAME)&&comment!=null&&author!=null){
						author.setName(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.URI)&&comment!=null&&author!=null){
						author.setUri(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.USERID)&&comment!=null&&author!=null){
						author.setUserid(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.CHANNELID)&&comment!=null){
						comment.setChannelId(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.REPLYCOUNT)&&comment!=null){
						comment.setReplycount(xmlParser.next());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.VIDEOID)&&comment!=null){
						comment.setVideoid(xmlParser.nextText());
					}
				} 
				break;
			case XmlPullParser.END_TAG:
				if(xmlParser.getName().equalsIgnoreCase(XmlCommonDao.ENTRY)&&comment!=null&&author!=null){
					comment.setAuthor(author);    
					comments.add(comment);
					pageBean.setList(comments);
				}
				break;
			default:
				break;
			}
			evtType = xmlParser.next();
		} 
		return pageBean;
	}
	 

	/**
	 * 获取视频集合
	 * @param path   视频列表地址
	 * @return		   视频集合
	 * @throws XmlPullParserException 
	 * @throws IOException 
	 */
	public static XmlPageBean getVideoByUrl(String path) throws IOException, XmlPullParserException {
		List<Video> videos = null;  
		XmlPageBean pageBean = null;
		Video video = null;
		Author author = null;
		XmlPullParser xmlParser = XmlCommonDao.getXmlFromU2B(path);
		int evtType = xmlParser.getEventType();
		while(evtType!=XmlPullParser.END_DOCUMENT){
			switch (evtType) { 
			case XmlPullParser.START_TAG:
				String tag = xmlParser.getName();
				if(tag.equalsIgnoreCase(XmlCommonDao.FEED)){
					pageBean = new XmlPageBean();
					videos = new ArrayList<Video>();
				}else if(pageBean != null){
					if(tag.equalsIgnoreCase(XmlCommonDao.ID)&&video==null){
						pageBean.setId(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.UPDATED)&&video==null){
						pageBean.setUpdated(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.TITLE)&&video==null){
						pageBean.setTitle(xmlParser.nextText());
					}else if(tag.equalsIgnoreCase(XmlCommonDao.LINK)&&video==null){
						if(xmlParser.getAttributeValue(null, "rel").equalsIgnoreCase("self")){
							pageBean.setLink(xmlParser.getAttributeValue(null, "href"));
						}else if(xmlParser.getAttributeValue(null, "rel").equalsIgnoreCase("previous")){
							pageBean.setPrevious(xmlParser.getAttributeValue(null, "href"));
						}else if (xmlParser.getAttributeValue(null, "rel").equalsIgnoreCase("next")) {
							pageBean.setNext(xmlParser.getAttributeValue(null, "href"));
						}
					}else if(tag.equalsIgnoreCase(XmlCommonDao.TOTALRESULTS)&&video==null){
						pageBean.setTotalResults(xmlParser.next());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.ENTRY)) {
						video = new Video();
					}else if (tag.equalsIgnoreCase(XmlCommonDao.ID)&& video != null) {
						video.setId(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.PUBLISHED)&& video != null) {
						video.setPublished(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.UPDATED)&& video != null) {
						video.setUpdated(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.CATEGORY)&& video != null) { 
						if(xmlParser.getAttributeValue(null, "label")!=null&&xmlParser.getAttributeValue(null, "term")!=null){
							Category category = new Category();
							category.setName(xmlParser.getAttributeValue(null, "label"));
							category.setSname(xmlParser.getAttributeValue(null, "term"));
							video.setCategory(category);
						}  
					}else if (tag.equalsIgnoreCase(XmlCommonDao.TITLE)&& video != null) {
						video.setTitle(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.CONTENT)&& video != null) {
						video.setContent(xmlParser.getAttributeValue(null, "src"));
					}else if (tag.equalsIgnoreCase(XmlCommonDao.LINK)&& video != null) {
						
						String rel = xmlParser.getAttributeValue(null, "rel");
						String [] arr = rel.split("#");
						String con = "";
						if(arr.length>1){
							con = arr[1];
						}else{
							con = arr[0];
						} 
						if(con.equalsIgnoreCase("video.related")){
							 video.setRelated(xmlParser.getAttributeValue(null, "href"));
						}else if(con.equalsIgnoreCase("self")){
							 video.setDetail(xmlParser.getAttributeValue(null, "href"));
						}
					}else if (tag.equalsIgnoreCase(XmlCommonDao.AUTHOR)&& video != null) { 
						author = new Author(); 
					}else if (tag.equalsIgnoreCase(XmlCommonDao.NAME)&&video != null&&author != null ) {
						
						author.setName(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.URI)&& video != null&&author != null) {
						
						author.setUri(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.USERID)&& video != null&&author != null) {
						author.setUserid(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.FEEDLINK)&& video != null) {
						String rel = xmlParser.getAttributeValue(null, "rel");
						String [] arr = rel.split("#");
						String con = arr[1];
						if(con.equalsIgnoreCase("comments")){
							video.setComment(xmlParser.getAttributeValue(null, "href"));
						} 
					}else if (tag.equalsIgnoreCase(XmlCommonDao.DESCRIPTION)&& video != null) {
						
						video.setDescription(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.THUMBNAIL)&& video != null) {
						if(xmlParser.getAttributeValue(null, "name").equalsIgnoreCase("default")){
							video.setDefaultpic(xmlParser.getAttributeValue(null, "url"));
						}
					}else if (tag.equalsIgnoreCase(XmlCommonDao.TITLE)&& video != null) {
						video.setTitle(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.DURATION)&& video != null) {
						video.setDuration(Integer.valueOf(xmlParser.getAttributeValue(null, "seconds")));
					}else if (tag.equalsIgnoreCase(XmlCommonDao.VIDEOID)&& video != null) {
						video.setVideoid(xmlParser.nextText());
					}else if (tag.equalsIgnoreCase(XmlCommonDao.RATING)&& video != null) {
						if(xmlParser.getAttributeValue(null, "average")!=null){
							video.setRating(Float.valueOf(xmlParser.getAttributeValue(null, "average")));
						}else{
							video.setNumDislikes(Integer.valueOf(xmlParser.getAttributeValue(null, "numDislikes")));
							video.setNumLikes(Integer.valueOf(xmlParser.getAttributeValue(null, "numLikes")));
						} 
					}else if (tag.equalsIgnoreCase(XmlCommonDao.STATISTICS)&& video != null) {
						video.setFavoriteCount(Integer.valueOf(xmlParser.getAttributeValue(null, "favoriteCount")));
						video.setViewCount(Integer.valueOf(xmlParser.getAttributeValue(null, "viewCount")));
					} 
				} 
				break;
			case XmlPullParser.END_TAG:
				if(xmlParser.getName().equalsIgnoreCase(XmlCommonDao.ENTRY)&&video!=null&&author!=null){
					video.setAuthor(author);
					videos.add(video); 
				}
				break; 
			default:
				break;
			}
			evtType = xmlParser.next();
		}
		pageBean.setList(videos);
		return null;
	} 
}
