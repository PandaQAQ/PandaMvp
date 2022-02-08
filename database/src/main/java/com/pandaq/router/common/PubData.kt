package com.pandaq.router.common

import android.os.Environment

/**
 * Created by huxinyu on 7/29/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class PubData {
    companion object {

        const val LAST_DEFAULT_POST_NAME = "default_post.webp"
        const val DEFAULT_POST_NAME = "default_post_v2.webp"

        const val BASE_CONFIG_AES_KEY = "6plwu888B@M1x917"
        const val AES_KIV = "0312032293271340"

        var configs: Configs? = null

        // 主页公告、网址导航图片央视
        const val PIC_STYLE_1080 = "?x-oss-process=style/bigw1080"
        const val PIC_STYLE_600 = "?x-oss-process=style/normalw600"
        const val PIC_STYLE_600x277 = "?x-oss-process=style/normal600x277"
        const val PIC_STYLE_320 = "?x-oss-process=style/small320"
        const val PIC_STYLE_320x213 = "?x-oss-process=style/small320x213"
        const val PIC_STYLE_200X281 = "?x-oss-process=style/small200x281"

        fun getSourcePath(): String {
            return "${Environment.getExternalStorageDirectory().path}/programs/sources"
        }

        fun getDefaultPath(): String {
            return "${Environment.getExternalStorageDirectory().path}/programs/default"
        }
    }
}