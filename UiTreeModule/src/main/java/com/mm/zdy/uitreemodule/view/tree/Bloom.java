package com.mm.zdy.uitreemodule.view.tree;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

//树叶
public class Bloom {
    static float sMaxScale = 0.2f;
    static int sMaxRadius = Math.round(sMaxScale * Heart.getRADIUS());
    static float sFactor;
    Point position;
    int color;
    float angle;
    float scale;
    private boolean isOdd;//

    Bloom(Point position) {
        this.position = position;
        this.color = Color.argb(CommonUtil.random(75, 255), 255, CommonUtil.random(255), CommonUtil.random(255));
        this.angle = CommonUtil.random(360);
    }

    public static void initDislayparam(float resolutionFactor) {
        sFactor = resolutionFactor;
        sMaxScale = 0.2f * resolutionFactor;
        sMaxRadius = Math.round(sMaxScale * Heart.getRADIUS());
    }

    float getRadius(){
        return Heart.getRADIUS()*scale;
    }

    public boolean grow(Canvas canvas) {
        if (scale <= sMaxScale) {
            if (isOdd) {
                scale += 0.0125f * sFactor;
                draw(canvas);
            }
            isOdd = !isOdd;
            return true;
        }
        return false;
    }
    void draw(Canvas canvas){
        Paint paint = CommonUtil.getPaint();
        paint.setColor(color);
        float r = getRadius();

        canvas.save();
        canvas.translate(position.x,position.y);
        canvas.saveLayerAlpha(-r,-r,r,r,Color.alpha(color));//类似save，有透名度画布
        canvas.rotate(angle);
        canvas.scale(scale,scale);
        canvas.drawPath(Heart.getPATH(),paint);
        canvas.restore();//避免下次绘制有影响
        canvas.restore();
    }


}
