package com.honpe.lxx.app.ui.main.oa.ui.position13.bean;

import android.text.Spannable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * FileName: RepairBean
 * Author: asus
 * Date: 2020/8/31 16:11
 * Description:
 */
public class MultiItemBean implements MultiItemEntity{
    public static final int HEAD = 0;
    public static final int TEXT_RADIO = 1;
    public static final int TEXT = 2;
    public static final int RADIO = 3;
    public static final int TEXT_EDIT = 4;
    public static final int LINE = 5;
    public static final int TYPE = 6;
    public static final int TYPE2 = 7;
    public static final int TYPE3 = 8;

    private String strTitle;
    private String content;
    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String content5;
    private String radioContent1;
    private String radioContent2;
    private String strTips;
    private String hint;
    private int type;
    private String strDepart;
    private Spannable strDate;
    private String timeLong;
    private String id;
    private String applyName;
    private String address;
    private String reason;
    private String[] radios;
    private String F_TypeContext;
    private String departSign; //部门签名
    private String AdminSigh; //行政签名
    private String worksConfirmed; //工务组确认
    private String presetTime; //确认时间
    private String description;//原因说明
    private int itemType;
    private int spanSize;

    public MultiItemBean() {
    }

    public MultiItemBean(int itemType, String strTitle, int spanSize) {
        this.strTitle = strTitle;
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public MultiItemBean(int itemType, String strTitle,String hint, int spanSize) {
        this.strTitle = strTitle;
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.hint = hint;
    }

    public MultiItemBean(int itemType, String strTitle,String content,String hint, int spanSize) {
        this.strTitle = strTitle;
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
        this.hint = hint;
    }

    public MultiItemBean(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public String getContent4() {
        return content4;
    }

    public void setContent4(String content4) {
        this.content4 = content4;
    }

    public String getContent5() {
        return content5;
    }

    public void setContent5(String content5) {
        this.content5 = content5;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public String getWorksConfirmed() {
        return worksConfirmed;
    }

    public void setWorksConfirmed(String worksConfirmed) {
        this.worksConfirmed = worksConfirmed;
    }

    public String getPresetTime() {
        return presetTime;
    }

    public void setPresetTime(String presetTime) {
        this.presetTime = presetTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartSign() {
        return departSign;
    }

    public void setDepartSign(String departSign) {
        this.departSign = departSign;
    }

    public String getAdminSigh() {
        return AdminSigh;
    }

    public void setAdminSigh(String adminSigh) {
        AdminSigh = adminSigh;
    }


    public String getF_TypeContext() {
        return F_TypeContext;
    }

    public void setF_TypeContext(String f_TypeContext) {
        F_TypeContext = f_TypeContext;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStrDepart() {
        return strDepart;
    }

    public void setStrDepart(String strDepart) {
        this.strDepart = strDepart;
    }

    public Spannable getStrDate() {
        return strDate;
    }

    public void setStrDate(Spannable strDate) {
        this.strDate = strDate;
    }

    public String getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(String timeLong) {
        this.timeLong = timeLong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getStrTips() {
        return strTips;
    }

    public void setStrTips(String strTips) {
        this.strTips = strTips;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String[] getRadios() {
        return radios;
    }

    public void setRadios(String[] radios) {
        this.radios = radios;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }


    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRadioContent1() {
        return radioContent1;
    }

    public void setRadioContent1(String radioContent1) {
        this.radioContent1 = radioContent1;
    }

    public String getRadioContent2() {
        return radioContent2;
    }

    public void setRadioContent2(String radioContent2) {
        this.radioContent2 = radioContent2;
    }


    @Override
    public int getItemType() {
        return itemType;
    }
}
