package com.doctor.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PhysicalOrder {
    private Integer id;

    private String userId;

    private String userName;

    private String userMobile;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderTime;

    private String orgId;

    private String doctorId;

    private Integer physicalId;

    private Integer status;

    private Integer over;

    private Date createTime;

    private Date updateTime;

    public Integer getOver() {
        return over;
    }

    public void setOver(Integer over) {
        this.over = over;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPhysicalId() {
        return physicalId;
    }

    public void setPhysicalId(Integer physicalId) {
        this.physicalId = physicalId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}