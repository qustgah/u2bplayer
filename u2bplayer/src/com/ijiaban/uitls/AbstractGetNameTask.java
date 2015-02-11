/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ijiaban.uitls;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.ijiaban.fragment.MainFragment;
import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.common.bean.OwnerPlists;
import com.ijiaban.u2bplayer.common.bean.SubItem;
import com.ijiaban.u2bplayer.common.dao.v3.CommonDao;
import com.ijiaban.u2bplayer.common.dao.v3.OwnerplaylistDao;
import com.ijiaban.u2bplayer.common.dao.v3.SubPlaylistDao;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Display personalized greeting. This class contains boilerplate code to consume the token but
 * isn't integral to getting the tokens.
 */
public abstract class AbstractGetNameTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "TokenInfoTask";
    private static final String NAME_KEY = "name";
    private String API_KEY="AIzaSyB0HBrXwJ3OFye2jHrqdy9pSXngcibObQg";
    protected MainFragment mFragment;
    //自己的播放列表 
    private String test="https://www.googleapis.com/youtube/v3/playlists?part=snippet&mine=true&maxResults=50&key=AIzaSyAyeP4S7nYrl8mcAtQLLK2Ycbod5l3K_TY&access_token=";
    //我的频道 
    private String mychannels="https://www.googleapis.com/youtube/v3/channels?part=snippet&mine=true&key=AIzaSyAyeP4S7nYrl8mcAtQLLK2Ycbod5l3K_TY&access_token=";
   //我的订阅 
    private String subschannels="https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true&maxResults=50&access_token=";
    //获取频道的播放列表 
    private String channeldetails="https://www.googleapis.com/youtube/v3/channelSections?part=contentDetails&channelId=";
    //activities
    protected String mScope;
    protected String mEmail;
    private String channelId="UCpko_-a4wgz2u_DgDgd9fqA";  //初始化一个  
    private ImageLoader imageloader;
/**
 * 接受mainfragment传递的channelid
 * @param id
 */
