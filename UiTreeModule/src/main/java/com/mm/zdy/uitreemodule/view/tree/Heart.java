package com.mm.zdy.uitreemodule.view.tree;

import android.graphics.Path;

//心形
public class Heart {
    private static Path PATH = new Path();
    private static final float SCALE_FACTORY = 10f;
    private static final float RADIUS = 18 * SCALE_FACTORY;

    static {
        //x
        int n = 100;
        Point[] points = new Point[n];
        float t = 0f;
        float dt = (float) (2 * Math.PI / (n - 1));
        for (int i = 0; i < n; i++) {
            float x = (float) (16 * (Math.pow(Math.sin(t), 3)));
            float y = (float) (13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t));
            points[i] = new Point(x * SCALE_FACTORY, y * SCALE_FACTORY);
            t += dt;
            if (i == 0) {
                PATH.moveTo(points[i].x, points[i].y);
            } else {
                PATH.lineTo(points[i].x, points[i].y);
            }

        }
        PATH.close();
    }

    public static float getRADIUS() {
        return RADIUS;
    }

    public static Path getPATH() {
        return PATH;
    }
}
