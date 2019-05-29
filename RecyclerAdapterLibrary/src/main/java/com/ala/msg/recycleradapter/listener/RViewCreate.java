package com.ala.msg.recycleradapter.listener;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.ala.msg.recycleradapter.base.RViewAdapter;


/**
 * 创建RViewHelper需要的数据，实现类方便创建RViewHelper对象
 * @param <T>
 */
public interface RViewCreate<T> {
    Context context();

    //创建下拉刷新
    SwipeRefreshLayout createSwipeRefreshLayout();

    //SwipeRefresh 下拉颜色集合
    int[] colorRes();

    //创建Adapter
    RViewAdapter<T> createRecyclerViewAdapter();

    //创建RecyclerView
    RecyclerView createRecyclerView();

    RecyclerView.LayoutManager createLayoutManager();

    //创建分割线
    RecyclerView.ItemDecoration createItemDecoration();

    //开始页码
    int startPagingNumber();


    //是否支持分页
    boolean isSupportPaging();
}
