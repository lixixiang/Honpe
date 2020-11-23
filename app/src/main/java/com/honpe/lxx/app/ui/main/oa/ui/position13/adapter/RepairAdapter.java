package com.honpe.lxx.app.ui.main.oa.ui.position13.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position13.bean.RepairBean;
import com.honpe.lxx.app.utils.StringUtil;

import java.util.List;

/**
 * FileName: RepairAdapter
 * Author: asus
 * Date: 2020/8/31 10:52
 * Description: 维修适配器
 */
public class RepairAdapter extends BaseQuickAdapter<RepairBean.DataEntity, BaseViewHolder> {

    private String permission;

    public RepairAdapter(@Nullable List<RepairBean.DataEntity> data, String permission) {
        super(R.layout.item_repair, data);
        this.permission = permission;
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairBean.DataEntity item) {
        TextView tvTypeName = helper.getView(R.id.tv_type_name);
        TextView tvRepairName = helper.getView(R.id.tv_repair_name);
        TextView tvStatus = helper.getView(R.id.tv_status);
        TextView tvTime = helper.getView(R.id.tv_time);
        TextView tvNo = helper.getView(R.id.tv_no);
        TextView tvDepart = helper.getView(R.id.tv_depart);
        TextView tvApplyName = helper.getView(R.id.tv_apply_name);


        if (item.get维修类型() == 0) {
            tvTypeName.setText("外部维修"+"：");
        } else {
            tvTypeName.setText("内部维修"+"：");
        }
        tvRepairName.setText(item.getF_TypeContext());
        if (item.getRepairBillDetailsModel() != null) {
            tvTime.setText(StringUtil.changeFontColor(item.get申请日期() + " --> " + item.getRepairBillDetailsModel().get(0).get要求时限(), R.color.grey, 17, 20));
        } else {
            tvTime.setText(StringUtil.changeFontColor(item.get申请日期() + " --> " + "即时", R.color.grey, 17, 20));
        }
        tvApplyName.setText(item.get申请人());
        tvNo.setText(item.get维修单号());
        tvDepart.setText(item.get申请部门() + "：");
        tvStatus.setBackgroundResource(R.drawable.shape_rectangle_white_green_5_lr15_tb5);
        tvStatus.setEnabled(true);
        if ("".equals(item.get确认人())&&permission.contains("部门")) {
            tvStatus.setText("部门主管确认");
        }else if ("".equals(item.getRepairBillDetailsModel().get(0).get维修人())) {
            tvStatus.setText("维修人签名");
        } else if ("".equals(item.get核准人())&&permission.contains("核准")) {
            tvStatus.setText("工务主管审批");
        } else if ("".equals(item.get验收人员())) {
            tvStatus.setText("验收人员签名");
        } else if ("".equals(item.get行政审批())&&permission.contains("行政")) {
            tvStatus.setText("行政主管审批");
        } else if (!"".equals(item.get行政审批())){
            tvStatus.setText("订单完成");
            tvStatus.setBackgroundResource(R.drawable.shape_rectangle_grey_5_lr15_tb5);
            tvStatus.setEnabled(false);
        } else {
            tvStatus.setText("订单进行中");
            tvStatus.setBackgroundResource(R.drawable.shape_rectangle_white_blue_5_lr15_tb5);
            tvStatus.setEnabled(false);
        }
    }
}










