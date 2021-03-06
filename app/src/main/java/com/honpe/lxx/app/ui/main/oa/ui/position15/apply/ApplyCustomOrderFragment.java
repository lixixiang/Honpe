package com.honpe.lxx.app.ui.main.oa.ui.position15.apply;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.google.gson.Gson;
import com.honpe.lxx.app.MyApplication;
import com.honpe.lxx.app.R;
import com.honpe.lxx.app.api.Constants;
import com.honpe.lxx.app.api.FinalClass;
import com.honpe.lxx.app.base.BaseBackFragment;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.adapter.GridViewAdapter;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean.ApplyChildBean4;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean.ApplyOrderBean4;
import com.honpe.lxx.app.ui.main.oa.ui.position15.apply.bean.MenuBean;
import com.honpe.lxx.app.utils.DateUtil;
import com.honpe.lxx.app.utils.gson.GsonBuildUtil;
import com.honpe.lxx.app.utils.LatteLogger;
import com.honpe.lxx.app.utils.ToastUtil;
import com.honpe.lxx.app.utils.event.Event;
import com.honpe.lxx.app.utils.event.EventBusUtil;
import com.honpe.lxx.app.utils.gson.Convert;
import com.honpe.lxx.app.widget.SpaceItemDecoration;
import com.honpe.lxx.app.widget.dialog.CustomBaseDialog;
import com.honpe.lxx.app.widget.font.FontTextView;
import com.honpe.lxx.app.widget.font.FontTextView4;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import jp.shts.android.library.TriangleLabelView;

import static com.honpe.lxx.app.api.FinalClass.Session;
import static com.honpe.lxx.app.utils.DateUtil.dateToWeek;
import static com.honpe.lxx.app.utils.DateUtil.getBetweenDates;

/**
 * FileName: ApplyCustomOrderManage
 * Author: asus
 * Date: 2020/8/10 16:58
 * Description: 申请客户订餐
 */
