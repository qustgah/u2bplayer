package com.ijiaban.fragment;

import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONException;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.bean.v3.SearchResultsItem;
import com.ijiaban.u2bplayer.bean.v3.Video;
import com.ijiaban.u2bplayer.common.bean.Constants;
import com.ijiaban.u2bplayer.common.bean.DeveloperKey;
import com.ijiaban.u2bplayer.dao.VideoDao;
import com.ijiaban.uitls.PageBeanAsyncTaskLoader;
import com.iiijiaban.u2bplayer.R;

public class VideoDetail extends SherlockFragment implements	LoaderCallbacks<PageBean> {
	private TextView video_title;
	private TextView video_publishAt;
	private TextView video_viewCount;
	private TextView video_description;
	private String _query;// ≤È’“Õ¯÷∑
	private String videoid;
	private PageBean pageBean;
	private Video video;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View rooterView=inflater.inflate(R.layout.fragment_video_detail, container,false);
		
		videoid = getActivity().getIntent().getStringExtra("videoid") ;
		_query = Constants.URL_VIDEODETAIL+"&id="+videoid+"&key="+DeveloperKey.DEVELOPER_KEY;
		video_title =(TextView)rooterView.findViewById(R.id.video_title);
		video_publishAt = (TextView) rooterView.findViewById(R.id.video_publishAt);
		video_viewCount = (TextView) rooterView.findViewById(R.id.video_viewCount);
		video_description = (TextView) rooterView.findViewById(R.id.video_description); 
		getLoaderManager().initLoader(100, null, this);
		return rooterView;
	}
	@Override
	public Loader<PageBean> onCreateLoader(int arg0, Bundle arg1) {
		PageBeanAsyncTaskLoader loader = new PageBeanAsyncTaskLoader(getActivity(), _query); 
		return loader;
	}
	@Override
	public void onLoadFinished(Loader<PageBean> arg0, PageBean arg1) {
		pageBean=arg1;
		List<Video> list = null;
		try {
			list = VideoDao.getVideoByCondition(arg1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		video = list.get(0);
		video_title.setText(video.getTitle());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String publishedAt = sdf.format(video.getPublishedAt());
		video_publishAt.setText("PublishedAt: "+publishedAt);
		video_viewCount.setText(String.valueOf(video.getStatistics().getViewCount())+" views");
		video_description.setText(video.getDescription());
	}
	@Override
	public void onLoaderReset(Loader<PageBean> arg0) {
		// TODO Auto-generated method stub
		
	}
}
