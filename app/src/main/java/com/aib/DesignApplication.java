package com.aib;

import android.app.Application;
import android.content.Context;

/**
 * Created by Android on 2017/12/1.
 */

public class DesignApplication extends Application {

    private static Context ctx;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();
    }

    /**
     * 项目中需要使用Context，尽量使用Application的Context，这样不会造成内存泄漏
     * 一些Dialog就需要传Activity的Context
     *
     * @return
     */
    public static Context getContext() {
        return ctx;
    }
}
