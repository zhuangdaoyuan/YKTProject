package com.mm.zdy.uitreemodule.imagetranslate_scale_rotate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.mm.zdy.uitreemodule.R;

public class CustomImageView extends ImageView {


    private Matrix matrix;
    private Matrix currentMatrix;
    private float originX;
    private float originY;


    private float currentX;
    private float currentY;


    private boolean isMoreFinger = false;//是否多指触碰
    private Point middlePoint = new Point();//两之间中点

    private float originLength;//两指间最初距离
    private double originDegree;

    private Bitmap bitmap;

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        matrix = new Matrix();
        currentMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        matrix.setTranslate((w-bitmap.getWidth())/2,(h-bitmap.getHeight())/2);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                isMoreFinger = false;
                originX = event.getX();
                originY = event.getY();
                currentMatrix.set(matrix);
                break;
            case MotionEvent.ACTION_MOVE:
                matrix.set(currentMatrix);

                if (isMoreFinger) {
                    matrix.set(currentMatrix);
                    float lengthScale = getLength(event) / originLength;
                    float degreeScale = (float) (getDegree(event)/originDegree);
                    matrix.postScale(lengthScale, lengthScale, middlePoint.x, middlePoint.y);
                    matrix.postRotate(degreeScale,middlePoint.x,middlePoint.y);
                } else {
                    currentX = event.getX();
                    currentY = event.getY();

                    float rX = currentX - originX;
                    float rY = currentY - originY;
//                    matrix.setTranslate(currentX,currentY);
                    matrix.postTranslate(rX, rY);
                }
                invalidate();

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                isMoreFinger = true;
                getMidPoint(event);
                originLength = getLength(event);
                originDegree = getDegree(event);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawBitmap(bitmap, matrix, null);

        canvas.restore();

    }


    private void getMidPoint(MotionEvent event) {
        middlePoint.set((int) (event.getX(1) + event.getX(0)) / 2, (int) (event.getY(1) + event.getY(0)) / 2);
    }

    private float getLength(MotionEvent event) {
         return  (float) Math.hypot(Math.abs(event.getX(1) - event.getX(0)), Math.abs(event.getY(1) - event.getY(0)));
    }


    private double getDegree(MotionEvent event){
        return Math.toDegrees(Math.atan2(event.getX(1)-event.getX(0),event.getY(1)-event.getY(0)));
    }
}
