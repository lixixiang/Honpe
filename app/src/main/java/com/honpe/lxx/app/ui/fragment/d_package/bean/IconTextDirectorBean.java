package com.honpe.lxx.app.ui.fragment.d_package.bean;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/6 15:47
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class IconTextDirectorBean {
    private int icon;
    private String text;
    private int icon_director;
    private String FontIcon;
    private int FontColor;

    public IconTextDirectorBean(String fontIcon,String text,int fontColor){
        this.FontIcon = fontIcon;
        this.text = text;
        this.FontColor = fontColor;
    }
    public IconTextDirectorBean(String text,int icon){
        this.text = text;
        this.icon = icon;
    }

    public String getFontIcon() {
        return FontIcon;
    }

    public void setFontIcon(String fontIcon) {
        FontIcon = fontIcon;
    }

    public int getFontColor() {
        return FontColor;
    }

    public void setFontColor(int fontColor) {
        FontColor = fontColor;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon_director() {
        return icon_director;
    }

    public void setIcon_director(int icon_director) {
        this.icon_director = icon_director;
    }
}