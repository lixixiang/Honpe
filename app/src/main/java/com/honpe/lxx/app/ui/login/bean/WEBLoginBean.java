package com.honpe.lxx.app.ui.login.bean;

import java.util.List;

/**
 * @ProjectName: Honpe
 * @CreateDate: 2020/7/24 16:40
 * @Author: 李熙祥
 * @Description: java类作用描述
 */
public class WEBLoginBean {

    /**
     * code : 200
     * info : 登录成功
     * data : {"baseinfo":{"userId":"70060f98-6ab0-4f5e-b9cc-b31aa0c78c1a","enCode":"777","account":"panweidong","password":null,"secretkey":null,"realName":"潘卫东","nickName":null,"headIcon":".png","gender":1,"mobile":null,"telephone":null,"email":null,"oICQ":null,"weChat":null,"companyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","companyIds":["8d416f75-76f9-48b6-aa97-4a626cd81fee"],"departmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","departmentName":"研发部","rootDepartment":{"Id":"1cc82017-73a2-474f-a908-c59cb121b2d3","Name":"研发部","HomePage":""},"parentDepartment":{"Id":"1cc82017-73a2-474f-a908-c59cb121b2d3","Name":"研发部","HomePage":""},"departmentIds":["1cc82017-73a2-474f-a908-c59cb121b2d3","5ba51dbc-f1d7-482e-a249-ebfe4a006aa0","65cc0d28-32ea-4eb8-9aba-2b1a0d5a28b3","1118f656-e4ee-4811-8187-71426be5e569","81e195a3-e8be-4628-9576-713a266a75c2","e7c22d4a-65fa-4a11-a9ed-edaf7e1ef776","a0d42e75-a70b-4cef-8b0f-52456fdf640f","47c465e0-bf8c-42a1-b749-c4449d1314f0"],"openId":null,"roleIds":"b7a61699-947c-4100-a18d-ddf5eeb6c3dd,d897ac32-5bbf-4dbc-9604-28bcad144470","postIds":"fe2a88ae-4e39-444e-ab01-d76b111b597d","isSystem":false,"appId":"RchlDev_1.0.0.0","logTime":"2020-07-24 16:39:22","iPAddress":"113.92.94.4","browser":"Mozilla 0.0","loginMark":"PZ","token":"263a645b-37fc-4094-984a-feb1f54daf97","imUrl":null,"wfProcessId":null},"post":[{"F_PostId":"fe2a88ae-4e39-444e-ab01-d76b111b597d","F_ParentId":"f0be1e46-66b5-4f56-94b0-04cc077d0aea","F_Name":"研发总监","F_EnCode":"HP-030001","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_DepartmentId":"45120f2a-a393-410f-82fb-f0671625450d","F_DeleteMark":0,"F_Description":null,"F_CreateDate":"2019-04-30 08:56:34","F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_CreateUserName":"覃勇鹏","F_ModifyDate":"2019-04-30 09:04:48","F_ModifyUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_ModifyUserName":"覃勇鹏"}],"role":[{"F_RoleId":"d897ac32-5bbf-4dbc-9604-28bcad144470","F_Category":"1","F_EnCode":"100003","F_FullName":"部门负责人","F_SortCode":null,"F_DeleteMark":0,"F_EnabledMark":1,"F_Description":null,"F_CreateDate":"2019-04-26 11:50:42","F_CreateUserId":"7c39151f-6daf-4236-8b23-aa4773e5da7e","F_CreateUserName":"潘东海","F_ModifyDate":null,"F_ModifyUserId":null,"F_ModifyUserName":null},{"F_RoleId":"b7a61699-947c-4100-a18d-ddf5eeb6c3dd","F_Category":"1","F_EnCode":"100001","F_FullName":"超级管理员","F_SortCode":1,"F_DeleteMark":0,"F_EnabledMark":1,"F_Description":"系统管理","F_CreateDate":"2015-11-04 16:10:48","F_CreateUserId":"System","F_CreateUserName":"超级管理员","F_ModifyDate":null,"F_ModifyUserId":null,"F_ModifyUserName":null}]}
     */

