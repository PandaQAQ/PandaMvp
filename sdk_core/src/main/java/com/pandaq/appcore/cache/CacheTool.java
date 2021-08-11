package com.pandaq.appcore.cache;

import android.content.Context;
import android.os.Environment;
import android.os.Parcelable;
import android.widget.Toast;

import com.pandaq.appcore.utils.crypto.CodeFactory;
import com.pandaq.appcore.utils.format.FormatFactory;
import com.pandaq.appcore.utils.system.AppUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :缓存工具类
 */
public class CacheTool {

    private static DiskLruCache mDiskLruCache = null;
    // 默认缓存空间为 20M
    private static long maxCacheSize = 20 * 1024 * 1024;
    private static String uniqueName = "catcher";
    private DiskLruCache.Editor mEditor = null;
    private DiskLruCache.Snapshot mSnapshot = null;

    public static synchronized Builder with(Context context) {
        if (context == null) throw new RuntimeException("context must not be null");
        return new Builder()
                .maxCacheSize(maxCacheSize)
                .uniqueName(uniqueName)
                .context(context);
    }

    public static long getMaxCacheSize() {
        return maxCacheSize;
    }

    public static void setMaxCacheSize(long maxCacheSize) {
        CacheTool.maxCacheSize = maxCacheSize;
    }

    public static String getUniqueName() {
        return uniqueName;
    }

    public static void setUniqueName(String uniqueName) {
        CacheTool.uniqueName = uniqueName;
    }

    /**
     * 缓存器构造类
     */
    public static class Builder {
        //存储文件名（只要相对名，目录固定为对应的包下目录）
        private String uniqueName;
        // 缓存最大占用空间
        private long maxSize;
        private Context context;

        private Builder() {

        }

        private Builder uniqueName(String uniqueName) {
            this.uniqueName = uniqueName;
            return this;
        }

        public CacheTool open(String uniqueName) {
            this.uniqueName = uniqueName;
            return new CacheTool(this.context, this.uniqueName, this.maxSize);
        }

        public Builder maxCacheSize(long maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        private Builder context(Context context) {
            this.context = context;
            return this;
        }
    }