//    @Override
//	public void onDingyueSelected(String id) {
//    	channelId =id;
//	}

	AbstractGetNameTask(MainFragment fragment, String email, String scope) {
        this.mFragment = fragment;
        this.mScope = scope;
        this.mEmail = email;
    }

    @Override
    protected Void doInBackground(Void... params) {
      try {
        fetchNameFromProfileServer();
        fetchImageFromProfileServer();
        fetchSubsChannelsfrmpprofileServer();
      } catch (IOException ex) {
        onError("Following Error occured, please try again. " + ex.getMessage(), ex);
      } catch (JSONException e) {
        onError("Bad response: " + e.getMessage(), e);
      } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return null;
    }

    private void fetchSubsChannelsfrmpprofileServer() throws Exception {
    	 String token = fetchToken();
         if (token == null) {
           // error has already been handled in fetchToken()
           return;
         }
         channelId=MainFragment.CHANNNEL_ID;
         String path=subschannels+token;
         String ssd=channeldetails+channelId+"&access_token="+token;
         String json=CommonDao.getDataFromServer(path);
         String channeljson=CommonDao.getDataFromServer(ssd);
         List<String> channelplaylistids=new ArrayList<String>();
         channelplaylistids=CommonDao.StringtoArray(channeljson);
         PageBean bean=CommonDao.StringToPageBean(json);
         List<SubItem> subitems=new ArrayList<SubItem>();
         subitems=SubPlaylistDao.getplaylist(bean);
         mFragment.listsubitemlist(subitems);		
	}

	private void fetchIMGFromProfileSever() throws IOException, JSONException {
    	String token=fetchToken();
    	if(token==null){
    		return;
    	}
		URL url2=new URL(test+token);
	    URL url3=new  URL(channeldetails+token);
		HttpURLConnection con2=(HttpURLConnection)url2.openConnection();
		int sc2=con2.getResponseCode();
		if(sc2==HttpURLConnection.HTTP_OK){
			InputStream is2=con2.getInputStream();
			String image=getImage(readResponse(is2));
			Toast.makeText(mFragment.getActivity(), image, 5000).show();
			is2.close();
			return;
		}
		else if(sc2==401){
			GoogleAuthUtil.invalidateToken(mFragment.getActivity(), token);
			onError("Server auth error, please try again.", null);
			 return;
		}
		else{
			 onError("Server returned the following error code: " + sc2, null);
	          return;
		}
	}

	protected void onError(String msg, Exception e) {
        if (e != null) {
          Log.e(TAG, "Exception: ", e);
        }
        mFragment.show(msg);  // will be run in UI thread
    }

    /**
     * Get a authentication token if one is not available. If the error is not recoverable then
     * it displays the error message on parent activity.
     */
    protected abstract String fetchToken() throws IOException;

    /**
     * Contacts the user info server to get the profile of the user and extracts the first name
     * of the user from the profile. In order to authenticate with the user info server the method
     * first fetches an access token from Google Play services.
     * @throws IOException if communication with user info server failed.
     * @throws JSONException if the response from the server could not be parsed.
     */
    private void fetchNameFromProfileServer() throws IOException, JSONException {
    	 String token = fetchToken();
         if (token == null) {
           // error has already been handled in fetchToken()
           return;
         }
         URL url = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token);
         
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         int sc = con.getResponseCode();
         if (sc == HttpURLConnection.HTTP_OK) {
           InputStream is = con.getInputStream();
           String detail=readResponse(is);
           JSONObject jb=new JSONObject(detail);
           
           String name = getFirstName(detail);
           String image=getImage(detail);
           mFragment.show("Welcome " + name + "!");
           mFragment.displayimage(image);
           
           is.close();
           return;
         } else if (sc == 401) {
             GoogleAuthUtil.invalidateToken(mFragment.getActivity(), token);
             onError("Server auth error, please try again.", null);
             Log.i(TAG, "Server auth error: " + readResponse(con.getErrorStream()));
             return;
         } else {
           onError("Server returned the following error code: " + sc, null);
           return;
         }
    }
    private void fetchImageFromProfileServer() throws Exception {
    	 String token = fetchToken();
         if (token == null) {
           // error has already been handled in fetchToken()
           return;
         }
         String path=test+token;
         String json=CommonDao.getDataFromServer(path);
         PageBean bean=CommonDao.StringToPageBean(json);
         List<OwnerPlists> ownerplists=new ArrayList<OwnerPlists>();
         ownerplists=OwnerplaylistDao.getPlaylist(bean);
         mFragment.listplaylist(ownerplists);
    }
    /**
     * Reads the response from the input stream and returns it as a string.
     * 把返回的json转化为的输入流转化成String形式
     * {
 "id": "118128481591363949098",
 "name": "Xp Lee",
 "given_name": "Xp",
 "family_name": "Lee",
 "link": "https://plus.google.com/118128481591363949098",
 "picture": "https://lh3.googleusercontent.com/-LFld6F-jurc/AAAAAAAAAAI/AAAAAAAAAA8/QKveWJwLYyw/photo.jpg",
 "gender": "male",
 "locale": "zh-CN"
}

     */
    private static String readResponse(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[2048];
        int len = 0;
        while ((len = is.read(data, 0, data.length)) >= 0) {
            bos.write(data, 0, len);
        }
        return new String(bos.toByteArray(), "UTF-8");
    }

    /**
     * Parses the response and returns the first name of the user.
     * 把string 转化为json像 ，提取信息 
     * @throws JSONException if the response is not JSON or if first name does not exist in response
     */
    private String getFirstName(String jsonResponse) throws JSONException {
      JSONObject profile = new JSONObject(jsonResponse);
      return profile.getString(NAME_KEY);
    }
    private String getImage(String jsonResponse) throws JSONException {
    	JSONObject obj =new JSONObject(jsonResponse);
    	
		return obj.getString("picture");
    	
    }
}

