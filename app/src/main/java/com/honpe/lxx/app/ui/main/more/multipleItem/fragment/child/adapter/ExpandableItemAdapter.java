package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.provider.group.FirstProvider;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.provider.group.SecondProvider;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean.TreeBean;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.provider.group.ThirdProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * FileName: ExpandableItemAdapter
 * Author: asus
 * Date: 2020/8/24 15:31
 * Description: 精确查找
 */
public class ExpandableItemAdapter extends BaseNodeAdapter {

    public ExpandableItemAdapter() {
        super();
        addNodeProvider(new FirstProvider());
        addNodeProvider(new SecondProvider());
        addNodeProvider(new ThirdProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int i) {
        BaseNode node = data.get(i);
        if (node instanceof TreeBean) {
            return 1;
        } else if (node instanceof TreeBean.TreeBean2) {
            return 2;
        } else if (node instanceof TreeBean.TreeBean2.TreeBean3) {
            return 3;
        }
        return -1;
    }

}
