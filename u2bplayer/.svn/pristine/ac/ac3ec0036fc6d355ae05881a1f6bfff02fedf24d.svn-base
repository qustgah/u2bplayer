package com.ijiaban.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import com.ijiaban.u2bplayer.bean.v3.Comments;
import com.ijiaban.u2bplayer.common.bean.XmlPageBean;
import com.ijiaban.u2bplayer.dao.v2.CommentsDao;
import com.qd.gah.u2bplayer.BofangActivity;
import com.iiijiaban.u2bplayer.R;
import com.qd.gah.u2bplayer.adapter.ListViewAdapter;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RateFragment extends Fragment{
private ListView listview;
private ListVAdapter adapter;
private String videoid;
private List<Comments> comments;
private String path;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.ratefragment, container, false);
		listview=(ListView)view.findViewById(R.id.list_comments2);
	    videoid=getActivity().getIntent().getStringExtra("videoid");
		path = "http://gdata.youtube.com/feeds/api/videos/"+videoid+"/comments?v=2&orderby=published";
		getComments();
		comments=new ArrayList<Comments>();
		
		adapter =new ListVAdapter(comments, getActivity());
		listview.setAdapter(adapter);
		return view;
	}
private void getComments() {

	new AsyncTask<Void, String,List<Comments> >() {

		@Override
		protected List<Comments> doInBackground(Void... params) {
			//   ÍøÂçÏß³Ì
			try {
				String s=path;
				XmlPageBean pageBean = CommentsDao.getCommentsByUrl(path);
				comments = (List<Comments>) pageBean.getList();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			return comments;
		}

		@Override
		protected void onPostExecute(List<Comments> result) {
			adapter.comments2=comments;
			adapter.notifyDataSetChanged();
		}
	}.execute();
		
	}
public class ListVAdapter extends BaseAdapter{
	private List<Comments> comments2 = new ArrayList<Comments>();
	private Context context;
	protected LayoutInflater      _inflater;
	
	public ListVAdapter(List<Comments> comments, Context context) {
		super();
		comments2 = comments;
		this.context = context;
		 _inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if(comments2!=null){
		return comments2.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return comments2.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView text ;
	    TextView name;
	    TextView time;
		if(convertView==null){
			convertView = _inflater.inflate(R.layout.item_comments, null);
			text = (TextView) convertView.findViewById(R.id.item_comments_text);
			name=(TextView)convertView.findViewById(R.id.textView1);
			time=(TextView)convertView.findViewById(R.id.time);
		}else{
			text = (TextView) convertView.findViewById(R.id.item_comments_text);	
			name=(TextView)convertView.findViewById(R.id.textView1);
			time=(TextView)convertView.findViewById(R.id.time);
		}
		String s=comments2.get(position).getContent();
		text.setText(comments2.get(position).getContent());
		name.setText(comments2.get(position).getAuthor().getName());
		time.setText(comments2.get(position).getPublished());
		return convertView;
	}}
 
}