    private int code;
    private String info;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * baseinfo : {"userId":"70060f98-6ab0-4f5e-b9cc-b31aa0c78c1a","enCode":"777","account":"panweidong","password":null,"secretkey":null,"realName":"潘卫东","nickName":null,"headIcon":".png","gender":1,"mobile":null,"telephone":null,"email":null,"oICQ":null,"weChat":null,"companyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","companyIds":["8d416f75-76f9-48b6-aa97-4a626cd81fee"],"departmentId":"1cc82017-73a2-474f-a908-c59cb121b2d3","departmentName":"研发部","rootDepartment":{"Id":"1cc82017-73a2-474f-a908-c59cb121b2d3","Name":"研发部","HomePage":""},"parentDepartment":{"Id":"1cc82017-73a2-474f-a908-c59cb121b2d3","Name":"研发部","HomePage":""},"departmentIds":["1cc82017-73a2-474f-a908-c59cb121b2d3","5ba51dbc-f1d7-482e-a249-ebfe4a006aa0","65cc0d28-32ea-4eb8-9aba-2b1a0d5a28b3","1118f656-e4ee-4811-8187-71426be5e569","81e195a3-e8be-4628-9576-713a266a75c2","e7c22d4a-65fa-4a11-a9ed-edaf7e1ef776","a0d42e75-a70b-4cef-8b0f-52456fdf640f","47c465e0-bf8c-42a1-b749-c4449d1314f0"],"openId":null,"roleIds":"b7a61699-947c-4100-a18d-ddf5eeb6c3dd,d897ac32-5bbf-4dbc-9604-28bcad144470","postIds":"fe2a88ae-4e39-444e-ab01-d76b111b597d","isSystem":false,"appId":"RchlDev_1.0.0.0","logTime":"2020-07-24 16:39:22","iPAddress":"113.92.94.4","browser":"Mozilla 0.0","loginMark":"PZ","token":"263a645b-37fc-4094-984a-feb1f54daf97","imUrl":null,"wfProcessId":null}
         * post : [{"F_PostId":"fe2a88ae-4e39-444e-ab01-d76b111b597d","F_ParentId":"f0be1e46-66b5-4f56-94b0-04cc077d0aea","F_Name":"研发总监","F_EnCode":"HP-030001","F_CompanyId":"8d416f75-76f9-48b6-aa97-4a626cd81fee","F_DepartmentId":"45120f2a-a393-410f-82fb-f0671625450d","F_DeleteMark":0,"F_Description":null,"F_CreateDate":"2019-04-30 08:56:34","F_CreateUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_CreateUserName":"覃勇鹏","F_ModifyDate":"2019-04-30 09:04:48","F_ModifyUserId":"c06d2904-2ca3-4198-b09d-b0f10bf7090a","F_ModifyUserName":"覃勇鹏"}]
         * role : [{"F_RoleId":"d897ac32-5bbf-4dbc-9604-28bcad144470","F_Category":"1","F_EnCode":"100003","F_FullName":"部门负责人","F_SortCode":null,"F_DeleteMark":0,"F_EnabledMark":1,"F_Description":null,"F_CreateDate":"2019-04-26 11:50:42","F_CreateUserId":"7c39151f-6daf-4236-8b23-aa4773e5da7e","F_CreateUserName":"潘东海","F_ModifyDate":null,"F_ModifyUserId":null,"F_ModifyUserName":null},{"F_RoleId":"b7a61699-947c-4100-a18d-ddf5eeb6c3dd","F_Category":"1","F_EnCode":"100001","F_FullName":"超级管理员","F_SortCode":1,"F_DeleteMark":0,"F_EnabledMark":1,"F_Description":"系统管理","F_CreateDate":"2015-11-04 16:10:48","F_CreateUserId":"System","F_CreateUserName":"超级管理员","F_ModifyDate":null,"F_ModifyUserId":null,"F_ModifyUserName":null}]
         */

