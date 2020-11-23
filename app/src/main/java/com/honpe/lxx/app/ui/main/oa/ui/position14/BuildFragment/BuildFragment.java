package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.adapter.BaseFragmentPagerAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.add.CheckAllNameFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.child.BuildChildFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.BedBean;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.BuildEntity;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.widget.NavigationTabStrip;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.REFRESH_SEARCH_APPROVE;

/**
 * FileName: BuildFragment
 * Author: asus
 * Date: 2020/9/15 11:20
 * Description: 建筑物fragment
 */
public class BuildFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tab)
    NavigationTabStrip tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    //    BuildFloorAdapter adapter;
    List<LevelEntity.FloorEntity.RoomEntity> roomEntities2 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity> roomEntities3 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity> roomEntities4 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity> roomEntities5 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity> roomEntities2A = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity> roomEntities3A = new ArrayList<>();

    List<LevelEntity.FloorEntity.RoomEntity.BedEntity> bedEntities2 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity.BedEntity> bedEntities3 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity.BedEntity> bedEntities4 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity.BedEntity> bedEntities5 = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity.BedEntity> bedEntities2A = new ArrayList<>();
    List<LevelEntity.FloorEntity.RoomEntity.BedEntity> bedEntities3A = new ArrayList<>();

    List<String> BuildTabs = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private BaseFragmentPagerAdapter adapter;
    private int curPos = 0;

    public static BuildFragment newInstance(String title) {
        BuildFragment fragment = new BuildFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_build;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            tvTitle.setText(bundle.getString("title"));
        }
        initToolbarNav(llBack);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getLeftJsonData("");
    }


    LevelEntity buildEntity;
    LevelEntity.FloorEntity floorEntity;
    List<BaseNode> secondList;
    List<BaseNode> thirdList;
    List<BaseNode> FourthList;

    private void getLeftJsonData(String status) {
        disposable = EasyHttp.post(Constants.AppDormitoryList)
                .retryCount(0)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity,1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("BUILD_LEFT_LEFT", s);
                        BuildEntity entity = GsonBuildUtil.NullToString().fromJson(s, BuildEntity.class);
                        LatteLogger.d("testEntity", GsonBuildUtil.GsonBuilder(entity));

                        List<LevelEntity> list = new ArrayList<>();
                        List<LevelEntity.FloorEntity> floorEntities = new ArrayList<>();

                        list.clear();
                        roomEntities2.clear();
                        roomEntities3.clear();
                        roomEntities4.clear();
                        roomEntities5.clear();
                        roomEntities2A.clear();
                        roomEntities3A.clear();
                        bedEntities2.clear();
                        bedEntities3.clear();
                        bedEntities4.clear();
                        bedEntities5.clear();
                        bedEntities2A.clear();
                        bedEntities3A.clear();

                        for (int i = 0; i < entity.getData().size(); i++) {
                            if (!"0".equals(entity.getData().get(i).getF_ParentId()) && entity.getData().get(i).getF_ParentId().equals("d31edb76-7435-4980-9b12-ca098fbad858")) {
                                 buildEntity = new LevelEntity();
                                 buildEntity.setBuildNum(entity.getData().get(i).getRemarks());
                                list.add(buildEntity);
                            }
                            if (entity.getData().get(i).getF_ParentId().equals("2005c0b1-3bdf-442e-94ea-498e8f916d20")) {
                                floorEntity = new LevelEntity.FloorEntity();
                                floorEntity.setF_FullName(entity.getData().get(i).getRemarks());
                                floorEntity.setF_ID(entity.getData().get(i).getF_ID());
                                floorEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                floorEntities.add(floorEntity);
                            } else if (entity.getData().get(i).getF_ParentId().equals("1a7e55c2-166e-4a46-9390-f6363cb73574")) {
                                floorEntity = new LevelEntity.FloorEntity();
                                floorEntity.setF_FullName(entity.getData().get(i).getRemarks());
                                floorEntity.setF_ID(entity.getData().get(i).getF_ID());
                                floorEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                floorEntities.add(floorEntity);
                            }

                            if (entity.getData().get(i).getF_ParentId().equals("7638bb9a-185a-4fef-a45b-3ce782dd6489")) {
                                LevelEntity.FloorEntity.RoomEntity roomEntity = new LevelEntity.FloorEntity.RoomEntity();
                                roomEntity.setF_FullName(entity.getData().get(i).getF_FullName());
                                roomEntity.setF_ID(entity.getData().get(i).getF_ID());
                                roomEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                roomEntity.setNumBeds(entity.getData().get(i).getNumBeds());
                                roomEntity.setUserdNum(entity.getData().get(i).getUserdNum());
                                roomEntities2.add(roomEntity);
                                for (int j = 0; j < entity.getData().get(i).getDormitoryDetails().size(); j++) {
                                    BuildEntity.DataEntity.DormitoryDetailsEntity detailsEntity = entity.getData().get(i).getDormitoryDetails().get(j);
                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = new  LevelEntity.FloorEntity.RoomEntity.BedEntity();
                                    bedEntity.setF_id(detailsEntity.getF_id());
                                    bedEntity.setF_fullname(detailsEntity.getF_fullname());
                                    bedEntity.setNumbeds(detailsEntity.getNumbeds());
                                    bedEntity.setUserid(detailsEntity.getUserid());
                                    bedEntity.setEmp_usernum(detailsEntity.getEmp_usernum());
                                    bedEntity.setEmp_username(detailsEntity.getEmp_username());
                                    bedEntity.setEmp_usersex(detailsEntity.getEmp_usersex());
                                    bedEntity.setDept_dep(detailsEntity.getDept_dep());
                                    bedEntities2.add(bedEntity);
                                }

                            } else if (entity.getData().get(i).getF_ParentId().equals("67821834-46e4-469d-a925-c5c327cb3d63")) {
                                LevelEntity.FloorEntity.RoomEntity roomEntity = new LevelEntity.FloorEntity.RoomEntity();
                                roomEntity.setF_FullName(entity.getData().get(i).getF_FullName());
                                roomEntity.setF_ID(entity.getData().get(i).getF_ID());
                                roomEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                roomEntity.setNumBeds(entity.getData().get(i).getNumBeds());
                                roomEntity.setUserdNum(entity.getData().get(i).getUserdNum());
                                roomEntities3.add(roomEntity);
                                for (int j = 0; j < entity.getData().get(i).getDormitoryDetails().size(); j++) {
                                    BuildEntity.DataEntity.DormitoryDetailsEntity detailsEntity = entity.getData().get(i).getDormitoryDetails().get(j);
                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = new  LevelEntity.FloorEntity.RoomEntity.BedEntity();
                                    bedEntity.setF_id(detailsEntity.getF_id());
                                    bedEntity.setF_fullname(detailsEntity.getF_fullname());
                                    bedEntity.setNumbeds(detailsEntity.getNumbeds());
                                    bedEntity.setUserid(detailsEntity.getUserid());
                                    bedEntity.setEmp_usernum(detailsEntity.getEmp_usernum());
                                    bedEntity.setEmp_username(detailsEntity.getEmp_username());
                                    bedEntity.setEmp_usersex(detailsEntity.getEmp_usersex());
                                    bedEntity.setDept_dep(detailsEntity.getDept_dep());
                                    bedEntities3.add(bedEntity);
                                }

                            } else if (entity.getData().get(i).getF_ParentId().equals("4a343215-7d95-4498-8519-a8be14096849")) {
                                LevelEntity.FloorEntity.RoomEntity roomEntity = new LevelEntity.FloorEntity.RoomEntity();
                                roomEntity.setF_FullName(entity.getData().get(i).getF_FullName());
                                roomEntity.setF_ID(entity.getData().get(i).getF_ID());
                                roomEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                roomEntity.setNumBeds(entity.getData().get(i).getNumBeds());
                                roomEntity.setUserdNum(entity.getData().get(i).getUserdNum());
                                roomEntities4.add(roomEntity);
                                for (int j = 0; j < entity.getData().get(i).getDormitoryDetails().size(); j++) {
                                    BuildEntity.DataEntity.DormitoryDetailsEntity detailsEntity = entity.getData().get(i).getDormitoryDetails().get(j);
                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = new  LevelEntity.FloorEntity.RoomEntity.BedEntity();
                                    bedEntity.setF_id(detailsEntity.getF_id());
                                    bedEntity.setF_fullname(detailsEntity.getF_fullname());
                                    bedEntity.setNumbeds(detailsEntity.getNumbeds());
                                    bedEntity.setUserid(detailsEntity.getUserid());
                                    bedEntity.setEmp_usernum(detailsEntity.getEmp_usernum());
                                    bedEntity.setEmp_username(detailsEntity.getEmp_username());
                                    bedEntity.setEmp_usersex(detailsEntity.getEmp_usersex());
                                    bedEntity.setDept_dep(detailsEntity.getDept_dep());
                                    bedEntities4.add(bedEntity);
                                }
                            } else if (entity.getData().get(i).getF_ParentId().equals("1e8cddf7-c8a6-45e5-9e08-4442f0feac7e")) {
                                LevelEntity.FloorEntity.RoomEntity roomEntity = new LevelEntity.FloorEntity.RoomEntity();
                                roomEntity.setF_FullName(entity.getData().get(i).getF_FullName());
                                roomEntity.setF_ID(entity.getData().get(i).getF_ID());
                                roomEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                roomEntity.setNumBeds(entity.getData().get(i).getNumBeds());
                                roomEntity.setUserdNum(entity.getData().get(i).getUserdNum());
                                roomEntities5.add(roomEntity);
                                for (int j = 0; j < entity.getData().get(i).getDormitoryDetails().size(); j++) {
                                    BuildEntity.DataEntity.DormitoryDetailsEntity detailsEntity = entity.getData().get(i).getDormitoryDetails().get(j);
                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = new  LevelEntity.FloorEntity.RoomEntity.BedEntity();
                                    bedEntity.setF_id(detailsEntity.getF_id());
                                    bedEntity.setF_fullname(detailsEntity.getF_fullname());
                                    bedEntity.setNumbeds(detailsEntity.getNumbeds());
                                    bedEntity.setUserid(detailsEntity.getUserid());
                                    bedEntity.setEmp_usernum(detailsEntity.getEmp_usernum());
                                    bedEntity.setEmp_username(detailsEntity.getEmp_username());
                                    bedEntity.setEmp_usersex(detailsEntity.getEmp_usersex());
                                    bedEntity.setDept_dep(detailsEntity.getDept_dep());
                                    bedEntities5.add(bedEntity);
                                }
                            } else if (entity.getData().get(i).getF_ParentId().equals("32bf7502-1518-457c-9a12-390cb4e7ebab")) {
                                LevelEntity.FloorEntity.RoomEntity roomEntity = new LevelEntity.FloorEntity.RoomEntity();
                                roomEntity.setF_FullName(entity.getData().get(i).getF_FullName());
                                roomEntity.setF_ID(entity.getData().get(i).getF_ID());
                                roomEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                roomEntity.setNumBeds(entity.getData().get(i).getNumBeds());
                                roomEntity.setUserdNum(entity.getData().get(i).getUserdNum());
                                roomEntities2A.add(roomEntity);
                                for (int j = 0; j < entity.getData().get(i).getDormitoryDetails().size(); j++) {
                                    BuildEntity.DataEntity.DormitoryDetailsEntity detailsEntity = entity.getData().get(i).getDormitoryDetails().get(j);
                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = new  LevelEntity.FloorEntity.RoomEntity.BedEntity();
                                    bedEntity.setF_id(detailsEntity.getF_id());
                                    bedEntity.setF_fullname(detailsEntity.getF_fullname());
                                    bedEntity.setNumbeds(detailsEntity.getNumbeds());
                                    bedEntity.setUserid(detailsEntity.getUserid());
                                    bedEntity.setEmp_usernum(detailsEntity.getEmp_usernum());
                                    bedEntity.setEmp_username(detailsEntity.getEmp_username());
                                    bedEntity.setEmp_usersex(detailsEntity.getEmp_usersex());
                                    bedEntity.setDept_dep(detailsEntity.getDept_dep());
                                    bedEntities2A.add(bedEntity);
                                }
                            } else if (entity.getData().get(i).getF_ParentId().equals("f01711cc-f698-433c-98f2-3ec5ef7afe15")) {
                                LevelEntity.FloorEntity.RoomEntity roomEntity = new LevelEntity.FloorEntity.RoomEntity();
                                roomEntity.setF_FullName(entity.getData().get(i).getF_FullName());
                                roomEntity.setF_ID(entity.getData().get(i).getF_ID());
                                roomEntity.setF_ParentId(entity.getData().get(i).getF_ParentId());
                                roomEntity.setNumBeds(entity.getData().get(i).getNumBeds());
                                roomEntity.setUserdNum(entity.getData().get(i).getUserdNum());
                                roomEntities3A.add(roomEntity);
                                for (int j = 0; j < entity.getData().get(i).getDormitoryDetails().size(); j++) {
                                    BuildEntity.DataEntity.DormitoryDetailsEntity detailsEntity = entity.getData().get(i).getDormitoryDetails().get(j);
                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = new  LevelEntity.FloorEntity.RoomEntity.BedEntity();
                                    bedEntity.setF_id(detailsEntity.getF_id());
                                    bedEntity.setF_fullname(detailsEntity.getF_fullname());
                                    bedEntity.setNumbeds(detailsEntity.getNumbeds());
                                    bedEntity.setUserid(detailsEntity.getUserid());
                                    bedEntity.setEmp_usernum(detailsEntity.getEmp_usernum());
                                    bedEntity.setEmp_username(detailsEntity.getEmp_username());
                                    bedEntity.setEmp_usersex(detailsEntity.getEmp_usersex());
                                    bedEntity.setDept_dep(detailsEntity.getDept_dep());
                                    bedEntities3A.add(bedEntity);
                                }
                            }
                        }
                        List<BaseNode> FistList = new ArrayList<>();
                        LatteLogger.d("testddddd",GsonBuildUtil.GsonBuilder(roomEntities2));
                        BuildTabs.clear();

                        for (int i = 0; i < list.size(); i++) {
                            LevelEntity buildEntity = new LevelEntity();
                            buildEntity.setBuildNum(list.get(i).getBuildNum());
                            secondList = new ArrayList<>();
                            for (int j = 0; j < floorEntities.size(); j++) {
                                LevelEntity.FloorEntity floorEntity = floorEntities.get(j);
                                if (i == 0 && floorEntity.getF_ParentId().equals("2005c0b1-3bdf-442e-94ea-498e8f916d20")) {
                                     thirdList = new ArrayList<>();
                                    if (j == 2) {
                                        for (int k = 0; k < roomEntities2.size(); k++) {
                                            LevelEntity.FloorEntity.RoomEntity roomEntity = roomEntities2.get(k);
                                            FourthList = new ArrayList<>();
                                            FourthList.clear();
                                            for (int l = 0; l < bedEntities2.size(); l++) {
                                                if (bedEntities2.get(l).getF_fullname().equals(roomEntity.getF_FullName())){
                                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = bedEntities2.get(l);
                                                    FourthList.add(bedEntity);
                                                    roomEntity.setChildNode(FourthList);
                                                }
                                            }
                                            thirdList.add(roomEntity);
                                        }

                                    } else if (j == 3) {
                                        for (int k = 0; k < roomEntities3.size(); k++) {
                                            LevelEntity.FloorEntity.RoomEntity roomEntity = roomEntities3.get(k);
                                            FourthList = new ArrayList<>();
                                            FourthList.clear();
                                            for (int l = 0; l < bedEntities3.size(); l++) {
                                                if (bedEntities3.get(l).getF_fullname().equals(roomEntity.getF_FullName())){
                                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = bedEntities3.get(l);
                                                    FourthList.add(bedEntity);
                                                    roomEntity.setChildNode(FourthList);
                                                }
                                            }
                                            thirdList.add(roomEntity);
                                        }
                                    } else if (j == 4) {
                                        for (int k = 0; k < roomEntities4.size(); k++) {
                                            LevelEntity.FloorEntity.RoomEntity roomEntity = roomEntities4.get(k);
                                            FourthList = new ArrayList<>();
                                            FourthList.clear();
                                            for (int l = 0; l < bedEntities4.size(); l++) {
                                                if (bedEntities4.get(l).getF_fullname().equals(roomEntity.getF_FullName())){
                                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = bedEntities4.get(l);
                                                    FourthList.add(bedEntity);
                                                    roomEntity.setChildNode(FourthList);
                                                }
                                            }
                                            thirdList.add(roomEntity);
                                        }
                                    } else if (j == 5) {
                                        for (int k = 0; k < roomEntities5.size(); k++) {
                                            LevelEntity.FloorEntity.RoomEntity roomEntity = roomEntities5.get(k);
                                            FourthList = new ArrayList<>();
                                            FourthList.clear();
                                            for (int l = 0; l < bedEntities5.size(); l++) {
                                                if (bedEntities5.get(l).getF_fullname().equals(roomEntity.getF_FullName())){
                                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = bedEntities5.get(l);
                                                    FourthList.add(bedEntity);
                                                    roomEntity.setChildNode(FourthList);
                                                }
                                            }
                                            thirdList.add(roomEntity);
                                        }
                                    }
                                    floorEntity.setChildNode(thirdList);
                                    secondList.add(floorEntity);
                                    buildEntity.setChildNode(secondList);
                                } else if (i == 1 && floorEntity.getF_ParentId().equals("1a7e55c2-166e-4a46-9390-f6363cb73574")) {
                                    thirdList = new ArrayList<>();
                                    if (j == 0) {
                                        for (int k = 0; k < roomEntities2A.size(); k++) {
                                            LevelEntity.FloorEntity.RoomEntity roomEntity = roomEntities2A.get(k);
                                            FourthList = new ArrayList<>();
                                            FourthList.clear();
                                            for (int l = 0; l < bedEntities2A.size(); l++) {
                                                if (bedEntities2A.get(l).getF_fullname().equals(roomEntity.getF_FullName())){
                                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = bedEntities2A.get(l);
                                                    FourthList.add(bedEntity);
                                                    roomEntity.setChildNode(FourthList);
                                                }
                                            }
                                            thirdList.add(roomEntity);
                                        }
                                    } else if (j == 1) {
                                        for (int k = 0; k < roomEntities3A.size(); k++) {
                                            LevelEntity.FloorEntity.RoomEntity roomEntity = roomEntities3A.get(k);
                                            FourthList = new ArrayList<>();
                                            FourthList.clear();
                                            for (int l = 0; l < bedEntities3A.size(); l++) {
                                                if (bedEntities3A.get(l).getF_fullname().equals(roomEntity.getF_FullName())){
                                                    LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = bedEntities3A.get(l);
                                                    FourthList.add(bedEntity);
                                                    roomEntity.setChildNode(FourthList);
                                                }
                                            }
                                            thirdList.add(roomEntity);
                                        }
                                    }
                                    floorEntity.setChildNode(thirdList);
                                    secondList.add(floorEntity);
                                    buildEntity.setChildNode(secondList);
                                }
                            }
                            buildEntity.setExpanded(true);
                            FistList.add(buildEntity);
                            BuildTabs.add(buildEntity.getBuildNum());
                        }
                        LatteLogger.d("testFistList",GsonBuildUtil.GsonBuilder(FistList));
                        if (!"".equals(status)) {
                            for (int i = 0; i < BuildTabs.size(); i++) {
                                adapter.replaceFragment(fragments.get(curPos),BuildChildFragment.newInstance(FistList.get(i).getChildNode()));
                            }
                        }else {
                            for (int i = 0; i < BuildTabs.size(); i++) {
                                fragments.add(BuildChildFragment.newInstance(FistList.get(i).getChildNode()));
                            }
                            adapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragments);
                            viewPager.setAdapter(adapter);
                        }
                        tab.setTitles(StringUtil.ListToArr(BuildTabs));
                        tab.setViewPager(viewPager);
                        tab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                curPos = position;
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    }
                });
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.ADD_CHECK_IN_DORM:
                LevelEntity.FloorEntity.RoomEntity.BedEntity entity = (LevelEntity.FloorEntity.RoomEntity.BedEntity) event.getData();
                start(CheckAllNameFragment.newInstance(entity));
                break;
            case REFRESH_SEARCH_APPROVE:
                getLeftJsonData("1");
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.checkSession(this, session);
    }
}

