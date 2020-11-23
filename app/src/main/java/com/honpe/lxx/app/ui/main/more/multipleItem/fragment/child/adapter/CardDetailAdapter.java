package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.more.multipleItem.position5.bean.CheckInTotalBean;

import java.util.List;

/**
 * FileName: CardDetailFragment
 * Author: asus
 * Date: 2020/8/7 14:04
 * Description: 考勤详情
 */
public class CardDetailAdapter extends BaseQuickAdapter<CheckInTotalBean.Data, BaseViewHolder> {

    String key;

    public CardDetailAdapter(String key, @Nullable List<CheckInTotalBean.Data> data) {
        super(R.layout.css_order_name_depart_type, data);
        this.key = key;
    }


    @Override
    protected void convert(BaseViewHolder helper, CheckInTotalBean.Data item) {
        helper.setText(R.id.tv_id,(helper.getLayoutPosition()+1)+".");
        helper.setText(R.id.id_card_no,item.getNo());
        helper.setText(R.id.tv_user_name, item.getUserName());
        helper.setText(R.id.tv_user_depart, item.getTeam());
        helper.setText(R.id.tv_str_type, key);
    }
}
