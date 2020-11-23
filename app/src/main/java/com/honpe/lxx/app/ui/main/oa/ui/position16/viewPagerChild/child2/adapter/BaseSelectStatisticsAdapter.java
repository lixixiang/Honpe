package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.MultiItemBean;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean2;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.provider.RootNodeProvider;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.provider.SecondNodeProvider;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: BaseSelectStatisticsAdapter
 * Author: asus
 * Date: 2020/8/12 9:16
 * Description: 选材统计适配器
 */
public class BaseSelectStatisticsAdapter extends BaseNodeAdapter {
    private Map<Integer, Boolean> map = new HashMap<>();// 存放已被选中的CheckBox

    public BaseSelectStatisticsAdapter() {
        super();
        addFullSpanNodeProvider(new RootNodeProvider());
        addNodeProvider(new SecondNodeProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int position) {
        BaseNode node = list.get(position);
        if (node instanceof StatisticsBean2.DataBean) {
            return 0;
        } else if (node instanceof StatisticsBean2.DataBean.DishesDetailsBean) {
            return 1;
        }
        return -1;
    }
}












