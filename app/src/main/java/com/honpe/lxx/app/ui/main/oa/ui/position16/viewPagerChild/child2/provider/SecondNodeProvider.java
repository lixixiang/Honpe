package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.provider;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean2;
import com.honpe.lxx.app.utils.Utils;

import org.jetbrains.annotations.NotNull;

/**
 * FileName: SecondNodeProvider
 * Author: asus
 * Date: 2020/10/15 17:38
 * Description:
 */
public class SecondNodeProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_title_center;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode data) {
        StatisticsBean2.DataBean.DishesDetailsBean entity = (StatisticsBean2.DataBean.DishesDetailsBean) data;
        TextView tvMenu = holder.getView(R.id.tv_name);
        tvMenu.setText(entity.getDishes());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(30,30,30,30);
        tvMenu.setLayoutParams(params);
        tvMenu.setGravity(Gravity.CENTER);
        tvMenu.setBackgroundResource(R.drawable.select_grey_radius5);
        Utils.TextSize(tvMenu,40);
        holder.setGone(R.id.iv_director, true);
    }
}