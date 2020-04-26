package com.pandaq.app_jetpack.app.entity

/**
 * Created by huxinyu on 2020/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
data class ZhihuData(
        val date: String?,
        val stories: List<Story?>?,
        val top_stories: List<TopStory?>?
)

data class Story(
        val ga_prefix: String?,
        val hint: String?,
        val id: Int?,
        val image_hue: String?,
        val images: List<String?>?,
        val title: String?,
        val type: Int?,
        val url: String?
)

data class TopStory(
        val ga_prefix: String?,
        val hint: String?,
        val id: Int?,
        val image: String?,
        val image_hue: String?,
        val title: String?,
        val type: Int?,
        val url: String?
)