package com.ijiaban.fragment;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.gson.Gson;
import com.ijiaban.actionbar.pulltorefreshlib.ActionBarPullToRefresh;
import com.ijiaban.actionbar.pulltorefreshlib.OnRefreshListener;
import com.ijiaban.actionbar.pulltorefreshlib.PullToRefreshLayout;
import com.ijiaban.u2bplayer.bean.v3.SearchResultsItem;
import com.ijiaban.u2bplayer.common.bean.DeveloperKey;
import com.ijiaban.uitls.EtagCache;
import com.ijiaban.uitls.GetYouTubePlaylistAsyncTask;
import com.ijiaban.uitls.PlayListItem;
import com.ijiaban.uitls.Playlist;
import com.ijiaban.youtubeplayer.ui.FoldingCirclesDrawable;
import com.lt.test.Constants;
import com.qd.gah.u2bplayer.BofangActivity;
import com.iiijiaban.u2bplayer.R;
import com.qd.gah.u2bplayer.SearchActivity;
import com.squareup.picasso.Picasso;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
// 
public class TuiJFragment extends SherlockFragment implements OnRefreshListener{
	private PullToRefreshLayout mPullToRefreshLayout;
	private static final String[] YOUTUBE_PLAYLIST = {"PL7KLwyJCC7QzpNciVGe-7K6ShC3MzL3vd", "PL5vtqDuUM1DngrWKmHlb8t64FRi-kVMUB" };
    private static final String PLAYLIST_KEY = "PLAYLIST_KEY";
    private JazzyListView mListView;
    private EtagCache mEtagCache;
    private Playlist mPlaylist;
    private PlaylistAdapter mAdapter;
    private ProgressBar progressview; 
    private AdView mAdView;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onRefreshStarted(View view) {

        /**
         * Simulate Refresh with 4 seconds sleep
         */
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                	newInstance();
                    Thread.sleep(Constants.SIMULATED_REFRESH_LENGTH);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                // Notify PullToRefreshLayout that the refresh has finished
                mPullToRefreshLayout.setRefreshComplete();
            }
        }.execute();
	}
/**
 * 重新实例化
 * @return  新的 TuiJFragment 
 */
	static Fragment newInstance() {
		Fragment f=new TuiJFragment();
		return f;
		
	}

	
	@Override
public void onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu,
		com.actionbarsherlock.view.MenuInflater inflater) {
		inflater.inflate(R.menu.search, menu);
	super.onCreateOptionsMenu(menu, inflater);
}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		switch (item.getItemId()){
		case R.id.menu_searcha:
			Intent intent=new Intent();
			intent.setClass(getActivity(), SearchActivity.class);
			startActivity(intent);
			break ;
		case R.id.menu_rotatea:


			// 在自己应用中直接连接到应用在android应用市场的位置去评价
			String str = "market://details?id="
					+ getActivity().getPackageName();
			Intent localIntent = new Intent(
					"android.intent.action.VIEW");
			localIntent.setData(Uri.parse(str));
			try {
				startActivity(localIntent);
			} catch (ActivityNotFoundException  e) {
				Intent intent2 = new Intent(Intent.ACTION_VIEW);	
				intent2.setData(Uri.parse("https://play.google.com/store/apps/details?id="+getActivity().getPackageName()));
    			startActivity(Intent.createChooser(intent2, getActivity().getTitle()));  
    			
			}
			break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		 ViewGroup viewGroup = (ViewGroup) view; 
         // As we're using a ListFragment we create a PullToRefreshLayout manually
         mPullToRefreshLayout = new PullToRefreshLayout(viewGroup.getContext());
         // We can now setup the PullToRefreshLayout
         ActionBarPullToRefresh.from(getActivity())
                 // We need to insert the PullToRefreshLayout into the Fragment's ViewGroup
                 .insertLayoutInto(viewGroup)
                 .theseChildrenArePullable(R.id.tuijian_listview)
                 .listener(this)
                 .setup(mPullToRefreshLayout); 
	}  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 	Picasso.with(getActivity()).setDebugging(true);
		 	 
	        View rootView = inflater.inflate(R.layout.tuijianfragment, container, false);
	        progressview = (ProgressBar) rootView.findViewById(R.id.progress);
	        progressview.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getActivity()).build());
	        mListView = (JazzyListView) rootView.findViewById(R.id.tuijian_listview);
	       
	        mListView.setTransitionEffect(JazzyHelper.SLIDE_IN);
	        LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.adlayouttuijian);
//			mAdView = new AdView(getActivity());
//	        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
//	        mAdView.setAdSize(AdSize.BANNER);
////	        mAdView.setAdListener(new ToastAdListener(this));
//	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//	        		LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//	        layout.addView(mAdView, params);
//	        mAdView.loadAd(new AdRequest.Builder().build());
	        // restore the playlist after an orientation change
	        if (savedInstanceState != null) {
	            mPlaylist = new Gson().fromJson(savedInstanceState.getString(PLAYLIST_KEY), Playlist.class);
	        } 
	        // ensure the adapter and listview are initialized
	        if (mPlaylist != null) {
	            initListAdapter(mPlaylist);
	        }

	        // start loading the first page of our playlist
	        new GetYouTubePlaylistAsyncTask() { 
	            @Override
	            public EtagCache getEtagCache() {
	                return mEtagCache;
	            } 
	            @Override
	            public void onPostExecute(JSONObject result) {
	                handlePlaylistResult(result);
	                progressview.setVisibility(View.GONE);
	            }
	        }.execute(YOUTUBE_PLAYLIST[0], null);
	        return rootView;
	}
	  @Override
	public void onCreate(Bundle savedInstanceState) {
			setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	
	} 
	public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        String json = new Gson().toJson(mPlaylist);
	        outState.putString(PLAYLIST_KEY, json);
	    }

