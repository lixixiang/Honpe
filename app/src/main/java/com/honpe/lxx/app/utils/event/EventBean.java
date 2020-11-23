package com.honpe.lxx.app.utils.event;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 16:00
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class EventBean {
    private String Content;
    private String Tag;

    public EventBean(String content, String tag) {
        Content = content;
        Tag = tag;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }
}

