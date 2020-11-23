package com.honpe.lxx.app.ui.main.oa.ui.position9.bean;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/20 11:56
 * @Author: 李熙祥
 * @Description: java类作用描述 往返
 */
public class CarReturnBean {
    private String CarNo;
    private String StartTime;
    private String EndTime;
    private int CarId;


    public int getCarId() {
        return CarId;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    public String getCarNo() {
        return CarNo;
    }

    public void setCarNo(String carNo) {
        CarNo = carNo;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}

