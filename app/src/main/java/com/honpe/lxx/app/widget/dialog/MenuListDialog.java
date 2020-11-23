package com.honpe.lxx.app.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child1.bean.StatisticsBean2;
import com.honpe.lxx.app.ui.main.oa.ui.position16.viewPagerChild.child2.adapter.BaseSelectStatisticsAdapter;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.honpe.lxx.app.api.FinalClass.Session;

/**
 * FileName: MenuListDialog
 * Author: asus
 * Date: 2020/10/16 12:24
 * Description: 食堂菜谱
 */
public class MenuListDialog extends Dialog {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_theme_title)
    TextView tvThemeTitle;
    @BindView(R.id.tv_theme_tips)
    TextView tvThemeTips;
    @BindView(R.id.tv_theme_add)
    TextView tvThemeAdd;

    @BindView(R.id.iv_no_order)
    ImageView ivNoOrder;
    Context context;
    String session, strDate, strTitle;
    List<BaseNode> list = new ArrayList<>();
    SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
    SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");

    public MenuListDialog(@NonNull Context context, String date, String title) {
        super(context);
        this.context = context;
        this.strDate = date;
        this.strTitle = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.css_title_recyclerview);
        ButterKnife.bind(this);
        llBack.setVisibility(View.GONE);
        llTitleBar.setVisibility(View.GONE);
        GetNetRequest();
    }


    private void GetNetRequest() {
        session = (String) MyApplication.get(context, Session, "");
        recyclerView.setBackgroundResource(R.color.white);
        EasyHttp.post(Constants.YGORDERMENULIST + session)
                .params("StartTime", strDate)
                .params("EndTime", strDate)
                .execute(new SimpleCallBack<String>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("fragment2", s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            if (obj.getString("Msg").contains("session expired") || obj.getString("Msg").contains("Invalid Session.")) {
                                ToastUtil.getInstance().showToast(obj.getString("Msg"));
                                dismiss();
                            } else {
                                StatisticsBean2 bean = Convert.fromJson(s, StatisticsBean2.class);

                                LatteLogger.d("testGson", GsonBuildUtil.GsonBuilder(bean));
                                if (bean.getData().size() > 0) {
                                    recyclerView.setVisibility(View.VISIBLE);
                                    BaseSelectStatisticsAdapter sectionAdapter = new BaseSelectStatisticsAdapter();
                                    final GridLayoutManager manager = new GridLayoutManager(context, 3);
                                    recyclerView.setLayoutManager(manager);

                                    recyclerView.setAdapter(sectionAdapter);
                                    sectionAdapter.setList(getJsonData(bean.getData()));
                                    sectionAdapter.addChildClickViewIds(R.id.tv_title);
                                    llTitleBar.setVisibility(View.VISIBLE);
                                    tvTitle.setText(sdf.format(DateUtil.setDate(sf,strTitle)) + " 菜谱");
                                    sectionAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                                        @Override
                                        public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                                            dismiss();
                                        }
                                    });

                                } else {
                                    recyclerView.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private List<BaseNode> getJsonData(List<StatisticsBean2.DataBean> data) {
        list.clear();
        for (int i = 0; i < data.size(); i++) {
            StatisticsBean2.DataBean bean = data.get(i);
            List<BaseNode> items = new ArrayList<>();
            for (int j = 0; j < bean.getDishesDetails().size(); j++) {
                StatisticsBean2.DataBean.DishesDetailsBean bean1 = bean.getDishesDetails().get(j);
                items.add(bean1);
            }
            bean.setChildNode(items);
            list.add(bean);
        }
        return list;
    }
}






















