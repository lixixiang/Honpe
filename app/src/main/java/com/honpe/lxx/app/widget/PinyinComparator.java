package com.honpe.lxx.app.widget;

import com.honpe.lxx.app.ui.main.oa.ui.position14.add.bean.AllNameBean;
import com.honpe.lxx.app.utils.StringUtil;

import java.util.Comparator;

/**
 * FileName: PinyinComparator
 * Author: asus
 * Date: 2020/9/22 17:12
 * Description:
 */
public class PinyinComparator implements Comparator<AllNameBean.InfoEntity> {

    @Override
    public int compare(AllNameBean.InfoEntity o1, AllNameBean.InfoEntity o2) {
        return StringUtil.getPinYinHeadChar(o1.getF_realname()).compareTo(StringUtil.getPinYinHeadChar(o2.getF_realname()));
    }
}