public class ApplyCustomOrderFragment extends BaseBackFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_titleBar)
    LinearLayout llTitleBar;
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.tv_Year_Month)
    TextView tvYearMonth;
    @BindView(R.id.tv_font_date)
    FontTextView tvFontDate;
    @BindView(R.id.tv_time1)
    FontTextView tvTime1;
    @BindView(R.id.lab_time1)
    TriangleLabelView labTime1;
    @BindView(R.id.tv_person_num)
    FontTextView tvPersonNum;
    @BindView(R.id.lab_person_num)
    TriangleLabelView labPersonNum;
    @BindView(R.id.tv_time2)
    FontTextView tvTime2;
    @BindView(R.id.lab_time2)
    TriangleLabelView labTime2;
    @BindView(R.id.tv_person_num2)
    FontTextView tvPersonNum2;
    @BindView(R.id.lab_person_num2)
    TriangleLabelView labPersonNum2;
    @BindView(R.id.ll_table)
    LinearLayout llTable;
    @BindView(R.id.tvDate)
    FontTextView4 tvDate;
    @BindView(R.id.time1)
    FontTextView4 time1;
    @BindView(R.id.num1)
    FontTextView4 num1;
    @BindView(R.id.time2)
    FontTextView4 time2;
    @BindView(R.id.num2)
    FontTextView4 num2;
    @BindView(R.id.ll_table2)
    LinearLayout llTable2;

    private String session, title, StartDay, EndDate, meals, strTime, strNum, strDay, strType, strOrder;
    private GridViewAdapter adapter;
    private List<String> title1Date, title1Week, title1day;

    ArrayList<String> ListFoodStyle = new ArrayList<>();
    ArrayList<String> ListFoodName = new ArrayList<>();
    ArrayList<Integer> ListFoodStatus = new ArrayList<>();
    ArrayList<String> ListFoodCorde = new ArrayList<>();
    private String[] titles1;
    private Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private ApplyChildBean4.DataBean MobObj;
    private String style;
    private String[] titlesAM = {"11:00-11:30", "11:30-12:00", "12:00-12:30", "12:30-13:00"};
    private String[] titlesPM = {"17:00-17:30", "17:30-18:00", "18:00-18:30", "18:30-19:00"};

    public static ApplyCustomOrderFragment newInstance(String title, String type) {
        ApplyCustomOrderFragment fragment = new ApplyCustomOrderFragment();
        fragment.title = title;
        fragment.style = type;
        return fragment;
    }

    public static ApplyCustomOrderFragment newInstance(String title, String type,ApplyChildBean4.DataBean MobObj) {
        ApplyCustomOrderFragment fragment = new ApplyCustomOrderFragment();
        fragment.title = title;
        fragment.style = type;
        fragment.MobObj = MobObj;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_apply_order;
    }

    @Override
    protected void initView() {
        init();
        //新增菜品栏
        iniBottom();


    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        if (style.equals("2")) {
            initMob();
        } else {
            //点菜的视图
            initMenu();
        }
    }

    private void initMob() {
//       LatteLogger.d("mobJson===",mobJson);
//        LatteLogger.d("names====", names);
        strOrder = MobObj.getOrderNo();

        day = sfDay.format(DateUtil.setDate(MobObj.getMealsDate()));

        strDay = MobObj.getMealsDate();
        tvDate.setText(day + "/" + dateToWeek(strDay));
        tvDate.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
        tvTitle.setText("修改客户点餐");
        tvEnd.setText("修改");
        strType = MobObj.getMealType();
        if (MobObj.getMealType().equals("午餐")) {
            time1.setText(MobObj.getMealTimer());
            num1.setText(MobObj.getMealsNumber() + "");
            strTime = time1.getText().toString();
            strNum = num1.getText().toString();

            time1.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
            num1.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
            Event<DataClick> event = new Event<DataClick>(FinalClass.B, new DataClick(MobObj.getMealType(), time1.getText().toString(), num1.getText().toString()));
            EventBusUtil.sendEvent(event);
        } else if (MobObj.getMealType().equals("晚餐")) {
            time2.setText(MobObj.getMealTimer());
            num2.setText(MobObj.getMealsNumber() + "");
            strTime = time2.getText().toString();
            strNum = num2.getText().toString();
            time2.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
            num2.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
            Event<DataClick> event = new Event<DataClick>(FinalClass.B, new DataClick(MobObj.getMealType(), time2.getText().toString(), num2.getText().toString()));
            EventBusUtil.sendEvent(event);
        }
        initMenu2();
    }
    private void initMenu() {
        EasyHttp.post(Constants.GETMENULIST + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        final MenuBean bean = Convert.fromJson(s, MenuBean.class);
                        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                return adapter.getItemViewType(position) == GridViewAdapter.TYPE_PERSON ? 1 : manager.getSpanCount();
                            }
                        });
                        rvMenu.setLayoutManager(manager);
                        adapter = new GridViewAdapter();
                        rvMenu.setAdapter(adapter);
                        adapter.setList(getMenuDatas(bean.getData()));
                        rvMenu.scheduleLayoutAnimation();
                    }
                });
    }
    private void initMenu2() {
        EasyHttp.post(Constants.GETMENULIST + session)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        final MenuBean bean = Convert.fromJson(s, MenuBean.class);
                        adapter = new GridViewAdapter();
                        adapter.setList(getMenuDatas2(bean.getData()));
                        int space = getResources().getDimensionPixelSize(R.dimen._1dp);
                        rvMenu.setAdapter(adapter);
                        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
                        rvMenu.setLayoutManager(manager);
                        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                return adapter.getItemViewType(position) == GridViewAdapter.TYPE_PERSON ? 1 : manager.getSpanCount();
                            }
                        });
                        rvMenu.addItemDecoration(new SpaceItemDecoration(space));
                    }
                });
    }

    private List<BaseNode> getMenuDatas2(List<MenuBean.DataBean> data) {
        Map<String, List<MenuBean.DataBean>> maps = new LinkedHashMap<>();

        for (int i = 0; i < data.size(); i++) {
            String menuStyle = data.get(i).getFoodStyle();
            List<MenuBean.DataBean> listBean = maps.get(menuStyle);
            if (listBean == null) {
                listBean = new ArrayList<>();
            }

            MenuBean.DataBean bean = new MenuBean.DataBean(
                    data.get(i).getFoodStyle(), data.get(i).getFoodName(), data.get(i).getUnit(), data.get(i).getFoodCode(), data.get(i).getStatus());
            listBean.add(bean);
            maps.put(menuStyle, listBean);
        }
        Map<String, ArrayList<MenuBean.DataBean>> maps2 = new LinkedHashMap<>();
        for (int i = 0; i < MobObj.getDetails().size(); i++) {
            String mobName = MobObj.getDetails().get(i).getFoodStyle();
            ArrayList<MenuBean.DataBean> list2 = maps2.get(mobName);
            if (list2 == null) {
                list2 = new ArrayList<>();
            }
            MenuBean.DataBean bean = new MenuBean.DataBean();
            bean.setFoodName(MobObj.getDetails().get(i).getFoodName());
            bean.setFoodStyle(MobObj.getDetails().get(i).getFoodStyle());
            bean.setFoodCode(MobObj.getDetails().get(i).getFoodCode());
            bean.setUnit(MobObj.getDetails().get(i).getUnit());
            bean.setMenuCheck(true);
            bean.setStatus(1);
            list2.add(bean);
            maps2.put(mobName, list2);
        }
        Set<String> keySet2 = maps2.keySet();
        List<BaseNode> list = new ArrayList<>();
        for (String key : maps.keySet()) {
            MenuBean bean = new MenuBean();
            bean.setMsg(key);
            List<MenuBean.DataBean> listDatas = maps.get(key);
            List<BaseNode> secondList = new ArrayList<>();
            for (int i = 0; i < listDatas.size(); i++) {
                MenuBean.DataBean bean2 = new MenuBean.DataBean();
                bean2.setFoodStyle(listDatas.get(i).getFoodStyle());
                bean2.setFoodName(listDatas.get(i).getFoodName());
                bean2.setUnit(listDatas.get(i).getUnit());
                bean2.setFoodCode(listDatas.get(i).getFoodCode());
                bean2.setStatus(listDatas.get(i).getStatus());
                for (String foodName : keySet2) {
                    if (foodName.contains(key)) {
                        List<MenuBean.DataBean> listDay = maps2.get(foodName);
                        Gson gson2 = new Gson();
                        String names2 = gson2.toJson(listDay);
                        LatteLogger.d("listdddd====", names2);
                        for (MenuBean.DataBean name : listDay) {
                            String names3 = name.getFoodName();
                            LatteLogger.d("names3====", names3);
                            if (bean2.getFoodName().equals(names3)) {
                                bean2.setStatus(1);
                                LatteLogger.d("names4====", bean2.getStatus());
                            }
                        }
                    }
                }
                bean2.setMenuCheck(listDatas.get(i).isMenuCheck());
                secondList.add(bean2);
            }
            bean.setChildNode(secondList);
            list.add(bean);
        }
        return list;
    }



    private void iniBottom() {
        tvDate.setText("\ue611");
        time1.setText("\ue611");
        num1.setText("\ue611");
        time2.setText("\ue611");
        num2.setText("\ue611");

        tvDate.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
        time1.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
        num1.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
        time2.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));
        num2.setTextColor(_mActivity.getResources().getColor(R.color.grey_home));

        getDate();
    }

    private void getDate() {
        title1Date = new ArrayList<>();
        title1Week = new ArrayList<>();
        title1day = new ArrayList<>();
        titles1 = title1Date.toArray(new String[title1Date.size()]);
        try {
            calendar.setTime(new Date());
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
            StartDay = sf.format(new Date());
            EndDate = sf.format(calendar.getTime());
            calendar.clear();
            List<Date> dateList = null;
            dateList = getBetweenDates(sf.parse(StartDay), sf.parse(EndDate));
            Date[] dates = dateList.toArray(new Date[dateList.size()]);
            for (int i = 0; i < dates.length; i++) {
                String strDates = sf.format(dates[i]);
                String strWeek = dateToWeek(sf.format(dates[i]));

                title1Date.add(strDates + " " + strWeek);
                title1day.add(strDates);
                title1Week.add(strWeek);
            }
            titles1 = title1Date.toArray(new String[title1Date.size()]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        initToolbarNav(llBack);
        tvTitle.setText(title);
        tvFontDate.setText("日期");
        tvTime1.setText("时间1");
        tvPersonNum.setText("人数");
        tvTime2.setText("时间1");
        tvPersonNum2.setText("人数");
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText("提交");
        session = (String) MyApplication.get(_mActivity, Session, "");
    }

    SimpleDateFormat sfDay = new SimpleDateFormat("dd");
    String day;

    @OnClick({R.id.tvDate, R.id.time1, R.id.num1, R.id.time2, R.id.num2, R.id.tv_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDate:
                final NormalListDialog dialog = new NormalListDialog(_mActivity, titles1);
                dialog.title("请选择日期")
                        .layoutAnimation(null)
                        .show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        day = sfDay.format(DateUtil.setDate(title1day.get(position)));
                        strDay = title1day.get(position);
                        tvDate.setText(day + "/" + title1Week.get(position));
                        tvDate.setTextColor(_mActivity.getResources().getColor(R.color.google_green));
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.time1:
            case R.id.num1:
                strType = "午餐";
                CustomBaseDialog dialog1 = new CustomBaseDialog(_mActivity, strType);
                dialog1.setCanceledOnTouchOutside(true);
                dialog1.show();
                dialog1.setOnButtonClickListener(new CustomBaseDialog.OnDialogButtonClickListener() {
                    @Override
                    public void ButtonClick(String time, String num) {
                        time1.setText(time);
                        num1.setText(num);
                        time1.setTextColor(getResources().getColor(R.color.google_green));
                        num1.setTextColor(getResources().getColor(R.color.google_green));
                        strTime = time;
                        strNum = num;
                        Event<DataClick> event = new Event<DataClick>(FinalClass.B, new DataClick(strType, strTime, strNum));
                        EventBusUtil.sendEvent(event);
                    }
                });
                break;

            case R.id.time2:
            case R.id.num2:
                strType = "晚餐";
                CustomBaseDialog dialog3 = new CustomBaseDialog(_mActivity, strType);
                dialog3.setCanceledOnTouchOutside(true);
                dialog3.show();
                dialog3.setOnButtonClickListener(new CustomBaseDialog.OnDialogButtonClickListener() {
                    @Override
                    public void ButtonClick(String time, String num) {
                        time2.setText(time);
                        num2.setText(num);
                        time2.setTextColor(getResources().getColor(R.color.google_green));
                        num2.setTextColor(getResources().getColor(R.color.google_green));
                        strTime = time;
                        strNum = num;
                        Event<DataClick> event3 = new Event<DataClick>(FinalClass.B, new DataClick(strType, strTime, strNum));
                        EventBusUtil.sendEvent(event3);
                    }
                });
                break;
            case R.id.tv_end:
                if (style.equals("1")) {
                    if (strDay != null && strTime != null && strNum != null && ListFoodName != null) {
                        submit(strTime, strNum, strType, strDay, ListFoodName, ListFoodStatus, ListFoodStyle, ListFoodCorde);
                    } else {
                        if (strDay == null) {
                            ToastUtil.getInstance().showToast("日期格式不对或者为空！");
                        }
                        if (strTime == null && strNum == null) {
                            ToastUtil.getInstance().showToast("时间和人数不能为空！");
                        }
                        if (ListFoodName == null) {
                            ToastUtil.getInstance().showToast("菜单不能为空！请先择菜品");
                        }
                    }
                } else {
                    if (strDay != null && strTime != null && strNum != null && ListFoodName != null) {
                        submit2(strOrder, strTime, strNum, strType, strDay, ListFoodName, ListFoodStatus, ListFoodStyle, ListFoodCorde);
                    } else {
                        if (strDay == null) {
                            ToastUtil.getInstance().showToast("日期格式不对或者为空！");
                        }
                        if (strTime == null && strNum == null) {
                            ToastUtil.getInstance().showToast("时间和人数不能为空！");
                        }
                        if (ListFoodName == null) {
                            ToastUtil.getInstance().showToast("菜单不能为空！请先择菜品");
                        }
                    }
                }
                break;
        }
    }

    public void submit(String time, String num, String type, String date, List<String> foodNames, List<Integer> foodNums, List<String> foodStyle, List<String> foodCode) {
        final ApplyOrderBean4 item = new ApplyOrderBean4();

        item.setFoodAmount(foodNames.size());
        item.setMealTimer(time);
        item.setMealType(type);
        item.setMealsDate(date);
        item.setMealsNumber(num);
        item.setDetails(ChildDate(foodNames, foodNums, foodStyle, foodCode));
        final String r = new Gson().toJson(item);
        LatteLogger.d("RRRRRRR", r);

        EasyHttp.post(Constants.COMMITORDER + session)
                .upJson(r)
                .execute(new com.zhouyou.http.callback.SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", s);
                        ToastUtil.getInstance().showToast("已提交成功");
                        Bundle bundle = new Bundle();
                        bundle.putString("result", s);
                        setFragmentResult(RESULT_OK, bundle);
                        _mActivity.onBackPressed();
                    }
                });
    }

    private void submit2(String strOrder, String time, String num, String type, String date, List<String> foodNames, List<Integer> foodNums, List<String> foodStyle, List<String> foodCode) {
        ApplyOrderBean4 item = new ApplyOrderBean4();
        item.setOrderNo(strOrder);
        item.setFoodAmount(foodNames.size());
        item.setMealTimer(time);
        item.setMealType(type);
        item.setMealsDate(date);
        item.setMealsNumber(num);
        item.setDetails(ChildDate(foodNames, foodNums, foodStyle, foodCode));

        final String r = new Gson().toJson(item);
        LatteLogger.d("RRRRRRR", r);

        EasyHttp.post(Constants.MOB_MENU + session)
                .upJson(r)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        ToastUtil.getInstance().showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String s) {
                        LatteLogger.d("result", s);
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(s);
                            ToastUtil.getInstance().showToast("修改成功");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("result", s);
                        setFragmentResult(RESULT_OK, bundle);
                        _mActivity.onBackPressed();
                    }
                });
    }

    private List<ApplyOrderBean4.DetailsBean> ChildDate(List<String> foods, List<Integer> nums, List<String> foodStyle, List<String> foodCode) {
        List<ApplyOrderBean4.DetailsBean> mList = new ArrayList<>();
        LatteLogger.d("foods", foods);
        for (int i = 0; i < foods.size(); i++) {
            // public DetailsBean(String foodStyle, String foodName, int foodNum, String foodCode, int canteenConfirm) {
            ApplyOrderBean4.DetailsBean bean = new ApplyOrderBean4.DetailsBean(foodStyle.get(i), foods.get(i), nums.get(i), foodCode.get(i), nums.get(i));
            mList.add(bean);
        }
        return mList;
    }


    private List<MenuBean.DataBean> listDatas = new ArrayList<>();

    private ArrayList<BaseNode> getMenuDatas(List<MenuBean.DataBean> data) {

        Map<String, List<MenuBean.DataBean>> maps = new LinkedHashMap<>();
        for (int i = 0; i < data.size(); i++) {
            String menuStyle = data.get(i).getFoodStyle();
            List<MenuBean.DataBean> listBean = maps.get(menuStyle);
            if (listBean == null) {
                listBean = new ArrayList<>();
            }
            MenuBean.DataBean bean = new MenuBean.DataBean(
                    data.get(i).getFoodStyle(), data.get(i).getFoodName(), data.get(i).getUnit(), data.get(i).getFoodCode(), data.get(i).getStatus());
            listBean.add(bean);
            maps.put(menuStyle, listBean);
        }

        ArrayList<BaseNode> FirstList = new ArrayList<>();

        for (String key : maps.keySet()) {
            MenuBean bean = new MenuBean();
            bean.setMsg(key);
            listDatas = maps.get(key);
            ArrayList<BaseNode> secondList = new ArrayList<>();
            secondList.addAll(listDatas);
            bean.setChildNode(secondList);
            FirstList.add(bean);
        }

        LatteLogger.d("key", GsonBuildUtil.GsonBuilder(FirstList));
        return FirstList;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.A:
                MenuBean.DataBean itemBean = (MenuBean.DataBean) event.getData();
                ListFoodStyle.add(itemBean.getFoodStyle());
                ListFoodName.add(itemBean.getFoodName());
                ListFoodStatus.add(itemBean.getStatus());
                ListFoodCorde.add(itemBean.getFoodCode());
                for (int i = 0; i < ListFoodName.size(); i++) {
                    for (int j = ListFoodName.size() - 1; j > i; j--) {
                        if (ListFoodName.get(j).equals(ListFoodName.get(i))) {
                            ListFoodName.remove(j);
                            ListFoodName.remove(i);
                            ListFoodStatus.remove(j);
                            ListFoodStatus.remove(i);
                            ListFoodStyle.remove(i);
                            ListFoodStyle.remove(j);
                        }
                    }
                }
                Gson gson = new Gson();
                String name = gson.toJson(ListFoodName);
                String style = gson.toJson(ListFoodStyle);
                String status = gson.toJson(ListFoodStatus);
                LatteLogger.d("nameddd", name + "\n" + style + "\n" + status);
                break;
            case FinalClass.B:
                DataClick strEvent = (DataClick) event.getData();
                LatteLogger.d("nameddd", strEvent.getType() + "\n" + strEvent.getTime() + "\n" + strEvent.getNum());
                if (strEvent.getType().equals("午餐") && Arrays.asList(titlesAM).contains(strEvent.getTime())) {
                    time2.setClickable(false);
                    num2.setClickable(false);
                } else if (strEvent.getType().equals("晚餐") && Arrays.asList(titlesPM).contains(strEvent.getTime())) {
                    time1.setClickable(false);
                    num1.setClickable(false);
                }
                break;
        }
    }

    public class DataClick {
        private String type;
        private String time;
        private String num;

        public DataClick(String type, String time, String num) {
            this.type = type;
            this.time = time;
            this.num = num;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
