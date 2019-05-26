package com.mm.zdy.uitreemodule.view.tree;

import android.graphics.Paint;

import java.util.Random;

public class CommonUtil {
    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final Paint PAINT = new Paint();


    /**
     * get random from range[0,n]
     */
    public static int random(int n) {
        return RANDOM.nextInt(n + 1);
    }

    /**
     * get random from range[m,n]
     * require m<n
     */
    public static int random(int m, int n) {
        int d = n - m;
        return m + RANDOM.nextInt(d + 1);
    }

    public static float random(float m, float n) {
        float d = n - m;
        return m + RANDOM.nextFloat() * d;
    }

    public static Paint getPaint() {
        return PAINT;
    }
}
