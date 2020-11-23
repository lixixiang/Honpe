package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.provide;

import android.graphics.Color;
import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;
import com.honpe.lxx.app.utils.LatteLogger;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.BuildFloorAdapter.build2;

/**
 * FileName: BuildThirdProvider
 * Author: asus
 * Date: 2020/9/19 16:28
 * Description:
 */
public class Build2Provider extends BaseNodeProvider {
    LevelEntity.FloorEntity.RoomEntity bean;

    public Build2Provider() {

    }

    @Override
    public int getItemViewType() {
        return build2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_build2;
    }


    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        bean = (LevelEntity.FloorEntity.RoomEntity) baseNode;
        helper.setText(R.id.tv_room_num, bean.getF_FullName());
        helper.setText(R.id.tv_room_status, bean.getRoomStatus());
        helper.setText(R.id.tv_total_bed_num, bean.getNumBeds());
        helper.setText(R.id.tv_freeRoom, String.valueOf(Integer.parseInt(bean.getNumBeds()) - Integer.parseInt(bean.getUserdNum())));
        helper.setTextColor(R.id.tv_room_status, Color.WHITE);
        if (bean.getRoomStatus().equals("空房")) {
            helper.setBackgroundResource(R.id.tv_room_status, R.drawable.shape_grey_r10);
        } else if (bean.getRoomStatus().equals("未满")) {
            helper.setBackgroundResource(R.id.tv_room_status, R.drawable.shape_blue_r10);
        } else if (bean.getRoomStatus().equals("满房")) {
            helper.setBackgroundResource(R.id.tv_room_status, R.drawable.shape_green_r10);
        }

        helper.setImageResource(R.id.iv_room_orientation, bean.isExpanded() ? R.mipmap.ic_bottom_grey : R.mipmap.ic_right_grey);
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        bean = (LevelEntity.FloorEntity.RoomEntity) data;

        LatteLogger.d("test", bean.isExpanded());
        if (bean.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expandAndCollapseOther(position);
        }
    }
}









