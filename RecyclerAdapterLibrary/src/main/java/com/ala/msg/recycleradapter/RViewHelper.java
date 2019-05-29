package com.ala.msg.recycleradapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ala.msg.recycleradapter.base.RViewAdapter;
import com.ala.msg.recycleradapter.listener.RViewCreate;

import java.util.List;

/**
 * Created by zhuangdaoyuan on 2019/5/27.
 * 安静撸码，淡定做人
 * 帮助管理类
 */
public class RViewHelper<T> {
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshHelper swipeRefreshHelper;
    private RecyclerView recyclerView;
    private RViewAdapter<T> rViewAdapter;
    private final int startPageNumber = 1;
    private SwipeRefreshHelper.SwipeRefreshListener listener;
    private int currentPageNum;
    private boolean isSupportPaging = false;


    public RViewHelper(Builder<T> builder) {
        this.swipeRefreshLayout = builder.create.createSwipeRefreshLayout();
        this.recyclerView = builder.create.createRecyclerView();
        this.rViewAdapter = builder.create.createRecyclerViewAdapter();
        this.context = builder.create.context();
        this.isSupportPaging = builder.create.isSupportPaging();
        this.listener = builder.listener;

        this.currentPageNum = this.startPageNumber;
        if (swipeRefreshLayout != null) {
            swipeRefreshHelper = SwipeRefreshHelper.createSwipeRefreshHelper(swipeRefreshLayout);
        }
        init();
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (swipeRefreshHelper != null) {
            swipeRefreshHelper.setSwipeRefreshListener(new SwipeRefreshHelper.SwipeRefreshListener() {
                @Override
                public void onRefresh() {
                    if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                        currentPageNum = startPageNumber;
                        if (listener != null) {
                            listener.onRefresh();
                        }
                    }
                }

            });
        }

    }


    public void notifyAdapterDataSetChanged(List datas) {
        if (currentPageNum == startPageNumber) {
            rViewAdapter.modifyDatas(datas);
            recyclerView.setAdapter(rViewAdapter);
        } else {
            rViewAdapter.addDatas(datas);
        }
        if (isSupportPaging) {
            // TODO: 2019/5/27 分页 加载更多，最后一条、空布局
        }
    }

    public static class Builder<T> {
        private RViewCreate<T> create;
        private SwipeRefreshHelper.SwipeRefreshListener listener;

        public Builder(RViewCreate<T> create, SwipeRefreshHelper.SwipeRefreshListener listener) {
            this.create = create;
            this.listener = listener;

        }

        public RViewHelper build() {
            return new RViewHelper(this);
        }
    }
}
