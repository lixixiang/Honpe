package com.honpe.lxx.app.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.bean.UpdateBean;
import com.honpe.lxx.app.ui.fragment.d_package.setting.ConnNetworkState;
import com.honpe.lxx.app.ui.login.LoginFragment;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.honpe.lxx.app.MyApplication.getContext;
import static com.honpe.lxx.app.api.FinalClass.UPDATA_CLIENT;


/**
 * created by lxx at 2019/11/12 10:59
 * 描述:
 */
public class Utils {
    private static Map<String, BaseBackFragment> fragmentList = new HashMap<>();
    private static InputMethodManager imm;

    /**
     * 根据Class创建Fragment
     *
     * @param clazz the Fragment of create
     * @return
     */
    public static BaseBackFragment createFragment(Class<?> clazz, boolean isObtain) {
        BaseBackFragment resultFragment = null;
        String className = clazz.getName();
        if (fragmentList.containsKey(className)) {
            resultFragment = fragmentList.get(className);
        } else {
            try {
                try {
                    resultFragment = (BaseBackFragment) Class.forName(className).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (isObtain)
                fragmentList.put(className, resultFragment);
        }

        return resultFragment;
    }

    public static BaseBackFragment createFragment(Class<?> clazz) {
        return createFragment(clazz, true);
    }


    /**
     * 设置edit hint 大小
     *
     * @param edit
     * @param hintSize
     * @param content  设置里面的内容
     */
    public static void setEditTextHint(EditText edit, int hintSize, String content) {
        //设置hint 大小
        SpannableString ss = new SpannableString(content);
        AbsoluteSizeSpan as = new AbsoluteSizeSpan(hintSize, true);//设置字体大小 true表示单位是sp
        ss.setSpan(as, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        edit.setHint(new SpannableString(ss));
    }


    /**
     * 设置radioButton 图片大小和位置摆放
     *
     * @param context  上下文
     * @param button   按钮
     * @param icon     图标
     * @param left     左边距离
     * @param top      上边距离
     * @param right    右边距离
     * @param bottom   下边距离
     * @param position 图片的位置 0 左 1 上 2 右 3 下
     */
    public static void setRadioIconSize(Context context, RadioButton button, int icon, int left, int top, int right, int bottom, int position) {
        //定义底部标签图片大小和位置
        Drawable drawable_news = context.getResources().getDrawable(icon);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(left, top, right, bottom);
        //设置图片在文字的哪个方向
        if (position == 0) {
            button.setCompoundDrawables(drawable_news, null, null, null);
        } else if (position == 1) {
            button.setCompoundDrawables(null, drawable_news, null, null);
        } else if (position == 2) {
            button.setCompoundDrawables(null, null, drawable_news, null);
        } else if (position == 3) {
            button.setCompoundDrawables(null, null, null, drawable_news);
        }
    }

    /**
     * dp转dip
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5F);
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕分辨率
     *
     * @param context
     * @return
     */
    public static String getPhoneSize(final Context context) {
        DisplayMetrics dm;
        dm = context.getResources().getDisplayMetrics();

       /* int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;*/
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：480px）
        int screenHeight = dm.heightPixels; // 屏幕高（像素，如：800px）

        return screenWidth + "*" + screenHeight;
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static float getPhoneWith() {
        DisplayMetrics outsize = getContext().getResources().getDisplayMetrics();
        return outsize.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static float getPhoneHeight() {
        DisplayMetrics outsize = getContext().getResources().getDisplayMetrics();
        return outsize.heightPixels;
    }


    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 获取屏幕高
     *
     * @return
     */
    public static int getWindowHeight() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point.y;
    }



    /**
     * 文本中的emojb字符处理为表情图片
     *
     * @param context
     * @param tv
     * @param source
     * @return
     */
    public static SpannableString getEmotionContent(final Context context, final TextView tv, String source) {
        SpannableString spannableString = new SpannableString(source);
        Resources res = context.getResources();

        String regexEmotion = "\\[([\u4e00-\u9fa5\\w])+\\]";
        Pattern patternEmotion = Pattern.compile(regexEmotion);
        Matcher matcherEmotion = patternEmotion.matcher(spannableString);

        while (matcherEmotion.find()) {
            // 获取匹配到的具体字符
            String key = matcherEmotion.group();
            // 匹配字符串的开始位置
            int start = matcherEmotion.start();
            // 利用表情名字获取到对应的图片
            Integer imgRes = EmotionUtil.EMOTION_STATIC_MAP.get(key);
            if (imgRes != null) {
                // 压缩表情图片
                int size = (int) tv.getTextSize() * 13 / 8;
                Bitmap bitmap = BitmapFactory.decodeResource(res, imgRes);
                Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

                ImageSpan span = new ImageSpan(context, scaleBitmap);
                spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }

    /**
     * 返回当前时间的格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(System.currentTimeMillis());
    }

    //毫秒转秒
    public static String long2String(long time) {
        //毫秒转秒
        int sec = (int) time / 1000;
        int min = sec / 60;    //分钟
        sec = sec % 60;        //秒
        if (min < 10) {    //分钟补0
            if (sec < 10) {    //秒补0
                return "0" + min + ":0" + sec;
            } else {
                return "0" + min + ":" + sec;
            }
        } else {
            if (sec < 10) {    //秒补0
                return min + ":0" + sec;
            } else {
                return min + ":" + sec;
            }
        }
    }

    /**
     * 毫秒转化时分秒毫秒
     *
     * @param ms
     * @return
     */
    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "d");
        }
        if (hour > 0) {
            sb.append(hour + "h");
        }
        if (minute > 0) {
            sb.append(minute + "′");
        }
        if (second > 0) {
            sb.append(second + "″");
        }
        return sb.toString();
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {//获取版本号(内部识别号)
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 保存当前版本号
     *
     * @param context
     * @param prefName
     * @param value
     */
    public static void putAppPrefInt(Context context, String prefName, int value) {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(prefName, value);
            if (Build.VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        }
    }

    /**
     * 获取版本信息
     *
     * @param context
     * @return
     */
    public static String getVersionDes(Context context) {//获取版本号
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本版本SDCard
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static void showSoftInput(Context context, View view) {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isShowSoftInput(Context context) {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        //获取状态信息
        return imm.isActive();//true 打开
    }

    public static void hideSoftKeyBoard(Activity activity) {
        View localView = activity.getCurrentFocus();
        if (imm == null) {
            imm = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (imm != null)) {
            imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    //file转uri:URI uri = file.toURI();
//uri转file:file = new File(new URI(uri.toString()));

    /**
     * file 转 uri
     */
    public static URI FileToUri(File file) {
        return file.toURI();
    }

    /**
     * uri 转 file
     */
    public static File UriToFile(Uri uri) {
        File FileToUri = null;
        try {
            FileToUri = new File(new URI(uri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return FileToUri;
    }

    /**
     * bitmap 转 uri 做分享用
     */
    public static Uri BitmapToUri(Context context, Bitmap bitmap) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null));
        return uri;
    }

    /**
     * @param array
     * @return 集合转数组
     */
    public static String[] ListToString(List<?> array) {
        return array.toArray(new String[array.size()]);
    }

    /**
     * 获取布局高度
     *
     * @param child
     * @return
     */
    public static float getRealHeight(View child) {
        LinearLayout llayout = (LinearLayout) child;
        ViewGroup.LayoutParams params = llayout.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, params.width);
        int heightSpec;
        if (params.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(params.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        llayout.measure(widthSpec, heightSpec);
        return llayout.getMeasuredHeight();//获取布局高度，获取布局宽度可以调用getMeasuredWidth()
    }

    public static float getRealHeight2(View child) {
        FrameLayout llayout = (FrameLayout) child;
        ViewGroup.LayoutParams params = llayout.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, params.width);
        int heightSpec;
        if (params.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(params.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        llayout.measure(widthSpec, heightSpec);
        return llayout.getMeasuredHeight();//获取布局高度，获取布局宽度可以调用getMeasuredWidth()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 图片自适应
     */
    public static void screenImageView(ImageView icon) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, MyApplication.getContext().getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        icon.setLayoutParams(params);
    }

    /**
     * 图片自适应2
     */
    public static void screenImageView(ImageView icon, int dimen) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimen, MyApplication.getContext().getResources().getDisplayMetrics());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
        icon.setLayoutParams(params);
    }

    /**
     * 跳转到TotalQueryActivity
     */
//    public static void start(Activity cls, int index, String msg) {
//        Intent intent = new Intent(getContext(), TotalQueryActivity.class);
//        intent.putExtra("tag", index);
//        intent.putExtra("title", msg);
//        cls.startActivity(intent);
//    }


//    public static void start(Activity cls, Bundle bundle) {
//        Intent intent = new Intent(getContext(), TotalQueryActivity.class);
//        intent.putExtras(bundle);
//        cls.startActivity(intent);
//    }

    /**
     * 顶部头像
     */
    public static void headIcon(Context context, String headIcon, ImageView ivIcon) {
        if ("".equals(headIcon)) {
            ivIcon.setImageResource(R.drawable.selector_men);
        } else {
            Glide.with(context).load(headIcon).into(ivIcon);
        }
    }


    /**
     * 检测是否过期
     */
    /**
     * 员工级别
     */
    public static void checkSession(BaseBackFragment fragment, String session) {
        EasyHttp.post(Constants.ADD_MOBE_DELETER + session)
                .params("ModuleId", "D01")
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        ProgressUtils.disLoadView(MyApplication.getContext(), 1);
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        ProgressUtils.disLoadView(MyApplication.getContext(), 0);
                    }

                    @Override
                    public void onError(ApiException e) {
                        ProgressUtils.disLoadView(MyApplication.getContext(), 0);
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        LatteLogger.d("checkSession", result);
                        try {
                            JSONObject obj = new JSONObject(result);
                            int isSuccess = obj.getInt("Status");
                            LatteLogger.d("isSuccess", isSuccess);
                            if (isSuccess == -1) {
                                fragment.startWithPop(LoginFragment.newInstance(fragment.getTag()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    public static void NewVersionRequest(Context context) {
        EasyHttp.post(Constants.APPDownload)
                .params("app_ver", String.valueOf(Utils.getVersionCode(context)))
                .params("app_desc", String.valueOf(Utils.getVersionCode(context)))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (e.getCode() == 1009) {
                            new ConnNetworkState(context).checkNetworkState();
                        } else {
                            ToastUtil.getInstance().showToast(e.getMessage());
                        }
                    }

                    @Override
                    public void onSuccess(String result) {
                        UpdateBean updateBean = Convert.fromJson(result, UpdateBean.class);
                        LatteLogger.d("updateResult", GsonBuildUtil.GsonBuilder(updateBean));
                        if (updateBean.getStatus() == 0) {
                            Event<UpdateBean> event = new Event<UpdateBean>(UPDATA_CLIENT, updateBean);
                            EventBusUtil.sendEvent(event);
//                            int num = Integer.parseInt(updateBean.getData().getVer());
//                            if (num == Utils.getVersionCode(context)) {
//                                EventBusUtil.sendEvent(new Event(UPDATA_NONEED));
//                            } else if (Utils.getVersionCode(context) < num) {
//                                EventBusUtil.sendEvent(new Event(UPDATA_CLIENT));
//                            }
                        } else {
                            ToastUtil.getInstance().showToast(updateBean.getMsg());
                        }
                    }
                });
    }

    /**
     * 倒序
     */
    public static void reverse(List<?> list) {
        Collections.reverse(list);
    }

    public static void removeParent(View v) {
        //  先找到爹 在通过爹去移除孩子
        ViewParent parent = v.getParent();
        //所有的控件 都有爹  爹一般情况下 就是ViewGoup
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(v);
        }
    }

    public static Integer[] getWidthAndHeight(Window window) {
        if (window == null) {
            return null;
        }
        Integer[] integer = new Integer[2];
        DisplayMetrics dm = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        } else {
            window.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        integer[0] = dm.widthPixels;
        integer[1] = dm.heightPixels;
        return integer;
    }

    /**
     * 检查包是否存在
     *
     * @param
     * @return
     */
    public static boolean checkPackInfo(Context context) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    /**
     * TextView Size 适配大小
     */
    public static void TextSize(TextView tv,int size){
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,Utils.getFontSize(size));
    }

    /**
     * 文字大小
     *
     * @param textSize
     * @return
     */
    public static int getFontSize(int textSize) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        int screenHeight = dm.heightPixels;
        int rate = (int) (textSize * (float) screenHeight / 1920);
        return rate;
    }

    /**
     * 编缉框是否可以点击有效
     * @param et
     * @param click
     * @return
     */
    public static void editIsEnable(EditText et,boolean click){
        et.setFocusable(click);
        et.setFocusableInTouchMode(click);
        et.setEnabled(click);
    }

    public static int getNavigationBarHeight(Context context) {
        boolean mInPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (hasNavBar((Activity) context)) {
                String key;
                if (mInPortrait) {
                    key = "navigation_bar_height";
                } else {
                    key = "navigation_bar_height_landscape";
                }
                return getInternalDimensionSize(context, key);
            }
        }
        return result;
    }
    private static boolean hasNavBar(Activity activity) {
        //判断小米手机是否开启了全面屏,开启了，直接返回false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Settings.Global.getInt(activity.getContentResolver(), "force_fsg_nav_bar", 0) != 0) {
                return false;
            }
        }
        //其他手机根据屏幕真实高度与显示高度是否相同来判断
        WindowManager windowManager = activity.getWindowManager();
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }

    private static int getInternalDimensionSize(Context context, String key) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier(key, "dimen", "android");
            if (resourceId > 0) {
                result = Math.round(context.getResources().getDimensionPixelSize(resourceId) *
                        Resources.getSystem().getDisplayMetrics().density /
                        context.getResources().getDisplayMetrics().density);
            }
        } catch (Resources.NotFoundException ignored) {
            return 0;
        }
        return result;
    }
}


