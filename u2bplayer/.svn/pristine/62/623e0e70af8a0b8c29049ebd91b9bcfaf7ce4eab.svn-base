package com.ijiaban.uitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ijiaban.fragment.MainFragment;
import com.ijiaban.u2bplayer.common.dao.v3.CommonDao;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.AsyncTask;
import android.util.Log;

public abstract class AbstractGetChannelList extends AsyncTask<Void, Void, Void>{
	 private static final String TAG = "TokenInfoTask";
	    private static final String NAME_KEY = "name";
	    private String API_KEY="AIzaSyB0HBrXwJ3OFye2jHrqdy9pSXngcibObQg";
	    protected MainFragment mFragment;
	    //获取频道的播放列表 
	    private String channeldetails="https://www.googleapis.com/youtube/v3/channelSections?part=contentDetails&channelId=";
	    //activities
	    protected String mScope;
	    protected String mEmail;
	    private String channelId="UCpko_-a4wgz2u_DgDgd9fqA";  //初始化一个  
	    private ImageLoader imageloader;
	public AbstractGetChannelList(MainFragment mFragment, String mScope,
				String mEmail) {
			super();
			this.mFragment = mFragment;
			this.mScope = mScope;
			this.mEmail = mEmail;
		}
	@Override
	protected Void doInBackground(Void... params) {
		  try {
			fetchSubsChannelsfrmpprofileServer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 protected abstract String fetchToken() throws IOException;
	private void fetchSubsChannelsfrmpprofileServer() throws Exception {
		 String token = fetchToken();
         if (token == null) {
           return;
         }
         channelId=MainFragment.CHANNNEL_ID;
         String ssd=channeldetails+channelId+"&access_token="+token;
         String channeljson=CommonDao.getDataFromServer(ssd);
         List<String> channelplaylistids=new ArrayList<String>();
         channelplaylistids=CommonDao.StringtoArray(channeljson);
	}
	protected void onError(String msg, Exception e) {
        if (e != null) {
          Log.e(TAG, "Exception: ", e);
        }
        mFragment.show(msg);  // will be run in UI thread
    }
}
