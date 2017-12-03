package com.aib.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import com.aib.fragment.ClassFragment;
import com.aib.fragment.StudentQuizFragment;
import com.aib.fragment.StudentSubjectFragment;
import com.aib.fragment.TeacherQuizFragment;
import com.aib.fragment.TeacherSubjectFragment;
import com.xxx.design.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private long lastTime;
    private BottomNavigationView bnv;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int type = 0;   //0表示老师 1表示学生

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        bnv = findViewById(R.id.bnv);
    }

    @Override
    protected void initData() {
        if (type == 0) {
            //老师
            bnv.inflateMenu(R.menu.menu_teacher_bnv);
            fragmentList.add(new TeacherQuizFragment());
            fragmentList.add(new ClassFragment());
            fragmentList.add(new TeacherSubjectFragment());
        } else {
            //学生
            bnv.inflateMenu(R.menu.menu_student_bnv);
            fragmentList.add(new StudentQuizFragment());
            fragmentList.add(new ClassFragment());
            fragmentList.add(new StudentSubjectFragment());
        }


        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn_quiz:
                        switchFragment(0);
                        return true;
                    case R.id.btn_class:
                        switchFragment(1);
                        return true;
                    case R.id.btn_subject:
                        switchFragment(2);
                        return true;
                }
                return false;
            }
        });

        switchFragment(0);
    }

    private void switchFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment fragment = fragmentList.get(i);
            if (i == position) {
                if (fragment.isAdded()) {
                    ft.show(fragment);
                } else {
                    ft.add(R.id.fl, fragment);
                }
            } else {
                if (fragment.isAdded()) {
                    ft.hide(fragment);
                }
            }
        }
        ft.commit();
    }

    /**
     * 按2次退出APP
     */
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
            lastTime = currentTime;
        }
    }
}
