package com.ala.msg.recycleradapter;

import android.support.v4.util.SparseArrayCompat;

import com.ala.msg.recycleradapter.holder.RViewHolder;
import com.ala.msg.recycleradapter.listener.RViewItem;

/**
 * Created by zhuangdaoyuan on 2019/5/27.
 * 安静撸码，淡定做人
 * 多类型，多样式管理器
 */
public class RViewItemManager<T> {
    //所有RViewItem集合 key: viewType value:RViewItem
    private SparseArrayCompat<RViewItem<T>> styles = new SparseArrayCompat<>();

    //加入一个新的Item样式
    public void addStyles(RViewItem<T> item) {
        if (item != null) {
            styles.put(styles.size(), item);
        }
    }

    public int getItemViewStylesCount() {
        return styles.size();
    }

    public int getItemViewStyle(T entity, int position) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> item = styles.valueAt(i);
            if (item.isItemView(entity, position)) {
                return styles.keyAt(i);
            }
        }
        throw new IllegalArgumentException("没有匹配的类型");
    }

    //styles中的value
    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    //将视图和数据源绑定显示
    public void conver(RViewHolder holder, T entity, int position) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> item = styles.valueAt(i);
            if(item.isItemView(entity,position)){
                item.convert(holder,entity,position);
            }
        }
        throw new IllegalArgumentException("");
    }
}
