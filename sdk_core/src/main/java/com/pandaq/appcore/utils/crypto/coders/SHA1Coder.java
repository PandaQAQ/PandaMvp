package com.pandaq.appcore.utils.crypto.coders;

import com.pandaq.appcore.utils.crypto.CodeType;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;

/**
 * Created by huxinyu on 2018/7/6.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :SHA1 字符串加密
 */
public class SHA1Coder {

    private static SHA1Coder sSHA1Coder;

    public static synchronized SHA1Coder getDefault() {
        if (sSHA1Coder == null) {
            sSHA1Coder = new SHA1Coder();
        }
        return sSHA1Coder;
    }

    public String encodeSha1(@NonNull String source) {
        try {
            MessageDigest alga = MessageDigest.getInstance(CodeType.SHA.getType());
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
