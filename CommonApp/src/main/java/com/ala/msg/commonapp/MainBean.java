package com.ala.msg.commonapp;

import java.io.Serializable;

/**
 * Created by zhuangdaoyuan on 2019/5/28.
 * 安静撸码，淡定做人
 */
public class MainBean implements Serializable {
    private String itemName;

    public MainBean(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
