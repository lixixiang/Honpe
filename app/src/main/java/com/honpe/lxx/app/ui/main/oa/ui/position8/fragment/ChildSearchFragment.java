package com.honpe.lxx.app.ui.main.oa.ui.position8.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position8.adapter.ChildSearchAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.ChildEntity;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.NameInfoEntity;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.SearchEntity;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.REFRESH_SEARCH_APPROVE;

/**
 * FileName: ChildFragment
 * Author: asus
 * Date: 2020/10/12 11:22
 * Description:公共子fragment 用于显示各布局
 */
public class ChildSearchFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.re_img)
    RelativeLayout reImg;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    List<BaseNode> listType = new ArrayList<>();
    List<BaseNode> secondList;
    ChildSearchAdapter adapter;
    HashMap<String, List<SearchEntity.DataEntity.RowsEntity>> maps2 = new LinkedHashMap<>();


    public static ChildSearchFragment newInstance(Bundle bundle) {
        ChildSearchFragment fragment = new ChildSearchFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(this);

        Bundle bundle = getArguments();

        HashMap<String, List<SearchEntity.DataEntity.RowsEntity>> maps = (HashMap<String, List<SearchEntity.DataEntity.RowsEntity>>) bundle.getSerializable("map");

        LatteLogger.d("curPos", GsonBuildUtil.GsonBuilder(maps));

        for (String key : maps.keySet()) {
            ChildEntity childEntity = new ChildEntity();
            childEntity.setType(key);
            LatteLogger.d("curPos", childEntity.getType());
            List<SearchEntity.DataEntity.RowsEntity> mLists = maps.get(key);
            if (key.contains("请假")) {
                childEntity.setIcon(R.mipmap.ic_oa2);
            } else if (key.contains("出差")) {
                childEntity.setIcon(R.mipmap.ic_oa3);
            } else if (key.contains("外出")) {
                childEntity.setIcon(R.mipmap.ic_oa4);
            } else if (key.contains("加班")) {
                childEntity.setIcon(R.mipmap.ic_oa5);
            } else if (key.contains("补卡")) {
                childEntity.setIcon(R.mipmap.ic_oa6);
            } else if (key.contains("客户")) {
                childEntity.setIcon(R.mipmap.ic_xz4);
            }

            secondList = new ArrayList<>();
            maps2.clear();
            for (int i = 0; i < mLists.size(); i++) {
                SearchEntity.DataEntity.RowsEntity rowsEntity = mLists.get(i);

                childEntity.setTime(rowsEntity.getF_CreateDate());
                String name = mLists.get(i).getF_CreateUserName();

                List<SearchEntity.DataEntity.RowsEntity> rowsEntities = maps2.get(name);
                if (rowsEntities == null) {
                    rowsEntities = new ArrayList<>();
                }
                rowsEntities.add(mLists.get(i));
                maps2.put(name, rowsEntities);
            }

            for (String key2 : maps2.keySet()) {
                NameInfoEntity nameInfoEntity = new NameInfoEntity();
                nameInfoEntity.setName(key2);

                List<SearchEntity.DataEntity.RowsEntity> rowsEntities2 = maps2.get(key2);
                nameInfoEntity.setTips(rowsEntities2.size());
                nameInfoEntity.setRowsEntities(rowsEntities2);
                List<BaseNode> ThirdList = new ArrayList<>();
                for (int i = 0; i < rowsEntities2.size(); i++) {
                    SearchEntity.DataEntity.RowsEntity rowsEntity = rowsEntities2.get(i);
                    rowsEntity.setFM_id(i + 1);
                    rowsEntity.setEmployeeId(EmployeeId);
                    ThirdList.add(rowsEntity);
                }
                nameInfoEntity.setChildNode(ThirdList);
                secondList.add(nameInfoEntity);
            }

            childEntity.setTips(mLists.size());
            childEntity.setChildNode(secondList);
            listType.add(childEntity);
        }

        adapter = new ChildSearchAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.setAdapter(adapter);
        adapter.setList(listType);
        adapter.notifyDataSetChanged();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Event<String> event = new Event<String>(REFRESH_SEARCH_APPROVE, "1");
                EventBusUtil.sendEvent(event);
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }




}







