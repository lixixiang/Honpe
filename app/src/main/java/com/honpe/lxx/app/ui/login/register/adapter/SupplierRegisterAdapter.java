package com.honpe.lxx.app.ui.login.register.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.ui.login.register.bean.SupplierBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * FileName: SupplierRegisterAdapter
 * Author: asus
 * Date: 2020/11/19 10:51
 * Description: 供应商注册适配器
 */
public class SupplierRegisterAdapter extends BaseQuickAdapter<SupplierBean, BaseViewHolder> {
    EditText editText;

    public SupplierRegisterAdapter(@Nullable List<SupplierBean> data) {
        super(R.layout.item_supplier, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, SupplierBean supplierBean) {
        holder.setText(R.id.title, supplierBean.getStrTitle());
        editText = holder.getView(R.id.et_count);

        if (editText.getTag() != null) {
            editText.removeTextChangedListener((TextWatcher) editText.getTag());
        }

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    supplierBean.setContent(s.toString());
                }else {
                    supplierBean.setContent("");
                }
            }
        };
        editText.addTextChangedListener(textWatcher);
        editText.setTag(textWatcher);
        editText.setText(supplierBean.getContent());
    }
}













