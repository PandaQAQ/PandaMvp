package com.pandaq.appcore.utils.format.formaters;

import androidx.annotation.Keep;

/**
 * Created by huxinyu on 2018/7/5.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :金钱显示格式工具类
 */
public class PriceFormatter {

    private static PriceFormatter sPriceFormatter;

    public static synchronized PriceFormatter getDefault() {
        if (sPriceFormatter == null) {
            sPriceFormatter = new PriceFormatter();
        }
        return sPriceFormatter;
    }

    /**
     * 精确到分
     *
     * @param money 钱
     * @return 钱大写
     */
    public String formatRmb(double money) {
        return money + "";
    }
}
