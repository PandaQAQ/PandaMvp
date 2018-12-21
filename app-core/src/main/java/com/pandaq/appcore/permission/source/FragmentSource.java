
package com.pandaq.appcore.permission.source;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :source from fragment
 */
public class FragmentSource extends Source {

    private Fragment mFragment;

    public FragmentSource(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public Context getContext() {
        return mFragment.getActivity();
    }

    @Override
    public void startActivity(Intent intent) {
        mFragment.startActivity(intent);
    }
}
