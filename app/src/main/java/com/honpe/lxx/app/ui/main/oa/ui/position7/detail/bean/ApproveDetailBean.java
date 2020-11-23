package com.honpe.lxx.app.ui.main.oa.ui.position7.detail.bean;

import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/23 13:57
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class ApproveDetailBean {
    /**
     *  name 姓名
     *  depart 部门
     *  startTime 起始时间
     *  endTime 结束时间
     *  reason 原因
     *  status 状态
     */
    private String name;
    private String depart;
    private String f_encode;//员工编号

    private List<DetailDataBean> dataList;


    public String getF_encode() {
        return f_encode;
    }

    public void setF_encode(String f_encode) {
        this.f_encode = f_encode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public List<DetailDataBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DetailDataBean> dataList) {
        this.dataList = dataList;
    }

    public static class DetailDataBean {
        private String startTime;
        private String endTime;
        private String reason;
        private String timeLag; //时差
        private String address;

        private String id;
        private String F_id;
        private String F_taskId;
        private String rettime;
        private String catorgry;
        private String level;
        private String guid;



        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCatorgry() {
            return catorgry;
        }

        public void setCatorgry(String catorgry) {
            this.catorgry = catorgry;
        }

        public String getRettime() {
            return rettime;
        }

        public void setRettime(String rettime) {
            this.rettime = rettime;
        }

        public String getF_taskId() {
            return F_taskId;
        }

        public void setF_taskId(String f_taskId) {
            F_taskId = f_taskId;
        }

        public String getF_id() {
            return F_id;
        }

        public void setF_id(String f_id) {
            F_id = f_id;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }



        public String getTimeLag() {
            return timeLag;
        }

        public void setTimeLag(String timeLag) {
            this.timeLag = timeLag;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
