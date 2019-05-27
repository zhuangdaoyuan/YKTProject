package com.ala.msg.recycleradapter.holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

/**
 * Created by zhuangdaoyuan on 2019/5/27.
 * 安静撸码，淡定做人
 * Adapter 中的ViewHolder
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    //一个布局，多个控件
    private SparseArray<View> mViews;

    //当前View对象
    private View mContentView;

    //不能开放private
    private RViewHolder(@NonNull View itemView) {
        super(itemView);
        mContentView = itemView;
        mViews = new SparseArray<>();
    }

    public static RViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RViewHolder(view);
    }

    //获取具体的控件
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if(view==null){
            view = mContentView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    //对外提供点击的View
    public View getmContentView() {
        return mContentView;
    }

}