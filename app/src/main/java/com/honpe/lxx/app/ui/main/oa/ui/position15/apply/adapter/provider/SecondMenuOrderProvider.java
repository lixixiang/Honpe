package com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.provider;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.GridViewAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean.MenuBean;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;

import org.jetbrains.annotations.NotNull;

/**
 * FileName: SecondMenuOrderProvider
 * Author: asus
 * Date: 2020/9/17 13:39
 * Description:
 */
public class SecondMenuOrderProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return GridViewAdapter.TYPE_PERSON;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_checkbox;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        final MenuBean.DataBean lv2 = (MenuBean.DataBean) baseNode;
        final CheckBox checkBox = helper.getView(R.id.item_check);
        checkBox.setText(lv2.getFoodName());
//                helper.addOnClickListener(R.id.item_check);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lv2.setStatus(1);
                    lv2.setMenuCheck(true);
                } else {
                    lv2.setStatus(0);
                    lv2.setMenuCheck(false);
                }
                Event<MenuBean.DataBean> event = new Event<MenuBean.DataBean>
                        (FinalClass.A, new MenuBean.DataBean(
                                lv2.getFoodStyle(), lv2.getFoodName(), lv2.getUnit(), lv2.getFoodCode(), lv2.getStatus()
                        ));
                EventBusUtil.sendEvent(event);
            }
        });

        if (lv2.getStatus() >0) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }
}
