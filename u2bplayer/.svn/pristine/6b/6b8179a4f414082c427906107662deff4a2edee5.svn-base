package com.ijiaban.fragment;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.gson.Gson;
import com.ijiaban.actionbar.pulltorefreshlib.ActionBarPullToRefresh;
import com.ijiaban.actionbar.pulltorefreshlib.OnRefreshListener;
import com.ijiaban.actionbar.pulltorefreshlib.PullToRefreshLayout;
import com.ijiaban.uitls.EtagCache;
import com.ijiaban.uitls.GetYouTubePlaylistAsyncTask;
import com.ijiaban.uitls.PlayListItem;
import com.ijiaban.uitls.Playlist;
import com.ijiaban.youtubeplayer.ui.FoldingCirclesDrawable;
import com.ijiaban.youtubeplayer.ui.GoogleMusicDicesDrawable;
import com.ijiaban.youtubeplayer.ui.MainActiivty.thransid;
import com.ijiaban.youtubeplayer.ui.NexusRotationCrossDrawable;
import com.lt.test.Constants;

import com.qd.gah.u2bplayer.BofangActivity;
import com.qd.gah.u2bplayer.LieBiaoActivity;
import com.iiijiaban.u2bplayer.R;
import com.squareup.picasso.Picasso;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class OwnerListFragment extends Fragment implements OnRefreshListener{
	private PullToRefreshLayout mPullToRefreshLayout;
	private static String YOUTUBE_PLAYLIST = "PLj6NQzHFCvkGOAwI_ofdmAmWiEO2QhVp2";
    private static final String PLAYLIST_KEY = "PLAYLIST_KEY";
    private JazzyListView mListView;
    private EtagCache mEtagCache;
    private Playlist mPlaylist;
    private PlaylistAdapter mAdapter;
    private ProgressBar bar;
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
                 // Here we mark just the ListView and it's Empty View as pullable
//                 .theseChildrenArePullable(android.R.id.list, android.R.id.empty)
                 .theseChildrenArePullable(R.id.owner_listview)
                 .listener(this)
                 .setup(mPullToRefreshLayout);
	}

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 Picasso.with(getActivity()).setDebugging(true);

	        View rootView = inflater.inflate(R.layout.ownerlistfragment, container, false);
	        mListView = (JazzyListView) rootView.findViewById(R.id.owner_listview);
	        mListView.setTransitionEffect(JazzyHelper.ZIPPER);
	        bar=(ProgressBar)rootView.findViewById(R.id.nexusprogress);
	        bar.setIndeterminateDrawable(new NexusRotationCrossDrawable.Builder(getActivity()).build());

	               
          
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
	                bar.setVisibility(View.GONE);
	            }
	        }.execute(YOUTUBE_PLAYLIST, null);

	        return rootView;
	}
	  public static Fragment newInstance() {
		Fragment onw=new OwnerListFragment();
	   return onw;
		
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
		 // initialize our etag cache for this playlist
        File cacheFile = new File(activity.getFilesDir(), YOUTUBE_PLAYLIST);
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
        private ProgressBar p;

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
            	p=(ProgressBar)v.findViewById(R.id.youtubeloading);
            	p.setIndeterminateDrawable(new GoogleMusicDicesDrawable.Builder().build());
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

            Picasso.with(getActivity())
                    .load(item.thumbnailUrl)
                    .into(viewHolder.thumbnail);

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
            viewHolder.description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                	//安装了youtube
                    if(YouTubeIntents.isYouTubeInstalled(getActivity().getApplicationContext())) {
                 	   if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getActivity()) == YouTubeInitializationResult.SUCCESS) {
                 	   //版本正常，跳转到界面
                 		  Intent intent =new Intent();
                       	intent.putExtra("videoid", item.videoId);
                      	intent.setClass(getActivity(), LieBiaoActivity.class);
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
                }.execute(YOUTUBE_PLAYLIST, nextPageToken);

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

	public void use(String id) {
		YOUTUBE_PLAYLIST=id;	
	}

}
