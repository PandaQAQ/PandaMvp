package com.pandaq.appcore.utils.code.coders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/7/6.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :base64 编码与逆编码提供者
 */
public class Base64Coder {

    private static Base64Coder sBase64Coder;

    public static synchronized Base64Coder getDefault() {
        if (sBase64Coder == null) {
            sBase64Coder = new Base64Coder();
        }
        return sBase64Coder;
    }
    // 字符串

    /**
     * 字符串 base64 编码
     *
     * @param source 待编码字符串
     * @return 编码后的字符串
     */
    public String encodeString(@NonNull String source) {
        return this.encodeString(source, Base64.DEFAULT);
    }

    /**
     * 字符串 base64 编码
     *
     * @param source 待编码字符串
     * @param flag   编码 flag 类型
     * @return 编码后的字符串
     */
    public String encodeString(@NonNull String source, int flag) {
        return Base64.encodeToString(source.getBytes(), flag);
    }

    /**
     * base64 解码成字符串
     *
     * @param source base64 资源
     * @return 解码后的字符串
     */
    public String decodeToString(@NonNull String source) {
        return this.decodeToString(source, Base64.DEFAULT);
    }

    /**
     * base64 解码成字符串
     *
     * @param source base64 资源
     * @param flag   解码 flag 类型
     * @return 解码后的字符串
     */
    public String decodeToString(@NonNull String source, int flag) {
        return new String(Base64.decode(source, flag));
    }

    //文件

    /**
     * 编码文件为 base64 字符串
     *
     * @param file 待编码文件
     * @param flag 编码方式
     * @return 编码后的字符串
     */
    public String encodeFile(@NonNull File file, int flag) {
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.encodeToString(buffer, flag).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 编码文件为 base64 字符串
     *
     * @param file 待编码文件
     * @return 编码后的字符串
     */
    public String encodeFile(@NonNull File file) {
        return encodeFile(file, Base64.DEFAULT);
    }

    /**
     * 解码 base64 字符串到指定 file
     *
     * @param decodeSource 待解码字符串
     * @param file         目标文件
     * @param flag         解码方式
     */
    public void decodeToFile(@NonNull String decodeSource, @NonNull File file, int flag) {
        FileOutputStream fos;
        try {
            byte[] decodeBytes = Base64.decode(decodeSource.getBytes(), flag);
            fos = new FileOutputStream(file);
            fos.write(decodeBytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解码 base64 字符串到指定 file
     *
     * @param decodeSource 待解码字符串
     * @param file         目标文件
     */
    public void decodeToFile(@NonNull String decodeSource, @NonNull File file) {
        decodeToFile(decodeSource, file, Base64.DEFAULT);
    }

    //bitmap图片

    /**
     * 获取 bitmap 的 base64 编码
     *
     * @param bitmap 待编码的 bitmap 对象
     * @param flag   编码模式
     * @return 编码后的字符串
     */
    public String encodeBitmap(@NonNull Bitmap bitmap, int flag) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            baos.flush();
            baos.close();
            // 转换为字节数组
            byte[] byteArray = baos.toByteArray();
            // 转换为字符串
            return Base64.encodeToString(byteArray, flag);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取 bitmap 的 base64 编码
     *
     * @param bitmap 待编码的 bitmap 对象
     * @return 编码后的字符串
     */
    public String encodeBitmap(@NonNull Bitmap bitmap) {
        return encodeBitmap(bitmap, Base64.DEFAULT);
    }

    /**
     * 将 base 64 字符串解密成 bitmap
     *
     * @param resource 待解码的 base64 字符串
     * @param flag     解码模式
     * @return 解码恢复后的 bitmap 对象
     */
    public Bitmap decodeToBitmap(@NonNull String resource, int flag) {
        byte[] decode = Base64.decode(resource, flag);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    /**
     * 将 base 64 字符串解密成 bitmap
     *
     * @param resource 待解码的 base64 字符串
     * @return 解码恢复后的 bitmap 对象
     */
    public Bitmap decodeToBitmap(@NonNull String resource) {
        return decodeToBitmap(resource, Base64.DEFAULT);
    }
}
