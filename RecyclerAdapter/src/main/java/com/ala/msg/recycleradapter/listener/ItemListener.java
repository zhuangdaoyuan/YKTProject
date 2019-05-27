package com.ala.msg.recycleradapter.listener;

import android.view.View;

/**
 * Created by zhuangdaoyuan on 2019/5/27.
 * 安静撸码，淡定做人
 */
public interface ItemListener<T> {

    void onItemClick(View view, T entity, int position);

    void onItemLongClick(View view, T entity, int position);

}
