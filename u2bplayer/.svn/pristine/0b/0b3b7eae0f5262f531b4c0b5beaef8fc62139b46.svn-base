package com.ijiaban.fragment;

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
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

import com.actionbarsherlock.app.SherlockListFragment;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.bean.v3.SearchResultsItem;
import com.ijiaban.u2bplayer.common.bean.DeveloperKey;
import com.ijiaban.u2bplayer.dao.VideoDao;
import com.ijiaban.uitls.PageBeanAsyncTaskLoader;
import com.ijiaban.youtubeplayer.ui.MainActiivty;
import com.lt.test.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.qd.gah.u2bplayer.BofangActivity;
import com.qd.gah.u2bplayer.ChannelActivity;
import com.qd.gah.u2bplayer.ChannelWithSearchActivity;
import com.qd.gah.u2bplayer.VideosByChannelActivity;
import com.iiijiaban.u2bplayer.R;
import com.qd.gah.u2bplayer.adapter.CommonBaseAdapter;

public class ChannelWithSearchFragment extends SherlockListFragment implements	LoaderCallbacks<PageBean>,
OnQueryTextListener{

	public ImageLoader imageloader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_launcher2) // 设置图片下载期间显示的图片
			.showImageForEmptyUri(R.drawable.ic_launcher2) // 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(R.drawable.ic_launcher2) // 设置图片加载或解码过程中发生错误显示的图片
			.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
			.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
			.displayer(new RoundedBitmapDisplayer(5)) // 设置成圆角图片
			.build(); // 创建配置过得DisplayImageOption对象
	private String _curFilter; // if non-null, this is the current filter the
								// user has provided.
	private String _query;// 查找网址
	private CommonBaseAdapter _adapter; // the adapter
	private List<SearchResultsItem> results ;
	PageBean pageBean;
	int loaderId; 
	
	 TextView clickmore; 
	 ProgressBar pgb;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	} 
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// set an empty filter
		_curFilter = null;
		// has menu
		setHasOptionsMenu(true);
		results = new ArrayList<SearchResultsItem>();
		_query = Constants.URL_SEARCHLIST +"&channelId="+MainFragment.CHANNNEL_ID+ "&key="+ DeveloperKey.DEVELOPER_KEY;
		// set empty adapter
		_adapter = new CommonBaseAdapter(getActivity(),new ArrayList<SearchResultsItem>()) {

			@Override
			public View InitView(int position, View convertView,ViewGroup parent) {
				ViewHolder holder;
				imageloader.init(ImageLoaderConfiguration.createDefault(context));
				if (convertView == null) {
					convertView = _inflater.inflate(R.layout.item_search_list,null);
					holder = new ViewHolder();
					holder.imageView = (ImageView) convertView.findViewById(R.id.item_searchlist_image);
					holder.nameTextView = (TextView) convertView.findViewById(R.id.item_searchlist_text);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				String name = ((SearchResultsItem) _data.get(position)).getTitle();
				int index = TextUtils.isEmpty(_query) ? -1 : name.toLowerCase()
						.indexOf(_query.toLowerCase());
				if (index == -1) {
					holder.nameTextView.setText(name);
					imageloader.displayImage(((SearchResultsItem) _data.get(position))
							.getThumbnails().getDefault_url(),
							holder.imageView, options);
				} else {
					SpannableStringBuilder ssb = new SpannableStringBuilder(name);
					ssb.setSpan(_querySpan, index, index + _query.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
					holder.nameTextView.setText(ssb);
					imageloader.displayImage(((SearchResultsItem) _data.get(position)).getThumbnails().getMedium(),holder.imageView, options);
				}
				return convertView;
			}
		};
		LayoutInflater inf = LayoutInflater.from(getActivity());
		View view = inf.inflate(R.layout.clickbutton, null);
		clickmore = (TextView) view.findViewById(R.id.clickmore);
		clickmore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				clickmore.setVisibility(View.GONE);
				_query =  Constants.URL_SEARCHLIST+"&channelId="+MainFragment.CHANNNEL_ID+"&pageToken="+pageBean.getNextPageToken()+"&key="+ DeveloperKey.DEVELOPER_KEY; 
				getLoaderManager().restartLoader(loaderId, null, ChannelWithSearchFragment.this); 
			}
		});
		getListView().addFooterView(view);
		setListAdapter(_adapter); 
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				// TODO  list点击事件
				final SearchResultsItem item = (SearchResultsItem) getListView().getItemAtPosition(position);
            	if(item.getKind().equals("playlist")){
//            		https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PL3ZQ5CpNulQnxWhokM8GdNgv_AcmsCft5&key={YOUR_API_KEY}
            		Intent intent=new Intent();
    				intent.putExtra("playlistid", item.getItemId()); 
					intent.setClass(getActivity(), VideosByChannelActivity.class);
					startActivity(intent); 
            	}else{
            		//安装了youtube
                    if(YouTubeIntents.isYouTubeInstalled(getActivity().getApplicationContext())) {
                 	   if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity()) == YouTubeInitializationResult.SUCCESS) {
                 	   //版本正常，跳转到界面
                 		  Intent intent =new Intent();
                       	intent.putExtra("videoid", item.getItemId());
                      	intent.setClass(getActivity(),ChannelWithSearchActivity.class);
                      	startActivity(intent);
                 	 } else if(YouTubeIntents.canResolvePlayVideoIntent(getActivity())) {
                 	   //版本不行，跳转到youtube
                 		getActivity().startActivity(
                 	   YouTubeIntents.createPlayVideoIntent(getActivity(),item.getItemId()));
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
    			                        Uri content_url = Uri.parse("https://www.youtube.com/watch?v="+item.getItemId());
    			                        intent.setData(content_url);
    			                        startActivity(intent);	
    			                        dialog.dismiss();
    								}
    							}).create();
                    	log.show(); 
                    }
            	} 
