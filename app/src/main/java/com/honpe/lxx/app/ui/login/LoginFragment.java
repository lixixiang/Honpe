package com.honpe.lxx.app.ui.login;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.honpe.lxx.app.MainFragment;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.bean.UserCarBean;
import com.honpe.lxx.app.ui.fragment.a_package.news.DetailContentFragment;
import com.honpe.lxx.app.ui.fragment.d_package.bean.LoginBean;
import com.honpe.lxx.app.ui.login.bean.WEBLoginBean;
import com.honpe.lxx.app.ui.login.forget_pass.ForgetPassword;
import com.honpe.lxx.app.ui.login.register.RegisterFragment;
import com.honpe.lxx.app.ui.login.register.RegisterTypeFragment;
import com.honpe.lxx.app.ui.main.appoint.AppointHomeFragment;
import com.honpe.lxx.app.ui.main.more.multipleItem.CheckInManager;
import com.honpe.lxx.app.ui.main.oa.OAMainFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position1.TotalQueryFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.BuildFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position15.CustomCookerOrderFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position16.EmployeeWithOrderFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position19.SubContractFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position5.OverTimeFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position6.CardQueryFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position9.CarInfoFragment;
import com.honpe.lxx.app.utils.AndroidBug5497Workaround;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ProgressUtils;
import com.honpe.lxx.app.utils.StringUtil;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.RxAnimationTool;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.honpe.lxx.app.api.FinalClass.CircleIcon;
import static com.honpe.lxx.app.api.FinalClass.DEPART_ID;
import static com.honpe.lxx.app.api.FinalClass.DEPART_NAME;
import static com.honpe.lxx.app.api.FinalClass.ENCODE;
import static com.honpe.lxx.app.api.FinalClass.F_Name;
import static com.honpe.lxx.app.api.FinalClass.HeadIcon;
import static com.honpe.lxx.app.api.FinalClass.PARENT_DEPART;
import static com.honpe.lxx.app.api.FinalClass.PassWord;
import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.api.FinalClass.Status;
import static com.honpe.lxx.app.api.FinalClass.Tokey;
import static com.honpe.lxx.app.api.FinalClass.UserCode;
import static com.honpe.lxx.app.api.FinalClass.UserId;
import static com.honpe.lxx.app.api.FinalClass.UserName;
import static com.honpe.lxx.app.api.FinalClass.UserType;
import static com.honpe.lxx.app.api.FinalClass.WEB_USE_ID;
import static com.honpe.lxx.app.base.BaseMainFragment.TOUCH_TIME;
import static com.honpe.lxx.app.base.BaseMainFragment.WAIT_TIME;


/**
 * created by lxx at 2019/11/20 16:47
 * 描述:登录界面
 */
