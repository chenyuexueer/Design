package com.aib.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 * 新创建的Fragment都继承本类
 * 功能同BaseActivity，封装一些Activity的跳转
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = createView(inflater, container, savedInstanceState);
        initView(view);
        initData();
        return view;
    }

    /**
     * 创建Fragment的布局
     *
     * @param inflater           布局填充器
     * @param container          父控件
     * @param savedInstanceState
     * @return
     */
    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化控件
     *
     * @param view 把布局的View传到子类中，直接使用view.findViewById
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
