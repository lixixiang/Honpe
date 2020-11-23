package com.honpe.lxx.app.api;

/**
 * created by lxx at 2019/11/11 11:33
 * 描述:
 */
public class FinalClass  {
    public static final int FIRST = 0;//首页
    public static final int SECOND = 1;//查询
    public static final int THIRD = 2; //订单
    public static final int FOURTH = 3;//我的

    public static final int START_CAMER_RESULT = 0x2;//扫描
    public static final int RC_CAMERA = 0X01;
    public static final int Address = 0x02;
    public static final int EDIT_ADDRESS = 0x03;
    public static final int ADD_ADDRESS = 0x04;
    //保存登录信息
    public static final String IsSession = "IsSession";
    public static final String Status = "Status";
    public static final String Session = "KeyValue";
    public static final String UserName = "UserName";
    public static final String UserCode = "UserCode";
    public static final String PassWord = "PassWord";
    public static final String HeadIcon = "Ico";
    public static final String CircleIcon = "InSpeechBg";
    public static final String UserType = "UserType"; //判断登陆时显示 内部员工首页图标
    public static final String EmployeeId = "EmployeeId";
    public static final String WEB_USE_ID = "web_use_id";
    public static final String PARENT_DEPART = "parent_depart";
    public static final String ENCODE = "enCode"; //登录人的工号
    public static final String DEPART_NAME = "depart_name"; //部门名称
    public static final String F_Name = "F_Name"; //例如 软件工程师  职务
    public static final String DEPART_ID = "DEPART_ID"; //部门ID
    //////////////////////////////////Event//////////////////////////////////////
    public static final int ME_info = 4;
    public static final int UPDATA_NONEED = 0;//不需要更新     //软件更新
    public static final int UPDATA_CLIENT = 1;
    public static final int GET_UNDATAINFO_ERROR = 2;


    //安装APK
    public static final int REQUEST_CODE_APP_INSTALL = 0x111;
    //退出APP
    public static final int EXIT_APP = 5;
    //上传头像返回到我的
    public static final int UPDATA_TO_ME = 6;
    //打开GPS权限
    public static final int OPEN_GPS = 5002;
    //首页跳到子界面返回按钮出现
    public static final int SHOW_BACK = 540;
    public static final int CHANGE = 101;
    public static final int ORDER = 102;
    //员工点餐
    public static final int ONE = 7;
    public static final int TWO = 8;
    public static final int THREE =9;
    //点赞
    public static final int PRISE = 10;
    //审批详情
    //点击审批刷新数据
    public static final int REFRESH_DATA = 1010;
    public static final int REFRESH_BACK_DATA = 1020;
    /**
     * viewpager
     */
    public static final int video_status = 0xff0;

    /**
     * 正方形二维码宽度
     */
    public static final int CODE_WIDTH = 440;
    /**
     * LOGO宽度值,最大不能大于二维码20%宽度值,大于可能会导致二维码信息失效
     */
    public static final int LOGO_WIDTH_MAX = CODE_WIDTH / 5;
    /**
     *LOGO宽度值,最小不能小鱼二维码10%宽度值,小于影响Logo与二维码的整体搭配
     */
    public static final int LOGO_WIDTH_MIN = CODE_WIDTH / 10;
    //新增访客单
    public static final int ADD_APPOINT = 0x11;
    public static final int REQ_MODIFY_FRAGMENT = 100;
    public static final int REFRESH_DATA_APPOINT = 1000;
    //访客新增内
    public static final int REFRASH = 0x1000; //用于访客申请成功刷新
    public static final int A = 0x111111;
    public static final int B = 0x222222;
    public static final int C = 0x333333;
    public static final int D = 0x444444;
    public static final int E = 0x555555;
    public static final int F = 0x666666;
    public static final int G = 0x777777;
    public static final int H = 0x888888;
    public static final int I = 0x999999;
    public static final int J = 0x2;
    public static final int K = 0x22;
    public static final int L = 0x222;
    //行车轨迹
    public static final String Tokey = "Tokey";
    public static final String UserId = "UserId";
    //派车刷新
    public static final int SEND_CAR_REFRESH = 0x4100;

    //复杂布局常量
    public static final int TEXT = 1;
    public static final int TEXT_IMAGE = 2;
    public static final int DEPART = 3;
    public static final int YEAR_MONTH_DAY_TIME_MINUTE = 4;
    public static final int EDIT = 5;
    public static final int REMARKS = 6;
    public static final int REPORT_STAFF = 0x1;//员工点餐1
    public static final int EMPLOYEE_SECOND = 0x2;//员工选材界面
    public static final int EMPLOYEE_THIRD = 0x3;//员工点赞
    public static final int EMPLOYEE_MAIN = 121; //员工主界面
    public static final int EMPLOYEE_MAIN2 = 122; //员工二界面
    public static final int EMPLOYEE_MAIN3 = 123; //员工三界面
    //派车
    public static final int MOB_CAR_INFO = 100;
    public static final int MOB = 101;
    ///////////////////////////////////session 过期 intent 类型///////////////////////////////////////////
    public static final int CHECK_SESSION = 10101;
    //访客预约
    public static final int TYPE_APPOINT =2000;
    //进入OA界面
    public static final int TYPE_OA = 3000;
    //进入更多界面
    public static final int TYPE_MORE = 4000;
    //审批ID
    public static final String categoryId = "1";
    //客户点餐请求码
    public static final int CustomApplyCore = 1510;
    //修改客户点餐请求码
    public static final int CustomModCore = 1520;
    //所有部门考勤界面搜索详情
    public static final int SEARCH_DEPART_DETAIL = 1600;
    //分组筛选返回所有部门界面
    public static final int COME_BACK_ALL_DEPART=1601;

    //返回考勤管理
    public static final int BACK_CHECK_IN_MANAGE = 1610;
    //全部应用管理界面返回时更新数据
    public static final int ALL_BACK_HOME=1700;
    //新增维修单数据
    //任务
    public static final int Add_REPAIR_LIST_TOAST = 2200;
    //返回数据进行刷新主界面
    public static final int Add_RESULT_BACK_DATA = 2300;
    //传递维修数据
    public static final int INTENT_REPAIR_DATA = 2400;
    //维修审批权限是否可以点击
    public static final int REPAIR_CLICK = 2500;
    //添加入住人员
    public static final int ADD_CHECK_IN_DORM = 0x3010;
    //获取 F_id 来 用于请求数据刷新床位
    public static final int UPDATE_CHECK_IN_INFO = 0x3020;
    //更新床位
    public static final int UPDATE_CHECK_IN_BED = 0x3030;
    //右侧列表头部数据
    public static final int RIGHT_HEAD_TITLE = 0X3040;
    //刷新查询审批数据
    public static final int REFRESH_SEARCH_APPROVE = 0x4080;
    //刷新车辆信息，车辆进出信息
    public static final int REFRESH_CAR_INFO_INTO = 0x5000;
}
