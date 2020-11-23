package com.honpe.lxx.app.ui.fragment.d_package;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.honpe.lxx.app.MainFragment;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseMainFragment;
import com.honpe.lxx.app.ui.fragment.d_package.about.AboutFragment;
import com.honpe.lxx.app.ui.fragment.d_package.adapter.MeAdapter;
import com.honpe.lxx.app.ui.fragment.d_package.address.AddressFragment;
import com.honpe.lxx.app.ui.fragment.d_package.bean.IconTextDirectorBean;
import com.honpe.lxx.app.ui.fragment.d_package.bean.LoginBean;
import com.honpe.lxx.app.ui.fragment.d_package.mobi_icon.MobIconFragment;
import com.honpe.lxx.app.ui.fragment.d_package.myScan.MyCordFragment;
import com.honpe.lxx.app.ui.fragment.d_package.qr_code.DownloadQRCodeFragment;
import com.honpe.lxx.app.ui.fragment.d_package.setting.FeedBackFragment;
import com.honpe.lxx.app.ui.fragment.d_package.setting.SettingFragment;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.widget.font.FontTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.honpe.lxx.app.api.FinalClass.DEPART_NAME;
import static com.honpe.lxx.app.api.FinalClass.ENCODE;
import static com.honpe.lxx.app.api.FinalClass.EXIT_APP;
import static com.honpe.lxx.app.api.FinalClass.F_Name;
import static com.honpe.lxx.app.api.FinalClass.HeadIcon;
import static com.honpe.lxx.app.api.FinalClass.ME_info;
import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.api.FinalClass.UPDATA_TO_ME;
import static com.honpe.lxx.app.api.FinalClass.UserName;
import static com.honpe.lxx.app.api.FinalClass.UserType;


/**
 * created by lxx at 2020/4/6 6:34
 * 描述:
 */
