package com.honpe.lxx.app.ui.main.oa.ui.position19.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position19.bean.SubContractBean;
import com.honpe.lxx.app.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * FileName: SubContractAdapter
 * Author: asus
 * Date: 2020/8/14 23:04
 * Description:
 */
public class SubContractAdapter extends BaseQuickAdapter<SubContractBean, BaseViewHolder> {
    SimpleDateFormat sf = new SimpleDateFormat("MM月dd");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private int[] icons;

    public SubContractAdapter(@Nullable List<SubContractBean> data, int[] icon) {
        super(R.layout.item_sub_contract, data);
        this.icons = icon;
    }

    @Override
    protected void convert(BaseViewHolder helper, SubContractBean item) {

        Date mDate = DateUtil.setDate(sdf, item.getTime());
        String mD = sf.format(mDate);
        if (item.getTips() >= 99) {
            helper.setText(R.id.tv_tips, "99+");
            helper.setBackgroundResource(R.id.tv_tips, R.drawable.circle_ovl_red);
        } else {
            helper.setText(R.id.tv_tips, item.getTips() + "");
            helper.setBackgroundResource(R.id.tv_tips, R.drawable.circle_red);
        }
        item.setIcon(icons[helper.getLayoutPosition()]);
        helper.setImageResource(R.id.iv_weChat, item.getIcon());
        helper.setText(R.id.tv_depart, item.getDepart())
                .setText(R.id.tv_time, mD);
    }
}

