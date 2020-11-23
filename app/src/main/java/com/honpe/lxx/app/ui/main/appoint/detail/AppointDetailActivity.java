package com.honpe.lxx.app.ui.main.appoint.detail;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseActivity;
import com.honpe.lxx.app.ui.main.appoint.bean.AppointBean;
import com.honpe.lxx.app.ui.main.appoint.detail.clickImg.FullDialogCordFragment;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.Utils;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bertsir.zbar.utils.QRUtils;

/**
 * 访客详情界面
 */
public class AppointDetailActivity extends BaseActivity {
    @BindView(R.id.detail_view)
    ViewPager detailView;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_man_num)
    TextView tvManNum;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.tv_car_no)
    TextView tvCarNo;
    @BindView(R.id.tv_staff_name)
    TextView tvStaffName;
    @BindView(R.id.tv_staff_depart)
    TextView tvStaffDepart;
    @BindView(R.id.tv_staff_time)
    TextView tvStaffTime;
    @BindView(R.id.tv_staff_day_num)
    TextView tvStaffDayNum;
    @BindView(R.id.tv_in_time)
    TextView tvInTime;
    @BindView(R.id.tv_out_time)
    TextView tvOutTime;
    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.iv_cord)
    ImageView ivCord;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_image)
    LinearLayout llImage;
    @BindView(R.id.ll_show)
    FrameLayout llShow;

    Bundle bundle;
    AppointBean.DataBean objBean;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sfTime = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat sfTime2 = new SimpleDateFormat("HH:mm");
    List<String> imageUrls = new ArrayList<>();
    private Uri imageFileUri;
    Bitmap qrCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_appoint_detail;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("访客详情");
        tvEnd.setText("分享");

        Intent intent = getIntent();
        bundle = intent.getExtras();
        objBean = (AppointBean.DataBean) bundle.getSerializable("AppointBean");
        tvName.setText(objBean.getVisitor());
        tvManNum.setText("(" + objBean.getEntourageNum() + "人)");
        tvPhone.setText(objBean.getTel());
        tvReason.setText(objBean.getReason());
        tvUnit.setText(objBean.getCompany());
        tvCarNo.setText(objBean.getCarNo());
        tvStaffName.setText(objBean.getStaffName());
        tvStaffDepart.setText("(" + objBean.getStaffDept() + ")");
        tvStaffDayNum.setText("(" + objBean.getVDays() + " 天)");
        tvInTime.setText(objBean.getReachTime());
        tvOutTime.setText(objBean.getDepartureTime());

        if (!TextUtils.isEmpty(objBean.getReachTime()) && !TextUtils.isEmpty(objBean.getDepartureTime())) {
            tvTotalTime.setVisibility(View.VISIBLE);
            String HHMM = DateUtil.CountAddMinus(sf2, objBean.getReachTime(), objBean.getDepartureTime(), 1);
            tvTotalTime.setText("(" + HHMM + ")");
        } else {
            tvTotalTime.setVisibility(View.GONE);
        }

        String[] strDate = objBean.getVisitDate().split(" ");
        Date date = DateUtil.setDate(sdf, strDate[0]);
        String mDate = sf.format(date);
        tvStaffTime.setText(mDate + "   " + objBean.getVisitTime());

        String[] applyDate = objBean.getApplyTime().split("T");
        Date date1 = DateUtil.setDate(sfTime, applyDate[1]);
        String strHHmm = sfTime2.format(date1);
        tvId.setText(objBean.getID());
        tvApplyTime.setText(" (" + applyDate[0] + " " + strHHmm + ")");

        if (!TextUtils.isEmpty(objBean.getPicUrl()) || !"".equals(objBean.getPicUrl())) {
            tvEnd.setVisibility(View.VISIBLE);
            LatteLogger.d(objBean.getID());
            Glide.with(this).load(objBean.getPicUrl()).into(ivCord);
            imageUrls.add(objBean.getPicUrl());
            qrCode = QRUtils.getInstance().createQRCodeAddLogo(objBean.getID(),300,300,
                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_honpe));
            ivCord.setImageBitmap(qrCode);
        } else {
            ivCord.setVisibility(View.GONE);
            tvTips.setVisibility(View.GONE);
            tvEnd.setVisibility(View.GONE);
        }
    }


    @OnClick({R.id.tv_end,R.id.iv_cord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                imageFileUri = Utils.BitmapToUri(_mActivity, qrCode);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageFileUri);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            case R.id.iv_cord:
//                ClickIvCordDialog dialog = new ClickIvCordDialog(_mActivity, qrCode);
//                dialog.show();
//                WindowManager m = getWindowManager();
//                Display display = m.getDefaultDisplay();
//                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//                params.width = display.getWidth();
//                params.height = display.getHeight();
//                dialog.getWindow().setAttributes(params);
                FullDialogCordFragment.newInstance(qrCode).show(getSupportFragmentManager(), FullDialogCordFragment.class.getSimpleName());

                break;
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
    }
}







