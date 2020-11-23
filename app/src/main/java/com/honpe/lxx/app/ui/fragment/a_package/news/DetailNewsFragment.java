package com.honpe.lxx.app.ui.fragment.a_package.news;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.honpe.lxx.app.MainFragment;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.bean.NewsBean;
import com.honpe.lxx.app.ui.fragment.a_package.adapter.DetailNewsAdapter;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.widget.font.FontTextView4;

import butterknife.BindView;

import static com.honpe.lxx.app.api.FinalClass.video_status;

/**
 * FileName: DetailNewsFragment
 * Author: asus
 * Date: 2020/8/20 13:27
 * Description: 点击新闻更多界面
 */
public class DetailNewsFragment extends BaseBackFragment {

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
    @BindView(R.id.re_none_order)
    RelativeLayout reNoneOrder;
    @BindView(R.id.iv_no_order)
    ImageView ivNoOrder;

    private NewsBean bean;
    private int type, layoutResId;
    private DetailNewsAdapter adapter;

    public static DetailNewsFragment newInstance(int type, NewsBean bean) {
        DetailNewsFragment fragment = new DetailNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundle", bean);
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.css_title_recyclerview;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initToolbarNav(llBack);
        tvTitle.setText(getString(R.string.more));
        Bundle bundle = getArguments();
        if (bundle != null) {
            bean = (NewsBean) bundle.getSerializable("bundle");
            type = bundle.getInt("type");
        }
        recyclerView.setLayoutManager(new GridLayoutManager(_mActivity, type));
        if (type == 2) {
            layoutResId = R.layout.css_icon_title;
        } else {
            layoutResId = R.layout.css_icon_title2;
        }
        adapter = new DetailNewsAdapter(layoutResId,bean.getData().getRows());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String ids = bean.getData().getRows().get(position).getId() + "";
                start(DetailContentFragment.newInstance(getString(R.string.news_details), ids, ""));
            }
        });
    }
}

















