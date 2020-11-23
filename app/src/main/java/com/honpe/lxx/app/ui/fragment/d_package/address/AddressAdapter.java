package com.honpe.lxx.app.ui.fragment.d_package.address;

import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.fragment.d_package.address.bean.ReceiverAddressBean;

import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 12:22
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class AddressAdapter extends BaseQuickAdapter<ReceiverAddressBean, BaseViewHolder> {

    public AddressAdapter(@Nullable List<ReceiverAddressBean> data) {
        super(R.layout.item_address_show, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReceiverAddressBean item) {
        helper.setText(R.id.consignee_name,item.getUserName());
        helper.setText(R.id.consignee_phone, item.getPhone());
        helper.setText(R.id.consignee_address,item.getArea() + item.getAddress());
        final CheckBox isCheck = helper.getView(R.id.radio_selected);
        showPhone(item.getPhone());
        isCheck.setClickable(false);
        isCheck.setChecked(item.isClick());
        TextView checkText = helper.getView(R.id.radio_selectedText);
        checkText.setText(item.isClick() ? "默认地址" : "设为默认");


    }

    private String showPhone(String phone) {
        return phone.substring(0, 3) + "*****" + phone.substring(phone.length() - 3);
    }
}
