package com.ijiaban.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.iiijiaban.u2bplayer.R;

public class OwnerlistKongbai extends SherlockFragment {
	private TextView text;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rooterView=inflater.inflate(R.layout.ownerlist_kongbai, container,false);
		text=(TextView)rooterView.findViewById(R.id.ownerlist_text);
		return rooterView;
	}
}