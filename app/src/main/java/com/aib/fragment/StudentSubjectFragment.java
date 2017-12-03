package com.aib.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xxx.design.R;

/**
 * 作业
 */

public class StudentSubjectFragment extends BaseFragment {
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }
}
