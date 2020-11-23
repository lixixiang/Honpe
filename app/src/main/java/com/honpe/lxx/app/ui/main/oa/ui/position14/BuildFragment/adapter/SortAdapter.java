package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position14.add.bean.AllNameBean;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * FileName: SortAdapter
 * Author: asus
 * Date: 2020/9/21 17:23
 * Description:
 */
public class SortAdapter extends BaseQuickAdapter<AllNameBean.InfoEntity, BaseViewHolder> {
    private List<AllNameBean.InfoEntity> mData;
    private LinearLayoutManager mLayoutManager;
    private boolean stateChanged;

    public SortAdapter(@Nullable List<AllNameBean.InfoEntity> data) {
        super(R.layout.css_title_status, data);
        mData = data;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, AllNameBean.InfoEntity item) {
        String[][] object2 = {new String[]{";", ""}};
        helper.setText(R.id.time, item.getF_realname());
        helper.setText(R.id.tv_content,StringUtil.replace(item.getDormitoryinfo(), object2));
        LatteLogger.d("testItem", item.getEmp_usersex());
        ImageView ivHead = helper.getView(R.id.iv_sex_head);
        ImageView ivSex = helper.getView(R.id.iv_sex);
        ivHead.setVisibility(View.VISIBLE);
        ivSex.setVisibility(View.VISIBLE);
        if (item.getEmp_usersex().equals("男")) {
            ivHead.setImageResource(R.drawable.health_guide_men_selected);
            ivSex.setImageResource(R.mipmap.ic_male);
        } else {
            ivHead.setImageResource(R.drawable.health_guide_woman_selected);
            ivSex.setImageResource(R.mipmap.ic_female);
        }

        if (!"".equals(item.getDormitoryinfo())) {
            helper.setTextColor(R.id.time, getContext().getResources().getColor(R.color.grey_home));
            helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.grey_home));
        } else {
            helper.setTextColor(R.id.time, getContext().getResources().getColor(R.color.black));
            helper.setTextColor(R.id.tv_content, getContext().getResources().getColor(R.color.grey_home));
        }
    }

    public void setLayoutManager(LinearLayoutManager manager) {
        this.mLayoutManager = manager;
    }

    /**
     * 滚动RecyclerView到索引位置
     *
     * @param index
     */
    public void scrollToSection(String index) {
        if (mData == null || mData.isEmpty()) return;
        if (TextUtils.isEmpty(index)) return;
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(index.substring(0, 1), mData.get(i).getSection().substring(0, 1))) {
                if (mLayoutManager != null) {
                    mLayoutManager.scrollToPositionWithOffset(i, 0);
                    if (TextUtils.equals(index.substring(0, 1), "定")) {
                        //防止滚动时进行刷新
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (stateChanged) notifyItemChanged(0);
                            }
                        }, 1000);
                    }
                    return;
                }
            }
        }
    }

    public void updateData(List<AllNameBean.InfoEntity> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void refreshLocationItem() {
        //如果定位城市的item可见则进行刷新
        if (stateChanged && mLayoutManager.findFirstVisibleItemPosition() == 0) {
            stateChanged = false;
            notifyItemChanged(0);
        }
    }
}
