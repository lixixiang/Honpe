package com.honpe.lxx.app.ui.main.oa.ui.position8.adapter.prove;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.SearchEntity;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;


/**
 * FileName: ChildThirdProvider
 * Author: asus
 * Date: 2020/10/13 9:25
 * Description:
 */
public class ChildThirdProvider extends BaseNodeProvider {

    SimpleDateFormat ef = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat sf = new SimpleDateFormat("MM-dd HH:mm");
    String[] stringItems = {"同意", "不同意"};
    TextView tvStatus;

    @Override
    public int getItemViewType() {
        return 3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_approve_detail_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, BaseNode baseNode) {
        SearchEntity.DataEntity.RowsEntity rowsEntity = (SearchEntity.DataEntity.RowsEntity) baseNode;
        helper.setText(R.id.tv_id, rowsEntity.getFM_id() + "、");
        if (!TextUtils.isEmpty(rowsEntity.getF_StartTime()) && !TextUtils.isEmpty(rowsEntity.getF_EndTime())) {
            helper.setText(R.id.tv_startTime, "时间：" + sf.format(DateUtil.setDate(ef, rowsEntity.getF_StartTime())));
            helper.setText(R.id.tv_endTime, sf.format(DateUtil.setDate(ef, rowsEntity.getF_EndTime())));
        } else {
            helper.setText(R.id.tv_startTime, "开始时间：" + "无");
            helper.setText(R.id.tv_endTime, "结束时间:" + "无");
        }
        if (!"".equals(rowsEntity.getCountHours())) {
            helper.setText(R.id.tv_time_lag, rowsEntity.getCountHours() + " H");
        }

        helper.setGone(R.id.tv_address, true);
        if (!"".equals(rowsEntity.getAddress())) {
            helper.setGone(R.id.tv_address, false);
            helper.setText(R.id.tv_address, "地址：" + rowsEntity.getAddress());
        }
        helper.setText(R.id.tv_reason, "事由：" + rowsEntity.getF_Reason());
        tvStatus = helper.getView(R.id.tv_status);

        if (rowsEntity.getF_CurState().equals("通过") || rowsEntity.getF_CurState().equals("不通过")) {
            if (rowsEntity.getF_CurState().equals("通过")) {
                tvStatus.setBackgroundResource(R.drawable.select_grey_radius10);
            } else {
                tvStatus.setBackgroundResource(R.drawable.select_red_radius10);
            }
            tvStatus.setEnabled(false);
            tvStatus.setText(rowsEntity.getF_CurState());
        } else {
            tvStatus.setBackgroundResource(R.drawable.select_blue_radius10);
            tvStatus.setEnabled(true);
            tvStatus.setText("待审批");
        }

        tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(rowsEntity.getF_TaskId())) {
                    ActionSheetDialog dialog = new ActionSheetDialog(context, stringItems, v);
                    dialog.lvBgColor(Color.WHITE)
                            .cancelText(Color.RED);
                    dialog.isTitleShow(false).show();
                    dialog.setOnOperItemClickL(new OnOperItemClickL() {
                        @Override
                        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                getRequest(rowsEntity, position, tvStatus);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            dialog.dismiss();
                        }
                    });
                } else {
                    ToastUtil.getInstance().showToast("未检测有审批权限！");
                }
            }
        });
    }


    private void getRequest(SearchEntity.DataEntity.RowsEntity rowsEntity, int position, TextView tvStatus) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("EmployeeId", rowsEntity.getEmployeeId());
        jsonObject.addProperty("F_Id", rowsEntity.getF_Id());
        jsonObject.addProperty("F_TaskId", rowsEntity.getF_TaskId());
        jsonObject.addProperty("F_SchemeName", rowsEntity.getF_SchemeName());
        jsonObject.addProperty("verifyType", position + 1 + "");

        LatteLogger.d("getRequest", GsonBuildUtil.GsonBuilder(jsonObject));
        tvStatus.setEnabled(false);
        tvStatus.setBackgroundResource(R.drawable.select_grey_radius10);
        if ((position + 1) == 1) {
            tvStatus.setText("通过");
            tvStatus.setBackgroundResource(R.drawable.select_grey_radius10);
        } else {
            tvStatus.setText("不通过");
            tvStatus.setBackgroundResource(R.drawable.select_red_radius10);
        }

        rowsEntity.setF_CurState(tvStatus.getText().toString());
        EasyHttp.post(Constants.EmployeePorcessVerify)
                .retryCount(0)
                .upJson(GsonBuildUtil.GsonBuilder(jsonObject))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("AppVerifyWorkFlow", s);
                        try {
                            JSONObject jsonObject1 = new JSONObject(s);
                            if (jsonObject1.getInt("code") == 200) {
                                tvStatus.setEnabled(false);
                                tvStatus.setBackgroundResource(R.drawable.select_grey_radius10);
                                if ((position+1) == 1) {
                                    tvStatus.setText("通过");
                                    tvStatus.setBackgroundResource(R.drawable.select_grey_radius10);
                                }else {
                                    tvStatus.setText("不通过");
                                    tvStatus.setBackgroundResource(R.drawable.select_red_radius10);
                                }
                            }
                            ToastUtil.getInstance().showToast(jsonObject1.getJSONObject("data").getString("desc"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
