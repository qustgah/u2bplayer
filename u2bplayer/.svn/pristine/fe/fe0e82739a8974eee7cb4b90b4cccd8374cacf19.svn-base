package com.ijiaban.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ijiaban.tabslide.PagerSlidingTabStrip;
import com.iiijiaban.u2bplayer.R;

public class RateandRelativeFragment extends Fragment{
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	 private AdView mAdView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.rateandrelativefragment, container,false);
		LinearLayout layout = (LinearLayout) view.findViewById(R.id.adlayoutrateandrelative);
		mAdView = new AdView(getActivity());
        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdListener(new ToastAdListener(this));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mAdView, params);
        mAdView.loadAd(new AdRequest.Builder().build());
		/**
		 * tab viewpager相关设置
		 */
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabsrateandrelative);
		// 下滑颜色
		tabs.setIndicatorColor(getResources().getColor(R.color.white));
		tabs.setBackgroundColor(getResources().getColor(R.color.tabsfcuk));
		tabs.setDividerColor(getResources().getColor(R.color.white));
		tabs.setTextColor(getResources().getColor(R.color.white));
		tabs.setTextSize(30);
		tabs.setUnderlineHeight(3);
		pager = (ViewPager) view.findViewById(R.id.rageandrelitavepager);
		adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
		pager.setAdapter(adapter);
		pager.getAdapter().notifyDataSetChanged();
		
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
		return view;
	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter {
		

		private ArrayList<Fragment> fragmentList;
		RateFragment tj3 = new RateFragment();
		RelativeFragment tj2 = new RelativeFragment(); 
		VideoDetail tj1 = new VideoDetail();
		private final String[] TITLES = { "INTRUDUCTION", "RELATIVE", "RATING"};
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		} 
		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		} 
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}
		@Override
		public Fragment getItem(int position) { 
			fragmentList = new ArrayList<Fragment>();
			fragmentList.add(tj1);
			fragmentList.add(tj2);
			fragmentList.add(tj3);
			return fragmentList.get(position);
		} 
	}
}
