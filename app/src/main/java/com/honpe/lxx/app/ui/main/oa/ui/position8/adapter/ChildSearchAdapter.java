package com.honpe.lxx.app.ui.main.oa.ui.position8.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.ui.main.oa.ui.position8.adapter.prove.ChildFirstProvider;
import com.honpe.lxx.app.ui.main.oa.ui.position8.adapter.prove.ChildSecondProvider;
import com.honpe.lxx.app.ui.main.oa.ui.position8.adapter.prove.ChildThirdProvider;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.ChildEntity;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.NameInfoEntity;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.SearchEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * FileName: ChildSearchAdapter
 * Author: asus
 * Date: 2020/10/12 12:31
 * Description:
 */
public class ChildSearchAdapter extends BaseNodeAdapter {
    public ChildSearchAdapter() {
        super();
        addNodeProvider(new ChildFirstProvider());
        addNodeProvider(new ChildSecondProvider());
        addNodeProvider(new ChildThirdProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof ChildEntity) {
           return 1;
        }
        else if (node instanceof NameInfoEntity) {
            return 2;
        } else if (node instanceof SearchEntity.DataEntity.RowsEntity) {
            return 3;
        }
        return 0;
    }
}



















