package com.doctor.model;

public class UserDoctorRelation {
    private Integer id;

    private String userId;

    private byte[] doctorId;

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

    public byte[] getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(byte[] doctorId) {
        this.doctorId = doctorId;
    }
}