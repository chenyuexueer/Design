package com.aib.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aib.adapter.TeacherQuizAdapter;
import com.xxx.design.R;

/**
 * 提问页
 */
public class TeacherQuizFragment extends BaseFragment {

    private RecyclerView rv;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        rv = view.findViewById(R.id.rv);
    }

    @Override
    protected void initData() {
        rv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new TeacherQuizAdapter());
    }
}
