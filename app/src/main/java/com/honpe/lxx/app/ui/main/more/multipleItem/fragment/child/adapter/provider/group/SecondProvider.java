package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.provider.group;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean.TreeBean;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;

import org.jetbrains.annotations.NotNull;


/**
 * FileName: SecondProvider
 * Author: asus
 * Date: 2020/9/16 10:35
 * Description:
 */
public class SecondProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_table2;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        TreeBean.TreeBean2 lv1 = (TreeBean.TreeBean2) baseNode;
        holder.setText(R.id.tv_group, lv1.getDeptName() + " (" + "事业部" + ")");
        ImageView ivArrow = holder.getView(R.id.iv_arrow);
        ivArrow.setColorFilter(getContext().getResources().getColor(R.color.black));
        ivArrow.setImageResource(lv1.isExpanded() ? R.mipmap.ic_bottom_grey : R.mipmap.ic_right_grey);
        if (lv1.getChildNode() != null) {
            ivArrow.setVisibility(View.VISIBLE);
        } else {
            ivArrow.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event<String> event = new Event<String>(FinalClass.COME_BACK_ALL_DEPART, lv1.getDeptID());
                EventBusUtil.sendEvent(event);
            }
        });
    }
}
