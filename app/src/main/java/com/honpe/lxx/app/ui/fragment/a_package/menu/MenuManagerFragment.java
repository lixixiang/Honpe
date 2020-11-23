package com.honpe.lxx.app.ui.fragment.a_package.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.AppConfig;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.bean.MyHomeBean;
import com.honpe.lxx.app.ui.fragment.a_package.menu.adapter.MenuParentAdapter;
import com.honpe.lxx.app.ui.fragment.a_package.menu.adapter.MyMenuAdapter;
import com.honpe.lxx.app.ui.main.more.multipleItem.CheckInManager;
import com.honpe.lxx.app.ui.main.oa.ui.car_manager.CarInfoManager;
import com.honpe.lxx.app.ui.main.oa.ui.position1.TotalQueryFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position13.RepairFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.BuildFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position15.CustomCookerOrderFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position16.EmployeeWithOrderFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position17.TotalOfMeatFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position19.SubContractFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position2.LeaveFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position5.OverTimeFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position6.CardQueryFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position7.ApproveFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position8.SearchFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position9.CarInfoFragment;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.widget.drag.CustomExpandableListView;
import com.honpe.lxx.app.widget.drag.DragCallback;
import com.honpe.lxx.app.widget.drag.DragForScrollView;
import com.honpe.lxx.app.widget.drag.DragGridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.ALL_BACK_HOME;

/**
 * FileName: MenuManagerFragment
 * Author: asus
 * Date: 2020/8/27 11:45
 * Description: 菜单管理
 */