@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		File cacheFile = new File(activity.getFilesDir(), YOUTUBE_PLAYLIST[0]);
      mEtagCache = EtagCache.create(cacheFile, EtagCache.FIVE_MB);
	}

	private void initListAdapter(Playlist playlist) {
        mAdapter = new PlaylistAdapter(playlist); 
        mListView.setAdapter(mAdapter);
    }
    private void handlePlaylistResult(JSONObject result) {
        try {
            if (mPlaylist == null) {
                mPlaylist = new Playlist(result);
                initListAdapter(mPlaylist);
            } else {
                mPlaylist.addPage(result);
            } 
            if (!mAdapter.setIsLoading(false)) {
                mAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    protected class PlaylistAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        private Playlist mPlaylist;
        private boolean mIsLoading = false;
        private ProgressBar loading; 

        PlaylistAdapter(Playlist playlist) {
            mPlaylist = playlist;
            mInflater = getLayoutInflater(null);
        }
        /**
         * @param isLoading
         * @return True if the adapter was notified that data set has changed, false otherwise
         */
        public boolean setIsLoading(boolean isLoading) {
            if (mIsLoading != isLoading) {
                mIsLoading = isLoading;
                notifyDataSetChanged();
                return true;
            }
            return false;
        }

        @Override
        public int getCount() {
            return mPlaylist.getCount() + (mIsLoading ? 1 : 0);
        }

        @Override
        public PlayListItem getItem(int i) {
            return mPlaylist.getItem(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            if (mIsLoading && position == (getCount() - 1)) {
            	View v=mInflater.inflate(R.layout.youtube_video_list_item_loading, null,false);
            	loading=(ProgressBar)v.findViewById(R.id.youtubeloading);
            	loading.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getActivity()).build());
                return v;
            } 
            ViewHolder viewHolder;

            if (convertView == null || convertView.getTag() == null) {

                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.youtube_video_list_item, null, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.video_title);
                viewHolder.description = (TextView) convertView.findViewById(R.id.video_description);
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.video_thumbnail);
                viewHolder.share = (ImageView) convertView.findViewById(R.id.video_share);
                convertView.setTag(viewHolder);
            }

            viewHolder = (ViewHolder) convertView.getTag();

            final PlayListItem item = getItem(position);
            viewHolder.title.setText(item.title);
            viewHolder.description.setText(item.description);

            Picasso.with(getActivity()).load(item.thumbnailUrl).into(viewHolder.thumbnail);

            viewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                	//安装了youtube
                    if(YouTubeIntents.isYouTubeInstalled(getActivity().getApplicationContext())) {
                 	   if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity()) == YouTubeInitializationResult.SUCCESS) {
                 	   //版本正常，跳转到界面
                 		  Intent intent =new Intent();
                       	intent.putExtra("videoid", item.videoId);
                      	intent.setClass(getActivity(), BofangActivity.class);
                      	startActivity(intent);
                 	 } else if(YouTubeIntents.canResolvePlayVideoIntent(getActivity())) {
                 	   //版本不行，跳转到youtube
                 		getActivity().startActivity(
                 	   YouTubeIntents.createPlayVideoIntent(getActivity(),item.videoId));
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
				                        Uri content_url = Uri.parse("https://www.youtube.com/watch?v="+item.videoId);
				                        intent.setData(content_url);
				                        startActivity(intent);	
				                        dialog.dismiss();
									}
								}).create();
                    	log.show();
                    	
                    	
                    }
                     
                }
            });
            viewHolder.description.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
                	//安装了youtube
                    if(YouTubeIntents.isYouTubeInstalled(getActivity().getApplicationContext())) {
                 	   if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity()) == YouTubeInitializationResult.SUCCESS) {
                 	   //版本正常，跳转到界面
                 		  Intent intent =new Intent();
                       	intent.putExtra("videoid", item.videoId);
                      	intent.setClass(getActivity(), BofangActivity.class);
                      	startActivity(intent);
                 	 } else if(YouTubeIntents.canResolvePlayVideoIntent(getActivity())) {
                 	   //版本不行，跳转到youtube
                 		getActivity().startActivity(
                 	   YouTubeIntents.createPlayVideoIntent(getActivity(),item.videoId));
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
				                        Uri content_url = Uri.parse("https://www.youtube.com/watch?v="+item.videoId);
				                        intent.setData(content_url);
				                        startActivity(intent);	
				                        dialog.dismiss();
									}
								}).create();
                    	log.show();
                    	
                    	
                    }
                     
                }
			});
            viewHolder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Watch \"" + item.title + "\" on YouTube");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=" + item.videoId);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });

            // get the next playlist page if we're at the end of the current page and we have another page to get
            final String nextPageToken = mPlaylist.getNextPageToken(position);
            if (!isEmpty(nextPageToken) && position == getCount() - 1) {
                new GetYouTubePlaylistAsyncTask() {
                    @Override
                    public EtagCache getEtagCache() {
                        return mEtagCache;
                    }
                    @Override
                    public void onPostExecute(JSONObject result) {
                        handlePlaylistResult(result);
                    }
                }.execute(YOUTUBE_PLAYLIST[0], nextPageToken);

                setIsLoading(true);
            }

            return convertView;
        }

        private boolean isEmpty(String s) {
            if (s == null || s.length() == 0) {
                return true;
            }
            return false;
        }

        class ViewHolder {
            ImageView thumbnail;
            TextView title;
            TextView description;
            ImageView share;
        }
    }


}
