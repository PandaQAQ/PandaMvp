package com.pandaq.appcore.utils.format.formaters;

import java.math.BigDecimal;

/**
 * Created by huxinyu on 2018/7/6.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :大小格式化 byte kb M G T
 */
public class SizeFormatter {

    private static SizeFormatter sSizeFormatter;

    public static synchronized SizeFormatter getDefault() {
        if (sSizeFormatter == null) {
            sSizeFormatter = new SizeFormatter();
        }
        return sSizeFormatter;
    }

    /**
     * 格式化单位
     *
     * @param size 传入的 byte大小
     * @return 常规 kb mb gb tb 单位的大小
     */
    public String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
