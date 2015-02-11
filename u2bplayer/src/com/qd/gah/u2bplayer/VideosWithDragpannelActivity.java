package com.qd.gah.u2bplayer;

import main.java.com.github.pedrovgs.DraggableListener;
import main.java.com.github.pedrovgs.DraggablePanel;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.iiijiaban.u2bplayer.R;
import com.ijiaban.fragment.MoviePosterFragment;
import com.ijiaban.fragment.RateandRelativeFragment;
import com.ijiaban.u2bplayer.bean.v3.DeveloperKey;

public class VideosWithDragpannelActivity extends SherlockFragmentActivity  
{

	 
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    ImageView thumbnailImageView;
    DraggablePanel draggablePanel;
    private YouTubePlayer youtubePlayer;
    private String videoid;
    private YouTubePlayerSupportFragment youtubeFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);   //取消标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_videoswithdragpannel);
 
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
    }

   
    private void playVideo() {
            youtubePlayer.play();
    }

	private void initializeDraggablePanel() {
		draggablePanel.setFragmentManager(getSupportFragmentManager());
		draggablePanel.setTopFragment(youtubeFragment);
		RateandRelativeFragment rrf = new RateandRelativeFragment();
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
	                    }

						@Override
						public void onInitializationFailure(Provider arg0,YouTubeInitializationResult arg1) {
							 if (arg1.isUserRecoverableError()) {
								 arg1.getErrorDialog(VideosWithDragpannelActivity.this, RECOVERY_DIALOG_REQUEST).show();
							    } else {
							      String errorMessage = String.format(getString(R.string.error_player), arg1.toString());
							      Toast.makeText(VideosWithDragpannelActivity.this, errorMessage, Toast.LENGTH_LONG).show();
							    } 
						}
	                }
	        );		
	}
	



}
