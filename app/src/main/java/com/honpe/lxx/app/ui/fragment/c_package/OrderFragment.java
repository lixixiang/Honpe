package com.honpe.lxx.app.ui.fragment.c_package;


import androidx.appcompat.widget.Toolbar;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseMainFragment;

import butterknife.BindView;

/**
 * created by lxx at 2019/12/17 12:09
 * 描述:订单主界面
 */
public class OrderFragment extends BaseMainFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        toolbar.setTitle(getString(R.string.text_order));

    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
