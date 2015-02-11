package com.ijiaban.u2bplayer.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.StaticLayout;

import com.google.gson.JsonObject;
import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.bean.v3.Statistics;
import com.ijiaban.u2bplayer.bean.v3.Thumbnails;
import com.ijiaban.u2bplayer.bean.v3.SearchResultsItem;
import com.ijiaban.u2bplayer.bean.v3.Video;
 
/**
 * 视频信息
 * @author Administrator
 *
 */
public class VideoDao {
	/**
	 * 获取查询列表
	 * @param pageBean
	 * @return
	 * @throws JSONException
	 */
	public static  List<SearchResultsItem> getSearchList(PageBean pageBean) throws JSONException{
		PageBean myBean = pageBean;
		JSONArray jsonArray = myBean.getJsonArray();
		List<SearchResultsItem> searchResultsItems  = new ArrayList<SearchResultsItem>();
		
		for(int i = 0;i<jsonArray.length();i++){ 
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			JSONObject id = null; 
			if(((JSONObject)jsonObject.get("snippet")).has("resourceId")){
				id =  (JSONObject) ((JSONObject)jsonObject.get("snippet")).get("resourceId");  
			}else{ 
				id =  (JSONObject) jsonObject.get("id");  
			} 
			String kind = id.getString("kind").split("#")[1];
			String itemId = id.getString(kind+"Id");
			JSONObject snippet = (JSONObject) jsonObject.get("snippet");
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd"); 
			Date publishedAt = null;
			try {
				publishedAt = sim.parse(snippet.getString("publishedAt"));
			} catch (ParseException e) {
				 System.out.println(e.getMessage().toString());
			} 
			String title = snippet.getString("title");
			String description = snippet.getString("description");
			String channelTitle = snippet.getString("channelTitle");
			
			JSONObject thumbnails = snippet.getJSONObject("thumbnails");
			JSONObject default_url_obj = thumbnails.getJSONObject("default");
			JSONObject medium_obj = thumbnails.getJSONObject("medium");
			JSONObject high_obj = thumbnails.getJSONObject("high");
			
			String default_url = default_url_obj.getString("url");
			String medium  = medium_obj.getString("url");
			String high  = high_obj.getString("url");
			
			Thumbnails thumb = new Thumbnails(default_url, medium, high);
			SearchResultsItem searchResultsItem = new SearchResultsItem(itemId,kind, publishedAt, title, description, channelTitle, thumb);
			searchResultsItems.add(searchResultsItem); 
		} 
		return searchResultsItems;
	} 
	/** 
	 * 获取videos的列表   video.list
	 * @param pageBean
	 * @return
	 * @throws JSONException
	 */
	public static List<Video> getVideoByCondition(PageBean pageBean) throws JSONException{
		List<Video> videos = new ArrayList<Video>();
		JSONArray jsonArray = pageBean.getJsonArray();
		for(int i = 0;i<jsonArray.length();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String id = jsonObject.getString("id");
			JSONObject snippet = jsonObject.getJSONObject("snippet");
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd"); 
			Date publishedAt = null;
			try {
				publishedAt = sim.parse(snippet.getString("publishedAt"));
			} catch (ParseException e) {
				 System.out.println(e.getMessage().toString());
			}
			String channelId = snippet.getString("channelId");
			String title = snippet.getString("title");
			String description = snippet.getString("description");
			JSONObject thumbnails = snippet.getJSONObject("thumbnails");
			
			JSONObject default_url_obj = thumbnails.getJSONObject("default");
			JSONObject medium_obj = thumbnails.getJSONObject("medium");
			JSONObject high_obj = thumbnails.getJSONObject("high");
			String standard = "";
			if(thumbnails.has("standard")){
				JSONObject standard_obj = thumbnails.getJSONObject("standard");
				standard = standard_obj.getString("url");
			}
			String  maxres = "";
			if(thumbnails.has("maxres")){
				JSONObject maxres_obj = thumbnails.getJSONObject("maxres"); 
				maxres = maxres_obj.getString("url");
			}
			
			Statistics statistics = null;
			if(jsonObject.has("statistics")){
				JSONObject statisticsjson = jsonObject.getJSONObject("statistics");
				int viewCount = statisticsjson.getInt("viewCount");
				int likeCount = statisticsjson.getInt("likeCount");
				int dislikeCount = statisticsjson.getInt("dislikeCount");
				int favoriteCount = statisticsjson.getInt("favoriteCount");
				int commentsCount  = statisticsjson.getInt("commentCount");
				 
				statistics =  new Statistics(viewCount, likeCount, dislikeCount, favoriteCount, commentsCount);
			} 
			String default_url = default_url_obj.getString("url");
			String medium = medium_obj.getString("url");
			String high = high_obj.getString("url"); 
			Thumbnails thumb = new Thumbnails(default_url, medium, high, standard, maxres);
			String channelTitle = snippet.getString("channelTitle");
			String categoryId = snippet.getString("categoryId");
			String kind = jsonObject.getString("kind").split("#")[1];    
			Video video = new Video(id, kind, publishedAt, channelId, title, description, channelTitle, categoryId, thumb);
			video.setStatistics(statistics);
			videos.add(video);
		} 
		return videos;
	}  
}
