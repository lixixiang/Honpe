package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.LeveLProvider;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.main.more.bean.muliItem.LevelItemBean;

import org.jetbrains.annotations.NotNull;

import static com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.LevelListAdapter.Level_1;


/**
 * FileName: LevelFirstProvider
 * Author: asus
 * Date: 2020/9/16 10:55
 * Description:
 */
public class LevelFirstProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return Level_1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_left_text_right_text_dirctor;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        LevelItemBean lv0 = (LevelItemBean) baseNode;
        helper.setText(R.id.tv_item_title, lv0.getTitle());
        if (!"0".equals(lv0.getDayNum())) {
            helper.setTextColor(R.id.tv_day, getContext().getResources().getColor(R.color.black));
        } else {
            helper.setTextColor(R.id.tv_day, getContext().getResources().getColor(R.color.grey_home));
        }
        helper.setText(R.id.tv_day, lv0.getDayNum() + "天");
//        ChangeNUm(helper,lv0);

        ImageView imageView = helper.getView(R.id.iv_director);
        imageView.setColorFilter(getContext().getResources().getColor(R.color.grey_dark));
        imageView.setImageResource(lv0.isExpanded() ? R.mipmap.ic_top_grey : R.mipmap.ic_bottom_grey);

    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        LevelItemBean lv0 = (LevelItemBean) data;
        if (lv0.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expand(position);
        }
    }

}