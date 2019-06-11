package com.pandaq.appcore.framework.mvp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :给出的模板基类
 * 也可完全自己写基类绑定 UI
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IContract.IMvpView {

    protected P mPresenter;

    protected abstract P injectPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = injectPresenter();
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
        initVariable();
        if (bindContentRes() != 0) {
            setContentView(bindContentRes());
        } else {
            throw new RuntimeException("must bindContentRes first!!!");
        }
        initView();
        loadData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /**
     * 绑定当前 BaseFragment 的 layout 资源 id
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
     * 封装的 fragment 切换工具方法（如 Home BaseActivity 导航栏切换不同功能模块 fragment）
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
            if (from != null) {
                transaction.hide(from).show(to).commit();
            }
        } else {
            if (from == null) {
                transaction.add(containerId, to).commit();
            } else {
                transaction.hide(from).add(containerId, to).commit();
            }
        }
        return to;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle", "removeObserver");
        if (mPresenter != null) {
            getLifecycle().removeObserver(mPresenter);
        }
    }
}
