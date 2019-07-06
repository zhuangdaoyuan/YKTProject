package com.example.densitymodule;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

//修改系统Density
public class Density {
    private static final int WIDTH =360;//参考设备的宽，单位dp 360／2=160

    private static float appDensity;//屏幕密度

    private static float appScaleDensity;//字体缩放比例

    public static  void setDensity(final Application application, Activity activity){
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if(appDensity==0){
            appDensity = displayMetrics.density;
            appScaleDensity = displayMetrics.scaledDensity;
            //添加字体大小改变监听
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig!=null&&newConfig.fontScale>0){
                        //表示字体大小发生改变，重新对appSCaleDensity赋值
                        appScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
            float targetDensity = displayMetrics.widthPixels/WIDTH;
            float targetScaleDensity = targetDensity*(appScaleDensity/appDensity);
            int targetDensityDPI = (int) (targetDensity/WIDTH/2);
            // 替换当前Activity Density
            DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
            activityDisplayMetrics.density = targetDensity;
            activityDisplayMetrics.scaledDensity = targetScaleDensity;
            activityDisplayMetrics.densityDpi = targetDensityDPI;
        }
    }

}
