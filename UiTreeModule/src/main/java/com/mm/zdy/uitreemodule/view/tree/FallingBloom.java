package com.mm.zdy.uitreemodule.view.tree;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

public class FallingBloom extends Bloom {
    private Snapshot snapshot;
    private boolean validate;

    private float xSpeed;
    private float ySpeed;

    FallingBloom(Point position) {
        super(position);
        color |= 0xFF000000;
        scale = sMaxScale;
        validate = false;
        snapshot = new Snapshot(Bitmap.createBitmap(sMaxRadius * 2, sMaxRadius * 2, Bitmap.Config.ARGB_8888));
        initSpeed();
    }

    private void initSpeed() {
        ySpeed = CommonUtil.random(1.6f * sFactor, 2.7f * sFactor);
        xSpeed = ySpeed * CommonUtil.random(0.5f * sFactor, 1.5f * sFactor);
    }

    public boolean fall(Canvas canvas,float fMaxY){
        if(!validate){
            //绘制飘落树叶
            makeSnapshot();
            validate = true;
        }
        float r = getRadius();
        if(position.y-r<fMaxY){
            position.x -= xSpeed;
            position.y += ySpeed;
            angle+=1f;
            draw(canvas);
            return true;
        }else {
            return false;
        }
    }

    private void makeSnapshot() {
        //绘制飘落的心形
        Paint paint = CommonUtil.getPaint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        Canvas canvas = snapshot.canvas;
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.save();
        canvas.translate(sMaxRadius,sMaxRadius);
        canvas.rotate(angle);
        canvas.scale(scale,scale);
        canvas.drawPath(Heart.getPATH(),paint);
        canvas.restore();
    }

    private void draw(Canvas canvas){
        Paint paint = CommonUtil.getPaint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.save();
        canvas.translate(position.x,position.y);
        canvas.rotate(angle);
        canvas.translate(-sMaxRadius,-sMaxRadius);
        canvas.drawBitmap(snapshot.bitmap,0,0,paint);
        canvas.restore();
    }

    public void reset(float x, float y) {
        this.position.y = x;
        this.position.y = y;
        this.color = Color.argb(255,255,CommonUtil.random(255),CommonUtil.random(255));
        this.angle = CommonUtil.random(360);
        validate = false;
    }
}
