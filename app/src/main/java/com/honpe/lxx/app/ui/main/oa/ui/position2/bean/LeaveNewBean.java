package com.honpe.lxx.app.ui.main.oa.ui.position2.bean;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/17 16:54
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class LeaveNewBean {
    private String date;
    private String morning;
    private String after;
    private String dinner;
    private String status;
    private String today;   //记录目前日子
    private String lastDay; //记录月底日子
    private String startTime; //开始时间
    private String endTime;   //结束时间
    private String Destination;//目的地
    private String reason; //原因
    private String _id; // id 号
    private String LongTime;//时长
    private String userName;//姓名
    private String type; //类型
    private String currentStatus; //当前审批到哪个环节
    private String gguid;

    public String getGguid() {
        return gguid;
    }

    public void setGguid(String gguid) {
        this.gguid = gguid;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLongTime() {
        return LongTime;
    }

    public void setLongTime(String longTime) {
        LongTime = longTime;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


