package com.honpe.lxx.app.ui.main.oa.ui.position17;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position17.entity.TotalOfMeatEntity;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.StringUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * FileName: TotalOfMeatAdapter
 * Author: asus
 * Date: 2020/10/21 15:36
 * Description: 用餐统计适配器
 */
@SuppressLint("SetTextI18n")
public class TotalOfMeatAdapter extends BaseQuickAdapter<TotalOfMeatEntity.DataEntity.RowsEntity, BaseViewHolder> {

    TextView tvData, tvItem1, tvItem2, tvItem3, tvItem4, tvItem5, tvItem6, tvItem7, tvItem8;
    SimpleDateFormat myDay = new SimpleDateFormat("dd");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<TotalOfMeatEntity.DataEntity.RowsEntity> data;

    public TotalOfMeatAdapter(@Nullable List<TotalOfMeatEntity.DataEntity.RowsEntity> data) {
        super(R.layout.item_meat_total, data);
        this.data = data;
    }


    @Override
    protected void convert(@NotNull BaseViewHolder holder, TotalOfMeatEntity.DataEntity.RowsEntity entity) {
        holder.setGone(R.id.ll_item_meat, false);
        tvData = holder.getView(R.id.tv_meal_total_date);
        tvItem1 = holder.getView(R.id.item_1);
        tvItem2 = holder.getView(R.id.item_2);
        tvItem3 = holder.getView(R.id.item_3);
        tvItem4 = holder.getView(R.id.item_4);
        tvItem5 = holder.getView(R.id.item_5);
        tvItem6 = holder.getView(R.id.item_6);
        tvItem7 = holder.getView(R.id.item_7);
        tvItem8 = holder.getView(R.id.item_8);

        tvItem1.setText(entity.getHasBreakFast() + "");
        tvItem2.setText(entity.getHasMealBreakFast() + "");
        tvItem3.setText(entity.getHasLunch() + "");
        tvItem4.setText(entity.getHasMealHasLunch() + "");
        tvItem5.setText(entity.getHasDinner() + "");
        tvItem6.setText(entity.getHasMealHasDinner() + "");
        tvItem7.setText(entity.getHasMidnight() + "");
        tvItem8.setText(entity.getHasMealHasmidNight() + "");

        String[][] object = {new String[]{"星期", ""}};

        String today = sdf.format(new Date());
        initChangeBG(R.color.white);
        initTextColor(Color.BLACK);
        if (today.equals(entity.getMealDate())) {
            initChangeBG(R.color.green);
            initTextColor(Color.WHITE);
        }

        if (holder.getLayoutPosition() < data.size() - 1) {
            tvData.setText(myDay.format(DateUtil.setDate(sdf, entity.getMealDate())) + "/" +
                    StringUtil.replace(DateUtil.dateToWeek(entity.getMealDate()), object));
        }
        if (holder.getLayoutPosition() == data.size() - 1) {
            tvData.setText(entity.getMealDate());
            initChangeBG(R.color.orange);
            initTextColor(Color.WHITE);
        }
    }

    private void initChangeBG(int color) {
        tvData.setBackgroundResource(color);
        tvItem1.setBackgroundResource(color);
        tvItem2.setBackgroundResource(color);
        tvItem3.setBackgroundResource(color);
        tvItem4.setBackgroundResource(color);
        tvItem5.setBackgroundResource(color);
        tvItem6.setBackgroundResource(color);
        tvItem7.setBackgroundResource(color);
        tvItem8.setBackgroundResource(color);
    }

    private void initTextColor(int color) {
        tvData.setTextColor(color);
        tvItem1.setTextColor(color);
        tvItem2.setTextColor(color);
        tvItem3.setTextColor(color);
        tvItem4.setTextColor(color);
        tvItem5.setTextColor(color);
        tvItem6.setTextColor(color);
        tvItem7.setTextColor(color);
        tvItem8.setTextColor(color);
    }
}
























