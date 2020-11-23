package com.honpe.lxx.app.ui.main.oa.ui.position14.entity;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: LevelEntity
 * Author: asus
 * Date: 2020/9/19 12:29
 * Description:
 */
public class LevelEntity extends BaseExpandNode implements Serializable {
    private String buildNum;
    private List<FloorEntity> floorData;
    private List<BaseNode> childNode;

    public LevelEntity() {
        setExpanded(false);
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public String getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }

    public List<FloorEntity> getFloorData() {
        return floorData;
    }

    public void setFloorData(List<FloorEntity> floorData) {
        this.floorData = floorData;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    public static class FloorEntity extends BaseExpandNode implements Serializable {
        private String UserdNum;
        private String F_FullName;
        private String NumBeds;
        private String F_ID;
        private String Remarks;
        private String UserID;
        private String F_ParentId;
        private List<RoomEntity> roomEntities;
        private List<BaseNode> childNode;

        public FloorEntity() {
            setExpanded(false);
        }

        public void setChildNode(List<BaseNode> childNode) {
            this.childNode = childNode;
        }

        public List<RoomEntity> getRoomEntities() {
            return roomEntities;
        }

        public void setRoomEntities(List<RoomEntity> roomEntities) {
            this.roomEntities = roomEntities;
        }

        public String getUserdNum() {
            return UserdNum;
        }

        public void setUserdNum(String userdNum) {
            UserdNum = userdNum;
        }

        public String getF_FullName() {
            return F_FullName;
        }

        public void setF_FullName(String f_FullName) {
            F_FullName = f_FullName;
        }

        public String getNumBeds() {
            return NumBeds;
        }

        public void setNumBeds(String numBeds) {
            NumBeds = numBeds;
        }

        public String getF_ID() {
            return F_ID;
        }

        public void setF_ID(String f_ID) {
            F_ID = f_ID;
        }

        public String getRemarks() {
            return Remarks;
        }

        public void setRemarks(String remarks) {
            Remarks = remarks;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String userID) {
            UserID = userID;
        }

        public String getF_ParentId() {
            return F_ParentId;
        }

        public void setF_ParentId(String f_ParentId) {
            F_ParentId = f_ParentId;
        }

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return childNode;
        }


        public static class RoomEntity extends BaseExpandNode implements Serializable {
            private String UserdNum;
            private String F_FullName;
            private String NumBeds;
            private String F_ID;
            private String Remarks;
            private String UserID;
            private String F_ParentId;
            private String roomStatus;
            private List<BedEntity> bedEntities;
            private List<BaseNode> childNode;

            public void setChildNode(List<BaseNode> childNode) {
                this.childNode = childNode;
            }

            public List<BedEntity> getBedEntities() {
                return bedEntities;
            }

            public void setBedEntities(List<BedEntity> bedEntities) {
                this.bedEntities = bedEntities;
            }

            public RoomEntity() {
                setExpanded(false);
            }
            public String getUserdNum() {
                return UserdNum;
            }

            public String getRoomStatus() {
                return roomStatus;
            }

            public void setRoomStatus(String roomStatus) {
                this.roomStatus = roomStatus;
            }

            public void setUserdNum(String userdNum) {
                UserdNum = userdNum;
            }

            public String getF_FullName() {
                return F_FullName;
            }

            public void setF_FullName(String f_FullName) {
                F_FullName = f_FullName;
            }

            public String getNumBeds() {
                return NumBeds;
            }

            public void setNumBeds(String numBeds) {
                NumBeds = numBeds;
            }

            public String getF_ID() {
                return F_ID;
            }

            public void setF_ID(String f_ID) {
                F_ID = f_ID;
            }

            public String getRemarks() {
                return Remarks;
            }

            public void setRemarks(String remarks) {
                Remarks = remarks;
            }

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String userID) {
                UserID = userID;
            }

            public String getF_ParentId() {
                return F_ParentId;
            }

            public void setF_ParentId(String f_ParentId) {
                F_ParentId = f_ParentId;
            }

            @Nullable
            @Override
            public List<BaseNode> getChildNode() {
                return childNode;
            }

            public static class BedEntity extends BaseNode implements Serializable{
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
                private String f_fullname;
                private String numbeds;
                private int f_id;
                private String emp_username;
                private String dept_dep;
                private String userid;
                private String emp_usernum;
                private String emp_usersex;
                private int index;

                public int getIndex() {
                    return index;
                }

                public void setIndex(int index) {
                    this.index = index;
                }

                public String getF_fullname() {
                    return f_fullname;
                }

                public void setF_fullname(String f_fullname) {
                    this.f_fullname = f_fullname;
                }

                public String getNumbeds() {
                    return numbeds;
                }

                public void setNumbeds(String numbeds) {
                    this.numbeds = numbeds;
                }

                public int getF_id() {
                    return f_id;
                }

                public void setF_id(int f_id) {
                    this.f_id = f_id;
                }

                public String getEmp_username() {
                    return emp_username;
                }

                public void setEmp_username(String emp_username) {
                    this.emp_username = emp_username;
                }

                public String getDept_dep() {
                    return dept_dep;
                }

                public void setDept_dep(String dept_dep) {
                    this.dept_dep = dept_dep;
                }

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getEmp_usernum() {
                    return emp_usernum;
                }

                public void setEmp_usernum(String emp_usernum) {
                    this.emp_usernum = emp_usernum;
                }

                public String getEmp_usersex() {
                    return emp_usersex;
                }

                public void setEmp_usersex(String emp_usersex) {
                    this.emp_usersex = emp_usersex;
                }

                @Nullable
                @Override
                public List<BaseNode> getChildNode() {
                    return null;
                }
            }
        }
    }
}
