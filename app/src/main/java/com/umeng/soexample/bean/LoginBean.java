package com.umeng.soexample.bean;

import java.io.Serializable;

/**
 * author：张腾
 * date：2018/11/17
 */
public class LoginBean implements Serializable {

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
