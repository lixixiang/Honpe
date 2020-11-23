package com.honpe.lxx.app.ui.main.oa.ui.position14.entity;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: BedBean
 * Author: asus
 * Date: 2020/9/21 9:30
 * Description:
 */
public class BedBean implements Serializable {

    /**
     * code : 200
     * data : {"rows":[{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位1(上)","F_ID":"74","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位2(下)","F_ID":"75","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位3(上)","F_ID":"76","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位4(下)","F_ID":"77","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位5(上)","F_ID":"78","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位6(下)","F_ID":"79","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位7(上)","F_ID":"80","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位8(下)","F_ID":"81","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位9(上)","F_ID":"82","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位10(下)","F_ID":"83","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null}]}
     * info : 响应成功
     */
    private int code;
    private DataEntity data;
    private String info;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public class DataEntity implements Serializable{
        /**
         * rows : [{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位1(上)","F_ID":"74","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位2(下)","F_ID":"75","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位3(上)","F_ID":"76","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位4(下)","F_ID":"77","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位5(上)","F_ID":"78","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位6(下)","F_ID":"79","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位7(上)","F_ID":"80","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位8(下)","F_ID":"81","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位9(上)","F_ID":"82","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null},{"Emp_UserName":null,"F_FullName":"201","NumBeds":"床位10(下)","F_ID":"83","UserID":"","Dept_Dep":null,"Emp_UserNum":null,"Emp_UserSex":null}]
         */
        private List<RowsEntity> rows;

        public void setRows(List<RowsEntity> rows) {
            this.rows = rows;
        }

        public List<RowsEntity> getRows() {
            return rows;
        }

        public class RowsEntity extends BaseNode implements Serializable{
            /**
             * Emp_UserName : null
             * F_FullName : 201
             * NumBeds : 床位1(上)
             * F_ID : 74
             * UserID :
             * Dept_Dep : null
             * Emp_UserNum : null
             * Emp_UserSex : null
             */
            private String Emp_UserName;
            private String F_FullName;
            private String NumBeds;
            private String F_ID;
            private String UserID;
            private String Dept_Dep;
            private String Emp_UserNum;
            private String Emp_UserSex;

            public void setEmp_UserName(String Emp_UserName) {
                this.Emp_UserName = Emp_UserName;
            }

            public void setF_FullName(String F_FullName) {
                this.F_FullName = F_FullName;
            }

            public void setNumBeds(String NumBeds) {
                this.NumBeds = NumBeds;
            }

            public void setF_ID(String F_ID) {
                this.F_ID = F_ID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public void setDept_Dep(String Dept_Dep) {
                this.Dept_Dep = Dept_Dep;
            }

            public void setEmp_UserNum(String Emp_UserNum) {
                this.Emp_UserNum = Emp_UserNum;
            }

            public void setEmp_UserSex(String Emp_UserSex) {
                this.Emp_UserSex = Emp_UserSex;
            }

            public String getEmp_UserName() {
                return Emp_UserName;
            }

            public String getF_FullName() {
                return F_FullName;
            }

            public String getNumBeds() {
                return NumBeds;
            }

            public String getF_ID() {
                return F_ID;
            }

            public String getUserID() {
                return UserID;
            }

            public String getDept_Dep() {
                return Dept_Dep;
            }

            public String getEmp_UserNum() {
                return Emp_UserNum;
            }

            public String getEmp_UserSex() {
                return Emp_UserSex;
            }

            @Nullable
            @Override
            public List<BaseNode> getChildNode() {
                return null;
            }
        }
    }
}