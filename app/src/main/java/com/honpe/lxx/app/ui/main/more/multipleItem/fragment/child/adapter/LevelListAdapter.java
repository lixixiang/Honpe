package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.ui.main.more.bean.muliItem.LevelItemBean;
import com.honpe.lxx.app.ui.main.more.bean.muliItem.LevelItemBean2;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.LeveLProvider.LevelFirstProvider;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.LeveLProvider.LevelSecondProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * FileName: LevelListAdapter
 * Author: asus
 * Date: 2020/8/8 10:04
 * Description: 通过日期分组
 */
public class LevelListAdapter extends BaseNodeAdapter {
    public static final int Level_1 = 0;
    public static final int Level_2 = 1;

    public LevelListAdapter() {
        super();
        addNodeProvider(new LevelFirstProvider());
        addNodeProvider(new LevelSecondProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof LevelItemBean) {
            return Level_1;
        } else if (node instanceof LevelItemBean2) {
            return Level_2;
        }
        return -1;
    }
}






















