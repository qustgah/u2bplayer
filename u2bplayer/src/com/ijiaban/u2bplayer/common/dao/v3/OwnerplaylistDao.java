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
import com.ijiaban.u2bplayer.common.bean.OwnerPlists;
import com.ijiaban.u2bplayer.common.bean.Thumbnails;

/**
 * 获取自己的播放列表存放到list中 。
 * {
 "kind": "youtube#playlistListResponse",
 "etag": "\"kjEFmP90GvrCl8BObMQtGoRfgaQ/ee0sWAOm2O2TxctQHSHXLl4QPsY\"",
 "pageInfo": {
  "totalResults": 3,
  "resultsPerPage": 50
 },
 "items": [
  {
   "kind": "youtube#playlist",
   "etag": "\"kjEFmP90GvrCl8BObMQtGoRfgaQ/uEp075N_S93u9Jxl98JHGX16zAg\"",
   "id": "PL8soSflgPJSy2OFSkKzr929faIATO2jFm",
   "snippet": {
    "publishedAt": "2014-09-05T08:30:05.000Z",
    "channelId": "UCWA0gJ2gVLIL7yYmqeLh1BA",
    "title": "google",
    "description": "",
    "thumbnails": {
     "default": {
      "url": "https://i.ytimg.com/vi/LKRpX98Hmok/default.jpg",
      "width": 120,
      "height": 90
     },
     "medium": {
      "url": "https://i.ytimg.com/vi/LKRpX98Hmok/mqdefault.jpg",
      "width": 320,
      "height": 180
     },
     "high": {
      "url": "https://i.ytimg.com/vi/LKRpX98Hmok/hqdefault.jpg",
      "width": 480,
      "height": 360
     },
     "standard": {
      "url": "https://i.ytimg.com/vi/LKRpX98Hmok/sddefault.jpg",
      "width": 640,
      "height": 480
     }
    },
    "channelTitle": "Xp Lee"
   }
  }
 * @author adamin
 * 
 *
 */
public class OwnerplaylistDao {
  public static List<OwnerPlists> getPlaylist (PageBean pageBean) throws JSONException, ParseException{
	  PageBean myBean = pageBean;
	  JSONArray jsonArray=myBean.getJsonArray();
	  List <OwnerPlists> ownerplasts=new ArrayList<OwnerPlists>();
	  for (int i=0;i<jsonArray.length();i++){
		  JSONObject object=jsonArray.getJSONObject(i);
		  String id=object.getString("id");
		  String kind=object.getString("kind").split("#")[1];
		  JSONObject snippet=object.getJSONObject("snippet");
		  String channelId=snippet.getString("channelId");
		  String channelTitle=snippet.getString("channelTitle");
		  String title=snippet.getString("title");
          SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");	
          Date publishedAt = null;
          publishedAt=dateformat.parse(snippet.getString("publishedAt"));
          String description=snippet.getString("description");
          JSONObject thumbnails=snippet.getJSONObject("thumbnails");
          JSONObject default_nail=thumbnails.getJSONObject("default");
          String default_url=default_nail.getString("url");
          JSONObject medium=thumbnails.getJSONObject("medium");
          String medium_url=medium.getString("url");
          JSONObject high=thumbnails.getJSONObject("high");
          String hith_url=high.getString("url");
//          JSONObject standard=thumbnails.getJSONObject("standard");
//          String standard_url=standard.getString("url");
          Thumbnails thumb=new Thumbnails(default_url, medium_url, hith_url);
          OwnerPlists ownerplist=new OwnerPlists(id, kind, publishedAt, channelId, description, title, thumb,channelTitle);
          ownerplasts.add(ownerplist);
	  }
	return ownerplasts;
	  
  }
}
 