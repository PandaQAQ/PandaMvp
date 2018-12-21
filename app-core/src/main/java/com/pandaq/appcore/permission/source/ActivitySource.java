
package com.pandaq.appcore.permission.source;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :source from activity
 */
public class ActivitySource extends Source {

    private Activity mActivity;

    public ActivitySource(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    @Override
    public void startActivity(Intent intent) {
        mActivity.startActivity(intent);
    }
}
