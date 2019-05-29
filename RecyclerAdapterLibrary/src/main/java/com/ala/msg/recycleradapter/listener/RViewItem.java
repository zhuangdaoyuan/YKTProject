package com.ala.msg.recycleradapter.listener;

import com.ala.msg.recycleradapter.holder.RViewHolder;

/**
 * Created by zhuangdaoyuan on 2019/5/27.
 * 安静撸码，淡定做人
 * 某一类item 对象
 */
public interface RViewItem<T> {
    //item布局
    int getItemLayout();

    //是否开启点击
    boolean openClick();

    //条目用到的布局
    boolean isItemView(T entity, int position);

    //将Item的控件与数据绑定
    void convert(RViewHolder holder, T entity, int position);
}
