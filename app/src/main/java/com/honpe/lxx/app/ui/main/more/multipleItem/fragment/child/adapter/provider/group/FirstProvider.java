package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.provider.group;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean.TreeBean;

import org.jetbrains.annotations.NotNull;

/**
 * FileName: FirstProvider
 * Author: asus
 * Date: 2020/9/16 10:24
 * Description:
 */
public class FirstProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_head_txt;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        final TreeBean lv0 = (TreeBean) baseNode;
        holder.setText(R.id.job_name_text, lv0.getDeptName() + " (" + "部门" + ")");
        ImageView ivArrowHead = holder.getView(R.id.iv_arrow_head);
        ivArrowHead.setColorFilter(getContext().getResources().getColor(R.color.black));
        ivArrowHead.setImageResource(lv0.isExpanded() ? R.mipmap.ic_bottom_grey : R.mipmap.ic_right_grey);
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        TreeBean entity = (TreeBean) data;
        if (entity.isExpanded()) {
            getAdapter().collapse(position);
        }else {
            getAdapter().expandAndCollapseOther(position);
        }
    }
}
