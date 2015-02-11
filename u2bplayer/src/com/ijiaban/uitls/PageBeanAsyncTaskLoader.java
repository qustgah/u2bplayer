package com.ijiaban.uitls;

import java.util.ArrayList;
import java.util.List;

import com.ijiaban.u2bplayer.bean.v3.PageBean;
import com.ijiaban.u2bplayer.bean.v3.SearchResultsItem;
import com.ijiaban.u2bplayer.common.dao.v3.CommonDao;
import com.ijiaban.u2bplayer.dao.VideoDao;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
/**
 * 下载类
 * @author Administrator
 *
 */
public class PageBeanAsyncTaskLoader extends  AsyncTaskLoader<PageBean> {
  
  private PageBean _data;
  private String _query; //查询url
  
  public PageBeanAsyncTaskLoader(Context context, String queryFiler) {
    super(context);
    _query = queryFiler; 
  }

  /** 
   * 后台运行 下载新的数据
   */
  @Override
  public PageBean loadInBackground() {
    List<SearchResultsItem> result = new ArrayList<SearchResultsItem>();
    try { 
		String data = CommonDao.getDataFromServer(_query);
		_data = CommonDao.StringToPageBean(data);
	} catch (Exception e) {
		// TODO 网络处理
		System.out.println(e.getMessage().toString());
	} 
    return _data;
  }
  /** 
   * 把新数据传送到客户端
   */
  @Override 
  public void deliverResult(PageBean pageBean) {
    if (isReset()) {
      //释放资源
      onReleaseResources(pageBean);
    }
    PageBean oldData = _data;
    _data = pageBean;

    if (isStarted()) {
      // 开始传数据
      super.deliverResult(pageBean);
    }

    //  释放不在用的资源
    if (oldData != null && oldData != pageBean) {
      onReleaseResources(oldData);
    }
  } 
  /** 
   * 
   * 开始loader
   */
  @Override 
  protected void onStartLoading() {
    if (_data != null) {
      // Deliver any previously loaded data immediately.
      deliverResult(_data);
    }

    if (takeContentChanged() || _data == null) {
      // When the observer detects a change, it should call onContentChanged()
      // on the Loader, which will cause the next call to takeContentChanged()
      // to return true. If this is ever the case (or if the current data is
      // null), we force a new load.
      forceLoad();
    }
  }
  
  /**
   * Handles a request to stop the Loader.
   * 
   * 停止laoder
   */
  @Override 
  protected void onStopLoading() {
    // Attempt to cancel the current load task if possible.
    cancelLoad();
  }
  
  /**
   * Handles a request to cancel a load.
   * 
   * 取消
   */
  @Override 
  public void onCanceled(PageBean data) {
    super.onCanceled(data);
    // At this point we can release the resources associated with 'apps' if needed.
    onReleaseResources(data);
  }
  
  /**
   * Handles a request to completely reset the Loader.
   */
  @Override 
  protected void onReset() {
    super.onReset();

    // Ensure the loader is stopped
    onStopLoading();

    // At this point we can release the resources associated with 'apps'
    // if needed.
    if (_data != null) {
      onReleaseResources(_data);
      _data = null;
    }
  } 
  /**
   * Helper function to take care of releasing resources associated
   * with an actively loaded data set.
   * 
   * 释放资源
   */
  protected void onReleaseResources(PageBean pageBean) {
    // For a simple List<> there is nothing to do. For something
    // like a Cursor, we would close it here.
  }

}
