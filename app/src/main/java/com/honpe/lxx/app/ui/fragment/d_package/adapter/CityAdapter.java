package com.honpe.lxx.app.ui.fragment.d_package.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.fragment.d_package.address.add.AddressBean;

import java.util.List;


/**
 * created by lxx at 2019/11/21 16:31
 * 描述:
 */
public class CityAdapter extends BaseQuickAdapter<AddressBean.CityBean, BaseViewHolder> {
    public CityAdapter(int layoutResId, @Nullable List<AddressBean.CityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean.CityBean item) {
        helper.setText(R.id.textview, item.getLabel());
        helper.setTextColor(R.id.textview, item.isStatus() ? Color.parseColor("#65C15C") : Color.parseColor("#444444"));
    }
}
