package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.provider.group;

import android.view.View;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean.TreeBean;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;

import org.jetbrains.annotations.NotNull;

import static com.honpe.lxx.app.api.FinalClass.COME_BACK_ALL_DEPART;

/**
 * FileName: ThirdProvider
 * Author: asus
 * Date: 2020/9/16 10:36
 * Description:
 */
public class ThirdProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_table3;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        TreeBean.TreeBean2.TreeBean3 lv2 = (TreeBean.TreeBean2.TreeBean3) baseNode;
        if (lv2 != null) {
            holder.setText(R.id.tv_team, lv2.getDeptName()+ " ("+"组别"+")");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event<String> event = new Event<String>(FinalClass.COME_BACK_ALL_DEPART,lv2.getDeptID());
                EventBusUtil.sendEvent(event);
            }
        });
    }

}



