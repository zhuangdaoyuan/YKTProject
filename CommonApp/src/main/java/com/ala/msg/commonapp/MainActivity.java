package com.ala.msg.commonapp;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ala.msg.commonapp.multiadapter.BaseRecyclerViewActivity;
import com.ala.msg.commonapp.multiadapter.MultiAdapter;
import com.ala.msg.commonapp.multiadapter.UserInfo;
import com.ala.msg.recycleradapter.base.RViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseRecyclerViewActivity {

    private List<UserInfo> datas = new ArrayList<>();
    private RViewAdapter adapter ;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initData();
    }


    private void initData() {
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public SwipeRefreshLayout createSwipeRefreshLayout() {
        return null;
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        return new MultiAdapter(null);
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom =1;
            }
        };
    }

}
