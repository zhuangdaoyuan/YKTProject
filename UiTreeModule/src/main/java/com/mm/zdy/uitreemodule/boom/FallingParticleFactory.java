package com.mm.zdy.uitreemodule.boom;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class FallingParticleFactory extends ParticleFactory {

    public static final int PART_WH = 8;

    @Override
    public Particle[][] generateParticles(Bitmap bitmap, Rect rect) {

        int w = rect.width();
        int h = rect.height();
        int partW_Count = w / PART_WH;//横向列数
        int partH_Count = h / PART_WH;//纵向行数
        partW_Count = partW_Count > 0 ? partW_Count : 1;
        partH_Count = partH_Count > 0 ? partH_Count : 1;

        int bitmap_part_w = bitmap.getWidth() / partW_Count;
        int bitmap_part_h = bitmap.getHeight() / partH_Count;

        Particle[][] particles = new Particle[partH_Count][partW_Count];
        for(int row =0;row<bitmap_part_h;row++){
            for(int column =0;column<bitmap_part_w;column++){
                //去的当前粒子所在的位置的颜色
                int color = bitmap.getPixel(column*bitmap_part_w,row*bitmap_part_h);
                float x = rect.left+PART_WH*column;
                float y = rect.top +PART_WH*row;

                particles[row][column] = new FallingParticle(color,x,y,rect);
            }
        }
        return  particles;
    }
}
