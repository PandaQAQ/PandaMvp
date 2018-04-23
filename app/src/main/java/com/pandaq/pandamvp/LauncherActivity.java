package com.pandaq.pandamvp;

import android.os.Bundle;

import com.pandaq.pandamvp.modules.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 启动页面 Activity
 */
public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected int bindContentRes() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadData() {

    }
}
