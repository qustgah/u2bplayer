package com.qd.gah.u2bplayer.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 通用的pagerAdapter继承类
 * 
 * @author Administrator
 * 
 */
public abstract class ViewPagerAdapter extends PagerAdapter {

	public List<?> mviews = new ArrayList<Object>();
	/**
	 * 上下文
	 */
	public Activity mActivity;

	public ViewPagerAdapter(List<?> mviews, Activity mActivity) {
		super();
		this.mviews = mviews;
		this.mActivity = mActivity;
	}

	@Override
	public int getCount() {
		return mviews.size();
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return (arg0 == arg1);
	}

	@Override
	public Object instantiateItem(View container, int position) {
		return setinstantiateItem(container, position);
	}

	/**
	 * 初始化instantiateItem
	 * 
	 * @param container
	 * @param position
	 * @return
	 */
	public abstract View setinstantiateItem(View container, int position);

}
