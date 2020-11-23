package com.honpe.lxx.app.ui.fragment.d_package.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.fragment.d_package.bean.IconTextDirectorBean;

import java.util.List;


/**
 * created by lxx at 2019/11/21 12:22
 * 描述:
 */
public class MeAdapter extends BaseQuickAdapter<IconTextDirectorBean, BaseViewHolder> {
    public MeAdapter(@Nullable List<IconTextDirectorBean> data) {
        super(R.layout.css_font_text_director, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IconTextDirectorBean item) {
        helper.setImageResource(R.id.icon, item.getIcon());
        helper.setText(R.id.tv_txt, item.getText());
        helper.setImageResource(R.id.iv_director, R.mipmap.ic_right_grey);
        ((ImageView) helper.getView(R.id.iv_director)).setColorFilter(getContext().getResources().getColor(R.color.grey_aeaeae));

    }
}
