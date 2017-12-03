package com.aib.base;

import android.content.Context;

import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/6/9.
 */

public class MyApplication extends BaseApplication {

    private static MyApplication mApp;
    private static Context context;
    //    private IWXAPI mWxApi;//微信
    public static String WEIXIN_APP_ID = "wx116d86add1ec2cac";
    public static MyApplication getApp() {
        return mApp;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
        context = getApplicationContext();

        //获取设备尺寸
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);

        //网络请求的基本配置
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
    }
}
