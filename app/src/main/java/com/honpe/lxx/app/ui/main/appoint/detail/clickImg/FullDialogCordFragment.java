package com.honpe.lxx.app.ui.main.appoint.detail.clickImg;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * FileName: ClickIvCordFragment
 * Author: asus
 * Date: 2020/8/15 10:19
 * Description: 点击二维码图标
 */
public class FullDialogCordFragment extends BaseDialogFragment {
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Bitmap bitmap;

    public static FullDialogCordFragment newInstance(Bitmap bitmap) {
        FullDialogCordFragment fragment = new FullDialogCordFragment();
        fragment.bitmap = bitmap;
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
//        mWindow.setWindowAnimations(R.style.RightAnimation);
    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    @Override
    protected int setLayoutId() {
        return R.layout.css_click_center_img;
    }

//    public ClickIvCordDialog(Activity activity, Bitmap bitmap) {
//        super(activity, R.style.all_screen_dialog);
//        this.bitmap = bitmap;
//        this.activity = activity;
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.css_click_center_img);
//        ButterKnife.bind(this);
//
//        ivCode.setImageBitmap(bitmap);
//    }


    @Override
    protected void initData() {
        super.initData();
        ivCode.setImageBitmap(bitmap);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this)
                .titleBar(toolbar)
                .statusBarDarkFont(true)
                .navigationBarColor(R.color.white)
                .keyboardEnable(true)
                .init();
    }

    @OnClick({R.id.ll_dialog, R.id.iv_code})
    public void onViewClicked(View view) {
        dismiss();
    }

}
