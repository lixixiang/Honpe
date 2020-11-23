package com.honpe.lxx.app.ui.main.more.multipleItem.fragment.child.allDepart.DepartOfGroupBean;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * FileName: TreeBean
 * Author: asus
 * Date: 2020/8/22 8:55
 * Description: 制作多级列表
 */
public class TreeBean extends BaseExpandNode {
    private List<BaseNode> childNode;
    private String ParentID;
    private String DeptLevel;
    private String DeptCode;
    private String DeptGm;
    private String DeptName;
    private String DeptID;
    private List<TreeBean2> treeBean2;

    public TreeBean() {
    }

    public TreeBean(List<BaseNode> childNode, String parentID, String deptLevel, String deptCode, String deptGm, String deptName, String deptID, List<TreeBean2> treeBean2) {
        this.childNode = childNode;
        ParentID = parentID;
        DeptLevel = deptLevel;
        DeptCode = deptCode;
        DeptGm = deptGm;
        DeptName = deptName;
        DeptID = deptID;
        this.treeBean2 = treeBean2;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getDeptLevel() {
        return DeptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        DeptLevel = deptLevel;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String deptCode) {
        DeptCode = deptCode;
    }

    public String getDeptGm() {
        return DeptGm;
    }

    public void setDeptGm(String deptGm) {
        DeptGm = deptGm;
    }

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public List<TreeBean2> getTreeBean2() {
        return treeBean2;
    }

    public void setTreeBean2(List<TreeBean2> treeBean2) {
        this.treeBean2 = treeBean2;
    }


    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    public static class TreeBean2 extends BaseExpandNode {
        private List<BaseNode> childNode;
        private String ParentID;
        private String DeptLevel;
        private String DeptCode;
        private String DeptGm;
        private String DeptName;
        private String DeptID;
        private List<TreeBean3> treeBean3;

        public TreeBean2() {
        }

        public TreeBean2(List<BaseNode> childNode, String parentID, String deptLevel, String deptCode, String deptGm, String deptName, String deptID, List<TreeBean3> treeBean3) {
            this.childNode = childNode;
            ParentID = parentID;
            DeptLevel = deptLevel;
            DeptCode = deptCode;
            DeptGm = deptGm;
            DeptName = deptName;
            DeptID = deptID;
            this.treeBean3 = treeBean3;
        }


        public void setChildNode(List<BaseNode> childNode) {
            this.childNode = childNode;
        }

        public String getParentID() {
            return ParentID;
        }

        public void setParentID(String parentID) {
            ParentID = parentID;
        }

        public String getDeptLevel() {
            return DeptLevel;
        }

        public void setDeptLevel(String deptLevel) {
            DeptLevel = deptLevel;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String deptCode) {
            DeptCode = deptCode;
        }

        public String getDeptGm() {
            return DeptGm;
        }

        public void setDeptGm(String deptGm) {
            DeptGm = deptGm;
        }

        public String getDeptID() {
            return DeptID;
        }

        public void setDeptID(String deptID) {
            DeptID = deptID;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String deptName) {
            DeptName = deptName;
        }

        public List<TreeBean3> getTreeBean3() {
            return treeBean3;
        }

        public void setTreeBean3(List<TreeBean3> treeBean3) {
            this.treeBean3 = treeBean3;
        }


        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return childNode;
        }

        public static class TreeBean3 extends BaseNode {
            private String ParentID;
            private String DeptLevel;
            private String DeptCode;
            private String DeptGm;
            private String DeptName;
            private String DeptID;

            public String getParentID() {
                return ParentID;
            }

            public void setParentID(String parentID) {
                ParentID = parentID;
            }

            public String getDeptLevel() {
                return DeptLevel;
            }

            public void setDeptLevel(String deptLevel) {
                DeptLevel = deptLevel;
            }

            public String getDeptCode() {
                return DeptCode;
            }

            public void setDeptCode(String deptCode) {
                DeptCode = deptCode;
            }

            public String getDeptGm() {
                return DeptGm;
            }

            public void setDeptGm(String deptGm) {
                DeptGm = deptGm;
            }

            public String getDeptName() {
                return DeptName;
            }

            public void setDeptName(String deptName) {
                DeptName = deptName;
            }

            public String getDeptID() {
                return DeptID;
            }

            public void setDeptID(String deptID) {
                DeptID = deptID;
            }


            @Nullable
            @Override
            public List<BaseNode> getChildNode() {
                return null;
            }
        }
    }


}
