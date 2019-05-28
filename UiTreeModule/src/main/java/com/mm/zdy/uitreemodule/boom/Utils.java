package com.mm.zdy.uitreemodule.boom;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.Random;

public class Utils {

    public static final Random RANDOM = new Random();

//    1.生成原来一样的图片
//    2.添加一个全屏动画场地
//    3.执行动画 1>震动 2>缩小透明---爆炸 3>爆炸结束--view恢复
    private static final Canvas CANVAS = new Canvas();
    public static Bitmap createBitmapFromView(View view){
        view.clearFocus();;//使view恢复原样
        Bitmap bitmap = createBitmapSafely(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888,1);
        if (bitmap!=null){
            synchronized (CANVAS){
                view.draw(CANVAS);
                CANVAS.restore();
            }
        }
        return bitmap;
    }

    private static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config ,int retryCount) {
        try {
            return Bitmap.createBitmap(width,height,config);

        }catch (OutOfMemoryError e){
            e.printStackTrace();
            if(retryCount>0){
                System.gc();
                return createBitmapSafely(width,height,config,retryCount-1);
            }
        }
        return null;
    }
}
