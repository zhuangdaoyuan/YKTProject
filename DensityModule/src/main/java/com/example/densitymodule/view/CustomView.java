package com.example.densitymodule.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 庄道园
 * 安静撸码淡定做人
 **/
public class CustomView extends View {

    private float originX;//初始位置
    private float originY;
    private int radius = 40;


    private float currentX;//手指位置x坐标
    private float currentY;//y坐标
    private final int tapSlot = 10;//移动最小距离


    private Paint mPaint;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        currentX = originX = getWidth() / 2;
        currentY = originY = getHeight() / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("HH", event.getAction() + "");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentX = event.getX();
                currentY = event.getY();
                if (isInside()) {
                    Toast.makeText(getContext(), "您点击的位置在view内部", Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                if (Math.hypot(Math.abs(currentX - originX), Math.abs(currentY - originY)) > tapSlot) {
                    originX = currentX;
                    originY = currentY;
                    invalidate();
                }
                break;


        }
        return true;

    }


    private boolean isInside() {
        return Math.hypot(Math.abs(currentX - originX), Math.abs(currentY - originY)) < radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(currentX, currentY, radius, mPaint);
    }
}
