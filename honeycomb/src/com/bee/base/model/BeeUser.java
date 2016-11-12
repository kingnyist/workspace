package com.bee.base.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.bee.base.bean.PageBean;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BeeUser extends PageBean{
	
    private String userNo;

    private String userName;

    private String password;

    private String phoneNo;

    private String email;

    private String idCard;

    private String departmentNo;

    private String departmentName;

    private String roleNo;

    private String roleName;

    private String branchNo;

    private String branchName;

    private String status;

    private String statusRem;

    private String loginIp;

    private Date createTime;

    private Date updateTime;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getDepartmentNo() {
        return departmentNo;
    }

    public void setDepartmentNo(String departmentNo) {
        this.departmentNo = departmentNo == null ? null : departmentNo.trim();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo == null ? null : roleNo.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo == null ? null : branchNo.trim();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
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

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")  
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")  
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BeeUser [userNo=");
		builder.append(userNo);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append(", email=");
		builder.append(email);
		builder.append(", idCard=");
		builder.append(idCard);
		builder.append(", departmentNo=");
		builder.append(departmentNo);
		builder.append(", departmentName=");
		builder.append(departmentName);
		builder.append(", roleNo=");
		builder.append(roleNo);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", branchNo=");
		builder.append(branchNo);
		builder.append(", branchName=");
		builder.append(branchName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", statusRem=");
		builder.append(statusRem);
		builder.append(", loginIp=");
		builder.append(loginIp);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}
    
}