//				SearchResultsItem item = (SearchResultsItem) getListView().getItemAtPosition(position);
//				Intent intent=new Intent();
//				intent.putExtra("videoid", item.getItemId());
//				intent.setClass(getActivity(), ChannelWithSearchActivity.class);
//				startActivity(intent);
			}
		});
		// start out with a progress indicator.
		setListShown(false);
		// prepare the loader. Either re-connect with an existing one, or start
		// a new one.
		getLoaderManager().initLoader(0, null, this);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	
@Override
	public void onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu,
			com.actionbarsherlock.view.MenuInflater inflater) {
	// Inflate the options menu from XML
			inflater.inflate(R.menu.search_list_menu, menu);

			// Get the SearchView and set the searchable configuration
			SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
			searchView.setOnQueryTextListener(this);
		super.onCreateOptionsMenu(menu, inflater);
	}

	// LoaderManager methods
	@Override
	public Loader<PageBean> onCreateLoader(int id, Bundle args) { 
		PageBeanAsyncTaskLoader loader = new PageBeanAsyncTaskLoader(getActivity(), _query);
		loaderId = loader.getId();
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<PageBean> loader,PageBean data) {
		// Set the new data in the adapter.
		pageBean=data;
		List<SearchResultsItem> list = null;
		try {
			list = VideoDao.getSearchList(data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		results.addAll(list);
		clickmore.setVisibility(View.VISIBLE);
		_adapter.setData(results);
		// The list should now be shown.
		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	} 
	@Override
	public void onLoaderReset(Loader<PageBean> loader) {
		// Android guides set data to null but I need check
		// what happen when I set data as null 
		_adapter.setData(new ArrayList<SearchResultsItem>());
	} 
	// OnQueryTextListener methods
	@Override
	public boolean onQueryTextChange(String newText) {
		// Called when the action bar search text has changed. sUpdate
		// the search filter, and restart the loader to do a new query
		// with this filter.
		_curFilter = !TextUtils.isEmpty(newText) ? newText : null; 
		_adapter.setQuery(_curFilter);
		if(_curFilter!=null){
			_query = Constants.URL_SEARCHLIST + "&q=" + _curFilter + "&key="+ DeveloperKey.DEVELOPER_KEY;
		}else{
			_query = Constants.URL_SEARCHLIST + "&key="+ DeveloperKey.DEVELOPER_KEY;
		} 
		results=new ArrayList<SearchResultsItem>();
		getLoaderManager().restartLoader(loaderId, null, this);
		return true;
	} 
	@Override
	public boolean onQueryTextSubmit(String query) {
		// Don't care about this.
		return false;
	}

	static class ViewHolder {
		TextView nameTextView;
		ImageView imageView;
	} 
}
