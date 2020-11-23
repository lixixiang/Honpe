package com.honpe.lxx.app.ui.main.oa;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.AppConfig;
import com.honpe.lxx.app.api.AppConfig_XZ;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.fragment.a_package.menu.MenuManagerFragment;
import com.honpe.lxx.app.ui.fragment.d_package.bean.LoginBean;
import com.honpe.lxx.app.ui.fragment.d_package.myScan.MyCordFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
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
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.honpe.lxx.app.api.FinalClass.DEPART_NAME;
import static com.honpe.lxx.app.api.FinalClass.ENCODE;
import static com.honpe.lxx.app.api.FinalClass.EXIT_APP;
import static com.honpe.lxx.app.api.FinalClass.F_Name;
import static com.honpe.lxx.app.api.FinalClass.HeadIcon;
import static com.honpe.lxx.app.api.FinalClass.ME_info;
import static com.honpe.lxx.app.api.FinalClass.UserName;
import static com.honpe.lxx.app.api.FinalClass.UserType;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 14:36
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class OAMainFragment extends BaseBackFragment {
    @BindView(R.id.et_search_text)
    EditText etSearchText;
    @BindView(R.id.ib_searchtext_delete)
    ImageView ibSearchtextDelete;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.home_logo)
    ImageView homeLogo;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<OABean> list = new ArrayList<>();
    String headIcon;
    //人事管理
    private String[] titles0 = {"考勤", "请假", "出差", "外出", "加班", "䃼卡", "审批", "审批查询"};
    //行政管理
    private String[] titles1 = {"派车", "维修", "住宿", "客户订餐", "员工报餐",
            "用餐统计", "车辆管理"};
    //业务管理
    private String[] titles3 = {"委外加工"};

    public String[] titles = {"人事管理", "行政管理", "业务管理"};
    public int[] OAIcons = {R.mipmap.ic_oa1, R.mipmap.ic_oa2, R.mipmap.ic_oa3, R.mipmap.ic_oa4, R.mipmap.ic_oa5,
            R.mipmap.ic_oa6, R.mipmap.ic_oa7, R.mipmap.ic_oa8};
    public int[] XZIcons = {R.mipmap.ic_xz1, R.mipmap.ic_xz2, R.mipmap.more7, R.mipmap.ic_xz4, R.mipmap.ic_xz5,
            R.mipmap.ic_xz6, R.mipmap.ic_xz7};
    public int[] CGIcons = {R.mipmap.ic_cg2};

    private OAdapter adapter;
    private int mUserType;
    private String userName, strEnCord, strDepartName, strPost;
    private static MyApplication appContext;

    public static OAMainFragment newInstance() {
        OAMainFragment fragment = new OAMainFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.oa_fragment;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        Utils.checkSession(this, session);
        appContext = (MyApplication) _mActivity.getApplication();

        GridLayoutManager manager = new GridLayoutManager(_mActivity, 5);
        recyclerView.setLayoutManager(manager);
        list = (ArrayList<OABean>) appContext.readObject(AppConfig_XZ.KEY_USER_TEMP);
        LatteLogger.d("onEnterAnimationEnd", GsonBuildUtil.GsonBuilder(list));

        if (list == null) {
            list = new ArrayList<>();
            list = getNormalMultipleEntities();
        }
        adapter = new OAdapter(list);
        adapter.setGridSpanSizeLookup(new GridSpanSizeLookup() {
            @Override
            public int getSpanSize(@NonNull GridLayoutManager gridLayoutManager, int viewType, int position) {
                int type = list.get(position).getType();
                if (type == OABean.SINGLE_TEXT) {
                    return 5;
                } else {
                    return 1;
                }
            }
        });
        adapter.getDraggableModule().setDragEnabled(true);
        adapter.getDraggableModule().setOnItemDragListener(listener);
        adapter.getDraggableModule().getItemTouchHelperCallback().setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LatteLogger.d("setOnItemClickListener", position);
                if (list.get(position).getTitle().equals("考勤管理")) {
                    start(CheckInManager.newInstance(list.get(position).getTitle(), ""));
                } else if (list.get(position).getTitle().equals("考勤")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title",list.get(position).getTitle());
                    start(TotalQueryFragment.newInstance(bundle));
                } else if (list.get(position).getTitle().equals("请假")) {
                    start(LeaveFragment.newInstance(list.get(position).getTitle(), 0));
                } else if (list.get(position).getTitle().equals("出差")) {
                    start(LeaveFragment.newInstance(list.get(position).getTitle(), 1));
                } else if (list.get(position).getTitle().equals("外出")) {
                    start(LeaveFragment.newInstance(list.get(position).getTitle(), 2));
                } else if (list.get(position).getTitle().equals("加班")) {
                    start(OverTimeFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("补卡")) {
                    start(CardQueryFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("审批")) {
                    start(ApproveFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("派车")) {
                    start(CarInfoFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("客户订餐")) {
                    start(CustomCookerOrderFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("员工报餐")) {
                    start(EmployeeWithOrderFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("委外加工")) {
                    start(SubContractFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("维修")) {
                    start(RepairFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().contains("住宿")) {
                    start(BuildFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("审批查询")) {
                    start(SearchFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("报餐统计")) {
                    start(TotalOfMeatFragment.newInstance(list.get(position).getTitle()));
                } else if (list.get(position).getTitle().equals("车辆管理")) {
                    start(CarInfoManager.newInstance(list.get(position).getTitle()));
                }
            }
        });
    }

    @Override
    protected void initView() {
        headIcon = (String) MyApplication.get(_mActivity, HeadIcon, "");
        Utils.headIcon(_mActivity, headIcon, ivCircle);
        userName = (String) MyApplication.get(_mActivity, UserName, "");
        mUserType = (int) MyApplication.get(_mActivity, UserType, -1);
        strEnCord = (String) MyApplication.get(_mActivity, ENCODE, "");
        strDepartName = (String) MyApplication.get(_mActivity, DEPART_NAME, "");
        strPost = (String) MyApplication.get(_mActivity, F_Name, "");
        homeLogo.setVisibility(View.GONE);
        llBack.setVisibility(View.VISIBLE);
        title.setText(getString(R.string.text_home));
        initToolbarNav(llBack);
    }

    private ArrayList<OABean> getNormalMultipleEntities() {
        for (int i = 0; i < titles.length; i++) {
            list.add(new OABean(OABean.SINGLE_TEXT, titles[i]));
            if (i == 0) {
                for (int j = 0; j < OAIcons.length; j++) {
                    list.add(new OABean(OABean.TEXT_IMG, OAIcons[j], titles0[j]));
                }

            } else if (i == 1) {
                for (int j = 0; j < XZIcons.length; j++) {
                    list.add(new OABean(OABean.TEXT_IMG, XZIcons[j], titles1[j]));
                }
            } else {
                for (int j = 0; j < CGIcons.length; j++) {
                    list.add(new OABean(OABean.TEXT_IMG, CGIcons[j], titles3[j]));
                }
            }
        }
        return list;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        //
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case ME_info: //登录回来得到新的session
                LoginBean loginBean = (LoginBean) event.getData();
                session = loginBean.getSessionKey();
                headIcon = loginBean.getLogonUser().getIco();
                LatteLogger.d("session=======", session);
                Utils.headIcon(_mActivity, headIcon, ivCircle);
                break;
            case EXIT_APP:
                ivCircle.setImageResource(R.drawable.health_guide_men_selected);
                break;

        }
    }

    @OnClick({R.id.et_search_text, R.id.ib_searchtext_delete, R.id.iv_circle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_search_text:
                break;
            case R.id.ib_searchtext_delete:
                break;
            case R.id.iv_circle:
                if ("".equals(session)) {
                    start(LoginFragment.newInstance(""));
                } else {
                    if (mUserType != 1)
                        start(MyCordFragment.newInstance("个人信息", strEnCord, strDepartName, headIcon, userName));
                }
                break;
        }
    }

    // 拖拽监听
    OnItemDragListener listener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "drag start");
            final BaseViewHolder holder = ((BaseViewHolder) viewHolder);

            // 开始时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            int startColor = Color.WHITE;
            int endColor = Color.rgb(245, 245, 245);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ValueAnimator v = ValueAnimator.ofArgb(startColor, endColor);
                v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        holder.itemView.setBackgroundColor((int) animation.getAnimatedValue());
                    }
                });
                v.setDuration(300);
                v.start();
            }
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "drag end");
            final BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            // 结束时，item背景色变化，demo这里使用了一个动画渐变，使得自然
            int startColor = Color.rgb(245, 245, 245);
            int endColor = Color.WHITE;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ValueAnimator v = ValueAnimator.ofArgb(startColor, endColor);
                v.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        holder.itemView.setBackgroundColor((int) animation.getAnimatedValue());
                    }
                });
                postMenu();
                v.setDuration(300);
                v.start();
            }
        }
    };

    private void postMenu() {
        LatteLogger.d("testNewData", GsonBuildUtil.GsonBuilder(list));
        if (list != null || list.size() > 0) {
            appContext.saveObject((Serializable) list, AppConfig_XZ.KEY_USER_TEMP);
        }
    }
}











