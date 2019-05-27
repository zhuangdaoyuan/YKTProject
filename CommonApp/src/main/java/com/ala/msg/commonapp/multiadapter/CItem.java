package com.ala.msg.commonapp.multiadapter;

import android.widget.TextView;

import com.ala.msg.commonapp.R;
import com.ala.msg.recycleradapter.holder.RViewHolder;
import com.ala.msg.recycleradapter.listener.RViewItem;


public class CItem implements RViewItem<UserInfo> {

    @Override
    public int getItemLayout() {
        return R.layout.item_c;
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(UserInfo entity, int position) {
        return entity.getType() == 3;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo entity, int position) {
        TextView textView = holder.getView(R.id.mtv);
        textView.setText(entity.getAccount());
    }
}
