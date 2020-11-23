package com.honpe.lxx.app.ui.fragment.a_package;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.flyco.dialog.widget.NormalListDialog;
import com.google.android.material.appbar.AppBarLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.honpe.lxx.app.MainFragment;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.AppConfig;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseMainFragment;
import com.honpe.lxx.app.bean.MyHomeBean;
import com.honpe.lxx.app.bean.NewsBean;
import com.honpe.lxx.app.bean.UpdateBean;
import com.honpe.lxx.app.ui.adapter.MyHomeAdapter;
import com.honpe.lxx.app.ui.fragment.a_package.adapter.MyHomeGridNewsAdapter;
import com.honpe.lxx.app.ui.fragment.a_package.adapter.MyHomeGridNewsAdapter2;
import com.honpe.lxx.app.ui.fragment.a_package.menu.MenuManagerFragment;
import com.honpe.lxx.app.ui.fragment.a_package.news.DetailContentFragment;
import com.honpe.lxx.app.ui.fragment.a_package.news.DetailNewsFragment;
import com.honpe.lxx.app.ui.fragment.d_package.bean.LoginBean;
import com.honpe.lxx.app.ui.fragment.d_package.myScan.MyCordFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.ui.main.appoint.AppointHomeFragment;
import com.honpe.lxx.app.ui.main.logistics.LogisticsCheckFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.CheckInManager;
import com.honpe.lxx.app.ui.main.oa.OAMainFragment;
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
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.Utils;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.BaseGridView;
import com.honpe.lxx.app.widget.RxTextViewVerticalMore;
import com.honpe.lxx.app.widget.ScanManager;
import com.honpe.lxx.app.widget.dialog.UpdateDialog;
import com.honpe.lxx.app.widget.video.SampleCoverVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.QrConfig;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.disposables.Disposable;

import static com.honpe.lxx.app.api.FinalClass.ALL_BACK_HOME;
import static com.honpe.lxx.app.api.FinalClass.DEPART_NAME;
import static com.honpe.lxx.app.api.FinalClass.ENCODE;
import static com.honpe.lxx.app.api.FinalClass.EXIT_APP;
import static com.honpe.lxx.app.api.FinalClass.F_Name;
import static com.honpe.lxx.app.api.FinalClass.HeadIcon;
import static com.honpe.lxx.app.api.FinalClass.ME_info;
import static com.honpe.lxx.app.api.FinalClass.REQUEST_CODE_APP_INSTALL;
import static com.honpe.lxx.app.api.FinalClass.UPDATA_CLIENT;
import static com.honpe.lxx.app.api.FinalClass.UserName;
import static com.honpe.lxx.app.api.FinalClass.UserType;
import static com.honpe.lxx.app.api.FinalClass.video_status;
import static com.honpe.lxx.app.utils.Utils.getFontSize;

/**
 * created by lxx at 2020/4/6 5:43
 * 描述:
 */
