package com.yxm.po;

import java.io.Serializable;
import java.util.Date;

public class SysUserVo implements Serializable {
//    id  userName  userPassword  userPhone  userIdNumber   state  salt    gender  loginCount  userCreate childPage  nickName
    private Integer id;
    private String userName;
    private String userPassword;
    private String userIdNumber;
    private String userPhone;
    private Byte state;
    private String salt;
    private Byte gender;
    private Integer loginCount;
    private Date userCreate;
    private String childPage;
    private String nickName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUserIdNumber() {
        return userIdNumber;
    }

    public void setUserIdNumber(String userIdNumber) {
        this.userIdNumber = userIdNumber;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Date userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getChildPage() {
        return childPage;
    }

    public void setChildPage(String childPage) {
        this.childPage = childPage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SysUserVo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userIdNumber='" + userIdNumber + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", state=" + state +
                ", salt='" + salt + '\'' +
                ", gender=" + gender +
                ", loginCount=" + loginCount +
                ", userCreate=" + userCreate +
                ", childPage='" + childPage + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
