package com.honpe.lxx.app.ui.main.oa.ui.position2.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position2.bean.LeaveNewBean;
import com.honpe.lxx.app.utils.LatteLogger;

import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 16:53
 * @Author: 李熙祥
 * @Description: java类作用描述 请假适配器
 */
public class LeaveAdapter extends BaseQuickAdapter<LeaveNewBean, BaseViewHolder> {

    public LeaveAdapter(@Nullable List<LeaveNewBean> data) {
        super(R.layout.item_leave, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, LeaveNewBean item) {
        helper.setText(R.id.tv_date, item.getDate());


        helper.setText(R.id.tv_morning, item.getMorning());
        helper.setText(R.id.tv_after, item.getAfter());
        helper.setText(R.id.tv_dinner, item.getDinner());


        if (item.getMorning().equals("\ue611")) {
            helper.setTextColor(R.id.tv_morning,getContext().getResources().getColor(R.color.grey_home));
            helper.setVisible(R.id.lab_morning, false);
        }else if (item.getMorning().equals("")){
            helper.setVisible(R.id.lab_morning, false);
        }else{
            helper.setTextColor(R.id.tv_morning,getContext().getResources().getColor(R.color.black));
            helper.setVisible(R.id.lab_morning, true);
        }

        if (item.getAfter().equals("\ue611")) {
            helper.setTextColor(R.id.tv_after,getContext().getResources().getColor(R.color.grey_home));
            helper.setVisible(R.id.lab_after, false);
        }else if (item.getAfter().equals("")){
            helper.setVisible(R.id.lab_after, false);
        }else {
            helper.setTextColor(R.id.tv_after,getContext().getResources().getColor(R.color.black));
            helper.setVisible(R.id.lab_after, true);
        }

        if (item.getDinner().equals("\ue611")) {
            helper.setTextColor(R.id.tv_dinner,getContext().getResources().getColor(R.color.grey_home));
            helper.setVisible(R.id.lab_dinner, false);
        }else if (item.getDinner().equals("")){
            helper.setVisible(R.id.lab_dinner, false);
        }else{
            helper.setTextColor(R.id.tv_dinner,getContext().getResources().getColor(R.color.black));
            helper.setVisible(R.id.lab_dinner, true);
        }

        if (!TextUtils.isEmpty(item.getMorning()) && !item.getMorning().equals("\ue611")
                || !TextUtils.isEmpty(item.getDinner()) && !item.getDinner().equals("\ue611")
                || !TextUtils.isEmpty(item.getAfter()) && !item.getAfter().equals("\ue611")) {
            if ( "0".equals(item.getStatus())) {
                helper.setText(R.id.tv_status, "待审批");
                helper.setBackgroundResource(R.id.tv_status,R.drawable.shape_grey_radius10_padding5);
            } else {
                helper.setText(R.id.tv_status, "已审批");
                helper.setBackgroundResource(R.id.tv_status,R.drawable.shape_green_radius10_padding5);
            }

        } else {
            helper.setText(R.id.tv_status, "");
            helper.setBackgroundResource(R.id.tv_status,R.drawable.shape_white);
        }

    }
}
