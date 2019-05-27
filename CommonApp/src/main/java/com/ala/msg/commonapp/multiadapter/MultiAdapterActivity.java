package com.ala.msg.commonapp.multiadapter;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ala.msg.commonapp.R;
import com.ala.msg.recycleradapter.base.RViewAdapter;
import com.netease.ioc.library.annotations.ContentView;
import com.netease.ioc.library.annotations.OnItemClick;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MultiAdapterActivity extends BaseRecyclerViewActivity {
    private List<UserInfo> datas = new ArrayList<>();
    private MultiAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDatas();
    }

    @Override
    public void onRefresh() {
        initDatas();
    }

    @OnItemClick(R.id.recyclerView)
    private void abc(View v, UserInfo info, int position) {

    }

    private void initDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (datas.isEmpty()) {
                    for (int i = 0; i < 15; i++) {
                        for (int j = 1; j <= 15; j++) {
                            UserInfo user = new UserInfo();
                            if (j % 15 == 1) {
                                user.setType(1);
                                user.setAccount("NetEase >>>>>>>>> 11111 >>>>>>>>>");
                            } else if (j % 15 == 2 || j % 15 == 3) {
                                user.setType(2);
                                user.setAccount("NetEase >>>>>>> 22222 >>>>>>>");
                            } else if (j % 15 == 4 || j % 15 == 5 || j % 15 == 6) {
                                user.setType(3);
                                user.setAccount("NetEase >>>>> 33333 >>>>>");
                            } else if (j % 15 == 7 || j % 15 == 8 || j % 13 == 9 || j % 15 == 10) {
                                user.setType(4);
                                user.setAccount("NetEase >>> 44444 >>>");
                            } else {
                                user.setType(5);
                                user.setAccount("NetEase > 55555 >");
                            }
                            datas.add(user);
                        }
                    }
                }
                notifyAdapterDataSetChanged(datas);
            }
        }).start();
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        adapter = new MultiAdapter(null);
        return adapter;
    }

    @Override
    public RecyclerView.ItemDecoration createItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        };
    }

}
