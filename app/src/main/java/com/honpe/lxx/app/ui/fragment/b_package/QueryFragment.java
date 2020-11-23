package com.honpe.lxx.app.ui.fragment.b_package;


import androidx.appcompat.widget.Toolbar;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseMainFragment;
import com.honpe.lxx.app.utils.event.Event;

import butterknife.BindView;

/**
 * created by lxx at 2019/12/17 12:08
 * 描述:查询主界面
 */
public class QueryFragment extends BaseMainFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final QueryFragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_query;
    }

    @Override
    protected void initView() {
        toolbar.setTitle(getString(R.string.text_query));

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
}