public class LoginFragment extends BaseBackFragment implements TextWatcher {
    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.iv_clean_phone)
    ImageView mIvCleanPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.clean_password)
    ImageView mCleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView mIvShowPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.regist)
    TextView mRegist;
    @BindView(R.id.forget_password)
    TextView mForgetPassword;
    @BindView(R.id.content)
    LinearLayout mContent;
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.service)
    LinearLayout mService;
    @BindView(R.id.root)
    RelativeLayout mRoot;
    @BindView(R.id.ck_password)
    CheckBox ckPassWord;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;
    private String userNameValue, passwordValue, headIcon, strUserName, strUserCode,tag;
    private SharedPreferences sp;


    public static LoginFragment newInstance(String tag) {
        LoginFragment fragment = new LoginFragment();
        fragment.tag = tag;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen(_mActivity)) {
            AndroidBug5497Workaround.assistActivity(_mActivity);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_login;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView() {
        LatteLogger.d("testTag",tag);
        screenHeight = getResources().getDisplayMetrics().heightPixels;//获取屏幕高度
        keyHeight = screenHeight / 3; //弹起高度为屏幕高度的1/3
        mIvCleanPhone.setColorFilter(Color.GRAY);
        mCleanPassword.setColorFilter(Color.GRAY);
        sp = _mActivity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        ckPassWord.setChecked(sp.getBoolean("isCheck", false));
        strUserName = sp.getString("USER_NAME2", "");
        mEtMobile.setText(sp.getString("USER_NAME", ""));
        mEtPassword.setText(sp.getString("PASSWORD", ""));

        LatteLogger.d("HEAD_ICON", sp.getString("HEAD_ICON", "") + "    " + sp.getBoolean("isCheck", false));
        if (!"".equals(sp.getString("HEAD_ICON", "")) && sp.getBoolean("isCheck", false)) {
            Glide.with(_mActivity).load(sp.getString("HEAD_ICON", "")).into(mLogo);
        } else {
            mLogo.setImageResource(R.drawable.health_guide_men_unselected);
        }
        addTextChange();
        remPass();
        mEtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvCleanPhone.getVisibility() == View.GONE) {
                    mIvCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
                    mCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty()) {
                    return;
                }
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(_mActivity, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtPassword.setSelection(s.length());
                }
            }
        });
        /**
         * 禁止键盘弹起的时候可以滚动
         */
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mScrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = mContent.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        RxAnimationTool.zoomIn(mLogo, 0.6f, dist);
                    }
                    mService.setVisibility(View.INVISIBLE);

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((mContent.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        RxAnimationTool.zoomOut(mLogo, 0.6f);
                    }
                    mService.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void addTextChange() {
        mEtMobile.addTextChangedListener(this);
        mEtPassword.addTextChangedListener(this);
        mEtMobile.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                mIvCleanPhone.setVisibility(View.VISIBLE);
                mIvCleanPhone.setVisibility(View.GONE);
                return false;
            }
        });
        mEtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                mIvCleanPhone.setVisibility(View.GONE);
                mIvCleanPhone.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    private void remPass() {
        ckPassWord.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ckPassWord.isChecked()) {
                    ckPassWord.setText(getString(R.string.rememb));
                    LatteLogger.d("记住密码已选中");
                    sp.edit().putBoolean("isCheck", true).commit();
                    ckPassWord.setChecked(sp.getBoolean("isCheck", false));
                } else {
                    LatteLogger.d("记住密码没有选中");
                    ckPassWord.setText("取消记住");
                    sp.edit().putBoolean("isCheck", false).commit();
                    ckPassWord.setChecked(sp.getBoolean("isCheck", false));
                }
            }
        });
    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @OnClick({R.id.iv_clean_phone, R.id.clean_password, R.id.iv_show_pwd,
            R.id.btn_login, R.id.regist, R.id.forget_password, R.id.tv_about_us, R.id.tv_contract_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean_phone:
                mEtMobile.setText("");
                break;
            case R.id.clean_password:
                mEtPassword.setText("");
                break;
            case R.id.iv_show_pwd:
                if (mEtPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_visuable);
                } else {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_gone);
                }
                String pwd = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    mEtPassword.setSelection(pwd.length());
                break;
            case R.id.btn_login:
                hideSoftKeyBoard();
                userNameValue = mEtMobile.getText().toString().trim();
                passwordValue = mEtPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(userNameValue) && !TextUtils.isEmpty(passwordValue)) {
                    jsonData(userNameValue, passwordValue);
                    SharedPreferences.Editor editor = sp.edit();
                    if (ckPassWord.isChecked()) {
                        //记住用户名、密码
                        editor.putString("USER_NAME", userNameValue);
                        editor.putString("PASSWORD", passwordValue);
                    } else {
                        editor.clear();
                    }
                    editor.commit();
                } else {
                    ToastUtil.getInstance().showToast("用户名或密码不能为空哦！");
                }
                break;
            case R.id.regist:
                start(RegisterTypeFragment.newInstance());
                break;
            case R.id.forget_password:
                start(ForgetPassword.newInstance());
                break;
            case R.id.tv_about_us:
                long nowTime = System.currentTimeMillis();
                if (nowTime - TOUCH_TIME > WAIT_TIME) {
                    start(DetailContentFragment.newInstance("企业简介", "509", ""));
                    TOUCH_TIME = nowTime;
                }
                break;
            case R.id.tv_contract_service:
                long nowTime2 = System.currentTimeMillis();
                if (nowTime2 - TOUCH_TIME > WAIT_TIME) {
                    //联系方式
                    start(DetailContentFragment.newInstance("联系方式", "508", ""));
                    TOUCH_TIME = nowTime2;
                }
                break;
        }
    }

    private void jsonData(String userNameValue, final String passwordValue) {
        String language = null;
        if (getResources().getConfiguration().locale.getCountry().equals("CN")) {
            language = "zh-CN";
        } else if (getResources().getConfiguration().locale.getCountry().equals("US")) {
            language = "en";
        } else {
            language = "jp";
        }
        EasyHttp.post(Constants.LoginURL)
                .params("usercode", userNameValue)
                .params("password", StringUtil.md5(passwordValue))
                .params("language", language)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(_mActivity, 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(_mActivity, 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(_mActivity, 0);
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("LoginInfo", s);
                        LoginBean bean = Convert.fromJson(s, LoginBean.class);
                        if (bean.getStatus() == 0) {
                            MyApplication.clear(_mActivity);
                            //登录成功，保存信息
                            MyApplication.put(_mActivity, Status, bean.getStatus()); //0 登录 -1 未登录
                            MyApplication.put(_mActivity, UserName, bean.getLogonUser().getUserName());
                            MyApplication.put(_mActivity, UserCode, bean.getLogonUser().getUserCode());
                            MyApplication.put(_mActivity, PassWord, passwordValue);
                            MyApplication.put(_mActivity, Session, bean.getLogonUser().getKeyValue());
                            MyApplication.put(_mActivity, "IsActive", bean.getLogonUser().isIsActive());
                            MyApplication.put(_mActivity, HeadIcon, bean.getLogonUser().getIco());              //登录头像
                            MyApplication.put(_mActivity, CircleIcon, bean.getLogonUser().getInSpeechBg());//朋友圈背景
                            MyApplication.put(_mActivity, UserType, bean.getLogonUser().getUserType()); //判断外部用户和内部用户
                            MyApplication.put(_mActivity, FinalClass.EmployeeId, bean.getLogonUser().getEmployeeId());
                            headIcon = (String) MyApplication.get(_mActivity, HeadIcon, "");
                            strUserName = (String) MyApplication.get(_mActivity, "EmployeeId", "");
                            strUserCode = (String) MyApplication.get(_mActivity, UserCode, "");
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("HEAD_ICON", headIcon);
                            editor.putString("USER_NAME2", strUserName);
                            editor.putString("USER_CODE2", strUserCode);
                            editor.commit();
                            if (bean.getLogonUser().getUserType() != 1) {
                                getCarLogin();
                            }

                            Event<LoginBean> event = new Event<LoginBean>(FinalClass.ME_info, bean);
                            EventBusUtil.sendEvent(event);
                            ToastUtil.getInstance().showToast(bean.getMsg());
                            if (tag.contains("OAMainFragment")) {
                                startWithPop(OAMainFragment.newInstance());
                            } else if (tag.contains("AppointHomeFragment")) {
                                startWithPop(AppointHomeFragment.newInstance());
                            } else if (tag.contains("CheckInManager")) {
                                startWithPop(CheckInManager.newInstance("考勤管理", ""));
                            } else if (tag.contains("CardQueryFragment")) {
                                startWithPop(CardQueryFragment.newInstance("补卡"));
                            } else if (tag.contains("OverTimeFragment")) {
                                startWithPop(OverTimeFragment.newInstance("加班"));
                            } else if (tag.contains("CarInfoFragment")) {
                                startWithPop(CarInfoFragment.newInstance("派车"));
                            } else if (tag.contains("CustomCookerOrderFragment")) {
                                startWithPop(CustomCookerOrderFragment.newInstance("客户订餐"));
                            } else if (tag.contains("EmployeeWithOrderFragment")) {
                                startWithPop(EmployeeWithOrderFragment.newInstance("员工订餐"));
                            } else if (tag.contains("SubContractFragment")) {
                                startWithPop(SubContractFragment.newInstance("委外加工"));
                            } else if (tag.contains("TotalQueryFragment")) {
                                Bundle bundle = new Bundle();
                                bundle.putString("title", "考勤");
                                startWithPop(TotalQueryFragment.newInstance(bundle));
                            } else if (tag.contains("BuildFragment")) {
                                startWithPop(BuildFragment.newInstance("住宿查询"));
                            } else if (tag.equals("main")) {
                                pop();
                            } else if (tag.equals("MainActivity")) {
                                startWithPop(MainFragment.newInstance());
                            } else {
                                pop();
                            }
                        } else {
                            ToastUtil.getInstance().showToast(bean.getMsg());
                        }
                    }
                });
    }

    /**
     * 派车地图
     */
    private void getCarLogin() {
        EasyHttp.post(Constants.LoginCarURL)
                .params("name", "honpe")
                .params("password", "honpe@123456")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage().toString());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("car", s);
                        try {
                            UserCarBean bean = Convert.fromJson(s, UserCarBean.class);
                            MyApplication.put(_mActivity, Tokey, bean.getData().getToken());
                            MyApplication.put(_mActivity, UserId, bean.getData().getUserId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
        LatteLogger.d("strUserName", strUserName);
        if (!strUserName.contains("appcs")) {
            EasyHttp.post(Constants.WEB_ERP_LOGIN)
                    .params("EmployeeId", strUserName)
                    .execute(new SimpleCallBack<String>() {
                        @Override
                        public void onError(ApiException e) {
                            ToastUtil.getInstance().showToast(e.getMessage());
                        }

                        @Override
                        public void onSuccess(String s) {
                            WEBLoginBean bean = Convert.fromJson(s, WEBLoginBean.class);
                            LatteLogger.d("WEB_ERP_LOGIN", GsonBuildUtil.GsonBuilder(bean));
                            MyApplication.put(_mActivity, WEB_USE_ID, bean.getData().getBaseinfo().getUserId());
                            MyApplication.put(_mActivity, PARENT_DEPART, bean.getData().getBaseinfo().getDepartmentId());
                            MyApplication.put(_mActivity, ENCODE, bean.getData().getBaseinfo().getEnCode());
                            MyApplication.put(_mActivity, DEPART_NAME, bean.getData().getBaseinfo().getRootDepartment().getName());
                            if (bean.getData().getPost().size() > 0) {
                                MyApplication.put(_mActivity, F_Name, bean.getData().getPost().get(0).getF_Name());
                            }
                            MyApplication.put(_mActivity,DEPART_ID,bean.getData().getBaseinfo().getRootDepartment().getId());

                        }
                    });
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return super.isRegisterEventBus();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        LatteLogger.d("dddddddd", mEtMobile.getText().toString().trim() + "  " + strUserName);
        if (mEtMobile.getText().toString().trim().equalsIgnoreCase(strUserName)) {//equalsIgnoreCase 忽略大小写
            Glide.with(_mActivity).load(sp.getString("HEAD_ICON", "")).into(mLogo);
        } else {
            mLogo.setImageResource(R.drawable.health_guide_men_unselected);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).statusBarColor(R.color.white).navigationBarColor(R.color.black).keyboardEnable(false).statusBarDarkFont(true).init();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}




