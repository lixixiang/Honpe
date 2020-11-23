package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.CheckInManager;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.CheckInMineFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.CardDetailAdapter;
import com.honpe.lxx.app.ui.main.more.multipleItem.position5.bean.CheckInTotalBean;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * FileName: CommonViewPagerDeitailFragment
 * Author: asus
 * Date: 2020/8/7 11:20
 * Description: 用于显示所有的viewPager 人员列表
 */
public class CommonViewPagerDetailFragment extends BaseBackFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    @BindView(R.id.re_img)
    RelativeLayout reImg;
    String key;
    CardDetailAdapter adapter;
    Bundle bundle = new Bundle();
    List<CheckInTotalBean.Data> listBean;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static CommonViewPagerDetailFragment newInstance(String key, List<CheckInTotalBean.Data> checkInBean) {
        CommonViewPagerDetailFragment fragment = new CommonViewPagerDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) checkInBean);

        bundle.putString("title", key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void initView() {
        bundle = this.getArguments();
        assert bundle != null;
        listBean = bundle.getParcelableArrayList("list");
        if (listBean.size() == 0) {
            recyclerview.setVisibility(View.GONE);
            reImg.setVisibility(View.VISIBLE);
        } else {
            recyclerview.setVisibility(View.VISIBLE);
            reImg.setVisibility(View.GONE);
            key = bundle.getString("title");
            LatteLogger.d("listBean", GsonBuildUtil.GsonBuilder(listBean));
            recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
            adapter = new CardDetailAdapter(key, listBean);
            recyclerview.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ((CheckInManager) getParentFragment().getParentFragment()).start(CheckInMineFragment.newInstance(key, listBean.get(position)));
                }
            });
        }
    }

}


