package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.adapter.ExpandableItemAdapter;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean.GroupBean;
import com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean.TreeBean;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.COME_BACK_ALL_DEPART;
import static com.honpe.lxx.app.api.FinalClass.SEARCH_DEPART_DETAIL;
import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * FileName: SelectorDepartFragment
 * Author: asus
 * Date: 2020/8/26 11:43
 * Description: 考勤进来时先选择部门
 */
public class SelectorDepartFragment extends BaseBackFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerview;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ExpandableItemAdapter adapter;
    private String session;
    private int flag;
    List<BaseNode> res = new ArrayList<>();

    public static SelectorDepartFragment newInstance(int flag) {
        SelectorDepartFragment fragment = new SelectorDepartFragment();
        fragment.flag = flag;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("选择部门或组别");
        session = (String) MyApplication.get(_mActivity, Session, "");

    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        DepartmentOfGroup(session);
    }

    //部门分组
    public void DepartmentOfGroup(String session) {
        LatteLogger.d("testData", Constants.DEPART_list + session);
        EasyHttp.post(Constants.DEPART_list + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        GroupBean bean = GsonBuildUtil.NullToString().fromJson(s, GroupBean.class);
                        LatteLogger.d("GsonDate", GsonBuildUtil.GsonBuilder(bean));

                        if (bean.getStatus() == 0) {

                            adapter = new ExpandableItemAdapter();
                            adapter.setList(getMethod(bean));
                            recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));

                            recyclerview.setAdapter(adapter);
                            adapter.addHeaderView(getHeaderView(), 0);
                        } else {
                            ToastUtil.getInstance().showToast(bean.getMsg());
                        }
                    }
                });

    }

    private List<BaseNode> getMethod(GroupBean bean) {

        for (int i = 0; i < bean.getData().size(); i++) {
            if ("0".equals(bean.getData().get(i).getParentID())) {
                TreeBean treeBean = new TreeBean();
                treeBean.setDeptName(bean.getData().get(i).getDeptName());
                treeBean.setDeptCode(bean.getData().get(i).getDeptCode());
                treeBean.setDeptGm(bean.getData().get(i).getDeptGm());
                treeBean.setDeptID(bean.getData().get(i).getDeptID());
                treeBean.setDeptLevel(bean.getData().get(i).getDeptLevel());
                treeBean.setParentID(bean.getData().get(i).getParentID());

                List<BaseNode> secondNodeList = new ArrayList<>();
                for (int j = 0; j < bean.getData().size(); j++) {
                    if (treeBean.getDeptCode().equals(StringUtil.deleteLastStr(bean.getData().get(j).getDeptCode(), 2))) {
                        if (bean.getData().get(j).getParentID().equals(treeBean.getDeptID())) {
                            TreeBean.TreeBean2 treeBean2 = new TreeBean.TreeBean2();
                            treeBean2.setDeptName(bean.getData().get(j).getDeptName());
                            treeBean2.setDeptCode(bean.getData().get(j).getDeptCode());
                            treeBean2.setDeptGm(bean.getData().get(j).getDeptGm());
                            treeBean2.setDeptID(bean.getData().get(j).getDeptID());
                            treeBean2.setDeptLevel(bean.getData().get(j).getDeptLevel());
                            treeBean2.setParentID(bean.getData().get(j).getParentID());

                            List<BaseNode> thirdNodeList = new ArrayList<>();
                            for (int k = 0; k < bean.getData().size(); k++) {
                                if (treeBean.getDeptCode().equals(StringUtil.deleteLastStr(bean.getData().get(k).getDeptCode(), 4))) {
                                    if (bean.getData().get(k).getParentID().equals(treeBean2.getDeptID())) {
                                        TreeBean.TreeBean2.TreeBean3 treeBean3 = new TreeBean.TreeBean2.TreeBean3();
                                        treeBean3.setDeptName(bean.getData().get(k).getDeptName());
                                        treeBean3.setDeptCode(bean.getData().get(k).getDeptCode());
                                        treeBean3.setDeptGm(bean.getData().get(k).getDeptGm());
                                        treeBean3.setDeptID(bean.getData().get(k).getDeptID());
                                        treeBean3.setDeptLevel(bean.getData().get(k).getDeptLevel());
                                        treeBean3.setParentID(bean.getData().get(k).getParentID());
                                        thirdNodeList.add(treeBean3);
                                        treeBean2.setChildNode(thirdNodeList);
                                    }
                                }
                            }
                            secondNodeList.add(treeBean2);
                            treeBean.setChildNode(secondNodeList);
                        }
                    }
                }

                res.add(treeBean);
            }
        }
        return res;
    }

    private View getHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.css_text_1, (ViewGroup) recyclerview.getParent(), false);
        TextView tv = view.findViewById(R.id.tv_record_text);
        tv.setText(StringUtil.changeFontColor("点击这里显示所有部门人员", R.color.google_red, 0, 4));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Event<String> event = new Event<String>(FinalClass.COME_BACK_ALL_DEPART, "");
                EventBusUtil.sendEvent(event);
            }
        });
        return view;
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case COME_BACK_ALL_DEPART:
                String departId = (String) event.getData();
                LatteLogger.d("departId",departId);
                Event<String> event1 = new Event<String>(SEARCH_DEPART_DETAIL, departId);
                EventBusUtil.sendEvent(event1);
                _mActivity.onBackPressed();
                break;
        }
    }
}

























