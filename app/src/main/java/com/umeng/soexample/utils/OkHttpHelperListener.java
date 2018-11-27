package com.umeng.soexample.utils;

/**
 * author：张腾
 * date：2018/11/16
 */
public interface OkHttpHelperListener {

    void succeed(String data);

    void failure(String error);
}
