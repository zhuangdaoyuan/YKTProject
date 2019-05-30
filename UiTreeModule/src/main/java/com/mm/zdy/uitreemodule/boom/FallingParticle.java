package com.mm.zdy.uitreemodule.boom;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class FallingParticle extends Particle {
    private Rect rect;
    private float radius = FallingParticleFactory.PART_WH;
    private float alpha = 1.0f;

    public FallingParticle(int color, float cx, float cy, Rect rect) {
        super(color, cx, cy);
        this.rect = rect;
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha));
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void calculate(float factor) {
        cx = factor * Utils.RANDOM.nextInt(rect.width()) * (Utils.RANDOM.nextFloat() - 0.5f);
        cy = factor * Utils.RANDOM.nextInt(rect.height() / 2);
        radius = factor * Utils.RANDOM.nextInt(2);
        alpha = factor * (Utils.RANDOM.nextFloat() + 1);
    }
}
