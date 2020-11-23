package com.honpe.lxx.app.ui.login.register.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.login.register.bean.RegisterTypeBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * FileName: FragmentTypeAdapter
 * Author: asus
 * Date: 2020/11/19 9:52
 * Description:
 */
public class FragmentTypeAdapter extends BaseQuickAdapter<RegisterTypeBean, BaseViewHolder> {
    public FragmentTypeAdapter(@Nullable List<RegisterTypeBean> data) {
        super(R.layout.item_register_type, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, RegisterTypeBean bean) {
        holder.setText(R.id.text, bean.getType());
        holder.setImageResource(R.id.icon, bean.getIcon());
    }
}
