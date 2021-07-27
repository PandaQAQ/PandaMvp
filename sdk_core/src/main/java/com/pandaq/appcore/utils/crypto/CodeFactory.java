package com.pandaq.appcore.utils.crypto;

import com.pandaq.appcore.utils.crypto.coders.AESCoder;
import com.pandaq.appcore.utils.crypto.coders.Base64Coder;
import com.pandaq.appcore.utils.crypto.coders.DesCoder;
import com.pandaq.appcore.utils.crypto.coders.Md5Coder;
import com.pandaq.appcore.utils.crypto.coders.SHA1Coder;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :加密/解密、编码/解码入口工厂类
 */
public class CodeFactory {
    private CodeFactory() {
        // private constructor
    }


    public static Md5Coder MD5 = Md5Coder.getDefault();

    public static Base64Coder BASE64 = Base64Coder.getDefault();

    public static SHA1Coder SHA1 = SHA1Coder.getDefault();

    public static DesCoder DES = DesCoder.getDefault();

    public static AESCoder AES = AESCoder.getDefault();

}
