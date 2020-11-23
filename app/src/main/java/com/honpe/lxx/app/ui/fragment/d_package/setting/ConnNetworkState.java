package com.honpe.lxx.app.ui.fragment.d_package.setting;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

import com.honpe.lxx.app.base.BaseAnimatorSet;
import com.honpe.lxx.app.utils.ToastUtil;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/7 11:43
 * @Author: 李熙祥
 * @Description: java类作用描述 无法获得wifi网络时出现的界面
 */
public class ConnNetworkState {
    //检测网络连接状态
    private ConnectivityManager manager;
    private Context context;
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    public ConnNetworkState(Context context) {
        this.context = context;
    }

    public void setBasIn(BaseAnimatorSet bas_in) {
        this.mBasIn = bas_in;
    }

    public void setBasOut(BaseAnimatorSet bas_out) {
        this.mBasOut = bas_out;
    }

    /**
     * 检测网络是否连接
     *
     * @return
     */
    public boolean checkNetworkState() {
        boolean flag = false;
        //得到网络连接信息
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {
            setNetwork();
        } else {
            isNetworkAvailable();
        }
        return flag;
    }

    /**
     * 网络未连接时，调用设置方法
     */
    private void setNetwork() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage("网络不可用，如果继续，请先设置网络！再尝试连接")
                .setTitle("提示");
        dialog.show();
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    /**
     * 网络已经连接，然后去判断是wifi连接还是GPRS连接
     * 设置一些自己的逻辑调用
     */
    private void isNetworkAvailable() {
        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING) {
            ToastUtil.getInstance().showToast("wifi is open! gprs");
        }
        //判断为wifi状态下才加载广告，如果是GPRS手机网络则不加载！
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            ToastUtil.getInstance().showToast("wifi is open! wifi");
        }
    }
}
