package com.ala.msg.recycleradapter;

import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshHelper {
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshListener swipeRefreshListener;

    static SwipeRefreshHelper createSwipeRefreshHelper(@Nullable SwipeRefreshLayout swipeRefreshLayout) {
        return new SwipeRefreshHelper(swipeRefreshLayout);
    }

    private SwipeRefreshHelper(@Nullable SwipeRefreshLayout swipeRefreshLayout, @ColorRes int... colorResIds) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        init(colorResIds);
    }

    private void init(@ColorRes int... colorResIds) {

        if (colorResIds == null || colorResIds.length == 0) {
            swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark, android.R.color.holo_green_dark, android.R.color.holo_blue_dark);
        } else {
            swipeRefreshLayout.setColorSchemeResources(colorResIds);
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshListener != null) {
                    swipeRefreshListener.onRefresh();
                }
            }
        });
    }

    public interface SwipeRefreshListener {
        void onRefresh();
    }

    void setSwipeRefreshListener(SwipeRefreshListener swipeRefreshListener) {
        this.swipeRefreshListener = swipeRefreshListener;
    }

}