        private BaseinfoBean baseinfo;
        private List<PostBean> post;
        private List<RoleBean> role;

        public BaseinfoBean getBaseinfo() {
            return baseinfo;
        }

        public void setBaseinfo(BaseinfoBean baseinfo) {
            this.baseinfo = baseinfo;
        }

        public List<PostBean> getPost() {
            return post;
        }

        public void setPost(List<PostBean> post) {
            this.post = post;
        }

        public List<RoleBean> getRole() {
            return role;
        }

        public void setRole(List<RoleBean> role) {
            this.role = role;
        }

        public static class BaseinfoBean {
            /**
             * userId : 70060f98-6ab0-4f5e-b9cc-b31aa0c78c1a
             * enCode : 777
             * account : panweidong
             * password : null
             * secretkey : null
             * realName : 潘卫东
             * nickName : null
             * headIcon : .png
             * gender : 1
             * mobile : null
             * telephone : null
             * email : null
             * oICQ : null
             * weChat : null
             * companyId : 8d416f75-76f9-48b6-aa97-4a626cd81fee
             * companyIds : ["8d416f75-76f9-48b6-aa97-4a626cd81fee"]
             * departmentId : 1cc82017-73a2-474f-a908-c59cb121b2d3
             * departmentName : 研发部
             * rootDepartment : {"Id":"1cc82017-73a2-474f-a908-c59cb121b2d3","Name":"研发部","HomePage":""}
             * parentDepartment : {"Id":"1cc82017-73a2-474f-a908-c59cb121b2d3","Name":"研发部","HomePage":""}
             * departmentIds : ["1cc82017-73a2-474f-a908-c59cb121b2d3","5ba51dbc-f1d7-482e-a249-ebfe4a006aa0","65cc0d28-32ea-4eb8-9aba-2b1a0d5a28b3","1118f656-e4ee-4811-8187-71426be5e569","81e195a3-e8be-4628-9576-713a266a75c2","e7c22d4a-65fa-4a11-a9ed-edaf7e1ef776","a0d42e75-a70b-4cef-8b0f-52456fdf640f","47c465e0-bf8c-42a1-b749-c4449d1314f0"]
             * openId : null
             * roleIds : b7a61699-947c-4100-a18d-ddf5eeb6c3dd,d897ac32-5bbf-4dbc-9604-28bcad144470
             * postIds : fe2a88ae-4e39-444e-ab01-d76b111b597d
             * isSystem : false
             * appId : RchlDev_1.0.0.0
             * logTime : 2020-07-24 16:39:22
             * iPAddress : 113.92.94.4
             * browser : Mozilla 0.0
             * loginMark : PZ
             * token : 263a645b-37fc-4094-984a-feb1f54daf97
             * imUrl : null
             * wfProcessId : null
             */

            private String userId;
            private String enCode;
            private String account;
            private Object password;
            private Object secretkey;
            private String realName;
            private Object nickName;
            private String headIcon;
            private int gender;
            private Object mobile;
            private Object telephone;
            private Object email;
            private Object oICQ;
            private Object weChat;
            private String companyId;
            private String departmentId;
            private String departmentName;
            private RootDepartmentBean rootDepartment;
            private ParentDepartmentBean parentDepartment;
            private Object openId;
            private String roleIds;
            private String postIds;
            private boolean isSystem;
            private String appId;
            private String logTime;
            private String iPAddress;
            private String browser;
            private String loginMark;
            private String token;
            private Object imUrl;
            private Object wfProcessId;
            private List<String> companyIds;
            private List<String> departmentIds;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getEnCode() {
                return enCode;
            }

