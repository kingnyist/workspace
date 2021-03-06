package com.bee.base.model;

import java.util.Date;

import com.bee.base.bean.PageBean;

public class MapRoleMenu extends PageBean {
    private String id;

    private String menuNo;

    private String status;

    private String statusRem;

    private Date createTime;

    private Date updateTime;

    private String roleNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo == null ? null : menuNo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStatusRem() {
        return statusRem;
    }

    public void setStatusRem(String statusRem) {
        this.statusRem = statusRem == null ? null : statusRem.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }
}