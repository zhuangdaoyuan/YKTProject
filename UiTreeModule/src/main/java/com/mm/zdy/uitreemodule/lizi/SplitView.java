package com.mm.zdy.uitreemodule.lizi;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mm.zdy.uitreemodule.R;

import java.util.ArrayList;
import java.util.List;


public class SplitView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    private List<Ball> mBallList = new ArrayList<>();
    private float d = 3;//粒子直径

    private ValueAnimator valueAnimator;

    public SplitView(Context context) {
        super(context);
        init();
    }

    public SplitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public SplitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
        for (int i = 0; i < mBitmap.getWidth(); i++) {
            for (int j = 0; j < mBitmap.getHeight(); j++) {
                Ball ball = new Ball();
                ball.color = mBitmap.getPixel(i, j);
                ball.cx = i * d + d / 2;
                ball.cy = j * d + d / 2;
                ball.r = d / 2;
                //速度初始化
                ball.vX = (float) (Math.pow(-1, Math.ceil(Math.random() * 1000)) * 20 * Math.random());
                ball.vY = rangInt(-15, 35);
                //加速度初始化
                ball.aX = 0;
                ball.aY = 0.98f;
                mBallList.add(ball);
            }
        }
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(200);
        valueAnimator.setRepeatCount(-1);//重复执行
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                updateBall();
                postInvalidate();
            }
        });
    }

    private float rangInt(int i, int i1) {
        int max = Math.max(i, i1);
        int min = Math.min(i, i1) - 1;
        return (int) (min + Math.ceil(Math.random() * (max - min)));
    }

    private void updateBall() {
        //更新粒子位置
        for (Ball ball : mBallList) {
            ball.cx += ball.vX;
            ball.cy += ball.vY;
            ball.vX += ball.aX;
            ball.vY += ball.aY;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
        for (Ball ball : mBallList) {
            mPaint.setColor(ball.color);
            canvas.drawCircle(ball.cx, ball.cy, ball.r, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                valueAnimator.start();
                break;
        }
       return super.onTouchEvent(event);
    }
}
