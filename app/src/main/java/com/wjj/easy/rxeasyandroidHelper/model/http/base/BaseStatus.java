package com.wjj.easy.easyandroidHelper.model.http.base;

/**
 * Created by zhaotun
 */

public class BaseStatus{

    public static final int RESPONSE_CODE_SUCCESS = 10000;// 0成功
    public static final int RESPONSE_CODE_ERROR_INVALID_TOKEN = 10003;//非法的用户会话信息

    private int code;
    private String message;

    @Override
    public String toString() {
        return "BaseStatus{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
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

    public boolean isSuccess() {
        return code == RESPONSE_CODE_SUCCESS;
    }
}
