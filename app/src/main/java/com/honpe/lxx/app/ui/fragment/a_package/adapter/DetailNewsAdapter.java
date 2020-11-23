package com.honpe.lxx.app.ui.fragment.a_package.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.bean.NewsBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.RoundedCornersTransformation;
import com.honpe.lxx.app.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * FileName: DetailNewsAdapter
 * Author: asus
 * Date: 2020/8/20 14:06
 * Description:
 */
public class DetailNewsAdapter extends BaseQuickAdapter<NewsBean.DataBean.RowsBean, BaseViewHolder> {
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int layoutResId;

    public DetailNewsAdapter(int layoutResId, @Nullable List<NewsBean.DataBean.RowsBean> data) {
        super(layoutResId, data);
        this.layoutResId = layoutResId;
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsBean.DataBean.RowsBean item) {
        if (layoutResId == R.layout.css_icon_title) {
            //顶部左边圆角
            RoundedCornersTransformation transformation = new RoundedCornersTransformation
                    (10, 0, RoundedCornersTransformation.CornerType.TOP_LEFT);
            //顶部右边圆角
            RoundedCornersTransformation transformation1 = new RoundedCornersTransformation
                    (10, 0, RoundedCornersTransformation.CornerType.TOP_RIGHT);


            //组合各种Transformation,
            MultiTransformation<Bitmap> mation = new MultiTransformation<>
                    //Glide设置圆角图片后设置ImageVIew的scanType="centerCrop"无效解决办法,将new CenterCrop()添加至此
                    (new CenterCrop(), transformation, transformation1);

            Glide.with(getContext())
                    .load(item.getPicUrl())
                    .centerCrop()
                    .error(R.mipmap.ic_normal_bg)
                    .apply(RequestOptions.bitmapTransform(mation))
                    .into((ImageView) helper.getView(R.id.iv));
            helper.setText(R.id.tv_title, item.getTitle());
        } else {
            //顶部左边圆角
            RoundedCornersTransformation transformation = new RoundedCornersTransformation
                    (20, 0, RoundedCornersTransformation.CornerType.TOP_LEFT);
            //顶部右边圆角
            RoundedCornersTransformation transformation1 = new RoundedCornersTransformation
                    (20, 0, RoundedCornersTransformation.CornerType.TOP_RIGHT);
            //顶部左边圆角
            RoundedCornersTransformation transformation2 = new RoundedCornersTransformation
                    (20, 0, RoundedCornersTransformation.CornerType.BOTTOM_LEFT);
            //顶部右边圆角
            RoundedCornersTransformation transformation3 = new RoundedCornersTransformation
                    (20, 0, RoundedCornersTransformation.CornerType.BOTTOM_RIGHT);

            //组合各种Transformation,
            MultiTransformation<Bitmap> mation = new MultiTransformation<>
                    //Glide设置圆角图片后设置ImageVIew的scanType="centerCrop"无效解决办法,将new CenterCrop()添加至此
                    (new CenterCrop(), transformation, transformation1, transformation2, transformation3);

            Glide.with(getContext())
                    .load(item.getPicUrl())
                    .error(R.mipmap.ic_normal_bg)
                    .apply(RequestOptions.bitmapTransform(mation))
                    .into((ImageView) helper.getView(R.id.iv));

            helper.setText(R.id.tv_title, item.getTitle());
            String str = item.getAddTime();
            String[][] object = {new String[]{"T", " "}};

            Date date = DateUtil.setDate(sf, StringUtil.replace(str, object));
            String newDate = sdf.format(date);
            helper.setText(R.id.tv_date, newDate);
        }
    }
}




















