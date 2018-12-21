
package com.pandaq.appcore.permission.test;

import android.database.Cursor;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :permission test class
 */
public abstract class PermissionTest {

    public abstract boolean test() throws Throwable;

    public void read(Cursor cursor) {
        int count = cursor.getCount();
        if (count > 0) {
            cursor.moveToFirst();
            int type = cursor.getType(0);
            switch (type) {
                case Cursor.FIELD_TYPE_BLOB:
                case Cursor.FIELD_TYPE_NULL: {
                    break;
                }
                case Cursor.FIELD_TYPE_INTEGER:
                case Cursor.FIELD_TYPE_FLOAT:
                case Cursor.FIELD_TYPE_STRING:
                default: {
                    cursor.getString(0);
                    break;
                }
            }
        }
    }
}