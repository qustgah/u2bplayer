package com.qd.gah.u2bplayer.adapter;

import java.util.List;


import android.content.Context;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public  abstract class CommonBaseAdapter extends BaseAdapter {
  protected List<?>        _data;
  protected LayoutInflater      _inflater;
  protected String              _query;
  protected ForegroundColorSpan _querySpan;
  protected Context context ;
  public CommonBaseAdapter(Context context, List<?> data){
    super();
    _data = data;
    this.context = context;
    _inflater = LayoutInflater.from(context);
    _query = null;
    _querySpan = new ForegroundColorSpan(Color.RED);
  }
  
  @Override
  public int getCount() {
    return _data.size();
  }

  @Override
  public Object getItem(int position) {
    return _data.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) { 
    return InitView(position, convertView, parent);
  } 
  public abstract View InitView(int position, View convertView, ViewGroup parent);
  
  @Override
  public void notifyDataSetChanged() {
    super.notifyDataSetChanged();
  }
  
  // Public methods
  // -----------------------------------------------------------------------------------------
  public void setData(List<?> data){
    _data = data;
    notifyDataSetChanged();
  }
  
  public String getQuery(){
    return _query;
  }
  
  public void setQuery(String query){
    _query = query;
  }
  
  // ViewHolder class
  // -----------------------------------------------------------------------------------------
  
}
