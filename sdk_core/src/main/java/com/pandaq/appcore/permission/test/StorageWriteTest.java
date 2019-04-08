
package com.pandaq.appcore.permission.test;

import android.os.Environment;

import java.io.File;

/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :write storage permission tester
 */
public class StorageWriteTest extends PermissionTest {

    public StorageWriteTest() {
    }

    @Override
    public boolean test() throws Throwable {
        File directory = Environment.getExternalStorageDirectory();
        if (!directory.exists() || !directory.canWrite()) return false;
        File file = new File(directory, "ANDROID.PERMISSION.TEST");
        if (file.exists()) {
            return file.delete();
        } else {
            // try create file,if success delete it
            boolean result = file.createNewFile();
            return file.delete();
        }
    }
}
