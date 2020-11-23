package com.honpe.lxx.app.ui.login.register;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.register.adapter.SupplierRegisterAdapter;
import com.honpe.lxx.app.ui.login.register.bean.SupplierBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * FileName: SupplierQuotationRegisterFragment
 * Author: asus
 * Date: 2020/11/19 10:25
 * Description: 供应商报价注册
 */
public class SupplierQuotationRegisterFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String[] titles = {"用户名","密码","确认密码","邮箱","手机","公司","组织机构代码","税务登记号",
    "对公银行","对公帐号"};
    SupplierRegisterAdapter adapter;
    List<SupplierBean> list = new ArrayList<>();
    public static SupplierQuotationRegisterFragment getInstance() {
        SupplierQuotationRegisterFragment fragment = new SupplierQuotationRegisterFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
       initToolbarNav(llBack);
       tvTitle.setText("供应商注册");
        for (int i = 0; i < titles.length; i++) {
            SupplierBean bean = new SupplierBean();
            bean.setStrTitle(titles[i]);
            list.add(bean);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new SupplierRegisterAdapter(list);

        View footView = getFootView();
        adapter.addFooterView(footView);
        recyclerView.setAdapter(adapter);

    }

    private View getFootView() {
        View view = LayoutInflater.from(_mActivity).inflate(R.layout.foot_view, (ViewGroup) recyclerView.getParent(), false);
        return view;
    }
}
