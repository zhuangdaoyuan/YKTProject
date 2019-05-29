package com.mm.zdy.yktproject;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    private GifInfoHandle gifInfoHandle;
    private Bitmap bitmap;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long nextFrameTime = gifInfoHandle.renderFrame(bitmap);
            imageView.setImageBitmap(bitmap);
            handler.sendEmptyMessageDelayed(1, nextFrameTime);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img);
        try {
            InputStream gifInputStream = getResources().getAssets().open("timg.gif");
            gifInfoHandle = new GifInfoHandle(gifInputStream);
            int width = gifInfoHandle.getWidth();
            final int height = gifInfoHandle.getHeight();
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            imageView.setImageBitmap(bitmap);
            long nextFrameTime = gifInfoHandle.renderFrame(bitmap);
            handler.sendEmptyMessageDelayed(1, nextFrameTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
