package com.honpe.lxx.app.ui.main.oa.ui.position8.adapter.prove;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.ChildEntity;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.widget.msgtips.QBadgeView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FileName: ChildFirstProvider
 * Author: asus
 * Date: 2020/10/12 14:10
 * Description:
 */
public class ChildFirstProvider extends BaseNodeProvider {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_approve;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        ChildEntity entity = (ChildEntity) baseNode;
        helper.setImageResource(R.id.iv_title, entity.getIcon());
        helper.setText(R.id.tv_content, entity.getType());
        CircleImageView iv = helper.getView(R.id.iv_title);

        new QBadgeView(context).bindTarget(iv).setBadgeTextSize(8f, true).setBadgeNumber(entity.getTips());

        ImageView ivDirector = helper.getView(R.id.iv_director);
        ivDirector.setVisibility(View.VISIBLE);
        ivDirector.setColorFilter(context.getResources().getColor(R.color.grey_home));
        if (entity.isExpanded()) {
            ivDirector.setImageResource(R.mipmap.ic_bottom_grey);
        } else {
            ivDirector.setImageResource(R.mipmap.ic_right_grey);
        }
        TextView tvTime = helper.getView(R.id.tv_time);
        tvTime.setText(DateUtil.getNewChatTime(DateUtil.setDate(sf, entity.getTime()).getTime()));

    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        ChildEntity entity = (ChildEntity) data;
        if (entity.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expandAndCollapseOther(position);
        }
    }
}












