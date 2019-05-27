package com.mm.zdy.uitreemodule.view.tree;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TreeView extends View {

    private Tree tree;

    private Context mContext;

    public TreeView(Context context) {
        super(context);
    }

    public TreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public TreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tree == null) {
            float screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
            float screenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
            tree = new Tree(getWidth(), getHeight(),screenWidth,screenHeight);
        }
        tree.draw(canvas);
        postInvalidate();
    }
}
