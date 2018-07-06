package com.pandaq.appcore.utils.crypto.cryptors;

import com.pandaq.appcore.utils.crypto.CryptType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :md5 加密方法工具类
 */
public class Md5Cryptor {

    private static Md5Cryptor sMd5Cryptor;

    public static synchronized Md5Cryptor getDefault() {
        if (sMd5Cryptor == null) {
            sMd5Cryptor = new Md5Cryptor();
        }
        return sMd5Cryptor;
    }

    /**
     * 获取资源 source
     *
     * @param source 资源字符串
     * @return MD5 加密结果字符串，默认小写
     */
    public String getMd5Code(String source) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return "";
        }
        char[] charArray = source.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 获取文件 MD5 值
     *
     * @param file 资源文件
     * @return 文件的 MD5 结果，默认小写
     */
    public String getFileMd5(File file, long bufferLen) {
        if (file == null || bufferLen <= 0 || !file.exists()) {
            return null;
        }
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            String md5 = getInputStreamMd5(fin, (int) (bufferLen <= file.length() ? bufferLen : file.length()));
            fin.close();
            return md5;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取输入流的 MD5 值
     *
     * @param is     输入流
     * @param bufLen 缓冲区大小
     * @return MD5值，默认小写
     */
    private String getInputStreamMd5(final InputStream is, final int bufLen) {
        if (is == null || bufLen <= 0) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance(CryptType.MD5.getType());
            StringBuilder md5Str = new StringBuilder(32);

            byte[] buf = new byte[bufLen];
            int readCount;
            while ((readCount = is.read(buf)) != -1) {
                md.update(buf, 0, readCount);
            }

            byte[] hashValue = md.digest();
            for (byte aHashValue : hashValue) {
                md5Str.append(Integer.toString((aHashValue & 0xff) + 0x100, 16).substring(1));
            }
            return md5Str.toString().toLowerCase();
        } catch (Exception e) {
            return null;
        }
    }
}
