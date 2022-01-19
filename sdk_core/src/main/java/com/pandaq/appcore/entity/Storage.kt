package com.pandaq.appcore.entity

data class Storage(
    // 设备 Mac
    val deviceMac: String,
    // 总存储空间
    val allSpace: Long,
    // 日志文件空间
    val logSpace: Long,
    // 剩余可用空间
    val residualSpace: Long,
    // 节目资源空间
    val resourceSpace: Long,
    // 其他空间
    val otherSpace: Long
)