package com.pandaq.pandamvp.framework.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pandaq.pandamvp.caches.DiskCache;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :Fragment 基类
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = null;
        if (bindContentRes() != 0) {
            view = inflater.inflate(bindContentRes(), container, false);
            mUnbinder = ButterKnife.bind(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // 在页面失去焦点时同步缓存
        DiskCache.getDiskCache().flush();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    /**
     * 初始化参数变量
     */
    public abstract void initVariable();

    /**
     * 绑定布局资源文件
     *
     * @return 布局资源文件 id
     */
    public abstract int bindContentRes();

    /**
     * 初始化 View
     */
    public abstract void initView();

    /**
     * 加载数据
     */
    public abstract void loadData();

}
