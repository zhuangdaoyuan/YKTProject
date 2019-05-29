package com.ala.msg.commonapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ala.msg.commonapp.multiadapter.BaseRecyclerViewActivity;
import com.ala.msg.commonapp.multiadapter.MultiAdapter;
import com.ala.msg.commonapp.multiadapter.MultiAdapterActivity;
import com.ala.msg.commonapp.multiadapter.UserInfo;
import com.ala.msg.recycleradapter.base.RViewAdapter;
import com.ala.msg.recycleradapter.listener.ItemListener;
import com.netease.ioc.library.annotations.ContentView;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseRecyclerViewActivity {

    private List<MainBean> datas = new ArrayList<>();
    private RViewAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initData();
        setListener();
    }


    private void initData() {
        datas.add(new MainBean("面向接口编程--RecyclerView Adapter"));
        notifyAdapterDataSetChanged(datas);
    }

    private void setListener() {
        adapter.setItemListener(new ItemListener<MainBean>() {
            @Override
            public void onItemClick(View view, MainBean entity, int position) {
                Intent intent = new Intent(MainActivity.this, MultiAdapterActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, MainBean entity, int position) {

            }
        });
    }

    @Override
    public void onRefresh() {
        initData();
    }


    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        adapter= new MainMultiAdapter(null);
        return adapter;
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 10;
            }
        };
    }

}
