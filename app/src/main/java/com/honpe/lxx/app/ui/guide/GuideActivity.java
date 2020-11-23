package com.honpe.lxx.app.ui.guide;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.gyf.immersionbar.ImmersionBar;
import com.honpe.lxx.app.MainActivity;
import com.honpe.lxx.app.MainFragment;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseActivity;
import com.honpe.lxx.app.widget.FullScreenVideoView;

import butterknife.BindView;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {
    @BindView(R.id.video_view)
    FullScreenVideoView videoView;

    @BindView(R.id.tv_enter)
    Button tvEnter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        initData();

    }

    private void initData() {
        //加载视频资源
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.guide1));
        //播放
        videoView.start();
        //循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
    }


    @OnClick({R.id.tv_enter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_enter:
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).transparentNavigationBar().init();
    }
}
