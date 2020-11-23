package com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.utils.Utils;

import java.util.List;

/**
 * FileName: MenuOrderDetailAdapter
 * Author: asus
 * Date: 2020/8/10 18:04
 * Description:
 */
public class MenuOrderDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MenuOrderDetailAdapter(@Nullable List<String> data) {
        super(R.layout.css_text_1, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        LinearLayout llParam = helper.getView(R.id.ll_css1);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dp2px(getContext(), 40));
        llParam.setLayoutParams(params);

        TextView tv = helper.getView(R.id.tv_record_text);
        tv.setText((helper.getLayoutPosition() + 1) + "." + item);
        tv.setTextSize(12);
    }
}