public class MyHomeFragment extends BaseMainFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.et_search_text)
    EditText etSearchText;
    @BindView(R.id.ib_searchtext_delete)
    ImageView ibSearchtextDelete;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    @BindView(R.id.ll_scan)
    LinearLayout llScan;
    @BindView(R.id.ll_order)
    LinearLayout llOrder;
    @BindView(R.id.ll_Logistics)
    LinearLayout llLogistics;
    @BindView(R.id.ll_way)
    LinearLayout llWay;
    @BindView(R.id.iv_way)
    ImageView ivWay;
    @BindView(R.id.abl_bar)
    AppBarLayout ablBar;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.tv_oa1)
    TextView tvOa1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.tv_oa2)
    TextView tvOa2;
    @BindView(R.id.ll_ISO)
    LinearLayout llISO;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.tv_oa3)
    TextView tvOa3;
    @BindView(R.id.ll_JP)
    LinearLayout llJP;
    @BindView(R.id.iv_4)
    ImageView iv4;
    @BindView(R.id.tv_oa4)
    TextView tvOa4;
    @BindView(R.id.ll_CN)
    LinearLayout llCN;
    @BindView(R.id.iv_5)
    ImageView iv5;
    @BindView(R.id.tv_oa5)
    TextView tvOa5;
    @BindView(R.id.ll_CF)
    LinearLayout llCF;
    @BindView(R.id.ll_head_icon)
    LinearLayout llHeadIcon;
    @BindView(R.id.sub_grid_view)
    GridView subGridView;
    @BindView(R.id.up_view)
    RxTextViewVerticalMore upView;
    @BindView(R.id.activity_play)
    LinearLayout activityPlay;
    @BindView(R.id.gridView_2)
    BaseGridView gridView2;
    @BindView(R.id.gridView_3)
    BaseGridView gridView3;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.iv_order)
    ImageView ivOrder;
    @BindView(R.id.iv_Logistics)
    ImageView ivLogistics;
    @BindView(R.id.video_player)
    SampleCoverVideo gsyPlay;
    @BindView(R.id.iv_video_bg)
    ImageView ivVideoBg;
    @BindView(R.id.iv_video_honpe)
    ImageView ivVideoHonpe;
    @BindView(R.id.ll_company_news)
    LinearLayout llCompanyNews;
    @BindView(R.id.ll_trade_news)
    LinearLayout llTradeNews;

    private String[] StrScans;
    private List<MyHomeBean> indexDataList = new ArrayList<>();
    private List<MyHomeBean> indexDataAll = new ArrayList<>();
    MyHomeGridNewsAdapter gridNewsAdapter;
    MyHomeGridNewsAdapter2 gridNewsAdapter2;
    private String language, headIcon, userName, strEnCord, strDepartName, strPost;
    private NewsBean bean, bean2; //版本更新实体
    private static MyApplication appContext;
    private int mUserType;
    MyHomeAdapter adapter;
    Disposable disposable;

    public static MyHomeFragment newInstance() {
        MyHomeFragment fragment = new MyHomeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        appContext = (MyApplication) _mActivity.getApplication();
        Utils.NewVersionRequest(_mActivity);//检测版本是否一致
        initImageView();
        initSubMenu();
        initRunTextView();
        initVideo();
        initNews();
    }


    /**
     * 企业新闻
     */
    private void initNews() {
        if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
            language = "CN";
        } else if (getResources().getConfiguration().locale.getCountry().equals("US")) {
            language = "en";
        } else {
            language = "jp";
        }

        disposable = EasyHttp.post(Constants.NEWS_URL)
                .params("top", "0")
                .params("newstype", "868")
                .params("language", language)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {
                        LatteLogger.d("ApiException", e.getCode());
                        if (e.getCode() == 1009) {
                            ToastUtil.getInstance().showToast("网络连接失败，请连接网络再试...");
                        } else {

                        }
                    }

                    @Override
                    public void onSuccess(String s) {
                        bean = Convert.fromJson(s, NewsBean.class);
                        if (bean.getStatus() == 0) {
                            llCompanyNews.setVisibility(View.VISIBLE);
                            gridNewsAdapter = new MyHomeGridNewsAdapter(_mActivity, getGridView(bean.getData().getRows()));
                            gridView2.setAdapter(gridNewsAdapter);
                            gridView2.setOnItemClickListener((parent, view, position, id) -> {
                                Event<Integer> event2 = new Event<Integer>(video_status, 0);
                                EventBusUtil.sendEvent(event2);
                                // 通知MainFragment跳转至NewFeatureFragment
                                String ids = bean.getData().getRows().get(position).getId() + "";
                                if (TextUtils.isEmpty(ids)) {
                                    ToastUtil.getInstance().showToast("id为空");
                                } else {
                                    //联系方式
                                    ((MainFragment) getParentFragment()).startBrotherFragment(DetailContentFragment.newInstance(getString(R.string.news_details), ids, ""));
                                }
                            });
                        } else {
                            llCompanyNews.setVisibility(View.GONE);
                            ToastUtil.getInstance().showToast(bean.getMsg());
                        }
                    }
                });

        disposable = EasyHttp.post(Constants.NEWS_URL)
                .params("top", "0")
                .params("newstype", "867")
                .params("language", language)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();

                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();

                    }

                    @Override
                    public void onError(ApiException e) {
                        LatteLogger.d("ApiException", e.getCode());
                        if (e.getCode() == 1009) {
                            ToastUtil.getInstance().showToast("网络连接失败，请连接网络再试...");
                        } else {

                        }
                    }

                    @Override
                    public void onSuccess(String s) {
                        bean2 = Convert.fromJson(s, NewsBean.class);
                        if (bean2.getStatus() == 0) {
                            llTradeNews.setVisibility(View.VISIBLE);
                            LatteLogger.d("onSuccess", GsonBuildUtil.GsonBuilder(bean2));
                            gridNewsAdapter2 = new MyHomeGridNewsAdapter2(_mActivity, getGridView2(bean2.getData().getRows()));
                            gridView3.setAdapter(gridNewsAdapter2);
                            gridView3.setOnItemClickListener((parent, view, position, id) -> {
                                Event<Integer> event2 = new Event<Integer>(video_status, 0);
                                EventBusUtil.sendEvent(event2);
                                // 通知MainFragment跳转至NewFeatureFragment
                                String ids = bean2.getData().getRows().get(position).getId() + "";
                                if (TextUtils.isEmpty(ids)) {
                                    ToastUtil.getInstance().showToast("id为空");
                                } else {
                                    ((MainFragment) getParentFragment()).startBrotherFragment(DetailContentFragment.newInstance(getString(R.string.news_details), ids, ""));
                                }
                            });
                        } else {
                            llCompanyNews.setVisibility(View.GONE);
                            ToastUtil.getInstance().showToast(bean2.getMsg());
                        }
                    }
                });
    }

    private List<NewsBean.DataBean.RowsBean> getGridView2(List<NewsBean.DataBean.RowsBean> bean) {
        String[] pics = {"http://5b0988e595225.cdn.sohucs.com/images/20190306/5cd36324cfcd4d3d9c9fabcd00843f55.jpeg"
                , "http://www.pvc-mold.cn/Content/ueditor-builder/net/upload1/Other/12341/6368332769061300088598793.jpg",
                "http://p3.pstatp.com/large/pgc-image/152144390869599ea1f72ed"
        };

        List<NewsBean.DataBean.RowsBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            NewsBean.DataBean.RowsBean item = new NewsBean.DataBean.RowsBean();
            item.setPicUrl(pics[i]);
            item.setTitle(bean.get(i).getTitle());
            item.setAddTime(bean.get(i).getAddTime());
            list.add(item);
        }
        return list;
    }

    /**
     * 做视频
     */
    private void initVideo() {
        String source1 = "http://www.honpe.com:8001/201911/2019112603125462713.mp4";
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) LinearLayout.LayoutParams.MATCH_PARENT);
        gsyPlay.setLayoutParams(lp);
        gsyPlay.setUp(source1, true, "Honpe视频");
        ImageView imageView = new ImageView(_mActivity);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //增加封面
        imageView.setImageResource(R.drawable.video_cover);
        gsyPlay.setThumbImageView(imageView);
        gsyPlay.getTitleTextView().setVisibility(View.GONE);
        gsyPlay.getTitleTextView().setVisibility(View.VISIBLE);
        gsyPlay.getBackButton().setVisibility(View.GONE);
        gsyPlay.getTitleTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, getFontSize(35));

        gsyPlay.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsyPlay.startWindowFullscreen(getContext(), true, true);
            }
        });
        //防止错位设置
        gsyPlay.setPlayTag(TAG);
        //是否可以滑动调整
        gsyPlay.setIsTouchWiget(true);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        gsyPlay.setAutoFullWithSize(true);
        //音频焦点冲突时是否释放
        gsyPlay.setReleaseWhenLossAudio(false);
        //全屏动画
        gsyPlay.setShowFullAnimation(true);
        //小屏时不触摸滑动
        gsyPlay.setIsTouchWiget(false);
    }

    /**
     * 上移的文字消息
     */
    private void initRunTextView() {
        List<View> views = new ArrayList<>();
        setUPMarqueeView(views, 11);
        upView.setViews(views);
    }

    private void setUPMarqueeView(List<View> views, int size) {
        for (int i = 0; i < size; i++) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.css_text_run, null);
            //初始化布局的控件
            TextView tv1 = moreView.findViewById(R.id.tv1);
            TextView tv2 = moreView.findViewById(R.id.tv2);
            TextView titleTv1 = moreView.findViewById(R.id.title_tv1);
            TextView titleTv2 = moreView.findViewById(R.id.title_tv2);
            moreView.findViewById(R.id.rl1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mActivity, "测试点击反应", Toast.LENGTH_SHORT).show();
                }
            });

            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mActivity, "测试点击反应", Toast.LENGTH_SHORT).show();
                }
            });
            titleTv1.setText("国内业务");
            titleTv2.setText("管理中心");
            //进行对控件赋值
            tv1.setText("消息提醒模版1。");
            tv2.setText("消息提醒模版2");

            //进行对控件赋值
            if (size > i + 1) {
                tv1.setText("中午十点国内重要客人来访通知请注意做好接代，提前做好准备工作。");
                tv2.setText("关于取消员工车辆停放资格通知，请各部门认真执行以下文件");
            } else {
                moreView.findViewById(R.id.rl1).setVisibility(View.GONE);
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }
            views.add(moreView);
        }
    }

    /**
     * 子菜单列表初始化
     */
    private void initSubMenu() {
        StrScans = _mActivity.getResources().getStringArray(R.array.scan);
        //初始化
        JsonParser parser = new JsonParser();
        String strByJson = GsonBuildUtil.getCityJson(_mActivity, "menulist");
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();
        Gson gson = new Gson();
        //加强for循环遍历JsonArray
        for (JsonElement indexArr : jsonArray) {
            //使用GSON，直接转成Bean对象
            MyHomeBean menuEntity = gson.fromJson(indexArr, MyHomeBean.class);
            indexDataAll.add(menuEntity);
        }
        appContext.saveObject((Serializable) indexDataAll, AppConfig.KEY_All);
        List<MyHomeBean> indexDataUser = (List<MyHomeBean>) appContext.readObject(AppConfig.KEY_USER);
        if (indexDataUser == null || indexDataUser.size() == 0) {
            appContext.saveObject((Serializable) indexDataAll, AppConfig.KEY_USER);
        }

        if (appContext.readObject(AppConfig.KEY_BACK_NEW_DATA) != null) {
            indexDataList = (List<MyHomeBean>) appContext.readObject(AppConfig.KEY_BACK_NEW_DATA);
        } else {
            for (int i = 0; i < indexDataAll.size(); i++) {
                if (i < 7) {
                    indexDataList.add(indexDataAll.get(i));
                }
            }
            MyHomeBean allMenuEntity = new MyHomeBean();
            allMenuEntity.setIco("ic_small_h");
            allMenuEntity.setId("all");
            allMenuEntity.setEnable(true);
            allMenuEntity.setTitle("更多");
            indexDataList.add(allMenuEntity);
        }
        LatteLogger.d("jsonArray", GsonBuildUtil.GsonBuilder(indexDataList));
        adapter = new MyHomeAdapter(_mActivity, indexDataList);
        subGridView.setAdapter(adapter);
        subGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (indexDataList.get(position).getTitle().equals("更多")) {
                    indexDataList.remove(indexDataList.size() - 1);
                    appContext.saveObject((Serializable) indexDataList, AppConfig.KEY_USER);
                    ((MainFragment) getParentFragment()).startBrotherFragment(MenuManagerFragment.newInstance());
                } else if (indexDataList.get(position).getTitle().equals("考勤管理")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (CheckInManager.newInstance(indexDataList.get(position).getTitle(), ""));
                } else if (indexDataList.get(position).getTitle().equals("考勤")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title",indexDataList.get(position).getTitle());
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (TotalQueryFragment.newInstance(bundle));
                } else if (indexDataList.get(position).getTitle().equals("请假")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (LeaveFragment.newInstance(indexDataList.get(position).getTitle(), 0));
                } else if (indexDataList.get(position).getTitle().equals("出差")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (LeaveFragment.newInstance(indexDataList.get(position).getTitle(), 1));
                } else if (indexDataList.get(position).getTitle().equals("外出")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (LeaveFragment.newInstance(indexDataList.get(position).getTitle(), 2));
                } else if (indexDataList.get(position).getTitle().equals("加班")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (OverTimeFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("补卡")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (CardQueryFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("审批")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (ApproveFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("派车")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (CarInfoFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("客户订餐")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (CustomCookerOrderFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("员工报餐")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (EmployeeWithOrderFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("委外加工")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (SubContractFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("维修")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (RepairFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().contains("住宿")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (BuildFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("审批查询")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (SearchFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("报餐统计")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (TotalOfMeatFragment.newInstance(indexDataList.get(position).getTitle()));
                } else if (indexDataList.get(position).getTitle().equals("车辆管理")) {
                    ((MainFragment) getParentFragment()).startBrotherFragment
                            (CarInfoManager.newInstance(indexDataList.get(position).getTitle()));
                }
            }
        });
    }

    /**
     * 将图标颜色转白色
     */
    private void initImageView() {
        headIcon = (String) MyApplication.get(_mActivity, HeadIcon, "");
        Utils.headIcon(_mActivity, headIcon, ivCircle);
        ivScan.setColorFilter(getResources().getColor(R.color.white));
        ivOrder.setColorFilter(getResources().getColor(R.color.white));
        ivLogistics.setColorFilter(getResources().getColor(R.color.white));
        ivWay.setColorFilter(getResources().getColor(R.color.white));
        ivVideoBg.setColorFilter(getResources().getColor(R.color.blue_dark));
        iv2.setColorFilter(getResources().getColor(R.color.alpha_gray));
        tvOa2.setTextColor(getResources().getColor(R.color.grey_home));
        iv3.setColorFilter(getResources().getColor(R.color.alpha_gray));
        tvOa3.setTextColor(getResources().getColor(R.color.grey_home));
        iv4.setColorFilter(getResources().getColor(R.color.alpha_gray));
        tvOa4.setTextColor(getResources().getColor(R.color.grey_home));
        iv5.setColorFilter(getResources().getColor(R.color.alpha_gray));
        tvOa5.setTextColor(getResources().getColor(R.color.grey_home));
        userName = (String) MyApplication.get(_mActivity, UserName, "");
        mUserType = (int) MyApplication.get(_mActivity, UserType, -1);
        strEnCord = (String) MyApplication.get(_mActivity, ENCODE, "");
        strDepartName = (String) MyApplication.get(_mActivity, DEPART_NAME, "");
        strPost = (String) MyApplication.get(_mActivity, F_Name, "");
    }

    @Override
    public boolean onBackPressedSupport() {
        if (GSYVideoManager.backFromWindowFull(_mActivity)) {
            return true;
        }

        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        }

        return super.onBackPressedSupport();
    }

    private List<NewsBean.DataBean.RowsBean> getGridView(List<NewsBean.DataBean.RowsBean> bean) {
        List<NewsBean.DataBean.RowsBean> list = new ArrayList<>();
        bean.remove(0);
        bean.remove(1);
        bean.remove(2);
        bean.remove(3);

        for (int i = 0; i < bean.size(); i++) {
            NewsBean.DataBean.RowsBean item = new NewsBean.DataBean.RowsBean();
            item.setPicUrl(bean.get(i).getPicUrl());
            item.setTitle(bean.get(i).getTitle());
            item.setAddTime(bean.get(i).getAddTime());
            list.add(item);
        }
        return list;
    }

    @OnClick({R.id.iv_circle, R.id.ll_scan, R.id.ll_order, R.id.ll_Logistics, R.id.ll_way, R.id.LL_OA, R.id.tv_company_more, R.id.tv_trade_more})
    public void onViewClicked(View view) {
        Event<Integer> event2 = new Event<Integer>(video_status, 0);
        EventBusUtil.sendEvent(event2);
        switch (view.getId()) {
            case R.id.iv_circle:
                if ("".equals(session)) {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                } else {
                    if (mUserType != 1)
                        ((MainFragment) getParentFragment()).startBrotherFragment(MyCordFragment.newInstance("个人信息", strEnCord, strDepartName, headIcon, userName));
                }
                break;
            case R.id.tv_company_more: //企业更多
                ((MainFragment) getParentFragment()).startBrotherFragment(DetailNewsFragment.newInstance(2, bean));
                break;
            case R.id.tv_trade_more: //行业更多
                ((MainFragment) getParentFragment()).startBrotherFragment(DetailNewsFragment.newInstance(3, bean2));
                break;
            case R.id.ll_scan:
                //扫一扫
                NormalListDialog normalListDialog = new NormalListDialog(_mActivity, StrScans);
                normalListDialog.title(getString(R.string.scan) + getString(R.string.style)).layoutAnimation(null).show();
                normalListDialog.setOnOperItemClickL((parent, view1, position, id) -> {
                    switch (position) {
                        case 0:
                            ScanManager.startScan(_mActivity, QrConfig.TYPE_QRCODE, QrConfig.SCANVIEW_TYPE_QRCODE);
                            break;
                        case 1:
                            ScanManager.startScan(_mActivity, QrConfig.TYPE_BARCODE, QrConfig.SCANVIEW_TYPE_BARCODE);
                            break;
                    }
                    normalListDialog.dismiss();
                });
                break;
            case R.id.ll_order:
                ((MainFragment) getParentFragment()).startBrotherFragment(AppointHomeFragment.newInstance());
                break;
            case R.id.ll_Logistics:
                if (Utils.checkPackInfo(_mActivity)) {
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("alipays://platformapi/startapp?appId=20000754"));
                    intent.putExtra("", "");//这里Intent当然也可传递参数,但是一般情况下都会放到上面的URL中进行传递
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LogisticsCheckFragment.newInstance(getString(R.string.Logistics)));
                }
                break;
            case R.id.ll_way:
                ((MainFragment) getParentFragment()).startBrotherFragment(DetailContentFragment.newInstance(getString(R.string.contact_way), "508", ""));
                break;
            case R.id.LL_OA:
                ((MainFragment) getParentFragment()).startBrotherFragment(OAMainFragment.newInstance());
                break;

        }
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case UPDATA_CLIENT: //版本更新
                UpdateBean updateBean = (UpdateBean) event.getData();
                String des = updateBean.getData().getDescription();
                String ver = updateBean.getData().getVer();
                int curVersion = Utils.getVersionCode(_mActivity);
                LatteLogger.d("UPDATA_CLIENT", Utils.getVersionCode(_mActivity) + "  " + ver);
                if (curVersion < Integer.parseInt(ver)) {
                    UpdateDialog dialog = new UpdateDialog(_mActivity, des);
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                }
                break;
            case REQUEST_CODE_APP_INSTALL:
                String apkFile = (String) event.getData();
                LatteLogger.d("apkFile", apkFile);
                installApk(apkFile);
                break;
            case video_status:
                int status = (int) event.getData();
                LatteLogger.d("video_status", status);
                switch (status) { //0 为暂停 1为播放
                    case 0:
                        GSYVideoManager.onPause();
                        break;
                    case 1:
                        GSYVideoManager.onResume();
                        break;
                }
                break;
            case EXIT_APP:
                session = "";
                Utils.headIcon(_mActivity, "", ivCircle);
                break;
            case ME_info:
                LoginBean bean = (LoginBean) event.getData();
                session = bean.getSessionKey();
                headIcon = bean.getLogonUser().getIco();
                Utils.headIcon(_mActivity, headIcon, ivCircle);
                break;
            case ALL_BACK_HOME:
                LatteLogger.d("testBack", "可以显示出来");
                indexDataList.clear();
                indexDataList = (List<MyHomeBean>) appContext.readObject(AppConfig.KEY_USER);
                MyHomeBean allMenuEntity = new MyHomeBean();
                allMenuEntity.setIco("ic_small_h");
                allMenuEntity.setId("all");
                allMenuEntity.setTitle("更多");
                allMenuEntity.setEnable(true);
                indexDataList.add(allMenuEntity);
                appContext.saveObject((Serializable) indexDataList, AppConfig.KEY_BACK_NEW_DATA);
                adapter = new MyHomeAdapter(_mActivity, indexDataList);
                subGridView.setAdapter(adapter);
                break;
        }
    }

    private void installApk(String apkFile) {
        File apk = new File(apkFile);
        LatteLogger.d("apkFile", apk + "");
        Uri uri = Uri.fromFile(apk);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(_mActivity, getContext().getPackageName() + ".fileProvider", apk);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        }
        startActivity(intent);
        Process.killProcess(Process.myPid());
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void deleteRequest(Disposable disposable) {
        this.disposable = disposable;
        super.deleteRequest(disposable);
    }
}
