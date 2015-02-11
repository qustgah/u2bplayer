package com.qd.gah.u2bplayer;

import com.iiijiaban.u2bplayer.R;
import com.ijiaban.fragment.SearchListFragment; 
import com.ijiaban.fragment.VideoCategoryFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

  
public class SearchActivity extends FragmentActivity{
	
	 @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_search);
	    
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();	
	    SearchListFragment fragment = new SearchListFragment();
	    fragmentTransaction.replace(R.id.fragment_container, fragment);
	    fragmentTransaction.commit();
	  }
}  
