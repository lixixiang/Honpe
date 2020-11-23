package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.provide;

import android.annotation.SuppressLint;
import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.BuildFloorAdapter.build1;

/**
 * FileName: BuildProvider1
 * Author: asus
 * Date: 2020/9/16 11:52
 * Description:
 */
@SuppressLint("ClickableViewAccessibility")
public class Build1Provider extends BaseNodeProvider {
    LevelEntity.FloorEntity bean;
    List<LevelEntity.FloorEntity.RoomEntity> roomEntities1 = new ArrayList<>();
    int empty,free,full;

    public Build1Provider() {

    }

    @Override
    public int getItemViewType() {
        return build1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_build;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        bean = (LevelEntity.FloorEntity) baseNode;
        if (bean.getF_FullName().contains("二层")) {
            helper.setText(R.id.txt_floor, "二楼");
        } else if (bean.getF_FullName().contains("三层")) {
            helper.setText(R.id.txt_floor, "三楼");
        } else if (bean.getF_FullName().contains("四层")) {
            helper.setText(R.id.txt_floor, "四楼");
        } else if (bean.getF_FullName().contains("五层")) {
            helper.setText(R.id.txt_floor, "五楼");
        }
        roomEntities1.clear();
        empty = 0;
        free = 0;
        full = 0;
      List<BaseNode> roomEntities = bean.getChildNode();
        for (int i = 0; i < roomEntities.size(); i++) {
            LevelEntity.FloorEntity.RoomEntity roomEntity = (LevelEntity.FloorEntity.RoomEntity) roomEntities.get(i);
            if ("0".equals(roomEntity.getUserdNum())) {
                empty ++;
                roomEntity.setRoomStatus("空房");
            } else if (!"0".equals(roomEntity.getUserdNum())&&!roomEntity.getUserdNum().equals(roomEntity.getNumBeds())){
                free++;
                roomEntity.setRoomStatus("未满");
            } else if (roomEntity.getUserdNum().equals(roomEntity.getNumBeds())) {
                full++;
                roomEntity.setRoomStatus("满房");
            }

            roomEntities1.add(roomEntity);
        }
        helper.setText(R.id.tv_totalRoom, roomEntities1.size()+"间");
        helper.setText(R.id.tv_emptyRoom,"空房:"+empty +"间");
        helper.setText(R.id.tv_freeRoom, "未满:"+free + "间");
        helper.setText(R.id.tv_fullRoom, "满房:"+full + "间");
        helper.setImageResource(R.id.iv_orientation, bean.isExpanded() ? R.mipmap.ic_bottom_grey : R.mipmap.ic_right_grey);

    }


    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        bean = (LevelEntity.FloorEntity) data;
        if (bean.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expandAndCollapseOther(position);
        }
    }
}



















