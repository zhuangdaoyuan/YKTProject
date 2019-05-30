package com.mm.zdy.uitreemodule.boom;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class ExplosionAnimator extends ValueAnimator {
    private static final int DEFAULT_DURATION = 1500;
    private Particle[][] particles;
    private Paint mPaint;
    private View mContainer;
    private ParticleFactory mParticleFactory;

    public ExplosionAnimator(ExplosionField explosionField, Bitmap bitmapFromView, Rect rect, ParticleFactory particleFactory) {
        mParticleFactory = particleFactory;
        mPaint = new Paint();
        mContainer = explosionField;
        particles = mParticleFactory.generateParticles(bitmapFromView,rect);
        setFloatValues(0f,1.0f);
        setDuration(DEFAULT_DURATION);

    }

    public void draw(Canvas canvas) {
        if(!isStarted()){
            return;
        }
        for(Particle[] particles:particles){
            for(Particle particle:particles){
                particle.advance(canvas,mPaint,(float)getAnimatedValue());
            }
        }
    }
    @Override
    public void start(){
        super.start();
        mContainer.invalidate();
    }
}
