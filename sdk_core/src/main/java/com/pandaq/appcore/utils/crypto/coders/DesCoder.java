package com.pandaq.appcore.utils.crypto.coders;

import android.util.Base64;

import com.pandaq.appcore.utils.crypto.CodeType;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by huxinyu on 2018/7/6.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :Des 加密解密器
 */
public class DesCoder {

    private static DesCoder sDesCoder;

    public static synchronized DesCoder getDefault() {
        if (sDesCoder == null) {
            sDesCoder = new DesCoder();
        }
        return sDesCoder;
    }

    // 对密钥进行处理,获得秘钥对象
    private static SecretKey getRawKey(String key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(CodeType.DES.getType());
        return keyFactory.generateSecret(dks);
    }

    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String key, String data) {
        return encode(key, data.getBytes());
    }


    /**
     * DES算法，加密
     *
     * @param data 待加密字符串
     * @param key  加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     */
    public static String encode(String key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(CodeType.DES.getType());
            IvParameterSpec iv = new IvParameterSpec(CodeType.DES.getType().getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, getRawKey(key), iv);
            byte[] bytes = cipher.doFinal(data);
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 解密 des 加密后的字符串
     *
     * @param key  解密秘钥
     * @param data 待解密对象
     * @return 解密结果
     */
    public static String decode(String key, String data) {
        return decode(key, data.getBytes());
    }

    /**
     * DES算法，解密
     *
     * @param data 待解密字符串
     * @param key  解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     */
    public static String decode(String key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(CodeType.DES.getType());
            IvParameterSpec iv = new IvParameterSpec(CodeType.DES.getType().getBytes());
            cipher.init(Cipher.DECRYPT_MODE, getRawKey(key), iv);
            byte[] original = cipher.doFinal(data);
            return new String(original);
        } catch (Exception e) {
            return null;
        }
    }

}
