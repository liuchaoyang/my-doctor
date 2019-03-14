package com.doctor.model;

import java.util.Date;

public class UserAddress {
    private Integer id;

    private String userId;

    private String mobile;

    private String userName;

    private String areaId;
    private String areaName;

    private String address;

    private Date createTime;

    private Date updateTime;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", areaId='" + areaId + '\'' +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}