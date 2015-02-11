package com.ijiaban.u2bplayer.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.bean.v3.VideoCategory;

public class VideoCategoryDao {

	public static List<VideoCategory> getVideoCategories(PageBean pageBean) throws JSONException{
		JSONArray jsonArray = pageBean.getJsonArray();
		List<VideoCategory> categories = new ArrayList<VideoCategory>();
		
		for(int i = 0;i<jsonArray.length();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			int id = jsonObject.getInt("id");
			JSONObject snippet = (JSONObject) jsonObject.get("snippet");
			String channelId = snippet.getString("channelId");
			String title = snippet.getString("title");
			boolean assignable = snippet.getBoolean("assignable");
			if(assignable){//true 是视频格式的可以查找视频，FALSE 查找不到结果
//				VideoCategory videoCategory = new VideoCategory(id, channelId, title,assignable);
				VideoCategory videoCategory=new VideoCategory(id, channelId, title,assignable);
				categories.add(videoCategory);
			} 
		} 
		return categories;
	}
}
