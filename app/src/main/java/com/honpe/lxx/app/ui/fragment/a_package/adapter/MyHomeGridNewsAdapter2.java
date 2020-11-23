package com.honpe.lxx.app.ui.fragment.a_package.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.bean.NewsBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.RoundedCornersTransformation;
import com.honpe.lxx.app.utils.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/6 13:41
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class MyHomeGridNewsAdapter2 extends BaseAdapter {
    private Context context = null;
    private LayoutInflater inflater;
    private List<NewsBean.DataBean.RowsBean> mList;
    private int myType;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public MyHomeGridNewsAdapter2(Context context, List<NewsBean.DataBean.RowsBean> mList) {
        this.context = context;
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.css_icon_title2, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsBean.DataBean.RowsBean bean = mList.get(position);
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
                (new CenterCrop(),transformation, transformation1,transformation2,transformation3);
//"http://www.honpe.com:8001/20186/2018061601285094568.jpg"
        if (bean.getPicUrl() == null || "".equals(bean.getPicUrl())) {
            Glide.with(context)
                    .load(R.mipmap.ic_normal_bg)
                    .error(R.mipmap.ic_normal_bg)
                    .apply(RequestOptions.bitmapTransform(mation))
                    .into(holder.iv);
        }else {
            Glide.with(context)
                    .load(bean.getPicUrl())
                    .error(R.mipmap.ic_normal_bg)
                    .apply(RequestOptions.bitmapTransform(mation))
                    .into(holder.iv);
        }

        holder.tvTitle.setText(bean.getTitle());
        String str = bean.getAddTime();
        String[][] object ={new String[]{"T"," "}};

        Date date =  DateUtil.setDate(sf, StringUtil.replace(str,object));
        String newDate = sdf.format(date);

        holder.tvDate.setText(newDate);

        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_date)
        TextView tvDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

