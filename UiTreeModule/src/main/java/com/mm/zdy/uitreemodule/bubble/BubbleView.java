package com.mm.zdy.uitreemodule.bubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.mm.zdy.uitreemodule.R;

import org.jetbrains.annotations.Nullable;

/**
 * Created by zhuangdaoyuan on 2019/6/5.
 * 安静撸码，淡定做人
 */
public class BubbleView extends View {

    private Path mPathOriginCircle;
    private Paint mPaintOriginCircle;
    private float originCircleCenterX;//原始小球圆心坐标X
    private float originCircleCenterY;//原始小球圆心坐标Y

    private float originCircleRadius;//原始小球半径
    private int originCircleColor ;//原始小球颜色

    private Paint textOrignPaint;
    private Path textOringPath;
    private String textContent;//内容
    private int textColor;//内容颜色
    private float textSize;//内容字体大小

    private Rect textRect;//内容绘制区域



    public BubbleView(Context context) {
        this(context,null);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        //初始化View属性
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.BubbleView);
        originCircleRadius = typedArray.getDimension(R.styleable.BubbleView_originCircleRadius,25);
        originCircleColor = typedArray.getColor(R.styleable.BubbleView_originCircleColor,Color.RED);
        textContent = typedArray.getString(R.styleable.BubbleView_textContent);
        textColor =typedArray.getColor(R.styleable.BubbleView_textColor,Color.WHITE);
        textSize = typedArray.getDimension(R.styleable.BubbleView_textSize,12);
        typedArray.recycle();
        mPaintOriginCircle = new Paint();
        mPaintOriginCircle.setColor(originCircleColor);

        textOrignPaint = new Paint();
        textOrignPaint.setAntiAlias(true);
        textOrignPaint.setTextSize(textSize);
        textOrignPaint.setColor(textColor);
        textOrignPaint.getTextBounds(textContent,0,textContent.length(),textRect);

        mPathOriginCircle  = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originCircleCenterX = w/2;
        originCircleCenterY = h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPathOriginCircle.addCircle(originCircleCenterX,originCircleCenterY,originCircleRadius,Path.Direction.CW);
        canvas.drawPath(mPathOriginCircle,mPaintOriginCircle);

//        canvas.drawPath();

    }
}
