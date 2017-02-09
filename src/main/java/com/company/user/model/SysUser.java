package com.company.user.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * sys_user 用户信息表 实体
 */
public class SysUser implements java.io.Serializable {

    private static final long serialVersionUID = -1411234234234L;

    private String userId;
    private String userName;
    private String userPassword;
    private String confirmPassword;
    private String phoneNum;
    private String picture;
    private int picWidth;
    private int picHeight;
    private int userSex;
    private Date birthDate;
    private int tpType;
    private String tpUserId;
    private String userAddress;
    private String createTime;
    private String updateTime;
    private String rememberPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    public int getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getTpType() {
        return tpType;
    }

    public void setTpType(int tpType) {
        this.tpType = tpType;
    }

    public String getTpUserId() {
        return tpUserId;
    }

    public void setTpUserId(String tpUserId) {
        this.tpUserId = tpUserId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRememberPwd() {
        return rememberPwd;
    }

    public void setRememberPwd(String rememberPwd) {
        this.rememberPwd = rememberPwd;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

}
