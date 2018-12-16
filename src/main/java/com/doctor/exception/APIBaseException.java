package com.doctor.exception;

public class APIBaseException extends Exception {

    private int code;

    public static final APIBaseException SERVER_ERROR = new APIBaseException(500, "服务器错误");
    public static final APIBaseException MOBILE_INVALID = new APIBaseException(601, "请填写正确的手机号");
    public static final APIBaseException USER_NOT_EXIST = new APIBaseException(602, "用户不存在");
    public static final APIBaseException DOCTOR_NOT_EXIST = new APIBaseException(603, "医生不存在");

    public APIBaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public APIBaseException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
