package com.honpe.lxx.app.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.fragment.d_package.address.add.AddressBean;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 17:41
 * @Author: 李熙祥
 * @Description: java类作用描述 三级列表地址
 */
public class AddressDialog extends Dialog implements View.OnClickListener{
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.et_address)
        EditText etAddress;
        @BindView(R.id.no)
        Button no;
        @BindView(R.id.yes)
        Button yes;

        private AreaPickerView areaPickerView;
        private List<AddressBean> addressBeans;
        private int[] i;
        private Activity activity;
        private String strAddress;

        public AddressDialog(Activity context, String strAddress) {
            super(context, R.style.comment_dialog);
            this.activity = context;
            this.strAddress = strAddress;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.address_dialog);
            initWindowParams();
            ButterKnife.bind(this);
            initView();
        }

        private void initView() {
            tvArea.setText("广东省深圳市宝安区");
            setPickerView();
            if (!TextUtils.isEmpty(strAddress) && !"".equals(strAddress)) {
                etAddress.setText(strAddress);
            }
        }

        private void initWindowParams() {
            Window dialogWindow = getWindow();
            //获取屏幕宽、高用
            WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = (int) (display.getWidth() * 0.84); // 宽度设置为屏幕的0.65

            dialogWindow.setGravity(Gravity.CENTER);
            dialogWindow.setAttributes(lp);
        }

        private void setPickerView() {
            Gson gson = new Gson();
            addressBeans = gson.fromJson(getCityJson(), new TypeToken<List<AddressBean>>() {}.getType());
            areaPickerView = new AreaPickerView(activity, R.style.Dialog, addressBeans);
            areaPickerView.setAreaPickerViewCallback(new AreaPickerView.AreaPickerViewCallback() {
                @Override
                public void callback(int... value) {
                    i = value;
                    if (value.length == 3)
                        tvArea.setText(addressBeans.get(value[0]).getLabel() + "省" + addressBeans.get(value[0]).getChildren().get(value[1]).getLabel() + "市" + addressBeans.get(value[0]).getChildren().get(value[1]).getChildren().get(value[2]).getLabel());
                    else
                        tvArea.setText(addressBeans.get(value[0]).getLabel() + "省" + addressBeans.get(value[0]).getChildren().get(value[1]).getLabel());
                }
            });
        }

        private String getCityJson() {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                AssetManager assetManager = activity.getAssets();
                BufferedReader bf = new BufferedReader(new InputStreamReader(
                        assetManager.open("region.json")));
                String line;
                while ((line = bf.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @OnClick({R.id.no, R.id.yes, R.id.tv_area})
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_area:
                    tvArea = (TextView) v;
                    areaPickerView.setSelect(i);
                    areaPickerView.show();
                    break;
                case R.id.et_address:
                    break;
                case R.id.no:
                    hideSoft(no);
                    dismiss();
                    break;
                case R.id.yes:
                    String message =  etAddress.getText().toString();
                    Event<String> event = new Event<String>(FinalClass.A, message);
                    EventBusUtil.sendEvent(event);
                    hideSoft(yes);
                    dismiss();
                    break;
            }
        }

        public void hideSoft(View view) {
            InputMethodManager imm =
                    (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
}


