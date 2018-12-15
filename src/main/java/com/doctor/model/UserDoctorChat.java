package com.doctor.model;

public class UserDoctorChat {
    private Integer id;

    private String userId;

    private String sendId;

    private String message;

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

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(byte[] doctorId) {
        this.doctorId = doctorId;
    }
}