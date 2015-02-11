package com.qd.gah.u2bplayer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.iiijiaban.u2bplayer.R;
import com.ijiaban.fragment.ChannelWithSearchFragment;
import com.ijiaban.fragment.VideosByChannelFragment;

public class VideosByChannelActivity extends SherlockFragmentActivity {

	 @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_search);
	    
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();	
	    VideosByChannelFragment fragment = new VideosByChannelFragment();
	    fragmentTransaction.replace(R.id.fragment_container, fragment);
	    fragmentTransaction.commit();
	  }
}
