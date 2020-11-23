package com.honpe.lxx.app.ui.main.oa.ui.position14.add.bean;

import android.text.TextUtils;

import com.honpe.lxx.app.utils.StringUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName: AllNameBean
 * Author: asus
 * Date: 2020/9/21 16:04
 * Description:
 */
public class AllNameBean {

    /**
     * code : 200
     * info : [{"f_departmentname":"软件组","f_encode":"947","f_userid":"7c39151f-6daf-4236-8b23-aa4773e5da7e","f_realname":"潘东海","f_departmentid":"a0d42e75-a70b-4cef-8b0f-52456fdf640f"}]
     */
    private int code;
    private List<InfoEntity> info;

    public void setCode(int code) {
        this.code = code;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public static class InfoEntity {
        /**
         * f_departmentname : 软件组
         * f_encode : 947
         * f_userid : 7c39151f-6daf-4236-8b23-aa4773e5da7e
         * f_realname : 潘东海
         * f_departmentid : a0d42e75-a70b-4cef-8b0f-52456fdf640f
         */
        private String f_departmentname;
        private String f_encode;
        private String f_userid;
        private String f_realname;
        private String f_departmentid;
        private String emp_usersex;
        private String dormitoryinfo;

        public InfoEntity() {
        }

        public InfoEntity(String f_departmentname, String f_encode, String f_userid, String f_realname, String f_departmentid) {
            this.f_departmentname = f_departmentname;
            this.f_encode = f_encode;
            this.f_userid = f_userid;
            this.f_realname = f_realname;
            this.f_departmentid = f_departmentid;
        }

        public String getDormitoryinfo() {
            return dormitoryinfo;
        }

        public void setDormitoryinfo(String dormitoryinfo) {
            this.dormitoryinfo = dormitoryinfo;
        }

        public String getEmp_usersex() {
            return emp_usersex;
        }

        public void setEmp_usersex(String emp_usersex) {
            this.emp_usersex = emp_usersex;
        }

        public void setF_departmentname(String f_departmentname) {
            this.f_departmentname = f_departmentname;
        }

        public void setF_encode(String f_encode) {
            this.f_encode = f_encode;
        }

        public void setF_userid(String f_userid) {
            this.f_userid = f_userid;
        }

        public void setF_realname(String f_realname) {
            this.f_realname = f_realname;
        }

        public void setF_departmentid(String f_departmentid) {
            this.f_departmentid = f_departmentid;
        }

        public String getF_departmentname() {
            return f_departmentname;
        }

        public String getF_encode() {
            return f_encode;
        }

        public String getF_userid() {
            return f_userid;
        }

        public String getF_realname() {
            return f_realname;
        }

        public String getF_departmentid() {
            return f_departmentid;
        }

        /***
         * 获取悬浮栏文本，（#、定位、热门 需要特殊处理）
         * @return
         */
        public String getSection() {
            String pinyin = StringUtil.getPingYin(f_realname);
                String c = pinyin.substring(0, 1);
                Pattern p = Pattern.compile("[a-zA-Z]");
                Matcher m = p.matcher(c);
                if (m.matches()) {
                    return c.toUpperCase();
            }
                return pinyin;
        }
    }
}
