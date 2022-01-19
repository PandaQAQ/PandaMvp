package com.pandaq.appcore.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.pandaq.appcore.utils.system.AppUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by huxinyu on 2019/9/29.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class StorageUtils {

    private StorageUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void closeIO(Closeable... closeables) {
        if (closeables != null) {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 删除之前的apk
     *
     * @param apkName apk名字
     * @return
     */
    public static File clearApk(Context context, String apkName) {
        File apkFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), apkName);
        if (apkFile.exists()) {
            apkFile.delete();
        }
        return apkFile;
    }

    /**
     * 查找apk
     *
     * @param context context
     * @param apkName apkName
     * @return apkPath
     */
    public static String getApk(Context context, String apkName) {
        File apkFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), apkName);
        if (apkFile.exists()) {
            return apkFile.getAbsolutePath();
        }
        return null;
    }

    /**
     * List 深拷贝
     *
     * @return new list
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopyList(List<T> src) {
        List<T> dest = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            dest = (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dest;
    }


    /**
     * 获取剩余空间
     *
     * @return 总存储空间
     */
    public static long getTotalSdcardSpace() {
        StatFs statFs = new StatFs(AppUtils.getContext().getExternalFilesDir(null).getAbsolutePath());
        //sd卡分区数
        long avCounts = statFs.getBlockCountLong();
        //一个分区数的大小
        long blockSize = statFs.getBlockSizeLong();
        //sd卡可用空间
        return avCounts * blockSize;
    }

    /**
     * 获取剩余空间
     *
     * @return 剩余可用空间
     */
    public static long getFreeSdcardSpace() {
        StatFs statFs = new StatFs(AppUtils.getContext().getExternalFilesDir(null).getAbsolutePath());
        //sd卡可用分区数
        long avCounts = statFs.getAvailableBlocksLong();
        //一个分区数的大小
        long blockSize = statFs.getBlockSizeLong();
        //sd卡可用空间
        return avCounts * blockSize;
    }

    public static long getLogSpace() {
        String logPath = AppUtils.getContext().getFilesDir() + File.separator + "logs";
        return getFolderSize(new File(logPath));
    }

    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            if (fileList != null) {
                for (File value : fileList) {
                    if (value.isDirectory()) {
                        size = size + getFolderSize(value);
                    } else {
                        size = size + value.length();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static void deleteFolder(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File value : files) {
                    if (value.isDirectory()){
                        deleteFolder(value.getPath());
                    }else {
                        value.delete();
                    }
                }
            }

        }
    }

}
