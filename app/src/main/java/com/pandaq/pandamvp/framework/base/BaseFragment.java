package com.pandaq.pandamvp.framework.base;

import android.view.View;

import com.pandaq.pandacore.framework.base.TemplateBaseFragment;
import com.pandaq.pandamvp.caches.DiskCache;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :Fragment 基类
 */

public  abstract class BaseFragment extends TemplateBaseFragment {

    private Unbinder mUnbinder;

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

    @Override
    public void bindButterKnife(Object target, View view) {
        mUnbinder = ButterKnife.bind(target,view);
    }
}
