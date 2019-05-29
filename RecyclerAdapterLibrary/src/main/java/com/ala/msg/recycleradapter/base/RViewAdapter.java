package com.ala.msg.recycleradapter.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ala.msg.recycleradapter.RViewItemManager;
import com.ala.msg.recycleradapter.holder.RViewHolder;
import com.ala.msg.recycleradapter.listener.ItemListener;
import com.ala.msg.recycleradapter.listener.RViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuangdaoyuan on 2019/5/27.
 * 安静撸码，淡定做人
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {
    private ItemListener<T> itemListener;
    private List<T> datas;//数据源
    private final static long QUICK_EVENT_TIME_SPAN = 1000;//事件阻塞
    private RViewItemManager<T> itemStyle;//item类型管理
    private long lastClickTime;

    //判断样式--单样式
    public RViewAdapter(List<T> datas) {
        if (datas == null) {
            this.datas = new ArrayList<>();
        }
        this.datas = datas;
        itemStyle = new RViewItemManager();
    }

    //多样式
    public RViewAdapter(List<T> datas, RViewItem<T> item) {
        if (datas == null) this.datas = new ArrayList<>();
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
        addItemStyle(item);
    }

    private void addItemStyle(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    //添加数据
    public void addDatas(List<T> datas) {
        if (datas == null) return;
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    //xiugaishuju

    public void modifyDatas(List<T> datas) {
        if (datas == null) return;
        this.datas = datas;
        notifyDataSetChanged();
    }

    //是否有多样式
    private boolean hasMultiItem() {
        return itemStyle.getItemViewStylesCount() > 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMultiItem()) {
            return itemStyle.getItemViewStyle(datas.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RViewItem viewItem = itemStyle.getRViewItem(viewType);
        int layoutId = viewItem.getItemLayout();
        RViewHolder viewHolder = RViewHolder.createViewHolder(viewGroup.getContext(), viewGroup, layoutId);
        if (viewItem.openClick()) {
            setListener(viewHolder);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder, datas.get(position));
    }

    private void convert(RViewHolder holder, T entity) {
        itemStyle.convert(holder, entity, holder.getAdapterPosition());
    }

    private void setListener(final RViewHolder rViewHolder) {
        rViewHolder.getmContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null) {
                    int position = rViewHolder.getAdapterPosition();
                    itemListener.onItemClick(v, datas.get(position), position);
                }
            }
        });

        rViewHolder.getmContentView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemListener != null) {
                    int position = rViewHolder.getAdapterPosition();
                    itemListener.onItemLongClick(v, datas.get(position), position);
                    return true;
                }
                return false;
            }
        });
    }


    public void addItemStyles(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }
}
