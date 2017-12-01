package com.aib.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.aib.adapter.RegisterAdapter;
import com.aib.fragment.StudentRegisterFragment;
import com.aib.fragment.TeacherRegisterFragment;
import com.xxx.design.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册页
 */
public class RegisterActivity extends BaseActivity {

    private TabLayout tl;
    private ViewPager vp;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] tabTitles = {"教师", "学生"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initView() {
        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);    //让本Activity支持Toolbar显示
        tb.setNavigationOnClickListener(new View.OnClickListener() {    //toolbar点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tl = findViewById(R.id.tl);
        vp = findViewById(R.id.vp);
    }

    @Override
    protected void initData() {
        //准备滑动Fragment
        fragmentList.add(new TeacherRegisterFragment());
        fragmentList.add(new StudentRegisterFragment());

        vp.setAdapter(new RegisterAdapter(getSupportFragmentManager(), fragmentList, tabTitles));
        tl.setupWithViewPager(vp);  //将ViewPager和Tablayout关联
    }
}
