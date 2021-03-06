package com.honpe.lxx.app.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.api.Constants;
import com.just.agentweb.AgentWeb;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.honpe.lxx.app.MyApplication.getContext;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/6 13:47
 * @Author: 李熙祥
 * @Description: java类作用描述 字符串工具
 */
public class StringUtil {

    /**
     * 复制字符串
     *
     * @param context
     * @param text
     */
    public static void toCopy(Context context, String text) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    public static void openBrowser(String targetUrl) {
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            Toast.makeText(getContext(), targetUrl + " 该链接无法使用浏览器打开。", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri mUri = Uri.parse(targetUrl);
        intent.setData(mUri);
        getContext().startActivity(intent);
    }

    /**
     * 清除 WebView 缓存
     */
    public static void toCleanWebCache(AgentWeb mAgentWeb) {
        if (mAgentWeb != null) {
            //清理所有跟WebView相关的缓存 ，数据库， 历史记录 等。
            mAgentWeb.clearWebCache();
            ToastUtil.getInstance().showToast("已清理缓存");
            //清空所有 AgentWeb 硬盘缓存，包括 WebView 的缓存 , AgentWeb 下载的图片 ，视频 ，apk 等文件。
//            AgentWebConfig.clearDiskCache(this.getContext());
        }
    }


    /**
     * 测试错误页的显示
     */
    public static void loadErrorWebSite(AgentWeb mAgentWeb) {
        if (mAgentWeb != null) {
            mAgentWeb.getUrlLoader().loadUrl("http://www.unkownwebsiteblog.me");
        }
    }

    /**
     * 截取部分字分串
     *
     * @param sourceString
     * @param objects
     * @return
     */
    public static String replace(String sourceString, Object[] objects) {
        String temp = sourceString;
        for (int i = 0; i < objects.length; i++) {
            String[] result = (String[]) objects[i];
            Pattern pattern = Pattern.compile(result[0]);
            Matcher matcher = pattern.matcher(temp);
            temp = matcher.replaceAll(result[1]);
        }
        return temp;
    }

    /**
     * 去除数组空元素
     *
     * @param strArray
     * @return
     */
    public static String[] removeArrayEmptyTextBackNewArray(String[] strArray) {
        List<String> strList = Arrays.asList(strArray);
        List<String> strListNew = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            if (strList.get(i) != null && !strList.get(i).equals("")) {
                strListNew.add(strList.get(i));
            }
        }
        String[] strNewArray = strListNew.toArray(new String[strListNew.size()]);
        return strNewArray;
    }

    /**
     * 去除数组中重复的元素
     *
     * @param arr
     * @return
     */
    public static String[] ifRepeart(String[] arr) {
        List<String> list = new ArrayList<>();
        for (String str : arr) {
            if (!list.contains(str)) {
                list.add(str);
            }
        }
        String[] strs = list.toArray(new String[list.size()]);
        return strs;
    }


    //1. 循环list中的所有元素然后删除重复
    public static List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    //2. 通过HashSet踢除重复元素
    public static List removeDuplicate2(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    //3. 删除ArrayList中重复元素，保持顺序
    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        System.out.println(" remove duplicate " + list);
    }

    //4.把list里的对象遍历一遍，用list.contain()，如果不存在就放入到另外一个list集合中
    public static List removeDuplicate4(List list) {
        List listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!listTemp.contains(list.get(i))) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    /**
     * 字符串不区分大小写
     */
    public static boolean igonreCaseEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

