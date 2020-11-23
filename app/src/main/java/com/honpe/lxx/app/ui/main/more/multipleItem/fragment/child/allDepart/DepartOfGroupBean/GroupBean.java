package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean;

import java.util.List;

/**
 * FileName: GroupBean
 * Author: asus
 * Date: 2020/8/21 15:37
 * Description:分组实体
 */
public class GroupBean {
    /**
     * Status : 0
     * Msg : 成功!
     * Data : [{"ParentID":"0","DeptLevel":"0","DeptCode":"1010101","DeptGm":"林朝阳","DeptName":"总经办","DeptID":"436e88c4-56a8-4bec-980f-40c52426c722"}]
     */
    private int Status;
    private String Msg;
    private List<DataEntity> Data;

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public void setData(List<DataEntity> Data) {
        this.Data = Data;
    }

    public int getStatus() {
        return Status;
    }

    public String getMsg() {
        return Msg;
    }

    public List<DataEntity> getData() {
        return Data;
    }

    public class DataEntity {
        /**
         * ParentID : 0
         * DeptLevel : 0
         * DeptCode : 1010101
         * DeptGm : 林朝阳
         * DeptName : 总经办
         * DeptID : 436e88c4-56a8-4bec-980f-40c52426c722
         */
        private String ParentID;
        private String DeptLevel;
        private String DeptCode;
        private String DeptGm;
        private String DeptName;
        private String DeptID;

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public void setDeptLevel(String DeptLevel) {
            this.DeptLevel = DeptLevel;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public void setDeptGm(String DeptGm) {
            this.DeptGm = DeptGm;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public void setDeptID(String DeptID) {
            this.DeptID = DeptID;
        }

        public String getParentID() {
            return ParentID;
        }

        public String getDeptLevel() {
            return DeptLevel;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public String getDeptGm() {
            return DeptGm;
        }

        public String getDeptName() {
            return DeptName;
        }

        public String getDeptID() {
            return DeptID;
        }
    }
}
