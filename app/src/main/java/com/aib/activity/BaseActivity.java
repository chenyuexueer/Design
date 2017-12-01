package com.aib.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity基类
 * 新创建的Activity继承本类，实现一些抽象方法，免去自己创建的时间
 * 本类封装了2个Activity的跳转，包括是否带数据跳转
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 当子类调用完setContentView会调用此方法
     *
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     * 可以是进行网络请求
     */
    protected abstract void initData();

    /**
     * 跳转Activity
     *
     * @param cls 新的Activity
     */
    protected void openActivity(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
    }

    /**
     * 跳转Activity，是否需要关闭当前的Activity
     *
     * @param cls     新的Activity
     * @param isClose 是否关闭旧Acticity
     */
    protected void openActivity(Class<?> cls, boolean isClose) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
        if (isClose) {
            finish();
        }
    }
}
