package com.pandaq.appcore.log.alisls

/**
 * Created by huxinyu on 2021/12/17.
 * Email : panda.h@foxmail.com
 * Description :日志所属流程类型
 */
enum class LogTopic {
    LAUNCH,//启动流程日志
    MQTT,//MQTT消息接收类型
    CONFIG_SYNC,//配置同步日志（baseConfig 和 设备默认配置两个接口）
    PLAY_SYNC,//播放列表同步日志
    UPDATE,//设备升级日志
    STATUS_CHANGE,//网络、mqtt 状态变更日志
    SCREEN_SHOOT
}