package com.honpe.lxx.app.widget.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;

import java.util.List;

/**
 * FileName: BuildFloorDecoration
 * Author: asus
 * Date: 2020/9/23 16:46
 * Description: 哪一楼所有的房间
 */
public class BuildFloorDecoration extends RecyclerView.ItemDecoration {
    List<LevelEntity.FloorEntity.RoomEntity> mRooms;
    private Paint mBgPaint;
    private TextPaint mTextPaint;
    private Rect mBounds;

    private int mSectionHeight;
    private int mBgColor;
    private int mTextColor;
    private int mTextSize;

    public BuildFloorDecoration(Context context, List<LevelEntity.FloorEntity.RoomEntity> roomEntities) {
       this.mRooms = roomEntities;
        LatteLogger.d("testMRoom", GsonBuildUtil.GsonBuilder(roomEntities));
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.cpSectionBackground, typedValue, true);
        mBgColor = context.getResources().getColor(R.color.grey_background);
        context.getTheme().resolveAttribute(R.attr.cpSectionHeight, typedValue, true);
        mSectionHeight = context.getResources().getDimensionPixelSize(R.dimen._30dp);

        context.getTheme().resolveAttribute(R.attr.cpSectionTextSize, typedValue, true);
        mTextSize = context.getResources().getDimensionPixelSize(R.dimen._14sp);

        context.getTheme().resolveAttribute(R.attr.cpSectionTextColor, typedValue, true);
        mTextColor = context.getResources().getColor(R.color.grey_dark);

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(mBgColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mBounds = new Rect();
    }

    public void setData(List<LevelEntity.FloorEntity.RoomEntity> data) {
        this.mRooms = data;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int position = params.getViewLayoutPosition();
            if (mRooms != null && !mRooms.isEmpty() && position <= mRooms.size() - 1 && position > -1) {
                if (position == 0) {
                    drawSection(c, left, right, child, params, position);
                } else {
                    if (null != mRooms.get(position).getF_FullName()
                            && !mRooms.get(position).getF_FullName().equals(mRooms.get(position - 1).getF_FullName())) {
                        drawSection(c, left, right, child, params, position);
                    }
                }
            }
        }
    }

    private void drawSection(Canvas c, int left, int right, View child,
                             RecyclerView.LayoutParams params, int position) {
        c.drawRect(left,
                child.getTop() - params.topMargin - mSectionHeight,
                right,
                child.getTop() - params.topMargin, mBgPaint);
        mTextPaint.getTextBounds(mRooms.get(position).getF_FullName(),
                0,
                mRooms.get(position).getF_FullName().length(),
                mBounds);
        c.drawText(mRooms.get(position).getF_FullName(),
                child.getPaddingLeft(),
                child.getTop() - params.topMargin - (mSectionHeight / 2 - mBounds.height() / 2),
                mTextPaint);
    }
}

























