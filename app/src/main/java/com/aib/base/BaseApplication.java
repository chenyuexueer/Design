package com.aib.base;

import android.app.Application;
import android.content.Context;

import com.aib.net.DataKeeper;
import com.aib.net.SettingUtil;

/**
 * 基础Application
 *
 * @author Lemon
 * @use extends BaseApplication 或 在你的Application的onCreate方法中BaseApplication.init(this);
 * @see #init
 */
public class BaseApplication extends Application {
    public static Context mContext;

    public BaseApplication() {
    }

    private static Application instance;

    public static Application getInstance() {
        return instance;
    }


    /**
     * 初始化方法
     *
     * @param application
     * @must 调用init方法且只能调用一次，如果extends BaseApplication会自动调用
     */
    public static void init(Application application) {
        instance = application;
        if (instance == null) {
        }
        DataKeeper.init(instance);
        SettingUtil.init(instance);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
        mContext = getApplicationContext();
    }
}
