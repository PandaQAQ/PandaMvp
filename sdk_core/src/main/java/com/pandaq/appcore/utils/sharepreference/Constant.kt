package com.pandaq.appcore.utils.sharepreference

/**
 * Created by huxinyu on 5/13/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class Constant {

    companion object {

        // 是否注册
        const val SP_KEY_REGISTER = "hasRegister"

        // 旋转角度
        const val SP_KEY_ROTATION = "deviceRotation"

        // 开机时间
        const val SP_KEY_ON_TIME = "onTime"

        // 关机时间
        const val SP_KEY_OFF_TIME = "offTime"

        // 是否启用开、关机时间
        const val SP_KEY_ENABLE_TIME = "timeEnable"

        // 播放列表版本
        const val SP_KEY_PLAYLIST_VERSION = "playListVersion"

        // 位置纬度
        const val SP_KEY_LON = "locationLon"

        // 位置经度
        const val SP_KEY_LAT = "locationLat"

        // 城市
        const val SP_KEY_CITY = "city"

        // 城市 CODE
        const val SP_KEY_CITY_CODE = "cityCode"

        // 天气温度
        const val SP_KEY_TEMP = "temp"

        // 音量
        const val SP_KEY_VOLUME = "volume"

        // 底部电话
        const val SP_KEY_PHONE = "phone"

        // 底部落款
        const val SP_KEY_COPYRIGHT = "copyright"

        // 本机以太网 MAC 地址
        const val SP_KEY_MAC = "macAddress"

        // 本机器上次数据重置至今 APP 启动次数
        const val SP_KEY_LAUNCH_TIMES = "launchTimes"
    }

}