package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.provide.Build3Provider;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.provide.Build1Provider;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.provide.Build2Provider;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.BedBean;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * FileName: TypeAdapter
 * Author: asus
 * Date: 2020/9/15 12:26
 * Description:
 */
public class BuildFloorAdapter extends BaseNodeAdapter {
    public static final int build1 = 1;
    public static final int build2 = 2;
    public static final int build3 = 3;
    public static final int ITEM_ADD = 902;

    public BuildFloorAdapter() {
        super();
        addNodeProvider(new Build1Provider());
        addNodeProvider(new Build2Provider());
        addNodeProvider(new Build3Provider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int position) {
        BaseNode node = list.get(position);
       if (node instanceof LevelEntity.FloorEntity) {
            return build1;
        } else if (node instanceof LevelEntity.FloorEntity.RoomEntity) {
            return build2;
       } else if (node instanceof LevelEntity.FloorEntity.RoomEntity.BedEntity) {
           return build3;
       }
        return -1;
    }
}
