package com.honpe.lxx.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.fragment.a_package.MyHomeFragment;
import com.honpe.lxx.app.ui.fragment.b_package.CustomHomeFragment;
import com.honpe.lxx.app.ui.fragment.d_package.MeFragment;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.widget.botBar.BottomBar;
import com.honpe.lxx.app.widget.botBar.BottomBarTab;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

import static com.honpe.lxx.app.api.FinalClass.FIRST;
import static com.honpe.lxx.app.api.FinalClass.SECOND;

public class MainFragment extends SupportFragment {
    private static final int REQ_MSG = 10;
    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    Unbinder unbinder;
    SupportFragment firstFragment;
    private SupportFragment[] mFragments = new SupportFragment[2];

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int type = (int) MyApplication.get(_mActivity, FinalClass.UserType, -1);
        LatteLogger.d("onActivityCreated",type+"");
        if (type == 1) {
            firstFragment = findFragment(CustomHomeFragment.class);
        } else {
            firstFragment = findFragment(MyHomeFragment.class);
        }

        if (firstFragment == null) {
            if (type == 1) {
                mFragments[FIRST] = CustomHomeFragment.newInstance();
            } else {
                mFragments[FIRST] = MyHomeFragment.newInstance();
            }
//            mFragments[SECOND] = QueryFragment.newInstance();
//            mFragments[THIRD] = OrderFragment.newInstance();
            mFragments[SECOND] = MeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
//                    mFragments[SECOND],
//                    mFragments[THIRD],
                    mFragments[SECOND]);
        } else {
            mFragments[FIRST] = firstFragment;
//            mFragments[SECOND] = findFragment(QueryFragment.class);
//            mFragments[THIRD] = findFragment(OrderFragment.class);
            mFragments[SECOND] = findFragment(MeFragment.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();

        return view;
    }


    public void initView() {
        bottomBar.addItem(new BottomBarTab(_mActivity, R.drawable.bottom1, getString(R.string.text_home)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.bottom2, getString(R.string.text_me)));
        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
                BottomBarTab tab = bottomBar.getItem(FIRST);
//                if (position == FIRST) {
//                    tab.setUnreadCount(0);
//                } else {
//                    tab.setUnreadCount(tab.getUnreadCount() + 1);
//                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == REQ_MSG && resultCode == RESULT_OK) {

        }
    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}



