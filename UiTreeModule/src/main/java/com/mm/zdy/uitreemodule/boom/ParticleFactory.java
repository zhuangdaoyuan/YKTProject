package com.mm.zdy.uitreemodule.boom;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class ParticleFactory {

    public abstract Particle[][] generateParticles(Bitmap bitmap, Rect rect);
}
