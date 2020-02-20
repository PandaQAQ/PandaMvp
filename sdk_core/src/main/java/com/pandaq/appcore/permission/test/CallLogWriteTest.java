
package com.pandaq.appcore.permission.test;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :write call log permission tester
 */

public class CallLogWriteTest extends PermissionTest {

    private ContentResolver mResolver;

    public CallLogWriteTest(Context context) {
        this.mResolver = context.getContentResolver();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @RequiresPermission(Manifest.permission.WRITE_CALL_LOG)
    @Override
    public boolean test() throws Throwable {
        try {
            ContentValues content = new ContentValues();
            content.put(CallLog.Calls.TYPE, CallLog.Calls.INCOMING_TYPE);
            content.put(CallLog.Calls.NUMBER, "1");
            content.put(CallLog.Calls.DATE, 20080808);
            content.put(CallLog.Calls.NEW, "0");
            Uri resourceUri = mResolver.insert(CallLog.Calls.CONTENT_URI, content);
            return ContentUris.parseId(resourceUri) > 0;
        } finally {
            mResolver.delete(CallLog.Calls.CONTENT_URI, CallLog.Calls.NUMBER + "=?", new String[]{"1"});
        }
    }
}