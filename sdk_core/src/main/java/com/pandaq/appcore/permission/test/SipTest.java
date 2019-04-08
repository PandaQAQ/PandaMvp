
package com.pandaq.appcore.permission.test;

import android.content.Context;
import android.net.sip.SipManager;
import android.net.sip.SipProfile;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :sip permission tester
 */

public class SipTest extends PermissionTest {

    private Context mContext;

    public SipTest(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean test() throws Throwable {
        if (!SipManager.isApiSupported(mContext)) {
            return true;
        }
        SipManager manager = SipManager.newInstance(mContext);
        if (manager == null) {
            return true;
        }
        SipProfile.Builder builder = new SipProfile.Builder("Permission", "127.0.0.1");
        builder.setPassword("password");
        SipProfile profile = builder.build();
        manager.open(profile);
        manager.close(profile.getUriString());
        return true;
    }
}