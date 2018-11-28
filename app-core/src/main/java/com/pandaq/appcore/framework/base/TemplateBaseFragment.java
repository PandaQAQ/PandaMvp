package com.pandaq.appcore.framework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 给出的模板基类,可选择继承此类实现 bindButterKnife（）方法使用 ButterKnife 绑定 UI
 * 也可完全自己写基类绑定 UI
 */
public abstract class TemplateBaseFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;
        if (bindContentRes() == 0) {
            throw new RuntimeException("must binContentRes first !!!");
        }
        view = inflater.inflate(bindContentRes(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    /**
     * 封装的 fragment 切换工具方法 fragment 中嵌套子 fragment
     *
     * @param containerId 显示 fragment 的 layout 资源 ID
     * @param from        当前显示的 fragment
     * @param to          即将显示的 fragment
     * @return 切换后的 currentFragment
     */
    protected Fragment switchFragment(int containerId, @Nullable Fragment from, @NonNull Fragment to) {
        FragmentManager manager = getChildFragmentManager();
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
