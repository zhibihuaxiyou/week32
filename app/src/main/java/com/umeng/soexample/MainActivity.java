package com.umeng.soexample;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.mvp.LoginPresenter;
import com.umeng.soexample.mvp.LoginView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText mEditMobile;
    private EditText mEditPassword;
    private Button mBtnLogin;
    private ImageView mBtnQq;
    private ImageView mLoginWx;
    private LoginPresenter loginPresenter;
    private UMAuthListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();
        initViewListener();
        initAuthority();
        loginPresenter = new LoginPresenter( this );
        initListener();
    }

    private void initListener() {
        authListener = new UMAuthListener() {
            /**
             * @desc 授权开始的回调
             * @param platform 平台名称
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            /**
             * @desc 授权成功的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param data 用户资料返回
             */
            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Intent intent = new Intent( MainActivity.this, WaterfallActivity.class );
                intent.putExtra( "name",data.get( "name" ) );
                intent.putExtra( "pic",data.get( "iconurl" ) );
                startActivity( intent );
                finish();
            }

            /**
             * @desc 授权失败的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                Toast.makeText( MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG ).show();
            }

            /**
             * @desc 授权取消的回调
             * @param platform 平台名称
             * @param action 行为序号，开发者用不上
             */
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText( MainActivity.this, "取消了", Toast.LENGTH_LONG ).show();
            }
        };
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        UMShareAPI.get( this ).onActivityResult( requestCode, resultCode, data );
    }

    //动态权限
    private void initAuthority() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions( this, mPermissionList, 123 );
        }
    }

    private void initViewListener() {
        mBtnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mEditMobile.getText().toString().trim();
                String password = mEditPassword.getText().toString().trim();
                loginPresenter.login( mobile, password );
            }
        } );
        mBtnQq.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get( MainActivity.this ).getPlatformInfo( MainActivity.this, SHARE_MEDIA.QQ, authListener );
            }
        } );
        mLoginWx.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get( MainActivity.this ).getPlatformInfo( MainActivity.this, SHARE_MEDIA.WEIXIN, authListener );
            }
        } );
    }

    private void initView() {
        mEditMobile = (EditText) findViewById( R.id.edit_mobile );
        mEditPassword = (EditText) findViewById( R.id.edit_password );
        mBtnLogin = (Button) findViewById( R.id.btn_login );
        mBtnQq = (ImageView) findViewById( R.id.btn_qq );
        mLoginWx = (ImageView) findViewById( R.id.login_wx );
    }

    @Override
    public void onSuccess(String result) {
        Intent intent = new Intent( this, WaterfallActivity.class );
        startActivity( intent );
        finish();
    }

    @Override
    public void onFailer(String msg) {
        Toast.makeText( MainActivity.this, msg, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }
}
