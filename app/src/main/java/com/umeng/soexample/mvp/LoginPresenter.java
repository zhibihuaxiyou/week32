package com.umeng.soexample.mvp;

import com.umeng.soexample.LoginCallback;

/**
 * author：张腾
 * date：2018/11/16
 */
public class LoginPresenter {

    public LoginModel loginModel;
    public LoginView loginView;

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    public void login(String mobile,String password){

        loginModel.login( mobile, password, new LoginCallback() {
            @Override
            public void onLoginSuccess(String result) {
                loginView.onSuccess( result );
            }

            @Override
            public void onLoginFailer(String msg) {
                loginView.onFailer( msg );
            }
        } );
    }
}