public class MenuManagerFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_item_cate_name)
    TextView tvItemCateName;
    @BindView(R.id.tv_drag_tip)
    TextView tvDragTip;
    @BindView(R.id.gridview)
    DragGridView dragGridView;
    @BindView(R.id.expandableListView)
    CustomExpandableListView expandableListView;
    @BindView(R.id.sv_index)
    DragForScrollView svIndex;

    private static MyApplication appContext;
    private static ArrayList<MyHomeBean> menuList = new ArrayList<MyHomeBean>();
    private static List<MyHomeBean> indexSelect = new ArrayList<>();
    private static MyMenuAdapter adapterSelect;
    private static MenuParentAdapter menuParentAdapter;
    private Context context;

    public static MenuManagerFragment newInstance() {
        MenuManagerFragment fragment = new MenuManagerFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_menu_manager;
    }

    @Override
    protected void initView() {
        appContext = (MyApplication) _mActivity.getApplication();
        initToolbarNav(llBack);
        tvTitle.setText("全部应用");
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText("管理");
        List<MyHomeBean> indexDataList = (List<MyHomeBean>) appContext.readObject(AppConfig.KEY_USER);
        if (indexDataList != null) {
            indexSelect.clear();
            indexSelect.addAll(indexDataList);
        }

        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvEnd.getText().toString().equals("管理")) {
                    tvEnd.setText("完成");
                    adapterSelect.setEdit();
                    if (menuParentAdapter != null) {
                        menuParentAdapter.setEdit();
                    }
                    tvDragTip.setVisibility(View.VISIBLE);
                } else {
                    tvEnd.setText("管理");
                    tvDragTip.setVisibility(View.GONE);
                    adapterSelect.endEdit();
                    if (menuParentAdapter != null) {
                        menuParentAdapter.endEdit();
                    }
                    postMenu();
                }
                adapterSelect.notifyDataSetChanged();
            }
        });

        dragGridView.setDragCallback(new DragCallback() {
            @Override
            public void startDrag(int position) {
                LatteLogger.d("start drag at ", "" + position);
                svIndex.startDrag(position);
            }

            @Override
            public void endDrag(int position) {
                LatteLogger.d("end drag at ", "" + position);
                svIndex.endDrag(position);
            }
        });
        dragGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LatteLogger.e("setOnItemClickListener", adapterSelect.getEditStatue() + "");
                if (!adapterSelect.getEditStatue()) {
                    MyHomeBean cateModel = indexDataList.get(position);
                    //  initUrl(cateModel);
                }
            }
        });
        dragGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (tvEnd.getText().toString().equals("管理")) {
                    tvEnd.setText("完成");
                    adapterSelect.setEdit();
                    if (menuParentAdapter != null) {
                        menuParentAdapter.setEdit();
                    }
                    tvDragTip.setVisibility(View.VISIBLE);
                }
                dragGridView.startDrag(position);
                return false;
            }
        });
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        adapterSelect = new MyMenuAdapter(_mActivity, MenuManagerFragment.this, appContext, indexSelect);
        dragGridView.setAdapter(adapterSelect);

        initElseIcon();
    }

    private void initElseIcon() {
        List<MyHomeBean> indexDataList = (List<MyHomeBean>) appContext.readObject(AppConfig.KEY_All);
        initData(indexDataList);
    }

    private void initData(List<MyHomeBean> indexAll) {
        expandableListView.setGroupIndicator(null);
        menuList.clear();

        MyHomeBean index = new MyHomeBean();
        index.setTitle("行政应用");
        index.setId("1");
        List<MyHomeBean> indexXZ = new ArrayList<>();
        for (int i = 0; i < indexAll.size(); i++) {
            if (Integer.parseInt(indexAll.get(i).getSort()) > 7 && Integer.parseInt(indexAll.get(i).getSort()) < 16) {
                indexXZ.add(indexAll.get(i));
            } else if (Integer.parseInt(indexAll.get(i).getSort()) > 22 && Integer.parseInt(indexAll.get(i).getSort()) < 28) {
                indexXZ.add(indexAll.get(i));
            }
        }
        for (int i = 0; i < indexXZ.size(); i++) {
            for (int j = 0; j < indexSelect.size(); j++) {
                if (indexXZ.get(i).getTitle().equals(indexSelect.get(j).getTitle())) {
                    indexXZ.get(i).setSelect(true);
                }
            }
        }
        index.setChilds(indexXZ);
        menuList.add(index);


        MyHomeBean index2 = new MyHomeBean();
        index2.setTitle("人事管理");
        index2.setId("3");
        List<MyHomeBean> indexRS = new ArrayList<MyHomeBean>();
        for (int i = 0; i < indexAll.size(); i++) {
            if (Integer.parseInt(indexAll.get(i).getSort()) > 15 && Integer.parseInt(indexAll.get(i).getSort()) < 23) {
                indexRS.add(indexAll.get(i));
            }
        }
        for (int i = 0; i < indexRS.size(); i++) {
            for (int j = 0; j < indexSelect.size(); j++) {
                if (indexRS.get(i).getTitle().equals(indexSelect.get(j).getTitle())) {
                    indexRS.get(i).setSelect(true);
                }
            }
        }
        index2.setChilds(indexRS);
        menuList.add(index2);

        MyHomeBean index3 = new MyHomeBean();
        index3.setTitle("业务管理");
        index3.setId("4");
        List<MyHomeBean> indexYW = new ArrayList<MyHomeBean>();
        for (int i = 0; i < indexAll.size(); i++) {
            if (Integer.parseInt(indexAll.get(i).getSort()) == 28) {
                indexYW.add(indexAll.get(i));
            }
        }
        for (int i = 0; i < indexYW.size(); i++) {
            for (int j = 0; j < indexSelect.size(); j++) {
                if (indexYW.get(i).getTitle().equals(indexSelect.get(j).getTitle())) {
                    indexYW.get(i).setSelect(true);
                }
            }
        }
        index3.setChilds(indexYW);
        menuList.add(index3);

        menuParentAdapter = new MenuParentAdapter(context, MenuManagerFragment.this, menuList);
        expandableListView.setAdapter(menuParentAdapter);

        for (int i = 0; i < menuParentAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                MyHomeBean cateModel = menuList.get(groupPosition);
                return true;
            }
        });
        expandableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tvEnd.getText().toString().equals("管理")) {
                    MyHomeBean cateModel = menuList.get(position);
                    initUrl(cateModel);
                }
            }
        });
        expandableListView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (tvEnd.getText().toString().equals("管理")) {
                    tvEnd.setText("完成");
                    adapterSelect.setEdit();
                    menuParentAdapter.setEdit();
                }
                return false;
            }
        });
    }

    public void initUrl(MyHomeBean cateModel) {
        // TODO Auto-generated method stub
        if (tvEnd.getText().toString().equals("管理")) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            String title = cateModel.getTitle();
            String strId = cateModel.getId();
            LatteLogger.d("testcateModel",cateModel.getTitle());
            if (title.equals("考勤管理")) {
                start(CheckInManager.newInstance(cateModel.getTitle(), ""));
            } else if (title.equals("考勤")) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("title",title);
                start(TotalQueryFragment.newInstance(bundle1));
            } else if (title.equals("请假")) {
                start(LeaveFragment.newInstance(cateModel.getTitle(), 0));
            } else if (title.equals("出差")) {
                start(LeaveFragment.newInstance(cateModel.getTitle(), 1));
            } else if (title.equals("外出")) {
                start(LeaveFragment.newInstance(cateModel.getTitle(), 2));
            } else if (title.equals("加班")) {
                start(OverTimeFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("补卡")) {
                start(CardQueryFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("审批")) {
                start(ApproveFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("派车")) {
                start(CarInfoFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("客户订餐")) {
                start(CustomCookerOrderFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("员工报餐")) {
                start(EmployeeWithOrderFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("委外加工")) {
                start(SubContractFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("维修")) {
                start(RepairFragment.newInstance(cateModel.getTitle()));
            } else if (title.contains("住宿")) {
                start(BuildFragment.newInstance(title));
            } else if (title.equals("审批查询")) {
                start(SearchFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("报餐统计")) {
                start(TotalOfMeatFragment.newInstance(cateModel.getTitle()));
            } else if (title.equals("车辆管理")) {
                start(CarInfoManager.newInstance(cateModel.getTitle()));
            }
        }
    }

    public void DelMenu(MyHomeBean indexData, int position) {
        for (int i = 0; i < menuList.size(); i++) {
            for (int j = 0; j < menuList.get(i).getChilds().size(); j++) {
                if (menuList.get(i).getChilds().get(j).getTitle().equals(indexData.getTitle())) {
                    menuList.get(i).getChilds().get(j).setSelect(false);
                }
            }
        }
        if (menuParentAdapter != null) {
            menuParentAdapter.notifyDataSetChanged();
        }
        adapterSelect.notifyDataSetChanged();
    }

    public static void AddMenu(MyHomeBean menuEntity) {
        // TODO Auto-generated method stub
        indexSelect.add(menuEntity);
        String key = AppConfig.KEY_USER_TEMP;
        appContext.saveObject((Serializable) indexSelect, key);

        for (int i = 0; i < menuList.size(); i++) {
            for (int k = 0; k < menuList.get(i).getChilds().size(); k++) {
                if (menuList.get(i).getChilds().get(k).getTitle().equals(menuEntity.getTitle())) {
                    menuList.get(i).getChilds().get(k).setSelect(true);
                }
            }
        }
        menuParentAdapter.notifyDataSetChanged();
        adapterSelect.notifyDataSetChanged();
    }

    private void postMenu() {
        List<MyHomeBean> indexDataList = (List<MyHomeBean>) appContext.readObject(AppConfig.KEY_USER_TEMP);
        appContext.saveObject((Serializable) indexDataList, AppConfig.KEY_USER);
    }

    @Override
    public boolean onBackPressedSupport() {
        EventBusUtil.sendEvent(new Event(ALL_BACK_HOME));
        return super.onBackPressedSupport();
    }
}