public class MeFragment extends BaseMainFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.re_un_login)
    RelativeLayout reUnLogin;
    @BindView(R.id.image_head)
    CircleImageView imageHead;
    @BindView(R.id.ll_login_with_register)
    LinearLayout llLoginWithRegister;
    @BindView(R.id.ll_is_login)
    LinearLayout llIsLogin;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.tv_font)
    FontTextView tvFont;
    MeAdapter adapter;
    List<IconTextDirectorBean> mList = new ArrayList<>();
    private String[] texts;
    private int[] mIcons = new int[]{R.mipmap.ic_me_1, R.mipmap.ic_me_2, R.mipmap.ic_me_4, R.mipmap.ic_me_5, R.mipmap.ic_me_6};
    private String headIcon, userName, session, strEnCord, strDepartName, strPost;
    private int mUserType;
    private LoginBean bean;
    private SharedPreferences sp;

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {
        llBack.setVisibility(View.GONE);
        tvTitle.setText(getString(R.string.me));
        texts = getResources().getStringArray(R.array.me_text_list);
        headIcon = (String) MyApplication.get(_mActivity, HeadIcon, "");
        userName = (String) MyApplication.get(_mActivity, UserName, "");
        session = (String) MyApplication.get(_mActivity, Session, "");
        sp = _mActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        strEnCord = (String) MyApplication.get(_mActivity, ENCODE, "");
        strDepartName = (String) MyApplication.get(_mActivity, DEPART_NAME, "");
        strPost = (String) MyApplication.get(_mActivity, F_Name, "");
        mUserType = (int) MyApplication.get(_mActivity, UserType, -1);
        for (int i = 0; i < mIcons.length; i++) {
            IconTextDirectorBean item = new IconTextDirectorBean(texts[i], mIcons[i]);
            mList.add(item);
        }
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        ImageOnTouchEvent();
        ChangeLoginStatus();

        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        adapter = new MeAdapter(mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            switch (position) {
                case 0:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(SettingFragment.newInstance(texts[position]));
                    }
                    break;
                case 1:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(AddressFragment.newInstance(texts[position]));
                    }
                    break;
//                    case 2:
//                        if (session == "") {
//                            ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
//                        } else {
//                            ((MainFragment) getParentFragment()).startBrotherFragment(MyCordFragment.newInstance(texts[position], strEnCord, strDepartName, strPost, headIcon, userName));
//                        }
//                        break;
                case 2:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(DownloadQRCodeFragment.newInstance());
                    }
                    break;
                case 3:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(FeedBackFragment.newInstance(texts[position]));
                    }
                    break;
                case 4:
                    if (session == "") {
                        ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                    } else {
                        ((MainFragment) getParentFragment()).startBrotherFragment(AboutFragment.newInstance(texts[position]));
                    }
                    break;
            }
        });
    }

    private void ChangeLoginStatus() {
        if (!"".equals(session)) {
            llLoginWithRegister.setVisibility(View.GONE);
            llIsLogin.setVisibility(View.VISIBLE);
            if (headIcon != null || !TextUtils.isEmpty(headIcon)) {
                Glide.with(_mActivity).load(headIcon).placeholder(R.drawable.selector_men).error(R.drawable.selector_men).into(imageHead);
            } else {
                imageHead.setImageResource(R.drawable.selector_men);
            }
            tvUserName.setText(userName);
        } else {
            llLoginWithRegister.setVisibility(View.VISIBLE);
            llIsLogin.setVisibility(View.GONE);
            imageHead.setImageResource(R.drawable.selector_men);
            mList.remove(2);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void ImageOnTouchEvent() {
        imageHead.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_selected);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_selected_click);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_unselected);
                        }
                        break;
                    default:
                        if (session == "") {
                            imageHead.setImageResource(R.drawable.health_guide_men_unselected);
                        }
                        break;
                }
                return false;
            }
        });
    }


    @OnClick({R.id.image_head, R.id.re_un_login, R.id.tv_font})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_head:
                if ("".equals(session)) {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                } else {
                    if (mUserType !=1)
                    ((MainFragment) getParentFragment()).startBrotherFragment(MyCordFragment.newInstance("个人信息", strEnCord, strDepartName,  headIcon, userName));
                }
                break;
            case R.id.re_un_login:
                LatteLogger.d("re_un_login", session);
                if ("".equals(session)) {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                }
                break;
            case R.id.tv_font:
                if (!"".equals(session)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("headIcon", headIcon);
                    bundle.putString("userName", userName);
                    bundle.putString("session", session);
                    ((MainFragment) getParentFragment()).startBrotherFragment(MobIconFragment.newInstance(bundle));
                } else {
                    ((MainFragment) getParentFragment()).startBrotherFragment(LoginFragment.newInstance(""));
                }
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
            case ME_info:
                bean = (LoginBean) event.getData();
                session = bean.getSessionKey();
                headIcon = bean.getLogonUser().getIco();
                userName = bean.getLogonUser().getUserName();
                mUserType = (int) MyApplication.get(_mActivity, UserType, -1);
                if (!"".equals(bean.getSessionKey())) {
                    llLoginWithRegister.setVisibility(View.GONE);
                    llIsLogin.setVisibility(View.VISIBLE);
                    LatteLogger.d("headIcon", headIcon);
                    if (!"".equals(headIcon) || !TextUtils.isEmpty(headIcon)) {
                        Glide.with(_mActivity).load(bean.getLogonUser().getIco()).into(imageHead);
                    } else {
                        imageHead.setImageResource(R.drawable.selector_men);
                    }
                    tvUserName.setText(bean.getLogonUser().getUserName());
                } else {
                    llLoginWithRegister.setVisibility(View.VISIBLE);
                    llIsLogin.setVisibility(View.GONE);
                    imageHead.setImageResource(R.drawable.selector_men);
                }
                break;
            case EXIT_APP:
                session = "";
                llLoginWithRegister.setVisibility(View.VISIBLE);
                llIsLogin.setVisibility(View.GONE);
                imageHead.setImageResource(R.drawable.selector_men);
                break;
            case UPDATA_TO_ME:
                Object obImg = event.getData();
                LatteLogger.d("obImg", obImg);
                MyApplication.remove(_mActivity, HeadIcon);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("HEAD_ICON", (String) obImg);
                editor.commit();
                MyApplication.put(_mActivity, HeadIcon, obImg);
                headIcon = (String) MyApplication.get(_mActivity, HeadIcon, "");
                Glide.with(_mActivity).load(obImg).into(imageHead);
                break;
        }
    }
}

