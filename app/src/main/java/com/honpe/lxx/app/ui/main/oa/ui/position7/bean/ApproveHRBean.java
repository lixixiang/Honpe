package com.honpe.lxx.app.ui.main.oa.ui.position7.bean;

import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/22 1:53
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class ApproveHRBean {


    /**
     * code : 200
     * info : 获取我的申请信息列表成功!
     * data : [{"HR_BusinessTravel":[{"rqtby":"7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e","id":"G200715002","rettime":"2020-07-15 16:22:39","dpt":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","level":"1","catorgry":"1","starttime":"2020-07-15 16:22:00","endtime":"2020-07-15 18:22:00","address":"测试","countday":1,"reason":"测试","gguid":"127e8016-5335-80ce-60c5-53af2c8dd606","f_encode":"3538","f_realname":"袁斌","f_fullname":"软件组","f_state":"0","f_taskid":"8066982b-c518-4852-937d-18e61ada4a5c","f_id":"127e8016-5335-80ce-60c5-53af2c8dd606"}]},{"HR_GoOut":[{"rqtby":"7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e","id":"C200716003","rettime":"2020-07-16 20:11:30","dpt":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","level":"1","catorgry":"5","cguid":"62f36dde-663d-88ba-a4e6-7d712bc2ae40","starttime":"2020-07-16 20:11:00","endtime":"2020-07-16 23:11:00","countday":1,"reason":"测试","f_encode":"3538","f_realname":"袁斌","f_fullname":"软件组","f_state":"0","f_taskid":"ee02710f-2edc-4524-88dd-82ccfd9ecd92","f_id":"62f36dde-663d-88ba-a4e6-7d712bc2ae40"}]},{"HR_AskforLeave":[{"rqtby":"7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e","id":"L200717002","dpt":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","rettime":"2020-07-17 10:08:27","catorgry":"1","level":"1","starttime":"2020-07-17 10:08:00","endtime":"2020-07-17 12:08:00","counthours":2,"holiday":0,"reason":"测试","lguid":"94f9c049-1afd-0302-0fc4-016ac8306267","f_encode":"3538","f_realname":"袁斌","f_fullname":"软件组","f_state":"0","f_taskid":"b095ff33-1159-4eee-bc06-6b2273c5711f","f_id":"94f9c049-1afd-0302-0fc4-016ac8306267"}]},{"HR_GoOut":[{"rqtby":"7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e","id":"C200717002","rettime":"2020-07-17 10:15:20","dpt":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","level":"1","catorgry":"5","cguid":"4beee79e-9b29-2028-e507-30e772d4a0d7","starttime":"2020-07-17 10:15:00","endtime":"2020-07-17 18:15:00","countday":8,"reason":"测试","f_encode":"3538","f_realname":"袁斌","f_fullname":"软件组","f_state":"0","f_taskid":"896f6a67-ee4b-4571-9ac2-f4a98596e685","f_id":"4beee79e-9b29-2028-e507-30e772d4a0d7"}]},{"HR_GoOut":[{"rqtby":"70060f98-6ab0-4f5e-b9cc-b31aa0c78c1a","id":"C200718001","rettime":"2020-07-18 10:40:05","dpt":"1cc82017-73a2-474f-a908-c59cb121b2d3","level":"1","catorgry":"5","cguid":"f44748e6-4a3c-d21b-4a5c-22cb0b96c6a5","starttime":"2020-07-18 10:40:00","endtime":"2020-07-18 11:40:00","countday":1,"reason":"测试","f_encode":"777","f_realname":"潘卫东","f_fullname":"研发部","f_state":"0","f_taskid":"40b8cb9d-e460-4957-a95e-eb3ad3fbc2da","f_id":"f44748e6-4a3c-d21b-4a5c-22cb0b96c6a5"}]},{"HR_GoOut":[{"rqtby":"ace02f97-9f6c-4be1-b072-cee56622fd52","id":"C200718003","rettime":"2020-07-18 17:32:41","dpt":"1cc82017-73a2-474f-a908-c59cb121b2d3","level":"1","catorgry":"5","cguid":"32cb1812-9949-e51d-48ff-a848ddd26d4c","starttime":"2020-07-18 17:32:00","endtime":"2020-07-18 17:32:00","countday":0,"reason":"出差","f_encode":"3466","f_realname":"徐菊","f_fullname":"研发部","f_state":"0","f_taskid":"1849c2cd-7b75-482e-b0c9-e61d592233c3","f_id":"32cb1812-9949-e51d-48ff-a848ddd26d4c"}]},{"HR_BusinessTravel":[{"rqtby":"7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e","id":"G200721001","rettime":"2020-07-21 09:11:56","dpt":"a0d42e75-a70b-4cef-8b0f-52456fdf640f","level":"1","catorgry":"1","starttime":"2020-07-21 09:11:00","endtime":"2020-07-21 18:12:00","address":"武汉","countday":1,"reason":"测试","gguid":"634d7031-0d3d-c8c1-4240-6c41abe6ffea","f_encode":"3538","f_realname":"袁斌","f_fullname":"软件组","f_state":"0","f_taskid":"ea7f986b-a12e-4e67-ae62-fa624eb3e1fd","f_id":"634d7031-0d3d-c8c1-4240-6c41abe6ffea"}]}]
     */

    private int code;
    private String info;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private List<HRBusinessTravelBean> HR_BusinessTravel;
        private List<HRGoOutBean> HR_GoOut;
        private List<HRAskforLeaveBean> HR_AskforLeave;

        public List<HRBusinessTravelBean> getHR_BusinessTravel() {
            return HR_BusinessTravel;
        }

        public void setHR_BusinessTravel(List<HRBusinessTravelBean> HR_BusinessTravel) {
            this.HR_BusinessTravel = HR_BusinessTravel;
        }

        public List<HRGoOutBean> getHR_GoOut() {
            return HR_GoOut;
        }

        public void setHR_GoOut(List<HRGoOutBean> HR_GoOut) {
            this.HR_GoOut = HR_GoOut;
        }

        public List<HRAskforLeaveBean> getHR_AskforLeave() {
            return HR_AskforLeave;
        }

        public void setHR_AskforLeave(List<HRAskforLeaveBean> HR_AskforLeave) {
            this.HR_AskforLeave = HR_AskforLeave;
        }

        public static class HRBusinessTravelBean {
            /**
             *  f_curstate : [{"NodeName":"开始","NodeRemarks":"当前节点:开始,处理时间:2020-08-05 10:38:41,处理人:李熙祥,备注:【发起】test"},{"NodeName":"主管审批","NodeRemarks":"当前节点:主管审批,处理时间:2020-08-05 10:40:14,处理人:潘卫东,备注:同意"},{"NodeName":"人事审批","NodeRemarks":""}]
             * rqtby : 7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e
             * id : G200715002
             * rettime : 2020-07-15 16:22:39
             * dpt : a0d42e75-a70b-4cef-8b0f-52456fdf640f
             * level : 1
             * catorgry : 1
             * starttime : 2020-07-15 16:22:00
             * endtime : 2020-07-15 18:22:00
             * address : 测试
             * countday : 1
             * reason : 测试
             * gguid : 127e8016-5335-80ce-60c5-53af2c8dd606
             * f_encode : 3538
             * f_realname : 袁斌
             * f_fullname : 软件组
             * f_state : 0
             * f_taskid : 8066982b-c518-4852-937d-18e61ada4a5c
             * f_id : 127e8016-5335-80ce-60c5-53af2c8dd606
             */
            private String f_curstate;
            private String rqtby;
            private String id;
            private String rettime;
            private String dpt;
            private String level;
            private String catorgry;
            private String starttime;
            private String endtime;
            private String address;
            private int countday;
            private String reason;
            private String gguid;
            private String f_encode;
            private String f_realname;
            private String f_fullname;
            private String f_state;
            private String f_taskid;
            private String f_id;

            public String getF_curstate() {
                return f_curstate;
            }

            public void setF_curstate(String f_curstate) {
                this.f_curstate = f_curstate;
            }

            public String getRqtby() {
                return rqtby;
            }

            public void setRqtby(String rqtby) {
                this.rqtby = rqtby;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRettime() {
                return rettime;
            }

            public void setRettime(String rettime) {
                this.rettime = rettime;
            }

            public String getDpt() {
                return dpt;
            }

            public void setDpt(String dpt) {
                this.dpt = dpt;
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

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getCountday() {
                return countday;
            }

            public void setCountday(int countday) {
                this.countday = countday;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getGguid() {
                return gguid;
            }

            public void setGguid(String gguid) {
                this.gguid = gguid;
            }

            public String getF_encode() {
                return f_encode;
            }

            public void setF_encode(String f_encode) {
                this.f_encode = f_encode;
            }

            public String getF_realname() {
                return f_realname;
            }

            public void setF_realname(String f_realname) {
                this.f_realname = f_realname;
            }

            public String getF_fullname() {
                return f_fullname;
            }

            public void setF_fullname(String f_fullname) {
                this.f_fullname = f_fullname;
            }

            public String getF_state() {
                return f_state;
            }

            public void setF_state(String f_state) {
                this.f_state = f_state;
            }

            public String getF_taskid() {
                return f_taskid;
            }

            public void setF_taskid(String f_taskid) {
                this.f_taskid = f_taskid;
            }

            public String getF_id() {
                return f_id;
            }

            public void setF_id(String f_id) {
                this.f_id = f_id;
            }
        }

        public static class HRGoOutBean {
            /**
             * rqtby : 7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e
             * id : C200716003
             * rettime : 2020-07-16 20:11:30
             * dpt : a0d42e75-a70b-4cef-8b0f-52456fdf640f
             * level : 1
             * catorgry : 5
             * cguid : 62f36dde-663d-88ba-a4e6-7d712bc2ae40
             * starttime : 2020-07-16 20:11:00
             * endtime : 2020-07-16 23:11:00
             * countday : 1
             * reason : 测试
             * f_encode : 3538
             * f_realname : 袁斌
             * f_fullname : 软件组
             * f_state : 0
             * f_taskid : ee02710f-2edc-4524-88dd-82ccfd9ecd92
             * f_id : 62f36dde-663d-88ba-a4e6-7d712bc2ae40
             */
            private String f_curstate;
            private String rqtby;
            private String id;
            private String rettime;
            private String dpt;
            private String level;
            private String catorgry;
            private String cguid;
            private String starttime;
            private String endtime;
            private int countday;
            private String reason;
            private String f_encode;
            private String f_realname;
            private String f_fullname;
            private String f_state;
            private String f_taskid;
            private String f_id;

            public String getF_curstate() {
                return f_curstate;
            }

            public void setF_curstate(String f_curstate) {
                this.f_curstate = f_curstate;
            }

            public String getRqtby() {
                return rqtby;
            }

            public void setRqtby(String rqtby) {
                this.rqtby = rqtby;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRettime() {
                return rettime;
            }

            public void setRettime(String rettime) {
                this.rettime = rettime;
            }

            public String getDpt() {
                return dpt;
            }

            public void setDpt(String dpt) {
                this.dpt = dpt;
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

            public String getCguid() {
                return cguid;
            }

            public void setCguid(String cguid) {
                this.cguid = cguid;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public int getCountday() {
                return countday;
            }

            public void setCountday(int countday) {
                this.countday = countday;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getF_encode() {
                return f_encode;
            }

            public void setF_encode(String f_encode) {
                this.f_encode = f_encode;
            }

            public String getF_realname() {
                return f_realname;
            }

            public void setF_realname(String f_realname) {
                this.f_realname = f_realname;
            }

            public String getF_fullname() {
                return f_fullname;
            }

            public void setF_fullname(String f_fullname) {
                this.f_fullname = f_fullname;
            }

            public String getF_state() {
                return f_state;
            }

            public void setF_state(String f_state) {
                this.f_state = f_state;
            }

            public String getF_taskid() {
                return f_taskid;
            }

            public void setF_taskid(String f_taskid) {
                this.f_taskid = f_taskid;
            }

            public String getF_id() {
                return f_id;
            }

            public void setF_id(String f_id) {
                this.f_id = f_id;
            }
        }

        public static class HRAskforLeaveBean {
            /**
             * rqtby : 7a7bb88f-de7e-4e42-ab5d-8cb5389ea73e
             * id : L200717002
             * dpt : a0d42e75-a70b-4cef-8b0f-52456fdf640f
             * rettime : 2020-07-17 10:08:27
             * catorgry : 1
             * level : 1
             * starttime : 2020-07-17 10:08:00
             * endtime : 2020-07-17 12:08:00
             * counthours : 2.0
             * holiday : 0.0
             * reason : 测试
             * lguid : 94f9c049-1afd-0302-0fc4-016ac8306267
             * f_encode : 3538
             * f_realname : 袁斌
             * f_fullname : 软件组
             * f_state : 0
             * f_taskid : b095ff33-1159-4eee-bc06-6b2273c5711f
             * f_id : 94f9c049-1afd-0302-0fc4-016ac8306267
             */
            private String f_curstate;
            private String rqtby;
            private String id;
            private String dpt;
            private String rettime;
            private String catorgry;
            private String level;
            private String starttime;
            private String endtime;
            private double counthours;
            private double holiday;
            private String reason;
            private String lguid;
            private String f_encode;
            private String f_realname;
            private String f_fullname;
            private String f_state;
            private String f_taskid;
            private String f_id;

            public String getF_curstate() {
                return f_curstate;
            }

            public void setF_curstate(String f_curstate) {
                this.f_curstate = f_curstate;
            }

            public String getRqtby() {
                return rqtby;
            }

            public void setRqtby(String rqtby) {
                this.rqtby = rqtby;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDpt() {
                return dpt;
            }

            public void setDpt(String dpt) {
                this.dpt = dpt;
            }

            public String getRettime() {
                return rettime;
            }

            public void setRettime(String rettime) {
                this.rettime = rettime;
            }

            public String getCatorgry() {
                return catorgry;
            }

            public void setCatorgry(String catorgry) {
                this.catorgry = catorgry;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getStarttime() {
                return starttime;
            }

            public void setStarttime(String starttime) {
                this.starttime = starttime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public double getCounthours() {
                return counthours;
            }

            public void setCounthours(double counthours) {
                this.counthours = counthours;
            }

            public double getHoliday() {
                return holiday;
            }

            public void setHoliday(double holiday) {
                this.holiday = holiday;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getLguid() {
                return lguid;
            }

            public void setLguid(String lguid) {
                this.lguid = lguid;
            }

            public String getF_encode() {
                return f_encode;
            }

            public void setF_encode(String f_encode) {
                this.f_encode = f_encode;
            }

            public String getF_realname() {
                return f_realname;
            }

            public void setF_realname(String f_realname) {
                this.f_realname = f_realname;
            }

            public String getF_fullname() {
                return f_fullname;
            }

            public void setF_fullname(String f_fullname) {
                this.f_fullname = f_fullname;
            }

            public String getF_state() {
                return f_state;
            }

            public void setF_state(String f_state) {
                this.f_state = f_state;
            }

            public String getF_taskid() {
                return f_taskid;
            }

            public void setF_taskid(String f_taskid) {
                this.f_taskid = f_taskid;
            }

            public String getF_id() {
                return f_id;
            }

            public void setF_id(String f_id) {
                this.f_id = f_id;
            }
        }
    }
}
