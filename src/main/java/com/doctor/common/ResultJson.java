package com.doctor.common;

public class ResultJson<T> {

    private int code;
    private String message;
    private T data;

    public ResultJson() {}
    public ResultJson(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultJson success() {
        ResultJson result = new ResultJson(200, "ok");
        return result;
    }

    public static ResultJson success(Object data) {
        ResultJson result = new ResultJson(200, "ok");
        result.setData(data);
        return result;
    }

    public static ResultJson failed(int code, String message) {
        ResultJson result = new ResultJson(code, message);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
