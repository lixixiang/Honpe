package com.honpe.lxx.app.ui.main.oa;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 14:47
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class OAdapter extends BaseMultiItemQuickAdapter<OABean, BaseViewHolder> implements DraggableModule {

    public OAdapter(ArrayList<OABean> data) {
        super(data);
        addItemType(OABean.SINGLE_TEXT, R.layout.css_title_status);
        addItemType(OABean.TEXT_IMG, R.layout.css_img_txt);

    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, OABean oaBean) {
        switch (helper.getItemViewType()) {
            case OABean.SINGLE_TEXT:
                helper.setText(R.id.time, oaBean.getContent());
                break;
            case OABean.TEXT_IMG:
                helper.setText(R.id.tv, oaBean.getTitle());
                helper.setImageResource(R.id.iv, oaBean.getIcon());
                break;
        }
    }
}

