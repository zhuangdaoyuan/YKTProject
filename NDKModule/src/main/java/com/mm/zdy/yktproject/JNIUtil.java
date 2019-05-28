package com.mm.zdy.yktproject;

public class JNIUtil {

    static {
        System.loadLibrary("native-lib");

    }


    public static native String stringFromJNI();

}
