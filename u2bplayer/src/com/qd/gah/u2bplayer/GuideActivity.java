package com.qd.gah.u2bplayer;

import java.util.ArrayList;
import java.util.List;
import com.iiijiaban.u2bplayer.R;
import com.ijiaban.youtubeplayer.ui.MainActiivty;
import com.qd.gah.u2bplayer.adapter.ViewPagerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class GuideActivity extends Activity implements OnPageChangeListener{
	
	private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView page0;
    private ImageView page1;
    

    // 记录当前选中位置
    private int currentIndex;
    private static final String SHAREDPREFERENCES_NAME = "first_pref";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initViews();
	}
	private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        // 初始化引导图片列表
        views.add(inflater.inflate(R.layout.guide_one, null));
        views.add(inflater.inflate(R.layout.guide_two, null)); 
        views.add(inflater.inflate(R.layout.guide_three, null));
        // 初始化Adapter
        vpAdapter = new ViewPagerAdapter(views, this){
			@Override
			public View setinstantiateItem(View container, int position) {
				
				((ViewPager) container).addView((View) mviews.get(position), 0);
				if (position == (mviews.size() - 1)||(position==mviews.size()-2))
				{
					Button mStartWeiboImageButton = (Button) container.findViewById(R.id.iv_start_weibo);
					mStartWeiboImageButton.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							// 设置已经引导
							setGuided();
							goHome();

						}

					});
				}
				return (View) mviews.get(position);
			}
        };

        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.setOnPageChangeListener(this);
    }
	public void setGuided(){
		SharedPreferences preferences = getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		// 存入数据
		editor.putBoolean("isFirstIn", false);
		// 提交修改
		editor.commit();
	}
	public void goHome(){
		// 跳转
		Intent intent = new Intent(this, MainActiivty.class);
		startActivity(intent);
		finish();
	} 
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	@Override
	public void onPageSelected(int arg0) {
				
	}
}
