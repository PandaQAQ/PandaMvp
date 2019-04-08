
package com.pandaq.appcore.permission.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :phone state permission tester
 */
public class PhoneStateReadTest extends PermissionTest {

    private Context mContext;

    public PhoneStateReadTest(Context context) {
        this.mContext = context;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    @Override
    public boolean test() throws Throwable {
        TelephonyManager service = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return !TextUtils.isEmpty(service.getDeviceId()) || !TextUtils.isEmpty(service.getSubscriberId());
    }
}