package com.ala.msg.commonapp.multiadapter;

import com.ala.msg.recycleradapter.base.RViewAdapter;

import java.util.List;

public class MultiAdapter extends RViewAdapter<UserInfo> {
    public MultiAdapter(List<UserInfo> datas) {
        super(datas);
        addItemStyles(new AItem());
        addItemStyles(new BItem());
        addItemStyles(new CItem());
        addItemStyles(new DItem());
        addItemStyles(new EItem());
    }
}
