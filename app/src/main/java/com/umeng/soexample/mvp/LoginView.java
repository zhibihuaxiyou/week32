package com.umeng.soexample.mvp;

/**
 * author：张腾
 * date：2018/11/16
 */
public interface LoginView {
    //成功
    void onSuccess(String result);
    //失败
    void onFailer(String msg);
}
