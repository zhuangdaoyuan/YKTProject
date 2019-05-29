package com.ala.msg.commonapp;

import com.ala.msg.recycleradapter.base.RViewAdapter;

import java.util.List;

/**
 * Created by zhuangdaoyuan on 2019/5/28.
 * 安静撸码，淡定做人
 */
public class MainMultiAdapter extends RViewAdapter<MainBean> {
    public MainMultiAdapter(List<MainBean> datas) {
        super(datas);
        addItemStyles(new MainItem());
    }


}
