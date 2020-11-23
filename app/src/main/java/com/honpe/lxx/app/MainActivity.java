package com.honpe.lxx.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseActivity;
import com.honpe.lxx.app.ui.fragment.b_package.CustomHomeFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.utils.LatteLogger;

import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity {

    /**
     * splash页面进入
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_framelayout;
    }

    @Override
    public void initView() {
        int type = (int) MyApplication.get(_mActivity, FinalClass.UserType, -1);
        if (findFragment(MainFragment.class) == null) {
            if (type == -1) {
                loadRootFragment(R.id.fl_main_fragment_container, LoginFragment.newInstance("MainActivity"));
            }else {
                loadRootFragment(R.id.fl_main_fragment_container, MainFragment.newInstance());
            }
        }
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultVerticalAnimator();
    }
}