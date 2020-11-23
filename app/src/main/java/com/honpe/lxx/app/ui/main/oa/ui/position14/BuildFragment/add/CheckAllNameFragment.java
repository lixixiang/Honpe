package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.add;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position14.add.bean.AllNameBean;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.SortAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.widget.PinyinComparator;
import com.honpe.lxx.app.widget.decoration.DividerItemDecoration;
import com.honpe.lxx.app.widget.decoration.SectionItemDecoration;
import com.honpe.lxx.app.widget.sideIndex.SideIndexBar;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.UPDATE_CHECK_IN_BED;

/**
 * FileName: CheckAllNameFragment
 * Author: asus
 * Date: 2020/9/21 16:35
 * Description: 添加宿舍人员名单
 */
public class CheckAllNameFragment extends BaseBackFragment implements TextWatcher {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cp_search_box)
    EditText mSearchBox;
    @BindView(R.id.cp_clear_all)
    ImageView mClearAllBtn;
    @BindView(R.id.cp_cancel)
    TextView cpCancel;
    @BindView(R.id.cp_overlay)
    TextView cpOverlay;
    @BindView(R.id.cp_side_index_bar)
    SideIndexBar mIndexBar;
    @BindView(R.id.cp_no_result_icon)
    ImageView cpNoResultIcon;
    @BindView(R.id.cp_no_result_text)
    TextView cpNoResultText;
    @BindView(R.id.cp_empty_view)
    LinearLayout mEmptyView;

    SortAdapter adapter;
    List<AllNameBean.InfoEntity> mList = new ArrayList<>();
    List<AllNameBean.InfoEntity> SaveDatas = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    /**
     * 根据拼音来排列RecyclerView里面的数据类
     */
    private PinyinComparator mComparator;
    private LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity;
    Bundle bundle = new Bundle();

    public static CheckAllNameFragment newInstance(LevelEntity.FloorEntity.RoomEntity.BedEntity floorDetailBean) {
        CheckAllNameFragment fragment = new CheckAllNameFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("floorDetailBean", floorDetailBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_check_all_name;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            bedEntity = (LevelEntity.FloorEntity.RoomEntity.BedEntity) bundle.getSerializable("floorDetailBean");
        }
        initToolbarNav(llBack);
        tvTitle.setText("添加宿舍人员姓名");
        mSearchBox.addTextChangedListener(this);
        mComparator = new PinyinComparator();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getEveryBody();
    }

    private void getEveryBody() {
        disposable = EasyHttp.post(Constants.AppAllStaff)
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
                        ToastUtil.getInstance().showToast(e.getMessage());
                        ProgressUtils.disLoadView(_mActivity,0);
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("getEveryBody", s);
                        String st = s.replace("\\", "").replace("\"info\":\"", "\"info\":")
                                .replace("]\"", "]");

                        AllNameBean allNameBean = GsonBuildUtil.NullToString().fromJson(st, AllNameBean.class);
                        LatteLogger.d("getEveryBody", GsonBuildUtil.GsonBuilder(allNameBean));
                        mList.addAll(allNameBean.getInfo());

                        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setHasFixedSize(true);

                        // 根据a-z进行排序源数据
                        Collections.sort(mList, mComparator);
                        SaveDatas.addAll(mList);
                        recyclerView.addItemDecoration(new SectionItemDecoration(_mActivity, mList), 0);
                        recyclerView.addItemDecoration(new DividerItemDecoration(_mActivity), 1);
                        recyclerView.setLayoutManager(mLayoutManager);
                        adapter = new SortAdapter(mList);
                        adapter.setLayoutManager(mLayoutManager);
                        adapter.addChildClickViewIds(R.id.re);

                        recyclerView.setAdapter(adapter);
                        mIndexBar.setNavigationBarHeight(Utils.getNavigationBarHeight(getActivity()));
                        mIndexBar.setOverlayTextView(cpOverlay)
                                .setOnIndexChangedListener(new SideIndexBar.OnIndexTouchedChangedListener() {
                                    @Override
                                    public void onIndexChanged(String index, int position) {
                                        //滚动RecyclerView到索引位置
                                        adapter.scrollToSection(index);
                                    }
                                });
                        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                    adapter.refreshLocationItem();
                                }
                                hideSoftKeyBoard();
                            }
                        });
                        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                                getJsonData(mList.get(position));
                                hideSoftKeyBoard();
                            }
                        });
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
        filterData(s.toString());

    }


    @Override
    public void afterTextChanged(Editable s) {

    }

    int length;

    /**
     * 根据输入框中的值来过滤数据并更新RecyclerView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<AllNameBean.InfoEntity> filterDateList = new ArrayList<>();
        if (filterStr.length() == 0) {
            mList.clear();
            filterDateList.clear();
            mList.addAll(SaveDatas);
            LatteLogger.d("testmList", GsonBuildUtil.GsonBuilder(mList));
            filterDateList.addAll(mList);
            mClearAllBtn.setVisibility(View.GONE);
        } else {
            if (length > filterStr.length()) {
                mList.clear();
                mList.addAll(SaveDatas);
            }
            length = filterStr.length();
            mClearAllBtn.setVisibility(View.VISIBLE);
            for (AllNameBean.InfoEntity sortModel : mList) {
                String name = sortModel.getF_realname();
                if (name.indexOf(filterStr.toString()) != -1 ||
                        StringUtil.getFirstSpell(name).startsWith(filterStr.toString())
                        //不区分大小写
                        || StringUtil.getFirstSpell(name).toLowerCase().startsWith(filterStr.toString())
                        || StringUtil.getFirstSpell(name).toUpperCase().startsWith(filterStr.toString())
                        || sortModel.getF_encode().contains(filterStr.toString())
                ) {
                    filterDateList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(filterDateList, mComparator);
        mList.clear();
        mList.addAll(filterDateList);
        adapter.updateData(mList);
    }

    @OnClick({R.id.cp_cancel, R.id.cp_clear_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cp_clear_all:
                mSearchBox.setText("");
                break;
        }
    }

    private void getJsonData(AllNameBean.InfoEntity userInfo) {
      disposable =  EasyHttp.post(Constants.AppExistDormitoryUser)
                .retryCount(0)
                .params("EmployeeId",EmployeeId)
                .params("strUserID",userInfo.getF_userid())
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
                        LatteLogger.d("getJsonData",s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getJSONArray("data").length() == 0) {
                                final NormalDialog dialog1 = new NormalDialog(_mActivity);
                                dialog1.isTitleShow(false)
                                        .content("是否要添加" + "\"" + userInfo.getF_realname() + "\"" + "的床位?")
                                        .cornerRadius(5)//
                                        .contentGravity(Gravity.CENTER)
                                        .btnTextColor(Color.RED, Color.BLUE)
                                        .btnPressColor(Color.LTGRAY)
                                        .show();
                                dialog1.setOnBtnClickL(new OnBtnClickL() {
                                    @Override
                                    public void onBtnClick() { //left
                                        dialog1.dismiss();
                                    }
                                }, new OnBtnClickL() {
                                    @Override
                                    public void onBtnClick() {//right
                                        getAddCheckInFor(userInfo);
                                        dialog1.dismiss();
                                    }
                                });
                            }else {
                                ToastUtil.getInstance().showToast(userInfo.getF_realname() + "已经成功入住宿舍");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getAddCheckInFor(AllNameBean.InfoEntity userInfo) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("EmployeeId",EmployeeId);
        jsonObject.addProperty("strF_ID",bedEntity.getF_id());
        jsonObject.addProperty("strUserID",userInfo.getF_userid());

        LatteLogger.d("AllNameBean",GsonBuildUtil.GsonBuilder(jsonObject));

        EasyHttp.post(Constants.AppSaveUser)
                .retryCount(0)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("getAddCheckInFor",s);
                        try {
                            bedEntity.setDept_dep(userInfo.getF_departmentname());
                            bedEntity.setUserid(userInfo.getF_userid());
                            bedEntity.setEmp_usernum(userInfo.getF_encode());
                            if (userInfo.getEmp_usersex().equals("男")) {
                                bedEntity.setEmp_usersex("男");
                            }else {
                                bedEntity.setEmp_usersex("女");
                            }
                            bedEntity.setEmp_username(userInfo.getF_realname());
                            bundle.putSerializable("bundle",bedEntity);
                            Event<Bundle> bundleEvent = new Event<Bundle>(UPDATE_CHECK_IN_BED,bundle);
                            EventBusUtil.sendEvent(bundleEvent);

                            JSONObject o = new JSONObject(s);
                            ToastUtil.getInstance().showToast(o.getString("info"));
                            _mActivity.onBackPressed();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}





