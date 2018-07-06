package com.pandaq.appcore.utils.system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


/**
 * Created by huxinyu on 2018/5/30.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :联系人公共方法，如打电话，发短信
 */
public class ContactUtil {

    /**
     * 拨打电话
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void makeCall(Context context, String phoneNumber) {
        if (phoneNumber == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
