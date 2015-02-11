package com.ijiaban.u2bplayer.common.dao.v3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.common.bean.ResourceId;
import com.ijiaban.u2bplayer.common.bean.SubItem;
import com.ijiaban.u2bplayer.common.bean.Thumbnails;


public class SubPlaylistDao {
	public static List<SubItem> getplaylist(PageBean pageBean) throws JSONException, ParseException{
		 PageBean myBean = pageBean;
		  JSONArray jsonArray=myBean.getJsonArray();
		  List<SubItem> subitems=new ArrayList<SubItem>();
		  for(int i=0;i<jsonArray.length();i++){
			  JSONObject object=jsonArray.getJSONObject(i);
			  String id=object.getString("id");
			  String kind=object.getString("kind").split("#")[1];
			  JSONObject snippet=object.getJSONObject("snippet");
			  String channelId=snippet.getString("channelId");
			  String title=snippet.getString("title");
			  SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");	
	          Date publishedAt = null;
	          publishedAt=dateformat.parse(snippet.getString("publishedAt"));
	          String description=snippet.getString("description");
	          JSONObject thumbnails=snippet.getJSONObject("thumbnails");
	          JSONObject default_nail=thumbnails.getJSONObject("default");
	          String default_url=default_nail.getString("url");
	          JSONObject high=thumbnails.getJSONObject("high");
	          String hith_url=high.getString("url");
	          Thumbnails thumb =new Thumbnails(default_url, hith_url);
	          JSONObject resourceids=snippet.getJSONObject("resourceId");
	          String resource_kind=resourceids.getString("kind").split("#")[1];
	          String resource_channelid=resourceids.getString("channelId");
	          ResourceId resourceid=new ResourceId(resource_kind, resource_channelid);
	          //String id, String kind, Date publishedAt, String channelId,
				//String description, String title, Thumbnails thumbnails,
				//ResourceId resourceid
	          SubItem subitem=new SubItem(id, kind, publishedAt, channelId, description, title, thumb, resourceid);
	          subitems.add(subitem);
		  }
		return subitems;
		
	}

}
