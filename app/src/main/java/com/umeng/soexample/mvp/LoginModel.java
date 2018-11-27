package com.umeng.soexample.mvp;


import com.google.gson.Gson;
import com.umeng.soexample.LoginCallback;
import com.umeng.soexample.bean.LoginBean;
import com.umeng.soexample.utils.OkHttpHelper;
import com.umeng.soexample.utils.OkHttpHelperListener;

/**
 * author：张腾
 * date：2018/11/16
 */
public class LoginModel {

    public void login(String mobile, String password, final LoginCallback loginCallback){

        final String url = "http://www.zhaoapi.cn/user/login?mobile="+mobile+"&password="+password;
        new OkHttpHelper().get( url ).setListener( new OkHttpHelperListener() {
            @Override
            public void succeed(String data) {
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson( data, LoginBean.class );
                if (loginBean.getCode().equals( "0" )) {
                    loginCallback.onLoginSuccess( loginBean.getMsg() );
                }else {
                    loginCallback.onLoginFailer( loginBean.getMsg() );
                }

            }

            @Override
            public void failure(String error) {

            }
        } );
    }
}
