package com.rex.myapplication.app;

import android.app.Application;
import android.os.Looper;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.OkHttpClient;

/**
 * @Description: TODO
 * @Author: 李宇峰 on 2019-02-19 11:01 AM.
 * @NO.: 146714
 * @Phone: 13756017116
 * @Email: liyufeng@syswin.com
 * @Leader：yupengfei <yupengfei@syswin.com>
 * @Charge: 李宇峰
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

}
