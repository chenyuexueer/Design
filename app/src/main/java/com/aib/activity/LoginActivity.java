package com.aib.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xxx.design.R;

/**
 * 登录页
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        TextView tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * 点击事件回调方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                openActivity(RegisterActivity.class);
                break;
            case R.id.btn_login:
                openActivity(MainActivity.class, true);
                break;
        }
    }
}
