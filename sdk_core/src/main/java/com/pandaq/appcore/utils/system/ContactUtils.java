package com.pandaq.appcore.utils.system;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.support.annotation.NonNull;


/**
 * Created by huxinyu on 2018/5/30.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :联系人公共方法，如打电话，发短信
 */
public class ContactUtils {

    private ContactUtils() {
        // private constructor
    }

    /**
     * 拨打电话
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void makeCall(Context context, @NonNull String phoneNumber) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 发送短消息
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     * @param message     短信内容
     */
    public static void sendMessage(Context context, @NonNull String phoneNumber, String message) {

    }
}
