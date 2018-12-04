package com.pandaq.appcore.utils.crypto.cryptors;

import com.pandaq.appcore.utils.crypto.CryptType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/7/6.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :SHA1 字符串加密
 */
public class SHA1Cryptor {

    private static SHA1Cryptor sSHA1Cryptor;

    public static synchronized SHA1Cryptor getDefault() {
        if (sSHA1Cryptor == null) {
            sSHA1Cryptor = new SHA1Cryptor();
        }
        return sSHA1Cryptor;
    }

    public String encodeSha1(@NonNull String source) {
        try {
            MessageDigest alga = MessageDigest.getInstance(CryptType.SHA.getType());
            alga.update(source.getBytes());
            StringBuilder hexValue = new StringBuilder();
            for (byte md5Byte : alga.digest()) {
                int val = ((int) md5Byte) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
