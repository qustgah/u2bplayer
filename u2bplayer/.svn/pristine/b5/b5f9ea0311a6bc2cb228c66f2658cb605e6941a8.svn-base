package com.qd.gah.u2bplayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.iiijiaban.u2bplayer.R;
import com.ijiaban.u2bplayer.bean.v3.Comments;
import com.ijiaban.u2bplayer.common.bean.DeveloperKey;
import com.ijiaban.u2bplayer.common.bean.XmlPageBean;
import com.ijiaban.u2bplayer.common.dao.XmlCommonDao;
import com.ijiaban.u2bplayer.dao.v2.CommentsDao;
import com.qd.gah.u2bplayer.adapter.ListViewAdapter;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class VideoPlayActivity extends YouTubeFailureRecoveryActivity{
	private String videoid ;
	private ListView listview;
	private ListViewAdapter adapter;
	private List<Comments> comments = new ArrayList<Comments>();
	private String path;
	
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    setContentView(R.layout.activity_video_play);
	    
	    YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
	    youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, this);
	    listview = (ListView) findViewById(R.id.list_comments);
	    videoid = "NUsoVlDFqZg";
	    path = "http://gdata.youtube.com/feeds/api/videos/"+videoid+"/comments?v=2&orderby=published";
	    adapter = new ListViewAdapter(comments,this) {
			
			@Override
			public View setView(View convertView, int position, ViewGroup parent) {
				// TODO 评论的功能的显示
				TextView text ;
				if(convertView==null){
					convertView = _inflater.inflate(R.layout.item_comments, null);
					text = (TextView) convertView.findViewById(R.id.item_comments_text);
				}else{
					text = (TextView) convertView.findViewById(R.id.item_comments_text);	
				}
				String s=comments.get(position).getContent();
				text.setText(comments.get(position).getContent());
				return convertView;
			} 
			@Override
			public long setItemId(int position) {
				// TODO 
				return position;
			}
		};
		getCommentsData();
		listview.setAdapter(adapter);
		}
		
	public void getCommentsData(){
		new AsyncTask<Void, String,List<Comments> >() {

			@Override
			protected List<Comments> doInBackground(Void... params) {
				// TODO  网络线程
				try {
					XmlPageBean pageBean = CommentsDao.getCommentsByUrl(path);
					comments = (List<Comments>) pageBean.getList();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return comments;
			}
			protected void onPostExecute(List<Comments> result) {
				adapter.setDatas(comments);
			};
		}.execute();
	} 
	
	  @Override
	  public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
	      boolean wasRestored) {
	    if (!wasRestored) {
	      player.cueVideo(videoid);
	    }
	  }

	  @Override
	  protected YouTubePlayer.Provider getYouTubePlayerProvider() {
	    return (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
	  }
}
