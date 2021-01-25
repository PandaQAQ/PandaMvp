package com.pandaq.app_jetpack.app.entity

/**
 * Created by huxinyu on 2020/3/30.
 * Email : panda.h@foxmail.com
 * Description :
 */
data class ZhihuData(
        val date: String?,
        var stories: MutableList<Story>,
        val top_stories: MutableList<TopStory>
)

data class Story(
        val ga_prefix: String?,
        val hint: String?,
        val id: Int?,
        val image_hue: String?,
        val images: List<String>,
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

data class WanAndroidData(
    val curPage: Int?,
    val datas: List<Data>?,
    val offset: Int?,
    val over: Boolean?,
    val pageCount: Int?,
    val size: Int?,
    val total: Int?
)

data class Data(
    val apkLink: String?,
    val audit: Int?,
    val author: String?,
    val canEdit: Boolean?,
    val chapterId: Int?,
    val chapterName: String?,
    val collect: Boolean?,
    val courseId: Int?,
    val desc: String?,
    val descMd: String?,
    val envelopePic: String?,
    val fresh: Boolean?,
    val id: Int?,
    val link: String?,
    val niceDate: String?,
    val niceShareDate: String?,
    val origin: String?,
    val prefix: String?,
    val projectLink: String?,
    val publishTime: Long?,
    val realSuperChapterId: Int?,
    val selfVisible: Int?,
    val shareDate: Long?,
    val shareUser: String?,
    val superChapterId: Int?,
    val superChapterName: String?,
    val tags: List<Tag>?,
    val title: String?,
    val type: Int?,
    val userId: Int?,
    val visible: Int?,
    val zan: Int?
)

data class Tag(
    val name: String?,
    val url: String?
)