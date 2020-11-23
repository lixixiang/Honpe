package com.honpe.lxx.app.ui.main.more.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * FileName: MyCheckFragment
 * Author: asus
 * Date: 2020/8/6 20:03
 * Description:
 */
public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments; //创建一个 List<Fragment>
    private HashMap<Integer, Boolean> mList_Need_Update = new HashMap<>();
    private FragmentManager mFragmentManager;
    public BaseViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mFragmentManager = fm;
        mList_Need_Update.clear();
        this.fragments = list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



    public void setFragmentNeedUpdate(List<Fragment> listFragments){
        fragments.clear();
        if (listFragments != null) {
            fragments.addAll(listFragments);
        }
        notifyDataSetChanged();
    }

}
