package com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.provider.FirstMenuOrderProvider;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.provider.SecondMenuOrderProvider;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean.MenuBean;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * FileName: GridViewAdapter
 * Author: asus
 * Date: 2020/8/10 17:18
 * Description:
 */
public class GridViewAdapter extends BaseNodeAdapter {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_PERSON = 1;

    public GridViewAdapter() {
        super();
        addNodeProvider(new FirstMenuOrderProvider());
        addNodeProvider(new SecondMenuOrderProvider());
    }


    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof MenuBean) {
            return TYPE_LEVEL_0;
        } else if (node instanceof MenuBean.DataBean) {
            return TYPE_PERSON;
        }
        return -1;
    }
}

