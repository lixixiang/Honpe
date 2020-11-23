package com.honpe.lxx.app.widget.dialog;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BaseDialog;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.widget.NumberProgressBar;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.HttpLog;

import java.math.BigDecimal;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

import static com.honpe.lxx.app.api.FinalClass.REQUEST_CODE_APP_INSTALL;

/**
 * FileName: UpdateDialog
 * Author: asus
 * Date: 2020/8/16 13:00
 * Description: 检测更新对话框
 */
public class UpdateDialog extends BaseDialog<UpdateDialog> {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.btn_version_dialog_commit)
    Button btnVersionDialogCommit;
    @BindView(R.id.number_progress_bar)
    NumberProgressBar numberProgressBar;
    @BindView(R.id.tv_progress_number)
    TextView tvProgressNumBer;
    @BindView(R.id.tv_progress_percent)
    TextView tvProgressPercent;
    @BindView(R.id.ll_download)
    LinearLayout llDownload;

    private String mProgressNumberFormat;
    private NumberFormat mProgressPercentFormat;
    private long oldBytesRead;
    Disposable disposable; //网络请求会返回Disposable对象，方便取消网络请求
    String des;
    public UpdateDialog(Context context, String des) {
        super(context);
        this.des = des;
    }

    @Override
    public View onCreateView() {
        widthScale(0.75f);
        View inflate = View.inflate(mContext, R.layout.css_update_dialog, null);
        ButterKnife.bind(this, inflate);
        tvDes.setText(des);
        return inflate;
    }


    @Override
    public void setUiBeforShow() {

    }

    @OnClick({R.id.btn_version_dialog_commit,R.id.ic_update_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_version_dialog_commit:
                llDownload.setVisibility(View.VISIBLE);
                btnVersionDialogCommit.setVisibility(View.GONE);
                getRequestDownload();
                break;
            case R.id.ic_update_cancel: //取消网络请求
                EasyHttp.cancelSubscription(disposable);
                dismiss();
                break;
        }
    }

    private void getRequestDownload() {
        disposable = EasyHttp.downLoad(Constants.VERSION)
                .savePath(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/test/")
                .saveName("honpe.apk")
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void onStart() {
                        HttpLog.i("======" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
//                        dismiss();
                        HttpLog.i("======" + e.getMessage());
                    }

                    @Override
                    public void update(final long bytesRead, final long contentLength, final boolean done) {
                        final int progress = (int) (bytesRead * 100 / contentLength);
                        double dProgress = (double) bytesRead / (double) (1024 * 1024);
                        double dMax = (double) contentLength / (double) (1024 * 1024);
                        initFormats();
                        if (mProgressNumberFormat != null) {
                            String format = mProgressNumberFormat;
                            tvProgressNumBer.setText(String.format(format, dProgress, dMax));
                        } else {
                            tvProgressNumBer.setText("");
                        }
                        if (oldBytesRead != 0) {
                            long NetWorkSpeek = bytesRead - oldBytesRead;
                            double newSpeek = (double) NetWorkSpeek / (double) (1024);
                            BigDecimal bg = new BigDecimal(newSpeek);
                            newSpeek = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            LatteLogger.d("NetWorkSpeek", NetWorkSpeek + "  " + bytesRead + "  " + oldBytesRead + "  " + newSpeek);
                            tvProgressPercent.setText(String.valueOf(newSpeek) + "KB/s");
                        }
                        oldBytesRead = bytesRead;
                        LatteLogger.d("progress", progress + "    " + String.valueOf(contentLength) + "   " + done);
                        numberProgressBar.setProgress((int) progress);
                    }

                    @Override
                    public void onComplete(String path) {
                        LatteLogger.d("path", path);
                        Event<String> event = new Event<String>(REQUEST_CODE_APP_INSTALL, path);
                        EventBusUtil.sendEvent(event);
                        dismiss();
                    }
                });
    }

    private void initFormats() {
        mProgressNumberFormat = "%1.2fM/%2.2fM";
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
    }
}






