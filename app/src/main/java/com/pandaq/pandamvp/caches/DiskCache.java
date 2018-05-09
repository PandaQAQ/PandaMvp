package com.pandaq.pandamvp.caches;

import android.content.Context;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;
import com.pandaq.pandamvp.app.App;
import com.pandaq.pandamvp.utils.EncryptFactory;

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
 * Created by huxinyu on 2018/3/30.
 * Email : panda.h@foxmail.com
 * Description : 磁盘缓存管理类
 */
public class DiskCache {
    private static DiskCache sDiskCache;
    private DiskLruCache mDiskLruCache = null;
    private DiskLruCache.Editor mEditor = null;
    private DiskLruCache.Snapshot mSnapshot = null;
    // default cache size is 30 MB
    private static final int DEFAULT_CACHE_SIZE = 30 * 1024 * 1024;

    /**
     * 图片缓存
     *
     * @return 缓存路径为图片缓存的 DiskLruCache
     */
    public static DiskCache initImgCache() {
        sDiskCache = getDiskCache();
        sDiskCache.getDefault("image");
        return sDiskCache;
    }

    /**
     * 默认缓存
     *
     * @return 缓存路径为默认的 DiskLruCache
     */
    public static DiskCache initDefault() {
        sDiskCache = getDiskCache();
        sDiskCache.getDefault("default");
        return sDiskCache;
    }

    /**
     * 数据缓存
     *
     * @return 缓存路径为数据缓存的 DiskLruCache
     */
    public static DiskCache initDataCache() {
        sDiskCache = getDiskCache();
        sDiskCache.getDefault("data");
        return sDiskCache;
    }

    /**
     * 获取管理类单例
     *
     * @return 管理类单例
     */
    public static synchronized DiskCache getDiskCache() {
        if (sDiskCache == null) {
            sDiskCache = new DiskCache();
        }
        return sDiskCache;
    }

    /**
     * get DiskLruCache instance
     *
     * @param uniqueName name of cache file
     */
    private void getDefault(String uniqueName) {
        try {
            //先关闭已有的缓存
            if (mDiskLruCache != null) {
                mDiskLruCache.close();
                mDiskLruCache = null;
            }
            File cacheFile = getCacheFile(App.getGlobalApp().getApplicationContext(), uniqueName);
            mDiskLruCache = DiskLruCache.open(cacheFile, App.getGlobalApp().getAppVersion(),
                    1, DEFAULT_CACHE_SIZE);
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
    private static File getCacheFile(Context context, String uniqueName) {
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
     * @throws IOException
     */
    private DiskLruCache.Editor edit(String key) throws IOException {
        key = EncryptFactory.getDefault().encryptStr(key, EncryptFactory.EncryptType.MD5);
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

    /*************************
     * 字符串存取
     *************************/

    public void put(String key, String value) {
        DiskLruCache.Editor editor = null;
        BufferedWriter writer = null;
        OutputStream os = null;
        try {
            editor = edit(key);
            if (editor == null) {
                return;
            }
            os = editor.newOutputStream(0);
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
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    /*************************
     * Json对象存取
     *************************/

    //Json 数据转换成 String 存储
    public void put(String key, JSONObject value) {
        put(key, value.toString());
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

    /*************************
     * Json数组对象存取
     *************************/

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

    /*************************
     *  inputStream 数据存取
     *************************/

    public void put(String key, InputStream inputStream) {
        OutputStream outputStream = null;
        DiskLruCache.Editor editor;
        try {
            editor = edit(key);
            if (editor == null) {
                return;
            }
            outputStream = editor.newOutputStream(0);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取 缓存数据的 InputStream
     *
     * @param key cache'key
     * @return InputStream
     */
    private InputStream getCacheInputStream(String key) {
        key = EncryptFactory.getDefault().encryptStr(key, EncryptFactory.EncryptType.MD5);
        InputStream in;
        DiskLruCache.Snapshot snapshot = snapshot(key);
        if (snapshot == null) {
            return null;
        }
        in = snapshot.getInputStream(0);
        return in;
    }

    /*************************
     * byte 数据存取
     *************************/

    public void put(String key, byte[] bytes) {
        OutputStream out = null;
        DiskLruCache.Editor editor = null;
        try {
            editor = edit(key);
            if (editor == null) {
                return;
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

    /*************************
     * 序列化对象数据存取
     *************************/

    public void put(String key, Serializable object) {
        ObjectOutputStream oos = null;
        DiskLruCache.Editor editor = null;
        try {
            editor = edit(key);
            if (editor == null) {
                return;
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


    /***********************************************************************
     **********************  辅助工具方法 分割线  **************************
     ***********************************************************************/

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
     * 同步记录文件
     */
    public void flush() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
