package com.mm.zdy.uitreemodule.boom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.HashMap;

public class ExplosionField extends View {

    private OnClickListener onClickListener;
    private ArrayList<ExplosionAnimator> explosionAnimators;
    private HashMap<View, ExplosionAnimator> explosionAnimatorHashMap;
    private ParticleFactory mParticleFactory;

    public ExplosionField(Context context, ParticleFactory factory) {
        super(context);
        attachActivity((Activity) context);
        explosionAnimators = new ArrayList<>();
        explosionAnimatorHashMap = new HashMap<>();
        mParticleFactory = factory;
    }

    /**
     * 重写onDraw()
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ExplosionAnimator explosionAnimator : explosionAnimators) {
            explosionAnimator.draw(canvas);
        }
    }

    /**
     * 分裂
     *
     * @param view 当前View
     */
    public void explode(final View view) {

        //防止重复
        if (explosionAnimatorHashMap.get(view) != null && explosionAnimatorHashMap.get(view).isStarted()) {
            return;
        }
        if (view.getVisibility() != VISIBLE && view.getAlpha() != 0) {

        }

        //得到View相对于整个屏幕的坐标（由于受到状态栏和标题栏影响，需要上移（沉浸式状态栏不需要））
        final Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        //标题栏高度
        int currentTop = ((ViewGroup) getParent()).getTop();

        //状态栏高度
        Rect frame = new Rect();
        ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        rect.offset(0, -currentTop - statusBarHeight);
        if (rect.width() == 0 || rect.height() == 0) {
            //不能分裂
            return;
        }

        //开始震动--随机
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(150);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((Utils.RANDOM.nextFloat() - 0.5f) * view.getHeight() * 0.05f);
                view.setTranslationX((Utils.RANDOM.nextFloat() - 0.5f) * view.getWidth() * 0.05f);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //震动结束--恢复view
                view.setTranslationY(0f);
                view.setTranslationX(0f);
                //开始分裂
                explode(view, rect);
            }
        });
        valueAnimator.start();

    }

    /**
     * 分裂
     *
     * @param
     */
    private void explode(final View view, Rect rect) {
        //粒子爆炸
        final ExplosionAnimator animator = new ExplosionAnimator(this, Utils.createBitmapFromView(view), rect, mParticleFactory);
        explosionAnimators.add(animator);
        explosionAnimatorHashMap.put(view, animator);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setClickable(false);
                view.animate().setDuration(150).scaleX(1f).scaleY(1f).alpha(1f).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setClickable(true);
                view.animate().setDuration(150).scaleX(0f).scaleY(0f).alpha(0f).start();
                //移除动画
                explosionAnimators.remove(animator);
                explosionAnimatorHashMap.remove(view);


            }
        });
    }

    private void attachActivity(Activity activity) {
        ViewGroup rootView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this, params);
    }

    //添加监听希望使用这个效果的View
    public void addListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                addListener(view);
            }
        } else {
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener());
        }
    }

    public OnClickListener getOnClickListener() {
        if (onClickListener == null) {
            onClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //执行粒子动画
//                    ExplosionField.this.e
                }
            };
        }
        return onClickListener;
    }
}
