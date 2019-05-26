package com.mm.zdy.uitreemodule.view.tree;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;

//树干
public class Branch {
    private static int branchColor = 0xFF775533;
    //二阶贝塞尔曲线，三个控制点
    private Point[] cp = new Point[3];
    //每一帧信息
    private int currentLength;//
    private float maxLength;//
    private float radius;//
    private float part;//
    private float growX;//
    private float growY;//

    LinkedList<Branch> childList;

    Branch(int[] data) {
//        {0, -1, 217, 490, 252, 60, 182, 10, 50, 100},
        cp[0] = new Point(data[2], data[3]);
        cp[1] = new Point(data[4], data[5]);
        cp[2] = new Point(data[6], data[7]);
        radius = data[8];
        maxLength = data[9];
        part = 1.0f / maxLength;
    }

    public boolean grow(Canvas canvas, float scaleFactor) {
        if (currentLength < maxLength) {
            bazier(part * currentLength);
            draw(canvas, scaleFactor);
            currentLength++;
            radius *= 0.8;
            return true;
        }
        return false;
    }

    private void draw(Canvas canvas, float scaleFactor) {
        Paint paint = CommonUtil.getPaint();
        paint.setColor(branchColor);

        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        canvas.translate(growX, growY);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.restore();
    }

    //二阶贝塞尔曲线公式 （1-t）*(1-t)*p0 +2*t(1-t)*p1 +t*t*p2
    private void bazier(float t) {
        float c0 = (1 - t) * (1 - t);
        float c1 = 2 * t * (1 - t);
        float c2 = t * t;
        growX = c0 * cp[0].x + c1 * cp[1].x + c2 * cp[2].x;
        growY = c0 * cp[0].y + c1 * cp[1].y + c2 * cp[2].y;
    }

    public void addChild(Branch branch) {
        if (childList == null) {
            childList = new LinkedList<>();
        }
        childList.add(branch);
    }
}
