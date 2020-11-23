package com.honpe.lxx.app.ui.login.register;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.login.register.adapter.FragmentTypeAdapter;
import com.honpe.lxx.app.ui.login.register.bean.RegisterTypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * FileName: RegisterTypeFragment
 * Author: asus
 * Date: 2020/11/19 9:48
 * Description:
 */
public class RegisterTypeFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    FragmentTypeAdapter adapter;
    List<RegisterTypeBean> beans = new ArrayList<>();
    private String[] Users = {"普通用户","供应商用户"};
    private int[] icons = {R.mipmap.gv_animation,R.mipmap.gv_databinding};

    public static RegisterTypeFragment newInstance() {
        RegisterTypeFragment fragment = new RegisterTypeFragment();
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("注册入口");
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        for (int i = 0; i < Users.length; i++) {
            RegisterTypeBean bean = new RegisterTypeBean();
            bean.setIcon(icons[i]);
            bean.setType(Users[i]);
            beans.add(bean);
        }
        adapter = new FragmentTypeAdapter(beans);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (position) {
                    case 0:
                        start(RegisterFragment.newInstance());
                        break;
                    case 1:
                        start(SupplierQuotationRegisterFragment.getInstance());
                        break;
                }
            }
        });
    }
}












