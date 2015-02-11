package com.ijiaban.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.ijiaban.fragment.SearchListFragment.ViewHolder;
import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.bean.v3.Video;
import com.ijiaban.u2bplayer.common.bean.Constants;
import com.ijiaban.u2bplayer.common.bean.DeveloperKey;
import com.ijiaban.u2bplayer.dao.VideoDao;
import com.ijiaban.uitls.PageBeanAsyncTaskLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.qd.gah.u2bplayer.BofangActivity;
import com.iiijiaban.u2bplayer.R;
import com.qd.gah.u2bplayer.VideosWithDragpannelActivity;
import com.qd.gah.u2bplayer.adapter.CommonBaseAdapter;

public class VideosFragment extends ListFragment implements LoaderCallbacks<PageBean> {
	public ImageLoader imageloader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.stub_image) // 设置图片下载期间显示的图片
			.showImageForEmptyUri(R.drawable.stub_image) // 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(R.drawable.stub_image) // 设置图片加载或解码过程中发生错误显示的图片
			.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
			.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
			.displayer(new RoundedBitmapDisplayer(10)) // 设置成圆角图片
			.build(); // 创建配置过得DisplayImageOption对象

	private List<Video> videos;
	public PageBean pageBean;
	private CommonBaseAdapter adapter;
	private String url_query;
	public boolean isloading = false ;//网络任务是否正在进行
	public boolean ishas = false;

	private static long categoryId;
	public View progressview;

 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		videos = new ArrayList<Video>();
		System.out.print(categoryId);
		categoryId = getActivity().getIntent().getLongExtra("categoryId", 1);
		

		url_query = Constants.URL_VIDEOSBYVIDEOCATEGORY+"&videoCategoryId="+categoryId+"&key="+DeveloperKey.DEVELOPER_KEY; 
		adapter = new CommonBaseAdapter(getActivity(), new ArrayList<Video>()) {
			@Override
			public int getCount() { 
				return _data.size() ;
			}
			
			@Override
			public View InitView(int position, View convertView,
					ViewGroup parent) {
				// TODO 
				ViewHolder holder;
				imageloader.init(ImageLoaderConfiguration.createDefault(context));
				if (convertView == null) {
					convertView = _inflater.inflate(R.layout.item_search_list,null);
					holder = new ViewHolder();
					holder.imageView = (ImageView) convertView.findViewById(R.id.item_searchlist_image);
					holder.nameTextView = (TextView) convertView.findViewById(R.id.item_searchlist_text);
					holder.publishView = (TextView) convertView.findViewById(R.id.item_searchlist_published);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				String name = ((Video) _data.get(position)).getTitle();  
				holder.nameTextView.setText(name);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(((Video) _data.get(position)).getPublishedAt()); 
				holder.publishView.setText(dateString);
				imageloader.displayImage(((Video) _data.get(position)).getThumbnails().getMedium(),holder.imageView, options);
				if(ishas && position == (getCount()-1)){
					url_query = Constants.URL_VIDEOSBYVIDEOCATEGORY+"&videoCategoryId="+categoryId+"&pageToken="+pageBean.getNextPageToken()+"&key="+DeveloperKey.DEVELOPER_KEY;
					getLoaderManager().restartLoader(5, null,VideosFragment.this);
				} 
				return convertView;
			}
		};
		progressview = getLayoutInflater(null).inflate(R.layout.youtube_video_list_item_loading, null, false);
		getListView().addFooterView(progressview);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO  list点击事件
				final Video item = (Video) getListView().getItemAtPosition(position);

            	//安装了youtube
                if(YouTubeIntents.isYouTubeInstalled(getActivity().getApplicationContext())) {
             	   if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity()) == YouTubeInitializationResult.SUCCESS) {
             	   //版本正常，跳转到界面
             		  Intent intent =new Intent();
                   	intent.putExtra("videoid", item.getId());
                  	intent.setClass(getActivity(), VideosWithDragpannelActivity.class);
                  	startActivity(intent);
             	 } else if(YouTubeIntents.canResolvePlayVideoIntent(getActivity())) {
             	   //版本不行，跳转到youtube
             		getActivity().startActivity(
             	   YouTubeIntents.createPlayVideoIntent(getActivity(),item.getId()));
             	 }
             	
             	}
               //没有安装youtbe
                else{
                	AlertDialog log=new AlertDialog.Builder(getActivity()).setTitle(R.string.waring)
                			.setMessage(getActivity().getResources().getString(R.string.detailwaring))
                			.setIcon(R.drawable.ic_launcher2).setPositiveButton(getActivity().getResources().getString(R.string.youtubedown), new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									//下载youtube
									Intent intent=new Intent(Intent.ACTION_VIEW);

					                intent.setData(Uri.parse("market://details?id=com.google.android.youtube"));

					                startActivity(intent);
					                dialog.dismiss();
								}
							}).setNegativeButton(getActivity().getResources().getString(R.string.browser), new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									/**
									 *跳转浏览器
									 */
									Intent intent = new Intent();
			                        intent.setAction("android.intent.action.VIEW");
			                        Uri content_url = Uri.parse("https://www.youtube.com/watch?v="+item.getId());
			                        intent.setData(content_url);
			                        startActivity(intent);	
			                        dialog.dismiss();
								}
							}).create();
                	log.show();
                	
                	
                }
                 
            
//				Intent intent =new Intent();
//             	intent.putExtra("videoid", item.getId());
//            	intent.setClass(getActivity(), VideosWithDragpannelActivity.class);
// 
//            	startActivity(intent); 
			}
		}); 
		setListShown(false);
		getLoaderManager().initLoader(5, null,this);
	}

	@Override
	public Loader<PageBean> onCreateLoader(int id, Bundle args) { 
		isloading = true;
		progressview.setVisibility(View.VISIBLE);
		return new PageBeanAsyncTaskLoader(getActivity(), url_query) ;
	}

	@Override
	public void onLoadFinished(Loader<PageBean> loader, PageBean data) {
		try {
			pageBean = data;
			if(pageBean.getNextPageToken()!=null&&!pageBean.getNextPageToken().equals("")){
				ishas=true;
			}else{
				ishas = false; 
				Toast.makeText(getActivity(), "There is No data", Toast.LENGTH_LONG).show();
			}
			progressview.setVisibility(View.GONE);
			List<Video> list = VideoDao.getVideoByCondition(pageBean);
			videos.addAll(list);
		} catch (JSONException e){ 
			e.printStackTrace();
		}
		isloading = false;
		adapter.setData(videos);
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<PageBean> loader) {
		// TODO Auto-generated method stub
		adapter.setData(new ArrayList<Video>());
	}
    public  void use(long id){
}
}
