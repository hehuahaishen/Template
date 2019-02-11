package com.example.shen.template.bean;

/**
 * author:  shen
 * date:    2018/7/17
 */
public class BaseWebBean<T> {

    /**
     * code : 200
     * message : 扫描成功!
     * data : null
     */
    private int code;
    private String message;
    private T data;

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

    public void T(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseWebBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
