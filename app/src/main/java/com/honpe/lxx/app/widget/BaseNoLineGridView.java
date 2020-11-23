package com.honpe.lxx.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * created by lxx at 2020/4/6 9:33
 * 描述:
 */
public class BaseNoLineGridView extends GridView {
    public BaseNoLineGridView(Context context) {
        super(context);
    }

    public BaseNoLineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseNoLineGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