            public void setEnCode(String enCode) {
                this.enCode = enCode;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public Object getSecretkey() {
                return secretkey;
            }

            public void setSecretkey(Object secretkey) {
                this.secretkey = secretkey;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public Object getNickName() {
                return nickName;
            }

            public void setNickName(Object nickName) {
                this.nickName = nickName;
            }

            public String getHeadIcon() {
                return headIcon;
            }

            public void setHeadIcon(String headIcon) {
                this.headIcon = headIcon;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getTelephone() {
                return telephone;
            }

            public void setTelephone(Object telephone) {
                this.telephone = telephone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getOICQ() {
                return oICQ;
            }

            public void setOICQ(Object oICQ) {
                this.oICQ = oICQ;
            }

            public Object getWeChat() {
                return weChat;
            }

            public void setWeChat(Object weChat) {
                this.weChat = weChat;
            }

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(String departmentId) {
                this.departmentId = departmentId;
            }

            public String getDepartmentName() {
                return departmentName;
            }

            public void setDepartmentName(String departmentName) {
                this.departmentName = departmentName;
            }

            public RootDepartmentBean getRootDepartment() {
                return rootDepartment;
            }

            public void setRootDepartment(RootDepartmentBean rootDepartment) {
                this.rootDepartment = rootDepartment;
            }

            public ParentDepartmentBean getParentDepartment() {
                return parentDepartment;
            }

            public void setParentDepartment(ParentDepartmentBean parentDepartment) {
                this.parentDepartment = parentDepartment;
            }

            public Object getOpenId() {
                return openId;
            }

            public void setOpenId(Object openId) {
                this.openId = openId;
            }

            public String getRoleIds() {
                return roleIds;
            }

            public void setRoleIds(String roleIds) {
                this.roleIds = roleIds;
            }

            public String getPostIds() {
                return postIds;
            }

            public void setPostIds(String postIds) {
                this.postIds = postIds;
            }

            public boolean isIsSystem() {
                return isSystem;
            }

            public void setIsSystem(boolean isSystem) {
                this.isSystem = isSystem;
            }

            public String getAppId() {
                return appId;
            }

            public void setAppId(String appId) {
                this.appId = appId;
            }

            public String getLogTime() {
                return logTime;
            }

            public void setLogTime(String logTime) {
                this.logTime = logTime;
            }

            public String getIPAddress() {
                return iPAddress;
            }

            public void setIPAddress(String iPAddress) {
                this.iPAddress = iPAddress;
            }

            public String getBrowser() {
                return browser;
            }

            public void setBrowser(String browser) {
                this.browser = browser;
            }

            public String getLoginMark() {
                return loginMark;
            }

            public void setLoginMark(String loginMark) {
                this.loginMark = loginMark;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public Object getImUrl() {
                return imUrl;
            }

            public void setImUrl(Object imUrl) {
                this.imUrl = imUrl;
            }

            public Object getWfProcessId() {
                return wfProcessId;
            }

            public void setWfProcessId(Object wfProcessId) {
                this.wfProcessId = wfProcessId;
            }

            public List<String> getCompanyIds() {
                return companyIds;
            }

            public void setCompanyIds(List<String> companyIds) {
                this.companyIds = companyIds;
            }

            public List<String> getDepartmentIds() {
                return departmentIds;
            }

            public void setDepartmentIds(List<String> departmentIds) {
                this.departmentIds = departmentIds;
            }

            public static class RootDepartmentBean {
                /**
                 * Id : 1cc82017-73a2-474f-a908-c59cb121b2d3
                 * Name : 研发部
                 * HomePage :
                 */

                private String Id;
                private String Name;
                private String HomePage;

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public String getHomePage() {
                    return HomePage;
                }

                public void setHomePage(String HomePage) {
                    this.HomePage = HomePage;
                }
            }

            public static class ParentDepartmentBean {
                /**
                 * Id : 1cc82017-73a2-474f-a908-c59cb121b2d3
                 * Name : 研发部
                 * HomePage :
                 */

                private String Id;
                private String Name;
                private String HomePage;

                public String getId() {
                    return Id;
                }

                public void setId(String Id) {
                    this.Id = Id;
                }

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public String getHomePage() {
                    return HomePage;
                }

                public void setHomePage(String HomePage) {
                    this.HomePage = HomePage;
                }
            }
        }

        public static class PostBean {
            /**
             * F_PostId : fe2a88ae-4e39-444e-ab01-d76b111b597d
             * F_ParentId : f0be1e46-66b5-4f56-94b0-04cc077d0aea
             * F_Name : 研发总监
             * F_EnCode : HP-030001
             * F_CompanyId : 8d416f75-76f9-48b6-aa97-4a626cd81fee
             * F_DepartmentId : 45120f2a-a393-410f-82fb-f0671625450d
             * F_DeleteMark : 0
             * F_Description : null
             * F_CreateDate : 2019-04-30 08:56:34
             * F_CreateUserId : c06d2904-2ca3-4198-b09d-b0f10bf7090a
             * F_CreateUserName : 覃勇鹏
             * F_ModifyDate : 2019-04-30 09:04:48
             * F_ModifyUserId : c06d2904-2ca3-4198-b09d-b0f10bf7090a
             * F_ModifyUserName : 覃勇鹏
             */

            private String F_PostId;
            private String F_ParentId;
            private String F_Name;
            private String F_EnCode;
            private String F_CompanyId;
            private String F_DepartmentId;
            private int F_DeleteMark;
            private Object F_Description;
            private String F_CreateDate;
            private String F_CreateUserId;
            private String F_CreateUserName;
            private String F_ModifyDate;
            private String F_ModifyUserId;
            private String F_ModifyUserName;

            public String getF_PostId() {
                return F_PostId;
            }

            public void setF_PostId(String F_PostId) {
                this.F_PostId = F_PostId;
            }

            public String getF_ParentId() {
                return F_ParentId;
            }

            public void setF_ParentId(String F_ParentId) {
                this.F_ParentId = F_ParentId;
            }

            public String getF_Name() {
                return F_Name;
            }

            public void setF_Name(String F_Name) {
                this.F_Name = F_Name;
            }

            public String getF_EnCode() {
                return F_EnCode;
            }

            public void setF_EnCode(String F_EnCode) {
                this.F_EnCode = F_EnCode;
            }

            public String getF_CompanyId() {
                return F_CompanyId;
            }

            public void setF_CompanyId(String F_CompanyId) {
                this.F_CompanyId = F_CompanyId;
            }

            public String getF_DepartmentId() {
                return F_DepartmentId;
            }

            public void setF_DepartmentId(String F_DepartmentId) {
                this.F_DepartmentId = F_DepartmentId;
            }

            public int getF_DeleteMark() {
                return F_DeleteMark;
            }

            public void setF_DeleteMark(int F_DeleteMark) {
                this.F_DeleteMark = F_DeleteMark;
            }

            public Object getF_Description() {
                return F_Description;
            }

            public void setF_Description(Object F_Description) {
                this.F_Description = F_Description;
            }

            public String getF_CreateDate() {
                return F_CreateDate;
            }

            public void setF_CreateDate(String F_CreateDate) {
                this.F_CreateDate = F_CreateDate;
            }

            public String getF_CreateUserId() {
                return F_CreateUserId;
            }

            public void setF_CreateUserId(String F_CreateUserId) {
                this.F_CreateUserId = F_CreateUserId;
            }

            public String getF_CreateUserName() {
                return F_CreateUserName;
            }

            public void setF_CreateUserName(String F_CreateUserName) {
                this.F_CreateUserName = F_CreateUserName;
            }

            public String getF_ModifyDate() {
                return F_ModifyDate;
            }

            public void setF_ModifyDate(String F_ModifyDate) {
                this.F_ModifyDate = F_ModifyDate;
            }

            public String getF_ModifyUserId() {
                return F_ModifyUserId;
            }

            public void setF_ModifyUserId(String F_ModifyUserId) {
                this.F_ModifyUserId = F_ModifyUserId;
            }

            public String getF_ModifyUserName() {
                return F_ModifyUserName;
            }

            public void setF_ModifyUserName(String F_ModifyUserName) {
                this.F_ModifyUserName = F_ModifyUserName;
            }
        }

        public static class RoleBean {
            /**
             * F_RoleId : d897ac32-5bbf-4dbc-9604-28bcad144470
             * F_Category : 1
             * F_EnCode : 100003
             * F_FullName : 部门负责人
             * F_SortCode : null
             * F_DeleteMark : 0
             * F_EnabledMark : 1
             * F_Description : null
             * F_CreateDate : 2019-04-26 11:50:42
             * F_CreateUserId : 7c39151f-6daf-4236-8b23-aa4773e5da7e
             * F_CreateUserName : 潘东海
             * F_ModifyDate : null
             * F_ModifyUserId : null
             * F_ModifyUserName : null
             */

            private String F_RoleId;
            private String F_Category;
            private String F_EnCode;
            private String F_FullName;
            private Object F_SortCode;
            private int F_DeleteMark;
            private int F_EnabledMark;
            private Object F_Description;
            private String F_CreateDate;
            private String F_CreateUserId;
            private String F_CreateUserName;
            private Object F_ModifyDate;
            private Object F_ModifyUserId;
            private Object F_ModifyUserName;

            public String getF_RoleId() {
                return F_RoleId;
            }

            public void setF_RoleId(String F_RoleId) {
                this.F_RoleId = F_RoleId;
            }

            public String getF_Category() {
                return F_Category;
            }

            public void setF_Category(String F_Category) {
                this.F_Category = F_Category;
            }

            public String getF_EnCode() {
                return F_EnCode;
            }

            public void setF_EnCode(String F_EnCode) {
                this.F_EnCode = F_EnCode;
            }

            public String getF_FullName() {
                return F_FullName;
            }

            public void setF_FullName(String F_FullName) {
                this.F_FullName = F_FullName;
            }

            public Object getF_SortCode() {
                return F_SortCode;
            }

            public void setF_SortCode(Object F_SortCode) {
                this.F_SortCode = F_SortCode;
            }

            public int getF_DeleteMark() {
                return F_DeleteMark;
            }

            public void setF_DeleteMark(int F_DeleteMark) {
                this.F_DeleteMark = F_DeleteMark;
            }

            public int getF_EnabledMark() {
                return F_EnabledMark;
            }

            public void setF_EnabledMark(int F_EnabledMark) {
                this.F_EnabledMark = F_EnabledMark;
            }

            public Object getF_Description() {
                return F_Description;
            }

            public void setF_Description(Object F_Description) {
                this.F_Description = F_Description;
            }

            public String getF_CreateDate() {
                return F_CreateDate;
            }

            public void setF_CreateDate(String F_CreateDate) {
                this.F_CreateDate = F_CreateDate;
            }

            public String getF_CreateUserId() {
                return F_CreateUserId;
            }

            public void setF_CreateUserId(String F_CreateUserId) {
                this.F_CreateUserId = F_CreateUserId;
            }

            public String getF_CreateUserName() {
                return F_CreateUserName;
            }

            public void setF_CreateUserName(String F_CreateUserName) {
                this.F_CreateUserName = F_CreateUserName;
            }

            public Object getF_ModifyDate() {
                return F_ModifyDate;
            }

            public void setF_ModifyDate(Object F_ModifyDate) {
                this.F_ModifyDate = F_ModifyDate;
            }

            public Object getF_ModifyUserId() {
                return F_ModifyUserId;
            }

            public void setF_ModifyUserId(Object F_ModifyUserId) {
                this.F_ModifyUserId = F_ModifyUserId;
            }

            public Object getF_ModifyUserName() {
                return F_ModifyUserName;
            }

            public void setF_ModifyUserName(Object F_ModifyUserName) {
                this.F_ModifyUserName = F_ModifyUserName;
            }
        }
    }
}
