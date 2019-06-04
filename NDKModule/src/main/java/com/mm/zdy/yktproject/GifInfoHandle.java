package com.mm.zdy.yktproject;

import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by zhuangdaoyuan on 2019/5/29.
 * 安静撸码，淡定做人
 */
public class GifInfoHandle {

    private   volatile long gifInfoPtr;
    //调用native
    static {
        System.loadLibrary("nativeLib");

    }

    public GifInfoHandle(String filePath){
       gifInfoPtr = openFile(filePath);
    }

    public GifInfoHandle(InputStream inputStream){
        gifInfoPtr = openStream(inputStream);
    }



    //下一次渲染间隔时间
    public long renderFrame(Bitmap bitmap){
       return renderFrame(gifInfoPtr,bitmap);
    }

    public synchronized  int getWidth(){
        return getWidth(gifInfoPtr);
    }
    public synchronized int getHeight(){
        return getHeight(gifInfoPtr);
    }

    private native long openFile(String path);

    private native long openStream(InputStream inputStream);

    private native long renderFrame(long gifInfoPtr, Bitmap bitmap);

    private native int getWidth(long gifInfoPtr);

    private native int getHeight(long gifInfoPtr);

}
