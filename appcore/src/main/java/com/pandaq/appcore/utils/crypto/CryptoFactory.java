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

    public static Md5Cryptor getMd5() {
        return Md5Cryptor.getDefault();
    }

    public static Base64Cryptor getBase64() {
        return Base64Cryptor.getDefault();
    }

    public static SHA1Cryptor getSha1() {
        return SHA1Cryptor.getDefault();
    }

    public static DesCryptor getDes() {
        return DesCryptor.getDefault();
    }

}
