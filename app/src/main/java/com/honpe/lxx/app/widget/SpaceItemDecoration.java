package com.honpe.lxx.app.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * FileName: SpaceItemDecoration
 * Author: asus
 * Date: 2020/8/10 17:28
 * Description: recyclerView 间距
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有4个，所以第一个都是4的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) % 54 == 0) {
            outRect.left = 0;
        }
    }
}
