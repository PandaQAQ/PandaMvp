package com.pandaq.pandamvp.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by huxinyu on 2018/3/30.
 * Email : panda.h@foxmail.com
 * Description :加密解密处理工具类
 */
public class EncryptFactory {

    private static EncryptFactory sFactory;

    /**
     * 加密编码类型
     */
    public enum EncryptType {
        DES, AES, MD5, SHA, RSA, BASE64
    }

    /**
     * 支持解密解码类型
     */
    public enum DecryptType {
        DES, AES, BASE64
    }

    public static EncryptFactory getDefault() {
        if (sFactory == null) {
            sFactory = new EncryptFactory();
        }
        return sFactory;
    }

    /**
     * get encrypted string with different type
     *
     * @param value the value to encrypt
     * @param type  encrypt type
     * @return result
     */
    public String encryptStr(String value, EncryptType type) {
        switch (type) {
            case MD5:
                return encryptByMD5(value);
            case SHA:
                return null;
            case BASE64:
                return null;
            default:
                throw new RuntimeException("nonsupport_encoding or nonsupport_encrypt");
        }
    }

    /**
     * get decrypted string with different type
     *
     * @param value the value to decrypt
     * @param type  decrypt type
     * @return result
     */
    public String decryptStr(String value, DecryptType type) {
        switch (type) {
            case BASE64:
            case AES:
            case DES:
            default:
                throw new RuntimeException("nonsupport_encoding or nonsupport_encrypt");
        }
    }

    /**
     * base64 编码字符串
     *
     * @param value 待编码字符串
     * @return 编码结果
     */
    private String base64Encoding(String value) {
        if (value == null) return null;
        return Base64.encodeToString(value.getBytes(), Base64.DEFAULT);
    }

    /**
     * base64 字符串解码
     *
     * @param value 待解码串
     * @return 解码结果字符串
     */
    private String base64Decoding(String value) {
        if (value == null) return null;
        return new String(Base64.decode(value, Base64.DEFAULT));
    }

    /**
     * base64 编码文件
     *
     * @param file 待编码文件
     * @return 编码结果字符串
     */
    private String base64EncodeFile(File file) {
        FileInputStream inputFile;
        String encodedString = null;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            encodedString = Base64.encodeToString(buffer, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
    }

    /**
     * base64 解码文件
     *
     * @param str        待解码文件字符串
     * @param outputFile 解码文件输出位置（包含文件名）
     * @return 输出路径
     */
    private String base64DecodeFile(String str, File outputFile) {
        FileOutputStream fos;
        try {
            byte[] decodeBytes = Base64.decode(str.getBytes(), Base64.DEFAULT);
            fos = new FileOutputStream(outputFile);
            fos.write(decodeBytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFile.getAbsolutePath();
    }

    /**
     * 传入字符串参数，返回MD5加密结果（小写）
     *
     * @param value 待加密的字符串
     * @return 加密结果
     */
    private String encryptByMD5(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(value.getBytes("UTF-8"));
            byte[] result = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int i = b & 0xff;
                if (i <= 0xf) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString().toLowerCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
