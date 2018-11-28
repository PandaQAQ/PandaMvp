package com.pandaq.pandamvp.framework.base;

import android.view.View;

import com.pandaq.appcore.framework.base.TemplateBaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description :Fragment 基类
 */

public abstract class BaseFragment extends TemplateBaseFragment {

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
