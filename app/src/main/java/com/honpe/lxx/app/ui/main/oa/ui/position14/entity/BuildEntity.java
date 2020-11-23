package com.honpe.lxx.app.ui.main.oa.ui.position14.entity;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: LeftBuildEntity
 * Author: asus
 * Date: 2020/9/19 11:21
 * Description:
 */
public class BuildEntity {

    /**
     * code : 200
     * data : [{"UserdNum":"3","DormitoryDetails":[{"f_fullname":"201","numbeds":"床位1(上)","f_id":74,"emp_username":"艾倍","dept_dep":"日本机壳项目部","userid":"b83ac39e-2219-43e0-89b5-3e13f15012ed","emp_usernum":"3051","emp_usersex":1},{"f_fullname":"201","numbeds":"床位2(下)","f_id":75,"emp_username":"宾国毅","dept_dep":"日本机壳打磨部","userid":"0697eca4-bdc6-4845-bdad-5f39778f7023","emp_usernum":"3473","emp_usersex":1},{"f_fullname":"201","numbeds":"床位3(上)","f_id":76,"emp_username":"陈安","dept_dep":"国际机壳手工部","userid":"94af573c-60ee-4904-a658-8fa7dda505c7","emp_usernum":"1276","emp_usersex":1},{"f_fullname":"201","numbeds":"床位4(下)","f_id":77,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位5(上)","f_id":78,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位6(下)","f_id":79,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位7(上)","f_id":80,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位8(下)","f_id":81,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位9(上)","f_id":82,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位10(下)","f_id":83,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null}],"F_FullName":"201","NumBeds":"10","F_ID":"945c30e3-3f1d-4ef3-a52a-223db8d5d81e","Remarks":"工程部五金组","UserID":null,"F_ParentId":"7638bb9a-185a-4fef-a45b-3ce782dd6489"}]
     * info : 响应成功
     */
    private int code;
    private List<DataEntity> data;
    private String info;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getInfo() {
        return info;
    }

    public class DataEntity {
        /**
         * UserdNum : 3
         * DormitoryDetails : [{"f_fullname":"201","numbeds":"床位1(上)","f_id":74,"emp_username":"艾倍","dept_dep":"日本机壳项目部","userid":"b83ac39e-2219-43e0-89b5-3e13f15012ed","emp_usernum":"3051","emp_usersex":1},{"f_fullname":"201","numbeds":"床位2(下)","f_id":75,"emp_username":"宾国毅","dept_dep":"日本机壳打磨部","userid":"0697eca4-bdc6-4845-bdad-5f39778f7023","emp_usernum":"3473","emp_usersex":1},{"f_fullname":"201","numbeds":"床位3(上)","f_id":76,"emp_username":"陈安","dept_dep":"国际机壳手工部","userid":"94af573c-60ee-4904-a658-8fa7dda505c7","emp_usernum":"1276","emp_usersex":1},{"f_fullname":"201","numbeds":"床位4(下)","f_id":77,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位5(上)","f_id":78,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位6(下)","f_id":79,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位7(上)","f_id":80,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位8(下)","f_id":81,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位9(上)","f_id":82,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null},{"f_fullname":"201","numbeds":"床位10(下)","f_id":83,"emp_username":null,"dept_dep":null,"userid":"","emp_usernum":null,"emp_usersex":null}]
         * F_FullName : 201
         * NumBeds : 10
         * F_ID : 945c30e3-3f1d-4ef3-a52a-223db8d5d81e
         * Remarks : 工程部五金组
         * UserID : null
         * F_ParentId : 7638bb9a-185a-4fef-a45b-3ce782dd6489
         */
        private String UserdNum;
        private List<DormitoryDetailsEntity> DormitoryDetails;
        private String F_FullName;
        private String NumBeds;
        private String F_ID;
        private String Remarks;
        private String UserID;
        private String F_ParentId;

        public void setUserdNum(String UserdNum) {
            this.UserdNum = UserdNum;
        }

        public void setDormitoryDetails(List<DormitoryDetailsEntity> DormitoryDetails) {
            this.DormitoryDetails = DormitoryDetails;
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

        public void setRemarks(String Remarks) {
            this.Remarks = Remarks;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public void setF_ParentId(String F_ParentId) {
            this.F_ParentId = F_ParentId;
        }

        public String getUserdNum() {
            return UserdNum;
        }

        public List<DormitoryDetailsEntity> getDormitoryDetails() {
            return DormitoryDetails;
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

        public String getRemarks() {
            return Remarks;
        }

        public String getUserID() {
            return UserID;
        }

        public String getF_ParentId() {
            return F_ParentId;
        }

        public class DormitoryDetailsEntity extends BaseNode implements Serializable {
            /**
             * f_fullname : 201
             * numbeds : 床位1(上)
             * f_id : 74
             * emp_username : 艾倍
             * dept_dep : 日本机壳项目部
             * userid : b83ac39e-2219-43e0-89b5-3e13f15012ed
             * emp_usernum : 3051
             * emp_usersex : 1
             */
            private String f_fullname;
            private String numbeds;
            private int f_id;
            private String emp_username;
            private String dept_dep;
            private String userid;
            private String emp_usernum;
            private String emp_usersex;

            public void setF_fullname(String f_fullname) {
                this.f_fullname = f_fullname;
            }

            public void setNumbeds(String numbeds) {
                this.numbeds = numbeds;
            }

            public void setF_id(int f_id) {
                this.f_id = f_id;
            }

            public void setEmp_username(String emp_username) {
                this.emp_username = emp_username;
            }

            public void setDept_dep(String dept_dep) {
                this.dept_dep = dept_dep;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public void setEmp_usernum(String emp_usernum) {
                this.emp_usernum = emp_usernum;
            }

            public void setEmp_usersex(String emp_usersex) {
                this.emp_usersex = emp_usersex;
            }

            public String getF_fullname() {
                return f_fullname;
            }

            public String getNumbeds() {
                return numbeds;
            }

            public int getF_id() {
                return f_id;
            }

            public String getEmp_username() {
                return emp_username;
            }

            public String getDept_dep() {
                return dept_dep;
            }

            public String getUserid() {
                return userid;
            }

            public String getEmp_usernum() {
                return emp_usernum;
            }

            public String getEmp_usersex() {
                return emp_usersex;
            }

            @Nullable
            @Override
            public List<BaseNode> getChildNode() {
                return null;
            }
        }
    }
}
