package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.provider;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean2;

import org.jetbrains.annotations.NotNull;

/**
 * FileName: RootNodeProvider
 * Author: asus
 * Date: 2020/10/15 17:37
 * Description:
 */
public class RootNodeProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_title_arraw;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        StatisticsBean2.DataBean item = (StatisticsBean2.DataBean) baseNode;
        holder.setText(R.id.tv_title, item.getMealTimes());
    }
}
