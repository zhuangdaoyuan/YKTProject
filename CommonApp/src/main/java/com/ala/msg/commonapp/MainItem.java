package com.ala.msg.commonapp;

import android.widget.TextView;

import com.ala.msg.recycleradapter.holder.RViewHolder;
import com.ala.msg.recycleradapter.listener.RViewItem;

/**
 * Created by zhuangdaoyuan on 2019/5/28.
 * 安静撸码，淡定做人
 */
public class MainItem implements RViewItem<MainBean> {
    @Override
    public int getItemLayout() {
        return R.layout.item_main;
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(MainBean entity, int position) {
        return true;
    }

    @Override
    public void convert(RViewHolder holder, MainBean entity, int position) {
        TextView textView = holder.getView(R.id.itemMain);
        textView.setText(entity.getItemName());
    }

}
