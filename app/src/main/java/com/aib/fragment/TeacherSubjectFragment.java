package com.aib.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.aib.adapter.TeacherSubjectAdapter;
import com.xxx.design.R;

/**
 * 题库页
 */

public class TeacherSubjectFragment extends BaseFragment {

    private ListView lv;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        lv = view.findViewById(R.id.lv);
    }

    @Override
    protected void initData() {
        lv.setAdapter(new TeacherSubjectAdapter(getContext()));
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(R.layout.dialog_subject);
                dialog.show();
                return false;
            }
        });
    }
}
