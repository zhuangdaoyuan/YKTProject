package com.ala.msg.commonapp.multiadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ala.msg.commonapp.R;
import com.ala.msg.recycleradapter.RViewHelper;
import com.ala.msg.recycleradapter.SwipeRefreshHelper;
import com.ala.msg.recycleradapter.listener.RViewCreate;
import com.netease.ioc.library.InjectManager;

import java.util.List;

public abstract class BaseRecyclerViewActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshHelper.SwipeRefreshListener {


    private RViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectManager.inject(this);
        helper = new RViewHelper.Builder(this, this).build();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public int[] colorRes() {
        return new int[0];
    }

    @Override
    public SwipeRefreshLayout createSwipeRefreshLayout() {
        return findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public RecyclerView createRecyclerView() {
        return findViewById(R.id.recyclerView);
    }

    @Override
    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public boolean isSupportPaging() {
        return false;
    }

    @Override
    public int startPagingNumber() {
        return 1;
    }

    protected void notifyAdapterDataSetChanged(List datas) {
        helper.notifyAdapterDataSetChanged(datas);
    }
}
