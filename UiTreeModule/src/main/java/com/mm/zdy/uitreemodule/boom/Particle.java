package com.mm.zdy.uitreemodule.boom;


import android.graphics.Canvas;
import android.graphics.Paint;

//粒子
public abstract class Particle {

    float cx;
    float cy;
    int color;

    public Particle(int color, float cx, float cy) {
        this.color = color;
        this.cx = cx;
        this.cy = cy;
    }

    protected abstract void draw(Canvas canvas,Paint paint);

    protected abstract void calculate(float factor);

    /**
     *
     * @param canvas
     * @param paint
     * @param factor 适配大小
     */
    public void advance(Canvas canvas, Paint paint,float factor){
        calculate(factor);
        draw(canvas,paint);
    }

}
