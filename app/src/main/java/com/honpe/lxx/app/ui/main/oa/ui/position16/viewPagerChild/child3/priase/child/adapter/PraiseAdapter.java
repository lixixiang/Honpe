package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.priase.child.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean3;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * FileName: PriaseAdapter
 * Author: asus
 * Date: 2020/8/12 16:22
 * Description: 点赞评论适配器
 */
public class PraiseAdapter extends BaseQuickAdapter<StatisticsBean3.DataBean.DetailsBean, BaseViewHolder> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat newSdf = new SimpleDateFormat("MM年dd日 HH:mm");

    public PraiseAdapter(@Nullable List<StatisticsBean3.DataBean.DetailsBean> data) {
        super(R.layout.item_theme_priase, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatisticsBean3.DataBean.DetailsBean item) {
        helper.setText(R.id.tv_userName, item.getUserName());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setText(R.id.level, item.getScore());
        helper.setText(R.id.tv_date,newSdf.format(DateUtil.setDate(sdf,item.getCommentTime())));
        String str = StringUtil.getLastStr(item.getRcode());
        if (str.equals("1")) {
            helper.setText(R.id.tv_switch_type, "早餐");
        } else if (str.equals("2")) {
            helper.setText(R.id.tv_switch_type, "中餐");
        } else if (str.equals("3")) {
            helper.setText(R.id.tv_switch_type, "晚餐");
        }else if (str.equals("4")) {
            helper.setText(R.id.tv_switch_type, "夜宵");
        }
    }
}
















