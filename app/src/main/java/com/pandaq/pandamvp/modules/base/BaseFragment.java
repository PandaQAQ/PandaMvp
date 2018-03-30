package com.pandaq.pandamvp.modules.base;

import android.support.v4.app.Fragment;

import com.pandaq.pandamvp.caches.DiskCache;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onPause() {
        super.onPause();
        // 在页面失去焦点时同步缓存
        DiskCache.getDiskCache().flush();
    }
}
