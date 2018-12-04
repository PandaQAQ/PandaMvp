package com.pandaq.appcore.framework.base;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :给出的模板基类,可选择继承此类实现 bindButterKnife（）方法使用 ButterKnife 绑定 UI
 * 也可完全自己写基类绑定 UI
 */
public abstract class TemplateBaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        if (bindContentRes() != 0) {
            setContentView(bindContentRes());
        } else {
            throw new RuntimeException("must bindContentRes first!!!");
        }
        mUnbinder = ButterKnife.bind(this);
        initView();
        loadData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    /**
     * 绑定当前 Fragment 的 layout 资源 id
     *
     * @return layout 资源 id
     */
    protected abstract int bindContentRes();

    /**
     * 初始化属性参数值
     */
    protected abstract void initVariable();

    /**
     * 初始化 View 视图
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void loadData();

    /**
     * 封装的 fragment 切换工具方法（如 Home Activity 导航栏切换不同功能模块 fragment）
     *
     * @param containerId 显示 fragment 的 layout 资源 ID
     * @param from        当前显示的 fragment
     * @param to          即将显示的 fragment
     * @return 切换后的 currentFragment
     */
    protected Fragment switchFragment(int containerId, @Nullable Fragment from, @NonNull Fragment to) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (to.isAdded()) {
            transaction.hide(from).show(to).commit();
        } else {
            if (from == null) {
                transaction.add(containerId, to).commit();
            } else {
                transaction.hide(from).add(containerId, to).commit();
            }
        }
        return to;
    }
}
