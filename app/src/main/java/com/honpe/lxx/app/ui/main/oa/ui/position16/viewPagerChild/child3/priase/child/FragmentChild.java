package com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.priase.child;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean3;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child3.priase.child.adapter.PraiseAdapter;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;

import java.util.List;

import butterknife.BindView;

/**
 * FileName: FragmentChild
 * Author: asus
 * Date: 2020/8/12 16:12
 * Description: 点赞评论
 */
public class FragmentChild extends BaseBackFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<StatisticsBean3.DataBean.DetailsBean> mList;
    PraiseAdapter adapter;
    private String type;

    public static FragmentChild newInstance(List<StatisticsBean3.DataBean.DetailsBean> list, String type) {
        FragmentChild fragmentChild = new FragmentChild();
        fragmentChild.mList = list;
        fragmentChild.type = type;
        return fragmentChild;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_child;
    }

    @Override
    protected void initView() {
        for (StatisticsBean3.DataBean.DetailsBean bean : mList) {
            if (bean.getScore().equals(type)) {
                recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
                LatteLogger.d("testInit", GsonBuildUtil.GsonBuilder(mList));
                adapter = new PraiseAdapter(mList);
                recyclerView.setAdapter(adapter);
            }
        }

    }
}
