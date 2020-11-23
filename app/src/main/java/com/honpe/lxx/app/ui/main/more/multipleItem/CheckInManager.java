package com.honpe.lxx.app.ui.main.more.multipleItem;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.DBName;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.adapter.BaseFragmentPagerAdapter;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.CheckInMineFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.CheckInTotalFragment;
import com.honpe.lxx.app.utils.DBUtils;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.widget.NavigationTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 考勤管理
 */
public class CheckInManager extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nts_bottom)
    NavigationTabStrip ntsBottom;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.fl_viewpager)
    FrameLayout flViewPager;

    List<Fragment> mFragments = new ArrayList<>();
    private String departId;


    public static CheckInManager newInstance(String title, String departId) {
        CheckInManager fragment = new CheckInManager();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("departId", departId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_check_in;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        Utils.checkSession(this, session);
        if (!"".equals(departId)) {
            DBUtils.put(_mActivity, DBName.SELECTOR_DEPART_DB, DBName.SELECTOR_DEPART_ID, departId);
        }
        departId = (String) DBUtils.get(_mActivity, DBName.SELECTOR_DEPART_DB, DBName.SELECTOR_DEPART_ID, "");
        LatteLogger.d("CheckInManager", departId);
        mFragments.add(CheckInTotalFragment.newInstance(departId));
        mFragments.add(CheckInMineFragment.newInstance("", null));
        viewPager.setAdapter(new BaseFragmentPagerAdapter(getChildFragmentManager(), mFragments));
        ntsBottom.setViewPager(viewPager, 0);
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        initToolbarNav(llBack);
        if (bundle != null) {
            tvTitle.setText(bundle.getString("title"));
            departId = bundle.getString("departId");
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}










