package com.honpe.lxx.app.widget.textView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * FileName: ItemTextView
 * Author: asus
 * Date: 2020/8/24 15:42
 * Description: 表格框
 */
public class ItemTextView extends AppCompatTextView {
    private final Paint paint;
    private int mwidth;
    private int mheight;

    public ItemTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawLine(0, mheight - 1, mwidth - 1, mheight - 1, paint);
        canvas.drawLine(mwidth - 1, mheight - 1, mwidth - 1, 0, paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mwidth = w;
        mheight = h;
    }
}

