package com.ala.msg.commonapp.multiadapter;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private String account;
    private String password;
    private int type; // 布局类型

    public UserInfo() {
    }

    public UserInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
