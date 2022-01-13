package com.pandaq.appcore.utils.crypto.coders;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by huxinyu on 7/20/21.
 * Email : panda.h@foxmail.com
 * Description :aes 加解密
 */
public class AESCoder {

    private static AESCoder sAESCoder;
    private static final String CHARSET_NAME = "UTF-8";
    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    public static synchronized AESCoder getDefault() {
        if (sAESCoder == null) {
            sAESCoder = new AESCoder();
        }
        return sAESCoder;
    }

    private AESCoder() {

    }

    /**
     * AES CBC 模式加密
     *
     * @param key  aes 密钥
     * @param kiv  aes 偏移量
     * @param data 待加密数据
     * @return 加密结果
     */
    public String encode(String key, String kiv, String data) {
        try {
            //处理传进来的明文
            byte[] originalContentBytes = data.getBytes(CHARSET_NAME);

            //处理传进来的密钥(String转成byte[])
            byte[] enKeyBytes = key.getBytes();

            //处理传进来的偏移量(String转成byte[])
            byte[] ivParameterBytes = kiv.getBytes();

            //根据传入的密钥按照AEC方式构造密钥
            SecretKeySpec sKeySpec = new SecretKeySpec(enKeyBytes, ALGORITHM);

            //根据传入的偏移量指定一个初始化偏移量
            IvParameterSpec iv = new IvParameterSpec(ivParameterBytes);
            //根据数据填充方式生成一个加解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            //初始化 传入类型(加密/解密)、构造过的密钥、指定的初始偏移量
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);

            //加密操作
            byte[] encrypted = cipher.doFinal(originalContentBytes);

            //base64转码
            return Base64.encodeToString(encrypted, Base64.DEFAULT).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * AES CBC 模式解密
     *
     * @param key  aes 密钥
     * @param kiv  aes 偏移量
     * @param data 待解密数据
     * @return 解密结果
     */
    public String decode(String key, String kiv, String data) {
        try {
            //处理传进来的密文 使用base64解密
            byte[] cipherStrByte = Base64.decode(data, Base64.DEFAULT);

            //处理传进来的密钥(String转成byte[])   可以指定编码格式为：ASCII
            byte[] enKeyBytes = key.getBytes();

            //处理传进来的偏移量(String转成byte[])
            byte[] ivParameterBytes = kiv.getBytes();

            //根据传入的密钥按照AEC方式构造密钥
            SecretKeySpec sKeySpec = new SecretKeySpec(enKeyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            IvParameterSpec iv = new IvParameterSpec(ivParameterBytes);
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);

            //获得解密的明文数组
            byte[] original = cipher.doFinal(cipherStrByte);
            return new String(original, CHARSET_NAME);

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
