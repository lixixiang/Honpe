package com.honpe.lxx.app.ui.fragment.d_package.myScan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.utils.LatteLogger;

import butterknife.BindView;
import cn.bertsir.zbar.utils.QRUtils;

/**
 * FileName: MyCordFragment
 * Author: asus
 * Date: 2020/10/10 11:31
 * Description: 我的二维码
 */
public class MyCordFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.iv_cord)
    ImageView ivCord;
    @BindView(R.id.tv_tips)
    TextView tvTips;

    String title, strEnCord, strDepartName, headIcon, userName;
    Bitmap qrCode;

    public static MyCordFragment newInstance(String title, String strEnCord, String strDepartName, String headIcon, String userName) {
        MyCordFragment fragment = new MyCordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("strEnCord", strEnCord);
        bundle.putString("strDepartName", strDepartName);
        bundle.putString("headIcon", headIcon);
        bundle.putString("userName", userName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me_cord;
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title");
            strEnCord = bundle.getString("strEnCord");
            strDepartName = bundle.getString("strDepartName");
            headIcon = bundle.getString("headIcon");
            userName = bundle.getString("userName");
            String mContent = "工号：" + strEnCord + "\r\n姓名：" + userName + "\r\n 部门：" + strDepartName + "\r\n  岗位：" + "未填写岗位";

            tvTitle.setText(title);
            initToolbarNav(llBack);
            if (!"".equals(headIcon) || !TextUtils.isEmpty(headIcon)){
                Glide.with(this).asBitmap().load(headIcon)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                                qrCode = QRUtils.getInstance().createQRCodeAddLogo(mContent, 500, 500, resource);
                                ivCord.setImageBitmap(qrCode);
                            }
                        });
            }else {
                qrCode = QRUtils.getInstance().createQRCodeAddLogo(mContent, 500, 500,  BitmapFactory.decodeResource(getResources(), R.drawable.health_guide_men_selected));
                ivCord.setImageBitmap(qrCode);
            }
        }
    }
}
