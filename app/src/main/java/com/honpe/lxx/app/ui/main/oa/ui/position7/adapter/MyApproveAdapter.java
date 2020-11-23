package com.honpe.lxx.app.ui.main.oa.ui.position7.adapter;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position7.bean.ApproveBean;
import com.honpe.lxx.app.widget.msgtips.QBadgeView;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/21 14:26
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class MyApproveAdapter extends BaseQuickAdapter<ApproveBean, BaseViewHolder> {

    CircleImageView ivOa;

    public MyApproveAdapter(@Nullable List<ApproveBean> data) {
        super(R.layout.item_approve, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApproveBean item) {
        CircleImageView iv = helper.getView(R.id.iv_title);
        iv.setImageResource(item.getIcons());
        helper.setText(R.id.tv_content, item.getTitle() + "审批");
        ivOa = helper.getView(R.id.iv_title);
        new QBadgeView(getContext()).bindTarget(iv).setBadgeTextSize(10f, true).setBadgeNumber(item.getTips());

        helper.setText(R.id.tv_time,item.getDate());
        if (helper.getLayoutPosition() == 0) {
            ivOa.setColorFilter(getContext().getResources().getColor(R.color.orange));
        } else if (helper.getLayoutPosition() == 1) {
            ivOa.setColorFilter(getContext().getResources().getColor(R.color.red_dark));
        } else if (helper.getLayoutPosition() == 2) {
            ivOa.setColorFilter(getContext().getResources().getColor(R.color.google_yellow));
        } else if (helper.getLayoutPosition() == 3) {
          //  ivOa.setColorFilter(getContext().getResources().getColor(R.color.yellow_gold));
        } else if (helper.getLayoutPosition() == 4) {
          //  ivOa.setColorFilter(getContext().getResources().getColor(R.color.green));
        }
    }
}


























