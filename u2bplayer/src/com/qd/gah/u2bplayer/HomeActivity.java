package com.qd.gah.u2bplayer;

import java.util.List;
import com.iiijiaban.u2bplayer.R;
import com.ijiaban.u2bplayer.bean.Category;
import com.ijiaban.u2bplayer.dao.CategoryDao;
import com.qd.gah.u2bplayer.adapter.ListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * v2分類界面 
 *
 */
public class HomeActivity extends Activity{
	
	private TextView tv ; 
	private CategoryDao categoryDao;
	private String path;//获取类别地址
	public List<Category> cates ;//类别集合
	public ListView listView;
	public ListViewAdapter adapter;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO 消息处理
			switch (msg.what) {
			case 1:
				adapter = new ListViewAdapter(cates,HomeActivity.this) {
					@Override
					public View setView(View convertView, int position, ViewGroup parent) {
						// TODO  初始化adapter
						View view ;
						TextView txtv;
						if(convertView == null){
							view = getLayoutInflater().inflate(R.layout.category_item, null);
							txtv = (TextView) view.findViewById(R.id.item_category);
						}else{
							view = convertView;
							txtv = (TextView) view.findViewById(R.id.item_category);
						}
						txtv.setText(cates.get(position).getName());
						return view;
					}
					@Override
					public long setItemId(int position) {
						// TODO 设置标识
						return position;
					}
				}; 
				listView.setAdapter(adapter); 
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_home); 
		listView = (ListView) findViewById(R.id.categories_home); 
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO listviewItem点击事件
				Category category = (Category) listView.getItemAtPosition(position);
				Intent intent = new Intent(HomeActivity.this, VideosActivity.class);
				intent.putExtra("category", category.getSname());
				startActivity(intent);
			}
		});
		path = "http://gdata.youtube.com/schemas/2007/categories.cat";
		new Thread(){
			public void run() {
				try {
					cates = categoryDao.getCategoryByXml(path);
					Message msg = new Message();
					msg.what = 1;
					handler.sendMessage(msg);
				} catch (Exception e) { 
					e.printStackTrace();
				} 
			}
		}.start();;
	}
}
