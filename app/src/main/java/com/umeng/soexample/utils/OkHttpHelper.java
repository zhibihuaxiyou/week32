package com.umeng.soexample.utils;

/**
 * author：张腾
 * date：2018/11/16
 */
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {
    OkHttpHelperListener listener;

    public void setListener(OkHttpHelperListener listener) {
        this.listener = listener;
    }


    public OkHttpHelper get(String url) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url1 = request.url();
                String method = request.method();
                Log.i("aaaa", "OkHttpHelper  intercept: " + "url:" + url1 + "    method:" + method);
                return chain.proceed(request);
            }
        };
        OkHttpClient build = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        build.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Message message = new Message();
                message.obj=e.getMessage();
                message.what=1;
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.obj=response.body().string();
                message.what=0;
                handler.sendMessage(message);
            }
        });
        return this;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String data = (String) msg.obj;
                    listener.succeed(data);
                    break;
                case 1:
                    String error= (String) msg.obj;
                    listener.failure(error);
                    break;
            }
        }
    };

}

