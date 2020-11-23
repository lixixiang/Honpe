package com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.provide;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.google.gson.JsonObject;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.ui.main.oa.ui.position14.entity.LevelEntity;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.BuildFloorAdapter.ITEM_ADD;
import static com.honpe.lxx.app.ui.main.oa.ui.position14.BuildFragment.adapter.BuildFloorAdapter.build3;

/**
 * FileName: BuildProvider1
 * Author: asus
 * Date: 2020/9/16 11:52
 * Description:
 */
public class Build3Provider extends BaseNodeProvider {
    public static final int ITEM_DELETE = 901;

    @Override
    public int getItemViewType() {
        return build3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.css_build3;
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode baseNode) {
        LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = (LevelEntity.FloorEntity.RoomEntity.BedEntity) baseNode;

        ImageView ivDelete = holder.getView(R.id.iv_delete);
        ImageView ivAddWithMob = holder.getView(R.id.iv_add_mob);
        ImageView ivBed = holder.getView(R.id.iv_bed);
        View line = holder.getView(R.id.view);

        ivDelete.setImageResource(R.mipmap.ic_delete);
        ivAddWithMob.setImageResource(R.mipmap.ic_add);
        ivBed.setImageResource(R.mipmap.ic_empty_bed);
        line.setVisibility(View.GONE);
        ivDelete.setVisibility(View.GONE);

        holder.setText(R.id.tv_BeNum, bedEntity.getNumbeds());
        holder.setText(R.id.tv_depart, bedEntity.getDept_dep());

        if ("".equals(bedEntity.getEmp_username())) {

        }

        if (bedEntity.getEmp_usersex().equals("男")) {
            holder.setText(R.id.tv_name_sex, bedEntity.getEmp_username() + " " + "男" + "    工号：" + bedEntity.getEmp_usernum());
            holder.setTextColorRes(R.id.tv_name_sex, R.color.green);
        } else if (bedEntity.getEmp_usersex().equals("女")) {
            holder.setText(R.id.tv_name_sex, bedEntity.getEmp_username() + " " + "女" + "    工号：" + bedEntity.getEmp_usernum());
            holder.setTextColorRes(R.id.tv_name_sex, R.color.google_red);
        } else {
            holder.setText(R.id.tv_name_sex, "空");
            holder.setTextColorRes(R.id.tv_name_sex, R.color.grey_dark);
        }

        if (!"".equals(bedEntity.getEmp_username())) {
            ivDelete.setImageResource(R.mipmap.ic_delete);
            ivAddWithMob.setImageResource(R.mipmap.ic_mend);
            ivBed.setImageResource(R.mipmap.ic_somebody_bed);
            line.setVisibility(View.VISIBLE);
            ivDelete.setVisibility(View.VISIBLE);
        }

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NormalDialog dialog1 = new NormalDialog(getContext());
                dialog1.isTitleShow(false)
                        .content("是否要删除" + "\"" + bedEntity.getEmp_username() + "\"" + "的床位?")
                        .cornerRadius(5)
                        .contentTextSize(13)
                        .btnTextSize(12f, 12f)
                        .widthScale(0.7f)
                        .contentGravity(Gravity.CENTER)
                        .btnTextColor(Color.RED, Color.BLUE)
                        .btnPressColor(Color.LTGRAY)
                        .show();
                dialog1.setOnBtnClickL(new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog1.dismiss();
                    }
                }, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        SubDeleteOrder(bedEntity, holder.getAdapterPosition());
                        LatteLogger.d("testDDDDD0", holder.getAdapterPosition());
                        dialog1.dismiss();
                    }
                });
            }
        });

        ivAddWithMob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bedEntity.setIndex(holder.getAdapterPosition());
                Event<LevelEntity.FloorEntity.RoomEntity.BedEntity> entityEvent
                        = new Event<LevelEntity.FloorEntity.RoomEntity.BedEntity>(FinalClass.ADD_CHECK_IN_DORM, bedEntity);
                EventBusUtil.sendEvent(entityEvent);
            }
        });
    }

    @Override
    public void convert(@NotNull BaseViewHolder holder, BaseNode item, @NotNull List<?> payloads) {
        LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity = (LevelEntity.FloorEntity.RoomEntity.BedEntity) item;
        ImageView ivDelete = holder.getView(R.id.iv_delete);
        ImageView ivAddWithMob = holder.getView(R.id.iv_add_mob);
        ImageView ivBed = holder.getView(R.id.iv_bed);
        View line = holder.getView(R.id.view);
        holder.setText(R.id.tv_depart, bedEntity.getDept_dep());
        for (Object p : payloads) {
            int payload = (int) p;
            if (payload == ITEM_DELETE) {
                ivAddWithMob.setImageResource(R.mipmap.ic_add);
                ivBed.setImageResource(R.mipmap.ic_empty_bed);
                line.setVisibility(View.GONE);
                ivDelete.setVisibility(View.GONE);
                holder.setText(R.id.tv_name_sex, "空");
                if (bedEntity.getEmp_usersex().equals("男")) {
                    holder.setText(R.id.tv_name_sex, bedEntity.getEmp_username() + " " + "男" + "    工号：" + bedEntity.getEmp_usernum());
                    holder.setTextColorRes(R.id.tv_name_sex, R.color.green);
                } else if (bedEntity.getEmp_usersex().equals("女")) {
                    holder.setText(R.id.tv_name_sex, bedEntity.getEmp_username() + " " + "女" + "    工号：" + bedEntity.getEmp_usernum());
                    holder.setTextColorRes(R.id.tv_name_sex, R.color.google_red);
                }
            } else if (payload == ITEM_ADD) {
                ivDelete.setImageResource(R.mipmap.ic_delete);
                ivAddWithMob.setImageResource(R.mipmap.ic_mend);
                ivBed.setImageResource(R.mipmap.ic_somebody_bed);
                line.setVisibility(View.VISIBLE);
                ivDelete.setVisibility(View.VISIBLE);

                if (bedEntity.getEmp_usersex().equals("男")) {
                    holder.setText(R.id.tv_name_sex, bedEntity.getEmp_username() + " " + "男" + "    工号：" + bedEntity.getEmp_usernum());
                    holder.setTextColorRes(R.id.tv_name_sex, R.color.green);
                } else if (bedEntity.getEmp_usersex().equals("女")) {
                    holder.setText(R.id.tv_name_sex, bedEntity.getEmp_username() + " " + "女" + "    工号：" + bedEntity.getEmp_usernum());
                    holder.setTextColorRes(R.id.tv_name_sex, R.color.google_red);
                }
                holder.setText(R.id.tv_BeNum, bedEntity.getNumbeds());
            }
        }
    }

    private void SubDeleteOrder(LevelEntity.FloorEntity.RoomEntity.BedEntity bedEntity, int position) {
        String EmployeeId = (String) MyApplication.get(getContext(), "EmployeeId", "");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("EmployeeId", EmployeeId);
        jsonObject.addProperty("strF_ID", bedEntity.getF_id());
        jsonObject.addProperty("strUserID", "");
        LatteLogger.d("AppSaveUser", GsonBuildUtil.GsonBuilder(jsonObject));

        EasyHttp.post(Constants.AppSaveUser)
                .retryCount(0)
                .upJson(GsonBuildUtil.NullToString().toJson(jsonObject))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("subResult", s);
                        GsonBuildUtil.ResultMsg(s);
                        bedEntity.setNumbeds(bedEntity.getNumbeds());
                        bedEntity.setDept_dep("");
                        bedEntity.setEmp_usersex("");
                        bedEntity.setEmp_username("");
                        bedEntity.setUserid("");
                        bedEntity.setF_id(bedEntity.getF_id());
                        bedEntity.setF_fullname(bedEntity.getF_fullname());
                        getAdapter().getData().set(position, bedEntity);
                        getAdapter().notifyItemChanged(position, ITEM_DELETE);
                    }
                });
    }
}




