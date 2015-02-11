package com.qd.gah.u2bplayer;

import main.java.com.github.pedrovgs.DraggableListener;
import main.java.com.github.pedrovgs.DraggablePanel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeIntents;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.iiijiaban.u2bplayer.R;
import com.ijiaban.fragment.MoviePosterFragment;
import com.ijiaban.fragment.RateandRelativeFragment;
import com.ijiaban.u2bplayer.bean.v3.DeveloperKey;

public class BofangActivity extends SherlockFragmentActivity{
	private static final String YOUTUBE_API_KEY =null;
    private static final String VIDEO_KEY = "pK2zYHWDZKo";
    private static final String VIDEO_POSTER_THUMBNAIL = "http://4.bp.blogspot.com/-BT6IshdVsoA/UjfnTo_TkBI/AAAAAAAAMWk/JvDCYCoFRlQ/s1600/xmenDOFP.wobbly.1.jpg";
    private static final String SECOND_VIDEO_POSTER_THUMBNAIL = "http://media.comicbook.com/wp-content/uploads/2013/07/x-men-days-of-future-past-wolverine-poster.jpg";
    private static final String VIDEO_POSTER_TITLE = "X-Men: Days of Future Past";
    ImageView thumbnailImageView;
    DraggablePanel draggablePanel;
    private YouTubePlayer youtubePlayer;
    private String videoid;
    private YouTubePlayerSupportFragment youtubeFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_youtube_sample);
	    Intent intent=getIntent();
        videoid=intent.getStringExtra("videoid");
		
		draggablePanel=(DraggablePanel)findViewById(R.id.draggable_panel);
		initializeYoutubeFragment();
		hookDraggablePanelListeners();
		initializeDraggablePanel();
		 
	}

	private void hookDraggablePanelListeners() {
		draggablePanel.setDraggableListener(new DraggableListener() {
            @Override
            public void onMaximized() {
                playVideo();
            }

			@Override
            public void onMinimized() {
            }

            @Override
            public void onClosedToLeft() {
                pauseVideo();
                youtubePlayer.release();
            }

            @Override
            public void onClosedToRight() {
                pauseVideo();
                youtubePlayer.release();
            }
        });		
	}
    
    private void pauseVideo() {
            youtubePlayer.pause();
            youtubePlayer.release();
    }

   
    private void playVideo() {
            youtubePlayer.play();
    }
    
	private void initializeDraggablePanel() {
		 draggablePanel.setFragmentManager(getSupportFragmentManager());
	     draggablePanel.setTopFragment(youtubeFragment);
	     RateandRelativeFragment rrf=new RateandRelativeFragment();
	     draggablePanel.setBottomFragment(rrf);
	     draggablePanel.initializeView();
	}
	
	private void initializeYoutubeFragment() {
		 youtubeFragment = new YouTubePlayerSupportFragment();
	        youtubeFragment.initialize(DeveloperKey.DEVELOPER_KEY,
	                new YouTubePlayer.OnInitializedListener() {

	                    @Override
	                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
	                        if (!wasRestored) {
	                            youtubePlayer = player;
	                            youtubePlayer.loadVideo(videoid);
	                            youtubePlayer.setShowFullscreenButton(true);
	                        }
//	                        if(YouTubeIntents.isYouTubeInstalled(getApplicationContext())) {
//	                        	   if(YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getApplicationContext()) == YouTubeInitializationResult.SUCCESS) {
//	                        	   // start the YouTube player
//	                        	   getApplicationContext().startActivity(
//	                        	   YouTubeStandalonePlayer.createVideoIntent((Activity) getApplicationContext(), DeveloperKey.DEVELOPER_KEY, videoid));
//	                        	 } else if(YouTubeIntents.canResolvePlayVideoIntent(getApplicationContext())) {
//	                        	   // Start an intent to the YouTube app
//	                        	   getApplicationContext().startActivity(
//	                        	   YouTubeIntents.createPlayVideoIntent(getApplicationContext(), videoid));
//	                        	 }
//	                        	}
	                        	// Falls through: last resort - render a webview with an iframe
	                    }

						@Override
						public void onInitializationFailure(Provider arg0,
								YouTubeInitializationResult arg1) {
							
						}

	                }
	        );		
	}
	
	

}
