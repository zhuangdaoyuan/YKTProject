package com.mm.zdy.uitreemodule.payedittext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText {

    private Paint mPaint;
    private int width;
    private int height;
    private Path mPath;
    private int contentWidth;


    public CustomEditText(Context context) {
        this(context, null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(16);
        mPath = new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        contentWidth = width / 6;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mPath.moveTo(width, 0);
        mPath.rLineTo(width, height);
        mPath.rLineTo(0, height);
        mPath.rLineTo(0, 0);
        canvas.drawPath(mPath, mPaint);
        canvas.drawLine(width / 6, 0, width / 6, height, mPaint);
        canvas.drawLine(width / 3, 0, width / 3, height, mPaint);
        canvas.drawLine(width / 2, 0, width / 2, height, mPaint);
        canvas.drawLine(width * 2 / 3, 0, width * 2 / 3, height, mPaint);
        canvas.drawLine(width * 5 / 6, 0, width * 5 / 6, height, mPaint);
        canvas.restore();

        drawText(canvas);
    }

    char[] content;

    private void drawText(Canvas canvas) {
        canvas.save();
        if (TextUtils.isEmpty(getText().toString())) return;
        content = getText().toString().toCharArray();
        for (int i = 0; i < content.length; i++) {
            canvas.drawCircle((i * contentWidth + contentWidth / 2), height / 2,10, mPaint);
        }
        canvas.restore();

    }
}
