package com.umeng.soexample;

/**
 * author：张腾
 * date：2018/11/16
 */
public interface LoginCallback {
    //登录成功
    void onLoginSuccess(String result);
    //登录失败
    void onLoginFailer(String msg);
}
