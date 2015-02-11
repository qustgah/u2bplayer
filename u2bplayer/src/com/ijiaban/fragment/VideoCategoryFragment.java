package com.ijiaban.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.iiijiaban.u2bplayer.R;
import com.qd.gah.u2bplayer.SearchActivity;
import com.qd.gah.u2bplayer.VideosInVideoCategoryActivity;
import com.qd.gah.u2bplayer.adapter.CommonBaseAdapter;
 
public class VideoCategoryFragment extends SherlockFragment {
	OnGridSelectedListener mListener;
	@Override
	public void onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu,
			com.actionbarsherlock.view.MenuInflater inflater) {
		inflater.inflate(R.menu.category_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	public interface OnGridSelectedListener {
        public void onGridSelected(long id);
    }
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		 try {
	            mListener = (OnGridSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
	        }
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.menu_searchb:
			Intent intent =new Intent();
			intent.setClass(getActivity(), SearchActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_shareb:
			Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Download from https://play.google.com/store/apps/details?id=\"" + getActivity().getPackageName() + "\"to see YouTube videos");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
			break;
			default:
				break;
			
		}
		return super.onOptionsItemSelected(item);
	}

	public ImageLoader imageloader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_launcher2) // 设置图片下载期间显示的图片
			.showImageForEmptyUri(R.drawable.ic_launcher2) // 设置图片Uri为空或是错误的时候显示的图片
			.showImageOnFail(R.drawable.ic_launcher2) // 设置图片加载或解码过程中发生错误显示的图片
			.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
			.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
			.displayer(new RoundedBitmapDisplayer(10)) // 设置成圆角图片
			.build(); // 创建配置过得DisplayImageOption对象 
	private View view;
	private GridView gridView;
	private ListView listView;
	private CommonBaseAdapter adapter;
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>(){
		{
			put(23,R.drawable.category_comedy);
			put(20,R.drawable.category_1_gaming);
			put(10,R.drawable.category_music);
			put(17,R.drawable.category_sports);
		}
	};
	private Map<Integer,String> map_name = new HashMap<Integer, String>(){
		{
			put(23,"Comedy"); 
			put(20,"Gaming");
			put(10,"Music");
			put(17,"Sports");
		}
	};
	private Map<Integer, Integer> smap = new HashMap<Integer, Integer>(){
		{
			put(1,R.drawable.category_1_film);
			put(24,R.drawable.category_entertainment);
			put(2,R.drawable.category_autos_vehicles);
			put(15,R.drawable.category_pets_animals);
			put(27,R.drawable.category_education);
			put(25,R.drawable.category_news);
			put(22,R.drawable.category_people);
			put(26,R.drawable.category_howto);
			put(19,R.drawable.category_travel);
			put(28,R.drawable.category_science);
			put(29,R.drawable.category_other);
		}
	};
	private Map<Integer,String> smap_name = new HashMap<Integer, String>(){
		{
			put(1,"Film & Animation"); 
			put(24,"Entertainment");
			put(2,"Autos & Vehicles ");
			put(15,"Pets & Animals");
			put(27,"Education"); 
			put(25,"News & Politics");
			put(22,"People & Events");
			put(26,"Howto & Style");
			put(19,"Travel & Events"); 
			put(28,"Science & Technology ");
			put(29,"Other"); 
		}
	};
	ArrayList<Map<Integer,Integer>> mlist = new ArrayList<Map<Integer,Integer>>();
	ArrayList<Map<Integer,String>> mlist_name = new ArrayList<Map<Integer,String>>();
	
	ArrayList<Map<Integer,Integer>> slist = new ArrayList<Map<Integer,Integer>>();
	ArrayList<Map<Integer,String>> slist_name = new ArrayList<Map<Integer,String>>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO fragment 
		view = inflater.inflate(R.layout.test_fragment_videocategories, null);
		mlist = mapToList(map);
		slist = mapToList(smap);
		mlist_name = mapToList_name(map_name);
		slist_name = mapToList_name(smap_name);
		setHasOptionsMenu(true);
		View myview = inflater.inflate(R.layout.test_fragment_videocategories_header, null);
		gridView = (GridView) myview.findViewById(R.id.videocategories_listview_header);
		gridView.setAdapter(new myadapter());
		
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mListener.onGridSelected(id);
				Intent intent = new Intent(getActivity(), VideosInVideoCategoryActivity.class);
				intent.putExtra("categoryId",id);
				
				
				getActivity().startActivity(intent);
			}
		});
		listView = (ListView) view.findViewById(R.id.videocategories_listview);
	
		listView.addHeaderView(myview);
		listView.setAdapter(new mylistadapter());
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mListener.onGridSelected(id);
				Intent intent = new Intent(getActivity(), VideosInVideoCategoryActivity.class);
				intent.putExtra("categoryId",id);
				getActivity().startActivity(intent);
				
			}  
		}); 
	 
		return view;
	} 
	static class VideoCategoryHolder{
		TextView textView;
		ImageView imageView;
	}
	class myadapter extends BaseAdapter{

		@Override
		public int getCount() { 
			return mlist.size();
		}

		@Override
		public Object getItem(int position) {
			int i = 0;
			Map<Integer ,Integer > map_ = mlist.get(position);
			for(Map.Entry<Integer,Integer> entry:map_.entrySet()){  
			  i = entry.getKey();
			}
			return i;
		}

		@Override
		public long getItemId(int position) {
			int i = 0;
			Map<Integer ,Integer > map_ = mlist.get(position);
			for(Map.Entry<Integer,Integer> entry:map_.entrySet()){  
			  i = entry.getKey();
			}
			return i;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO  
			VideoCategoryHolder holder;
			if(convertView==null){
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.test_item_category,null);
				holder = new VideoCategoryHolder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.imageView2);
				holder.textView = (TextView) convertView.findViewById(R.id.textView2);
				convertView.setTag(holder);
				
			}else{
				holder = (VideoCategoryHolder) convertView.getTag();
			} 
			Map<Integer ,Integer > map_ = mlist.get(position);
			for(Map.Entry<Integer,Integer> entry:map_.entrySet()){ 
				Drawable drawable = getActivity().getApplication().getResources().getDrawable(entry.getValue());
				holder.imageView.setImageDrawable(drawable);
			}
			Map<Integer ,String> map_name_ = mlist_name.get(position);
			for(Map.Entry<Integer,String> entry:map_name_.entrySet()){ 
				holder.textView.setText(entry.getValue());
			}
			return convertView; 
		} 
	}
	class mylistadapter extends BaseAdapter{

		@Override
		public int getCount() {
			return smap.size();
		}

		@Override
		public Object getItem(int position) {
			int i = 0;
			Map<Integer ,Integer > map_ = slist.get(position);
			for(Map.Entry<Integer,Integer> entry:map_.entrySet()){  
			  i = entry.getKey();
			}
			return i;
		}
		@Override
		public long getItemId(int position) {
			int i = 0;
			Map<Integer ,Integer > map_ = slist.get(position);
			for(Map.Entry<Integer,Integer> entry:map_.entrySet()){  
			  i = entry.getKey();
			}
			return i;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			VideoCategoryHolder holder;
			if(convertView==null){
				convertView = LayoutInflater.from(getActivity()).inflate(R.layout.test_item_category,null);
				holder = new VideoCategoryHolder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.imageView2);
				holder.textView = (TextView) convertView.findViewById(R.id.textView2);
				convertView.setTag(holder);
			}else{
				holder = (VideoCategoryHolder) convertView.getTag();
			}
			Map<Integer ,Integer > map_ = slist.get(position);
			for(Map.Entry<Integer,Integer> entry:map_.entrySet()){ 
				Drawable drawable = getActivity().getApplication().getResources().getDrawable(entry.getValue());
				holder.imageView.setImageDrawable(drawable);
			}
			Map<Integer ,String> map_name_ = slist_name.get(position);
			for(Map.Entry<Integer,String> entry:map_name_.entrySet()){ 
				holder.textView.setText(entry.getValue());
			}
			return convertView; 
		}
	}
	public ArrayList mapToList(Map<Integer, Integer> map){
		ArrayList<Map<Integer, Integer>> list = new ArrayList<Map<Integer,Integer>>();
		for(Integer i : map.keySet()){
			Map<Integer, Integer> map_item = new HashMap<Integer, Integer>();
			map_item.put(i, map.get(i)); 
			list.add(map_item);
		}
		return  list;
	}
	public ArrayList mapToList_name(Map<Integer, String> map){
		ArrayList<Map<Integer, String>> list = new ArrayList<Map<Integer,String>>();
		for(Integer i : map.keySet()){
			Map<Integer, String> map_item = new HashMap<Integer, String>();
			map_item.put(i, map.get(i)); 
			list.add(map_item);
		}
		return  list;
	} 
	
	
}
