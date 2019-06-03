package com.mm.zdy.uitreemodule.lizi;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.mm.zdy.uitreemodule.R;

public class SplashView extends View {

    //旋转圆的画笔
    private Paint mPaint;
    //扩散圆的画笔
    private Paint mHolePaint;
    //属性动画
    private ValueAnimator mValueAnimator;

    //背景色
    private int mBackgroundColor = Color.WHITE;
    private int[] mCircleColor;

    //表示旋转圆的中心坐标
    private float mCenterX;
    private float mCenterY;

    //表示斜对角长度的一半，扩散圆最大半径
    private float mDistance;

    //6个小球半径
    private float mCircleRadius = 18;
    // 旋转大圆的半径
    private float mRotateRadius = 90;

    //当前最大的旋转角度
    private float mCurrentRotateAngle = 0F;
    //当前最大圆半径
    private float mCurrentRotateRadius = mRotateRadius;
    //扩散圆的半径
    private float mCurrentHoleRadius = 0F;
    //表示旋转动画的时长
    private int mRotationDuration = 1200;

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mHolePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHolePaint.setStyle(Paint.Style.STROKE);
        mHolePaint.setColor(mBackgroundColor);

        mCircleColor = context.getResources().getIntArray(R.array.splash_circle_colors);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w * 1f / 2;
        mCenterY = h * 1f / 2;
        mDistance = (float) (Math.hypot(w, h) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mState == null) {
            mState = new RotateState();
        }
        mState.drawState(canvas);
    }

    private SplashState mState;

    private abstract class SplashState {
        abstract void drawState(Canvas canvas);
    }

    //1.旋转
    private class RotateState extends SplashState {
        private RotateState (){
            mValueAnimator = ValueAnimator.ofFloat(0, (float) (Math.PI*2));
            mValueAnimator.setRepeatCount(2);
            mValueAnimator.setDuration(mRotationDuration);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotateAngle  = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            mValueAnimator .start();
        }
        @Override
        void drawState(Canvas canvas) {
            //绘制背景
            drawBackground(canvas);
            //绘制6个小球
            drawCircles(canvas);
        }

    }

    private void drawCircles(Canvas canvas) {
        float rotateAngle = (float) (Math.PI * 2 / mCircleColor.length);
        for (int i = 0; i < mCircleColor.length; i++) {
            //x = r *cos(a)+centX;
            //y = r*sin(a) +centY;
            float angle = i * rotateAngle;
            float cx = (float) (Math.cos(angle) * mRotateRadius + mCenterY);
            float cy = (float) (Math.sin(angle) * mRotateRadius + mCenterY);
            mPaint.setColor(mCircleColor[i]);
            canvas.drawCircle(cx, cy, mCircleRadius, mPaint);
        }
    }

    private void drawBackground(Canvas canvas) {
        canvas.drawColor(mBackgroundColor);
    }

    //2.扩散聚合

    //3.水波纹

}
