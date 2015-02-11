package com.qd.gah.u2bplayer;

import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import com.iiijiaban.u2bplayer.R;
import com.ijiaban.u2bplayer.bean.Video;
import com.ijiaban.u2bplayer.common.bean.XmlPageBean;
import com.ijiaban.u2bplayer.dao.VideoDao;
import com.qd.gah.u2bplayer.adapter.ListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class VideosActivity extends Activity{

	private ListView listView ;
	private TextView textView;
	
	private XmlPageBean xmlPageBean;
	private List<Video> videos;
	private String path;
	private ListViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 获取视频列表的activity
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videos_activity);
		
		String category = getIntent().getStringExtra("category");
		
		listView = (ListView) findViewById(R.id.videos_list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 
				Video video = (Video) listView.getItemAtPosition(position);
				Intent intent = new Intent(VideosActivity.this, VideoPlayActivity.class);
				intent.putExtra("videoid",video.getVideoid());
				startActivity(intent);
					 
			}
			
		});  
		
		path = "http://gdata.youtube.com/feeds/api/standardfeeds/top_rated_"+category+"?v=2&max-results=5&start-index=1";
		
		new Thread(){
			public void run() {
				try {
					videos = (List<Video>) xmlPageBean.getList();
					Message msg = new Message();
					msg.what = 1 ;
					handler.sendMessage(msg); 
				} catch (Exception e) { 
					e.printStackTrace();
				}  
			};
		}.start();
		
		
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				adapter = new ListViewAdapter(videos,VideosActivity.this) {
					
					@Override
					public View setView(View convertView, int position, ViewGroup parent) {
						// TODO Auto-generated method stub
						View view ;
						TextView txtv;
						if(convertView == null){
							view = getLayoutInflater().inflate(R.layout.video_item, null);
							txtv = (TextView) view.findViewById(R.id.video);
						}else{
							view = convertView;
							txtv = (TextView) view.findViewById(R.id.video);
						}
						txtv.setText(videos.get(position).getTitle());
						return view;
					}
					@Override
					public long setItemId(int position) {
						// TODO Auto-generated method stub
						return position;
					}
				}; 
				listView.setAdapter(adapter); 
				break;

			default:
				break;
			}
		}
	};
	
}




















