package com.pandaq.appcore.log.alisls

/**
 * Created by huxinyu on 2021/12/17.
 * Email : panda.h@foxmail.com
 * Description :
 */
data class SlsLog(
    val logTopic: LogTopic, // log 分组
    val content: String, //备注内容
) {
    fun toMap(): HashMap<String, String> {
        val map = hashMapOf<String, String>()
        map["logTopic"] = logTopic.name
        map["content"] = content
        return map
    }
}