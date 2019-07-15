package com.pandaq.appcore.utils.system;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;


/**
 * Created by huxinyu on 2019/4/4.
 * Email : panda.h@foxmail.com
 * Description :7.0 文件访问权限工具类
 */
public class FileUtils {

    /**
     * 获取 uri
     *
     * @param context 上下文
     * @param file    文件
     * @return 对应的 uri
     */
    public static Uri getUri(Context context, File file) {
        String authority = context.getPackageName() + ".file.path.share";
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = FileProvider.getUriForFile(context, authority, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

    /**
     * 获取 uri
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @return 对应的 uri
     */
    public static Uri getUri(Context context, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                throw new RuntimeException("file not found !!");
            } catch (Exception throwable) {
                throwable.printStackTrace();
            }
        }
        String authority = context.getPackageName() + ".file.path.share";
        Uri fileUri;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = FileProvider.getUriForFile(context, authority, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }

}
