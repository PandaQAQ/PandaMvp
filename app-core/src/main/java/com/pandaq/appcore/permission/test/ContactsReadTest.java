
package com.pandaq.appcore.permission.test;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :read contact permission tester
 */
public class ContactsReadTest extends PermissionTest {

    private ContentResolver mResolver;

    public ContactsReadTest(Context context) {
        mResolver = context.getContentResolver();
    }

    @Override
    public boolean test() throws Throwable {
        String[] projection = new String[]{ContactsContract.Data._ID, ContactsContract.Data.DATA1};
        Cursor cursor = mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null);
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