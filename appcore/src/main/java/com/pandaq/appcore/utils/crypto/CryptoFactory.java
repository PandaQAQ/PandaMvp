package com.pandaq.appcore.utils.crypto;

import com.pandaq.appcore.utils.crypto.cryptors.Base64Cryptor;
import com.pandaq.appcore.utils.crypto.cryptors.DesCryptor;
import com.pandaq.appcore.utils.crypto.cryptors.Md5Cryptor;
import com.pandaq.appcore.utils.crypto.cryptors.SHA1Cryptor;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :加密/解密、编码/解码入口工厂类
 */
public class CryptoFactory {
    private CryptoFactory() {
        // private constructor
    }


    public static Md5Cryptor MD5 = Md5Cryptor.getDefault();

    public static Base64Cryptor BASE64 = Base64Cryptor.getDefault();

    public static SHA1Cryptor SHA1 = SHA1Cryptor.getDefault();

    public static DesCryptor DES = DesCryptor.getDefault();

}
