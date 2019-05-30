package com.mm.zdy.uitreemodule.boom;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class ParticleFactory {

    //将图片拆分存到二维数组中
    public abstract Particle[][] generateParticles(Bitmap bitmap, Rect rect);
}
