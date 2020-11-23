package com.honpe.lxx.app.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.honpe.lxx.app.widget.font.FontTextView2;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/20 11:41
 * @Author: 李熙祥
 * @Description: java类作用描述 修改消息工具类
 */
public class GetMessageUtil{
    public void editChanged(EditText view, final FontTextView2 iconView) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    iconView.setVisibility(View.VISIBLE);
                } else {
                    iconView.setVisibility(View.GONE);
                }
            }
        });

    }
}

