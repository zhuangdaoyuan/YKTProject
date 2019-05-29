package com.netease.qrcode.library;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;

import com.google.zxing.Result;
import com.netease.qrcode.library.camera.CameraManager;

public interface CaptureCallback {
    
    Rect getCropRect();
    
    Handler getHandler();

    CameraManager getCameraManager();

    void handleDecode(Result result, Bundle bundle);

    void setResult(int resultCode, Intent intent);

    void finish();
}
