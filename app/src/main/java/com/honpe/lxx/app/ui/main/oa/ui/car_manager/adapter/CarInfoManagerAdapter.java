package com.honpe.lxx.app.ui.main.oa.ui.car_manager.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.car_manager.entity.CarInfoEntity;
import com.honpe.lxx.app.utils.DateUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * FileName: CarIntoManagerAdapter
 * Author: asus
 * Date: 2020/10/21 18:04
 * Description:第一个
 */
public class CarInfoManagerAdapter extends BaseQuickAdapter<CarInfoEntity.DataEntity, BaseViewHolder> {
    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CarInfoManagerAdapter(@Nullable List<CarInfoEntity.DataEntity> data) {
        super(R.layout.item_car_info_manager, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, CarInfoEntity.DataEntity entity) {
        holder.setGone(R.id.ll_tab_car_info, false);
        holder.setText(R.id.item_1, (holder.getAdapterPosition() +1)+ "");
        holder.setText(R.id.item_2, entity.getUserName());
        holder.setText(R.id.item_3, entity.getCardNo());
        holder.setText(R.id.item_4,sdf.format(DateUtil.setDate(sf,entity.getRegisterDate())));
        holder.setText(R.id.item_5,sdf.format(DateUtil.setDate(sf, entity.getStartDate())));
        holder.setText(R.id.item_6, sdf.format(DateUtil.setDate(sf, entity.getValidDate())));
        holder.setTextColorRes(R.id.item_6,R.color.green);
    }
}


























