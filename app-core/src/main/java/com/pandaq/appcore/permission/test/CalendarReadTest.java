
package com.pandaq.appcore.permission.test;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.CalendarContract;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;


/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :read calendar permission tester
 */

public class CalendarReadTest extends PermissionTest {

    private ContentResolver mResolver;

    public CalendarReadTest(Context context) {
        mResolver = context.getContentResolver();
    }

    @RequiresPermission(Manifest.permission.READ_CALENDAR)
    @RequiresApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public boolean test() throws Throwable {
        String[] projection = new String[]{CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME};
        Cursor cursor = mResolver.query(CalendarContract.Calendars.CONTENT_URI, projection, null, null, null);
        if (cursor != null) {
            try {
                read(cursor);
            } finally {
                cursor.close();
            }
            return true;
        } else {
            return false;
        }
    }
}