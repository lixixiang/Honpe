package com.honpe.lxx.app.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.gyf.immersionbar.ImmersionBar;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.utils.RxPermissionsTool;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.zhouyou.http.EasyHttp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportFragment;

import static com.honpe.lxx.app.api.FinalClass.DEPART_ID;
import static com.honpe.lxx.app.api.FinalClass.DEPART_NAME;
import static com.honpe.lxx.app.api.FinalClass.ENCODE;
import static com.honpe.lxx.app.api.FinalClass.PARENT_DEPART;
import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.api.FinalClass.UserName;
import static com.honpe.lxx.app.api.FinalClass.WEB_USE_ID;


/**
 * created by lxx at 2019/11/12 11:06
 * 描述:
 */
public abstract class BaseBackFragment extends SupportFragment {
    public static final String TAG = "BaseBackFragment";
    protected View rootView;
    private boolean isFragmentVisible;
    public boolean isFirst;
    private InputMethodManager imm;
    protected String fragmentTitle;//fragment标题
    public String session;
    public String sessionE = "session expired";
    public String sessionI = "Invalid Session.";
    public String sessionPastDue = "Session 过期了，请重新登录！";
    public String EmployeeId;
    public String RqtBy;
    public String Dpt;
    public String userName; //用户名
    public String myCode; //我的工号
    public String departId; //部门Id
    public String[] HRtypes = {"Leave", "BusinessTravel", "GoOut"};
    protected Activity mActivity;
    public Disposable disposable;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (Activity) activity;
    }

    protected void initToolbarNav(final View homeBack) {
        homeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyBoard();
                EasyHttp.cancelSubscription(disposable);
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxPermissionsTool.
                with(_mActivity).
                addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.INTERNET).
                initPermission();
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(TAG);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(TAG, isHidden());
    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyBusCome(Event event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发的粘性事件
     *
     * @param event
     */
    protected void receiveStickyEvent(Event event) {

    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(Event event) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container,false);
            ButterKnife.bind(this, rootView);
            //初始化沉浸式
            EmployeeId = (String) MyApplication.get(_mActivity, FinalClass.EmployeeId, "");
            RqtBy = (String) MyApplication.get(_mActivity, WEB_USE_ID, "");
            Dpt = (String) MyApplication.get(_mActivity, PARENT_DEPART, "");
            session = (String) MyApplication.get(_mActivity, Session, "");
            userName = (String) MyApplication.get(_mActivity, UserName, "");
            myCode = (String) MyApplication.get(_mActivity, ENCODE, "");
            departId = (String) MyApplication.get(_mActivity, DEPART_ID, "");
            View titleBar = rootView.findViewById(setTitleBar());
            ImmersionBar.setTitleBar(mActivity, titleBar);
            View statusBarView = rootView.findViewById(setStatusBarView());
            ImmersionBar.setStatusBarView(mActivity, statusBarView);
            initView();
            //可见，但是并没有加载过
            if (isFragmentVisible && !isFirst) {
                onFragmentVisibleChange(true);
            }
        }
        return rootView;
    }


    //获取布局文件
    protected abstract int getLayoutResource();

    //初始化view
    protected abstract void initView();

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作.
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        //请在onSupportVisible实现沉浸式
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void initImmersionBar() {
        ImmersionBar.with(this).keyboardEnable(true).navigationBarColor(R.color.blue_dark).init();
    }

    protected int setTitleBar() {
        return R.id.toolbar;
    }

    protected int setStatusBarView() {
        return 0;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isFragmentVisible = true;
        }
        if (rootView == null) {
            return;
        }
        //可见，并且没有加载过
        if (!isFirst && isFragmentVisible) {
            onFragmentVisibleChange(true);
            return;
        }
        //由可见——>不可见 已经加载过
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
        this.imm = null;
    }

    public void showSoftInput(Context context, View view) {
        this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public void hideSoftInput(Context context, View view) {
        this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    public boolean isShowSoftInput(Context context) {
        this.imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //获取状态信息
        return imm.isActive();//true 打开
    }

    public void finish() {
        super._mActivity.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = _mActivity.getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) _mActivity.getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    public String getTitle() {
        return TextUtils.isEmpty(fragmentTitle) ? "" : fragmentTitle;
    }

    public void setTitle(String title) {
        fragmentTitle = title;
    }

    @Override
    public boolean onBackPressedSupport() {
        EasyHttp.cancelSubscription(disposable);
        return super.onBackPressedSupport();
    }
}


