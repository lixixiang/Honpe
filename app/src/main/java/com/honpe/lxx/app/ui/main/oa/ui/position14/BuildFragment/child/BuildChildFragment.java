package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.child;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.BuildFloorAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;

import java.util.List;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.REFRESH_SEARCH_APPROVE;

/**
 * FileName: BuildChildFragment
 * Author: asus
 * Date: 2020/10/17 9:31
 * Description:
 */
public class BuildChildFragment extends BaseBackFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.re_img)
    RelativeLayout reImg;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    BuildFloorAdapter buildFloorAdapter;
    List<BaseNode> floorEntity;

    public static BuildChildFragment newInstance(List<BaseNode> floorEntity) {
        BuildChildFragment fragment = new BuildChildFragment();
        fragment.floorEntity = floorEntity;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.layout_recyclerview;
    }


    @Override
    protected void initView() {
        LatteLogger.d("initViewddd", GsonBuildUtil.GsonBuilder(floorEntity));
        refreshLayout.setOnRefreshListener(this);
        GridLayoutManager manager = new GridLayoutManager(_mActivity, 2);
        recyclerView.setLayoutManager(manager);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return buildFloorAdapter.getItemViewType(position) == BuildFloorAdapter.build3 ? 1 : manager.getSpanCount();
            }
        });

        buildFloorAdapter = new BuildFloorAdapter();

        recyclerView.setAdapter(buildFloorAdapter);
        buildFloorAdapter.setList(floorEntity);
        buildFloorAdapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();


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
                buildFloorAdapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.UPDATE_CHECK_IN_BED:
                Bundle bundle = (Bundle) event.getData();
                LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = (LevelEntity.FloorEntity.RoomEntity.BedEntity) bundle.getSerializable("bundle");
                LatteLogger.d("bundle", GsonBuildUtil.GsonBuilder(bedEntity)+"     "+bedEntity.getIndex());
                buildFloorAdapter.getData().set(bedEntity.getIndex(),bedEntity);
                buildFloorAdapter.notifyItemChanged(bedEntity.getIndex(),buildFloorAdapter.ITEM_ADD);
                break;
        }
    }
}














