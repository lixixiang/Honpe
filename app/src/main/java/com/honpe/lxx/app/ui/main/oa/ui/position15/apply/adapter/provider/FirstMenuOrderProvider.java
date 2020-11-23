package com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.provider;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.GridViewAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean.MenuBean;
import com.honpe.lxx.app.utils.LatteLogger;

import org.jetbrains.annotations.NotNull;

/**
 * FileName: FirstMenuOrderProvider
 * Author: asus
 * Date: 2020/9/17 13:38
 * Description:
 */
public class FirstMenuOrderProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return GridViewAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_title_small_depart;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        MenuBean lv1 = (MenuBean) baseNode;
        LatteLogger.d("testData",lv1.getMsg());
        helper.setText(R.id.tv_title, lv1.getMsg());
        helper.setTextColor(R.id.tv_title, getContext().getResources().getColor(R.color.green));
    }
}

