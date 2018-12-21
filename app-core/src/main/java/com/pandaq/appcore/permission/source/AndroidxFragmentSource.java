
package com.pandaq.appcore.permission.source;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :source from androidx.fragment
 */
public class AndroidxFragmentSource extends Source {

    private Fragment mFragment;

    public AndroidxFragmentSource(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public Context getContext() {
        return mFragment.getContext();
    }

    @Override
    public void startActivity(Intent intent) {
        mFragment.startActivity(intent);
    }
}