    private CacheTool(Context context, String uniqueName, long maxCacheSize) {
        try {
            //先关闭已有的缓存
            if (mDiskLruCache != null) {
                mDiskLruCache.close();
                mDiskLruCache = null;
            }
            File cacheFile = getCacheFile(context, uniqueName);
            mDiskLruCache = DiskLruCache.open(cacheFile, 1,
                    1, maxCacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取缓存的路径 两个路径在卸载程序时都会删除，因此不会在卸载后还保留乱七八糟的缓存
     * 有SD卡时获取  /sdcard/Android/data/<application package>/cache
     * 无SD卡时获取  /data/data/<application package>/cache
     *
     * @param context    上下文
     * @param uniqueName 缓存目录下的细分目录，用于存放不同类型的缓存
     * @return 缓存目录 File
     */
    private File getCacheFile(Context context, String uniqueName) {
        String cachePath = null;
        if ((Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable())
                && context.getExternalCacheDir() != null) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 获取缓存 editor
     *
     * @param key 缓存的key
     * @return editor
     * @throws IOException exception
     */
    private DiskLruCache.Editor edit(String key) throws IOException {
        key = CodeFactory.MD5.getMd5Code(key); //存取的 key
        if (mDiskLruCache != null) {
            mEditor = mDiskLruCache.edit(key);
        }
        return mEditor;
    }

    /**
     * 根据 key 获取缓存缩略
     *
     * @param key 缓存的key
     * @return Snapshot
     */
    private DiskLruCache.Snapshot snapshot(String key) {
        if (mDiskLruCache != null) {
            try {
                mSnapshot = mDiskLruCache.get(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mSnapshot;
    }
    /* ************************
     * 字符串读写
     * ************************/

    /**
     * 缓存 String
     *
     * @param key   缓存文件键值（MD5加密结果作为缓存文件名）
     * @param value 缓存内容
     */
    public CacheTool put(String key, String value) {
        DiskLruCache.Editor editor = null;
        BufferedWriter writer = null;
        try {
            editor = edit(key);
            if (editor == null) {
                return this;
            }
            OutputStream os = editor.newOutputStream(0);
            writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write(value);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (editor != null)
                    editor.abort();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 获取字符串缓存
     *
     * @param key cache'key
     * @return string
     */
    public String getString(String key) {
        InputStream inputStream = getCacheInputStream(key);
        if (inputStream == null) {
            return null;
        }
        try {
            return inputStream2String(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /* ************************
     * Json对象读写
     * ************************/
    //Json 数据转换成 String 存储
    public CacheTool put(String key, JSONObject value) {
        return put(key, value.toString());
    }

    //取得 json 字符串再转为 Json对象
    public JSONObject getJsonObject(String key) {
        String json = getString(key);
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ************************
     * Json数组对象读写
     * ************************/

    public void put(String key, JSONArray array) {
        put(key, array.toString());
    }

    public JSONArray getJsonArray(String key) {
        try {
            return new JSONArray(getString(key));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* ************************
     * byte 数据读写
     * ************************/

    /**
     * 存入byte数组
     *
     * @param key   cache'key
     * @param bytes bytes to save
     */
    public CacheTool put(String key, byte[] bytes) {
        OutputStream out = null;
        DiskLruCache.Editor editor = null;
        try {
            editor = edit(key);
            if (editor == null) {
                return this;
            }
            out = editor.newOutputStream(0);
            out.write(bytes);
            out.flush();
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (editor != null) {
                    editor.abort();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return this;
    }

    /**
     * 获取缓存的 byte 数组
     *
     * @param key cache'key
     * @return bytes
     */
    public byte[] getBytes(String key) {
        byte[] bytes = null;
        InputStream inputStream = getCacheInputStream(key);
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[256];
        int len = 0;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /* ************************
     * 序列化对象数据读写
     * ************************/

    /**
     * 序列化对象写入
     *
     * @param key    cache'key
     * @param object 待缓存的序列化对象
     */
    public CacheTool put(String key, Serializable object) {
        ObjectOutputStream oos = null;
        DiskLruCache.Editor editor = null;
        try {
            editor = edit(key);
            if (editor == null) {
                return this;
            }
            oos = new ObjectOutputStream(editor.newOutputStream(0));
            oos.writeObject(object);
            oos.flush();
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (editor != null)
                    editor.abort();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 序列化对象写入
     *
     * @param key    cache'key
     * @param object 待缓存的序列化对象
     */
    public CacheTool put(String key, Parcelable object) {
        ObjectOutputStream oos = null;
        DiskLruCache.Editor editor = null;
        try {
            editor = edit(key);
            if (editor == null) {
                return this;
            }
            oos = new ObjectOutputStream(editor.newOutputStream(0));
            oos.writeObject(object);
            oos.flush();
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (editor != null)
                    editor.abort();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * 获取 序列化对象
     *
     * @param key cache'key
     * @param <T> 对象类型
     * @return 读取到的序列化对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getSerializable(String key) {
        T object = null;
        ObjectInputStream ois = null;
        InputStream in = getCacheInputStream(key);
        if (in == null) {
            return null;
        }
        try {
            ois = new ObjectInputStream(in);
            object = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 获取 序列化对象
     *
     * @param key cache'key
     * @param <T> 对象类型
     * @return 读取到的序列化对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getParcelable(String key) {
        T object = null;
        ObjectInputStream ois = null;
        InputStream in = getCacheInputStream(key);
        if (in == null) {
            return null;
        }
        try {
            ois = new ObjectInputStream(in);
            object = (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 删除一条缓存
     *
     * @param key 缓存的 key
     * @return 删除结果
     */
    public boolean clear(String key) {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.remove(CodeFactory.MD5.getMd5Code(key));
                flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 删除 DiskLruCache 所有缓存文件
     *
     * @return 删除结果
     */
    public boolean clearDiskLruCache() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.delete();
                flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /* ***********************************************************************
     **********************  辅助工具方法 分割线  ****************************
     * ***********************************************************************/

    /**
     * inputStream 转 String
     *
     * @param is 输入流
     * @return 结果字符串
     */
    private String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * 获取 缓存数据的 InputStream
     *
     * @param key cache'key
     * @return InputStream
     */
    private InputStream getCacheInputStream(String key) {
        key = CodeFactory.MD5.getMd5Code(key);
        InputStream in;
        DiskLruCache.Snapshot snapshot = snapshot(key);
        if (snapshot == null) {
            return null;
        }
        in = snapshot.getInputStream(0);
        return in;
    }

    /**
     * 同步记录文件
     */
    public void flush() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.flush();
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取缓存信息的工具方法

    /**
     * 获取缓存文件总大小
     *
     * @param context 上下文
     * @return 大小格式化后的缓存文件总大小
     */
    public static String getTotalCacheSize(Context context) {
        long cacheSize = 0;
        try {
            cacheSize = getFolderSize(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheSize += getFolderSize(context.getExternalCacheDir());
            }
        } catch (Exception e) {
            Toast.makeText(context, "Get Cache Size Fail", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return FormatFactory.SIZE.formatSize(cacheSize);
    }

    /**
     * 清除应用的所有缓存信息
     * 清除应用的所有缓存信息，从应用缓存目录去清除，也包括非 CacheTool 缓存下来的文件
     *
     * @param context 上下文
     */
    public static void clearWholeAppCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    /**
     * 删除缓存文件
     *
     * @param dir 文件对象
     * @return 是否删除成功
     */
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir != null && dir.delete();
    }

    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据

    /**
     * 获取文件包括子文件夹、子文件整体的大小
     *
     * @param file 文件对象
     * @return 文件大小
     */
    private static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                // 如果下面还有文件
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