    //返回一个网页 没有带前缀
    public static String[] returnImageUrlsFromHtml2(String data) {
        List<String> imageSrcList = new ArrayList<String>();
        //  String htmlCode = returnExampleHtml();
        String htmlCode = data;
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            Log.e("imageSrcList", "资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }

    /**
     * 首行缩进的SpannableString
     *
     * @param label       标签信息
     * @param description 描述信息
     * @param dimen       描述信息  R.dimen.label_size
     */
    private SpannableString getSpannableString(Context context, String label, int dimen, String description) {
        SpannableString spannableString = new SpannableString(description);
        int marginSpanSize = (int) (label.length() * context.getResources().getDimension(dimen)
                + Utils.dp2px(context, 6));//文字宽度+ 背景padding4dp+间隔2dp
        LeadingMarginSpan leadingMarginSpan = new LeadingMarginSpan.Standard(marginSpanSize, 0);//仅首行缩进
        spannableString.setSpan(leadingMarginSpan, 0, description.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }


    /**
     * 返回一个网页
     */
    public static String[] returnImageUrlsFromHtml(String data) {
        List<String> imageSrcList = new ArrayList<String>();
        //  String htmlCode = returnExampleHtml();
        String htmlCode = data;
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(Constants.path + src);
        }
        if (imageSrcList == null || imageSrcList.size() == 0) {
            Log.e("imageSrcList", "资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }

    /**
     * 是否有邮箱
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 是否有密码
     *
     * @param pass
     * @return
     */
    public static boolean isPass(String pass) {
        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(pass);
        return m.matches();
    }

    /**
     * 是否有电话
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isPhone(String phoneNumber) {
        String pho = "^1[0-9]{10}$";
        Pattern p = Pattern.compile(pho);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    //=========================================================================

    /**
     * 检查数组是否包含某个值
     */
    public static boolean useList(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    public static String useList2(String[] arr, String targetValue) {
       boolean b =  Arrays.asList(arr).contains(targetValue);
        if (b) {
            return targetValue;
        }else {
            return "没有这个字符";
        }
    }

    //使用Set
    public static boolean useSet(String[] arr, String targetValue) {
        Set<String> set = new HashSet<String>(Arrays.asList(arr));
        return set.contains(targetValue);
    }

    //使用循环判断
    public static boolean useLoop(String[] arr, String targetValue) {
        for (String s : arr) {
            if (s.equals(targetValue))
                return true;
        }
        return false;
    }

    //查找有序数组中是否包含某个值的用法
    public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
        int a = Arrays.binarySearch(arr, targetValue);
        if (a > 0)
            return true;
        else
            return false;
    }

    //=========================================================================
    public static String returnExampleHtml() {

        return "<img src=\"/uploadfile/image/20170628/20170628155301_17333.jpg\" alt=\"\" />&nbsp;<img src=\"/uploadfile/image/20170628/20170628155301_84385.jpg\" alt=\"\" />";
    }

    /**
     * 代码实现editText提示文字
     */
    public static SpannableString editHint(String hint) {
        SpannableString s = new SpannableString(hint);
        return s;
    }

    /**
     * editText hint文字大小
     *
     * @param et
     * @param content
     */
    public static void HintUtil(EditText et, CharSequence content) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(content);
        // 新建 一个属性对象,设置文字的大小,
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        //附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置hint
        et.setHint(new SpannableString(ss)); // 一定要进行转换,否则属性会消失
    }

    public static void HintUtil(TextView et, CharSequence content) {
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString(content);
        // 新建 一个属性对象,设置文字的大小,
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
        //附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置hint
        et.setHint(new SpannableString(ss)); // 一定要进行转换,否则属性会消失
    }

    /**
     * 删除集合中所有重复的元素
     *
     * @param list
     */
    public static void DeleteArraySingleStr(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                    list.remove(i);
                }
            }
        }
    }


    /**
     * 删除集合中重复的元素保留维一一个元素
     *
     * @param list
     */
    public static void DeleteArrayRepeatStr(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
    }

    /**
     * 将数据按照名称分组
     *
     * @param list 需要被改变的json
     * @param name 按照name来提取
     * @return
     */
    public static List group(List<Map> list, String name) {
        List<Map> newList = new ArrayList<>();
        /*
         *第一次外循环与内循环完成以name为比较准则的封装
         */
        for (int i = 0; i < list.size(); i++) {
            //用map接收list中一个键值对
            Map<String, Object> dt_i = list.get(i);
            if (!dt_i.containsKey(name))
                continue;
            String date1 = dt_i.get(name).toString();

            Map<String, Object> res = new HashMap<String, Object>();
            res.put(name, date1);

            //用于存放第i次的比较后的结果
            List lst_1 = new ArrayList();

            for (int j = 0; j < list.size(); j++) {
                Map<String, Object> dt_j = list.get(j);
                if (!dt_j.containsKey(name))
                    continue;
                String date2 = dt_j.get(name).toString();

                if (date1 == date2 || date1.equals(date2)) {

                    //深拷贝当前第j条的json数组
                    Map<String, Object> dt1 = new HashMap<String, Object>();
                    dt1.putAll(dt_j);
                    dt1.remove(name);

                    //将数据按照json的形式存储，方便前端解析
                    String dataJson = new Gson().toJson(dt1);
                    lst_1.add(dataJson);
                }

            }
            res.put("data", lst_1);
            newList.add(res);
        }

        /*
         * 将封装后的结果进行去重复
         */
        for (int i = 0; i < newList.size(); i++) {
            Map<String, Object> dt_i = newList.get(i);
            if (!dt_i.containsKey(name))
                continue;
            String date1 = dt_i.get(name).toString();
            for (int j = newList.size() - 1; j > i; j--) {
                Map<String, Object> dt_j = newList.get(j);
                if (!dt_j.containsKey(name))
                    continue;
                String date2 = dt_j.get(name).toString();
                if (date1 == date2 || date1.equals(date2)) {
                    newList.remove(j);
                }
            }
        }
        return newList;
    }

    //判断Java数组是否包含某个值
    public static boolean findStr(String[] args, String str) {
        boolean result = false;
        //第一种：list
        result = Arrays.asList(args).contains(str);
        //第二种: set
        Set<String> sets = new HashSet<>(Arrays.asList(args));
        result = sets.contains(str);
        //第三种：loop
        for (String s : args) {
            if (s.equals(str)) {
                return true;
            }
        }
        //第四种：binarySearch(Arrays的binarySearch方法必须应用于有序数组)
        int res = Arrays.binarySearch(args, str);
        if (res > 0) {
            return true;
        }
        return result;
    }

    public static String md5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 数组转集合
     */
    public static List<String> ArrToList(String[] strArr) {
        return Arrays.asList(strArr);
    }

    /**
     * 集合转数组
     */
    public static String[] ListToArr(List<String> list) {
        return list.toArray(new String[]{});
    }

    /**
     * 去掉字符串最后一位字任
     *
     * @param str
     * @param index 去掉后面几位字符
     * @return
     */
    public static String deleteLastStr(String str, int index) {
        return str.substring(0, str.length() - index);
    }

    /**
     * 获得字符串最后一位字符
     *
     * @return
     */
    public static String getLastStr(String str) {
        return str.substring(str.length() - 1, str.length());
    }

    /**
     * 没有订单时
     */
    public static void getUnOrder(TextView tvTitle, TextView tvTips, TextView tvAddOrder, String strTitle, String strTips, String strAdd) {
        tvTitle.setText(strTitle);
        tvTips.setText(strTips);
        tvAddOrder.setText(strAdd);
    }

    /**
     * textView 显示文字某几个字改变颜色
     *
     * @param str   需要修改的字符串
     * @param color 改变字符的颜色
     * @param start 开始位置
     * @param end   结束位置
     */
    public static SpannableString changeFontColor(String str, int color, int start, int end) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan
                (MyApplication.getContext().getResources().getColor(color)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    /**
     * 获取全拼
     *
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] chars = null;
        chars = src.toCharArray();
        String[] strings = new String[chars.length];
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String result = "";
        int t0 = chars.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(chars[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    strings = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
                    result += strings[0];
                } else {
                    result += java.lang.Character.toString(chars[i]);
                }
            }
            return result;
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取所有中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 获取第一个中文首字母并装换为大写
     *
     * @param str
     * @return
     */

    public static String getHeadCharUpper(String str) {
        String convert = "";
        for (int i = 0; i < str.length(); i++) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        char charAt = convert.charAt(0);
        convert = String.valueOf(charAt).toUpperCase();
        return convert;
    }

    /**
     * 获取第一个字的拼音首字母
     *
     * @param chinese
     * @return
     */
    public static String getFirstSpell(String chinese) {
        StringBuffer pinYinBF = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char curChar : arr) {
            if (curChar > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curChar, defaultFormat);
                    if (temp != null) {
                        pinYinBF.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinYinBF.append(curChar);
            }
        }
        return pinYinBF.toString().replaceAll("\\W", "").trim();
    }

    public static void main(String[] args) {
        String cnStr = "拼音转换";
        System.out.println("转换拼音 : " + getPingYin(cnStr));
        System.out.println("所有首字母 : " + getPinYinHeadChar(cnStr));
        System.out.println("第一个字首字母 : " + getHeadCharUpper(cnStr));
    }
    // 工具类 绘制不同状态的颜色
    /**
     * 对TextView设置不同状态时其文字颜色
     * @param normal
     * @param pressed
     * @param focused
     * @param unable
     * @return
     */
    public static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}


