package com.honpe.lxx.app.ui.main.oa.ui.position8.adapter.prove;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.NameInfoEntity;
import com.honpe.lxx.app.ui.main.oa.ui.position8.entity.SearchEntity;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.honpe.lxx.app.api.FinalClass.REFRESH_SEARCH_APPROVE;

/**
 * FileName: ChildSecondProvider
 * Author: asus
 * Date: 2020/10/12 14:10
 * Description:
 */
public class ChildSecondProvider extends BaseNodeProvider  {
    String[] stringItems = {"同意", "不同意"};

    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_child_second_search;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        NameInfoEntity entity = (NameInfoEntity) baseNode;
        TextView tv = holder.getView(R.id.tv_name);
        tv.setText(entity.getName());
        holder.setText(R.id.tv_depart, "(共 " + entity.getTips() + " 条)");
        ImageView ivDirector = holder.getView(R.id.iv_director);
        ivDirector.setVisibility(View.VISIBLE);
        ivDirector.setColorFilter(context.getResources().getColor(R.color.grey_home));
        if (entity.isExpanded()) {
            ivDirector.setImageResource(R.mipmap.ic_bottom_grey);
        } else {
            ivDirector.setImageResource(R.mipmap.ic_right_grey);
        }

        TextView tvStatus = holder.getView(R.id.tv_status);
        for (int i = 0; i < entity.getRowsEntities().size(); i++) {
            if (entity.getRowsEntities().get(i).getF_CurState().equals("等待审批")) {
                tvStatus.setEnabled(true);
                tvStatus.setBackgroundResource(R.drawable.select_blue_radius5);
            } else {
                tvStatus.setEnabled(false);
                tvStatus.setBackgroundResource(R.drawable.select_grey_radius5);
            }
        }

        tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionSheetDialog dialog = new ActionSheetDialog(context, stringItems, v);
                dialog.lvBgColor(Color.WHITE)
                        .cancelText(Color.RED);
                dialog.isTitleShow(false).show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            getRequest(entity.getRowsEntities(), position, tvStatus);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    private void getRequest(List<SearchEntity.DataEntity.RowsEntity> rowsEntities, int position, TextView tvStatus) {
        for (int i = 0; i < rowsEntities.size(); i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("EmployeeId", rowsEntities.get(i).getEmployeeId());
            jsonObject.addProperty("F_Id", rowsEntities.get(i).getF_Id());
            jsonObject.addProperty("F_TaskId", rowsEntities.get(i).getF_TaskId());
            jsonObject.addProperty("F_SchemeName", rowsEntities.get(i).getF_SchemeName());
            jsonObject.addProperty("verifyType", position + 1 + "");

            LatteLogger.d("getRequest", GsonBuildUtil.GsonBuilder(jsonObject));

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
                                    tvStatus.setBackgroundResource(R.drawable.select_grey_radius5);
                                    Event<String> event = new Event<String>(REFRESH_SEARCH_APPROVE, "1");
                                    EventBusUtil.sendEvent(event);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        NameInfoEntity entity = (NameInfoEntity) data;
        if (entity.isExpanded()) {
            getAdapter().collapse(position);
        } else {
            getAdapter().expand(position);
        }
    }
}














