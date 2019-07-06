package com.mm.zdy.uitreemodule.bubble;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mm.zdy.uitreemodule.R;

import org.jetbrains.annotations.Nullable;

/**
 * Created by zhuangdaoyuan on 2019/6/5.
 * 安静撸码，淡定做人
 */
public class BubbleView extends View {

    private Paint mPaintOriginCircle;
    private Path mPathOriginCircle;

    private float originCircleCenterX;//原始小球圆心坐标X
    private float originCircleCenterY;//原始小球圆心坐标Y
    private float smallCircleRadius;//原始小球最小半径
    private float originCircleRadius;//原始小球半径
    private float originChangeingRadius;//原小球变化中的半径

    private float moveCircleCenterX;
    private float moveCircleCenterY;

    private int circleColor;//原始小球颜色

    private Paint textOriginPaint;
    private Path textOriginPath;
    private String textContent;//内容
    private int textColor;//内容颜色
    private float textSize;//内容字体大小

    private Rect textRect = new Rect();//内容绘制区域

    private float maxDistance;

    private float mSlot = 10;

    //动画各个阶段状态
    private static final int BUBBLE_STATE_DEFAULT = 0;
    private static final int BUBBLE_STATE_CONNECT = 1;
    private static final int BUBBLE_STATE_MOVE = 2;
    private static final int BUBBLE_STATE_BOME = 3;

    private int STATE = BUBBLE_STATE_DEFAULT;


    public BubbleView(Context context) {
        this(context, null);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        //初始化View属性
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BubbleView);
        originCircleRadius = typedArray.getDimension(R.styleable.BubbleView_originCircleRadius, 20);
        maxDistance = 4 * originCircleRadius;
        smallCircleRadius = typedArray.getDimension(R.styleable.BubbleView_smallCircleRadius, 10);
        circleColor = typedArray.getColor(R.styleable.BubbleView_originCircleColor, Color.RED);
        textContent = typedArray.getString(R.styleable.BubbleView_textContent);
        textColor = typedArray.getColor(R.styleable.BubbleView_textColor, Color.WHITE);
        textSize = typedArray.getDimension(R.styleable.BubbleView_textSize, 12);
        typedArray.recycle();


        mPaintOriginCircle = new Paint();
        mPaintOriginCircle.setColor(circleColor);
        mPathOriginCircle = new Path();

        textOriginPaint = new Paint();
        textOriginPaint.setAntiAlias(true);
        textOriginPaint.setTextSize(textSize);
        textOriginPaint.setColor(textColor);
        textOriginPaint.getTextBounds(textContent, 0, textContent.length(), textRect);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        moveCircleCenterX = originCircleCenterX = w / 2;
        moveCircleCenterY = originCircleCenterY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (STATE != BUBBLE_STATE_BOME) {
            mPathOriginCircle.addCircle(moveCircleCenterX, moveCircleCenterY, originCircleRadius, Path.Direction.CW);
            canvas.drawPath(mPathOriginCircle, mPaintOriginCircle);
            canvas.drawText(textContent, 0, textContent.length(), originCircleCenterX - textRect.width() / 2, originCircleCenterY + textRect.height() / 2, textOriginPaint);
        }
    }

    private float pointerX;
    private float pointerY;
    private double moveDistance;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                pointerX = event.getX();
                pointerY = event.getY();
                moveDistance = Math.hypot(Math.abs(pointerX - originCircleCenterX), Math.abs(pointerY - originCircleCenterY));
                if (moveDistance > mSlot) {
                    moveCircleCenterX = pointerX;
                    moveCircleCenterY = pointerY;
//                    originChangeingRadius = ;


                    invalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                break;
        }


        return true;
    }
}
