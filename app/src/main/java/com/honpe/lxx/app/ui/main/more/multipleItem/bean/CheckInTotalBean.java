package com.honpe.lxx.app.ui.main.more.multipleItem.position5.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: CheckInTotalBean
 * Author: asus
 * Date: 2020/8/6 15:36
 * Description:
 */
public class CheckInTotalBean implements Parcelable {

    private String strDate;  //日期
    private String totalCardNum; //打卡人数
    private List<Data> LeakAgeCard; //漏卡人数
    private List<Data> beLate; //迟到
    private List<Data> early; //早退
    private List<Data> legwork; //外勤

    protected CheckInTotalBean(Parcel in) {
        strDate = in.readString();
        totalCardNum = in.readString();
        LeakAgeCard = in.createTypedArrayList(Data.CREATOR);
        beLate = in.createTypedArrayList(Data.CREATOR);
        early = in.createTypedArrayList(Data.CREATOR);
        legwork = in.createTypedArrayList(Data.CREATOR);
    }

    public static final Creator<CheckInTotalBean> CREATOR = new Creator<CheckInTotalBean>() {
        @Override
        public CheckInTotalBean createFromParcel(Parcel in) {
            return new CheckInTotalBean(in);
        }

        @Override
        public CheckInTotalBean[] newArray(int size) {
            return new CheckInTotalBean[size];
        }
    };

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getTotalCardNum() {
        return totalCardNum;
    }

    public void setTotalCardNum(String totalCardNum) {
        this.totalCardNum = totalCardNum;
    }

    public List<Data> getLeakAgeCard() {
        return LeakAgeCard;
    }

    public void setLeakAgeCard(List<Data> leakAgeCard) {
        LeakAgeCard = leakAgeCard;
    }

    public List<Data> getBeLate() {
        return beLate;
    }

    public void setBeLate(List<Data> beLate) {
        this.beLate = beLate;
    }

    public List<Data> getEarly() {
        return early;
    }

    public void setEarly(List<Data> early) {
        this.early = early;
    }

    public List<Data> getLegwork() {
        return legwork;
    }

    public void setLegwork(List<Data> legwork) {
        this.legwork = legwork;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(strDate);
        dest.writeString(totalCardNum);
        dest.writeTypedList(LeakAgeCard);
        dest.writeTypedList(beLate);
        dest.writeTypedList(early);
        dest.writeTypedList(legwork);
    }

    public static class Data implements Parcelable{
        private String No; //员工编号
        private String userName; //姓名；
        private String status; // 漏卡/迟到/早退/外勤
        private String team; //组别

        public Data(Parcel in) {
            No = in.readString();
            userName = in.readString();
            status = in.readString();
            team = in.readString();
        }

        public Data() {

        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(No);
            dest.writeString(userName);
            dest.writeString(status);
            dest.writeString(team);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        public String getNo() {
            return No;
        }

        public void setNo(String no) {
            No = no;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }
    }
}
