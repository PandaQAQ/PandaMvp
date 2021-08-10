package com.pandaq.appcore.utils.sharepreference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by huxinyu on 8/5/21.
 * Email : panda.h@foxmail.com
 * Description :SharePreference 工具类
 */
class PreferenceUtil(context: Context) {

    private var preference: SharedPreferences = context.getSharedPreferences(
        context.packageName,
        Context.MODE_PRIVATE
    )

    // 保存注册状态
    fun saveRegister(value: Boolean) {
        preference.edit()
            .putBoolean(Constant.SP_KEY_REGISTER, value)
            .apply()
    }

    // 获取注册状态
    fun hasRegistered(): Boolean {
        return preference.getBoolean(
            Constant.SP_KEY_REGISTER,
            false
        )
    }

    // 保存开机时间
    fun saveOnTime(value: String?) {
        preference.edit()
            .putString(Constant.SP_KEY_ON_TIME, value)
            .apply()
    }

    // 获取开机状态
    fun getOnTime(): String? {
        return preference.getString(
            Constant.SP_KEY_ON_TIME,
            "06:00"
        )
    }

    // 保存关机时间
    fun saveOffTime(value: String?) {
        preference.edit()
            .putString(Constant.SP_KEY_OFF_TIME, value)
            .apply()
    }

    // 获取关机状态
    fun getOffTime(): String? {
        return preference.getString(
            Constant.SP_KEY_OFF_TIME,
            "23:50"
        )
    }

    // 保存是否启用开关机时间
    fun saveTimeEnable(value: Boolean) {
        preference.edit()
            .putBoolean(Constant.SP_KEY_ENABLE_TIME, value)
            .apply()
    }

    // 获取是否启用开关机时间
    fun isTimeEnable(): Boolean {
        return preference.getBoolean(
            Constant.SP_KEY_ENABLE_TIME,
            false
        )
    }

    // 保存注册状态
    @SuppressLint("ApplySharedPref")
    fun saveRotation(value: Int) {
        preference.edit()
            .putInt(Constant.SP_KEY_ROTATION, value)
            .commit()
    }

    // 获取注册状态
    fun getRotation(): Int {
        return preference.getInt(
            Constant.SP_KEY_ROTATION,
            0
        )
    }

    // 保存注册状态
    fun savePlayListVersion(version: String) {
        preference.edit()
            .putString(Constant.SP_KEY_PLAYLIST_VERSION, version)
            .apply()
    }

    // 获取注册状态
    fun getPlayListVersion(): String? {
        return preference.getString(Constant.SP_KEY_PLAYLIST_VERSION, "")
    }

}