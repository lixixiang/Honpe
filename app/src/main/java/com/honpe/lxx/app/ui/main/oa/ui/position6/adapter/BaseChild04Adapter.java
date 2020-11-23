package com.honpe.lxx.app.ui.main.oa.ui.position6.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position6.bean.CardQueryBean;
import com.honpe.lxx.app.widget.BaseListView;


import java.util.List;

/**
 * created by lxx at 2019/12/3 14:44
 * 描述:
 */
public class BaseChild04Adapter extends BaseQuickAdapter<CardQueryBean, BaseViewHolder> {
    /**
     * 标记展开的item
     */
    public BaseChild04Adapter(@Nullable List<CardQueryBean> data) {
        super(R.layout.item_send_fragment04, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CardQueryBean item) {
        String[] strHead = item.getMsg().split(" ");
        helper.setText(R.id.tv_name, strHead[0]);

        Log.d("test", strHead[1]);

        if (item.getData().get(helper.getLayoutPosition()).getPicUrl().equals("dd")) {
            helper.setImageResource(R.id.headerIcon, R.mipmap.ic_normal_bg);
        } else {
            Glide.with(getContext()).load(strHead[1]).placeholder(R.mipmap.ic_normal_bg)
                    .error(R.mipmap.ic_normal_bg).into((ImageView) helper.getView(R.id.headerIcon));
        }

        final BaseListViewAdapter adapter = new BaseListViewAdapter(getContext(),item.getData());
        ((BaseListView) helper.getView(R.id.list_time)).setAdapter(adapter);
        final ImageView iv = ((ImageView)helper.getView(R.id.iv));
        if (item.getData().size() > 1) {
            iv.setVisibility(View.VISIBLE);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapter.getCount() == 1) {
                        adapter.addItemNum(item.getData().size());
                        iv.setImageResource(R.drawable.ic_top);
                        adapter.notifyDataSetChanged();
                    }else{
                        adapter.addItemNum(1);
                        iv.setImageResource(R.drawable.ic_bottom);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }else{
            iv.setVisibility(View.GONE);
        }
    }
}
