package com.pandaq.appcore.framework.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by huxinyu on 2018/5/19.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 给出的模板基类,可选择继承此类实现 bindButterKnife（）方法使用 ButterKnife 绑定 UI
 * 也可完全自己写基类绑定 UI
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IContract.IMvpView {
    protected P mPresenter;

    protected abstract P injectPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = injectPresenter();
        getLifecycle().addObserver(mPresenter);
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
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
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
    protected abstract void initView(View contentView);

    /**
     * 加载数据
     */
    protected abstract void loadData();

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
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            getLifecycle().removeObserver(mPresenter);
        }
    }
}
