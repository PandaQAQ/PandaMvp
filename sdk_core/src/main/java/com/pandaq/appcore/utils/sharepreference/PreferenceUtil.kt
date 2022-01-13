package com.pandaq.appcore.utils.sharepreference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.baidu.mapapi.model.LatLng

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
    fun saveRegister(value: Int) {
        preference.edit()
            .putInt(Constant.SP_KEY_REGISTER, value)
            .apply()
    }

    // 获取注册状态
    fun getRegisterStatus(): Int {
        return preference.getInt(
            Constant.SP_KEY_REGISTER,
            0
        )
    }

    fun getRegisterStatusName(): String {
        return when (getRegisterStatus()) {
            0 -> {
                "未注册"
            }
            1 -> {
                "审核中"
            }
            2 -> {
                "已激活"
            }
            else -> {
                "激活失败"
            }
        }
    }

    // 保存开机时间
    fun saveOnTime(value: String?) {
        preference.edit()
            .putString(Constant.SP_KEY_ON_TIME, value)
            .apply()
    }

    // 获取开机状态
    fun getOnTime(): String {
        return preference.getString(
            Constant.SP_KEY_ON_TIME,
            "06:00"
        )!!
    }

    // 保存关机时间
    fun saveOffTime(value: String?) {
        preference.edit()
            .putString(Constant.SP_KEY_OFF_TIME, value)
            .apply()
    }

    // 获取关机状态
    fun getOffTime(): String {
        return preference.getString(
            Constant.SP_KEY_OFF_TIME,
            "22:00"
        )!!
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

    // 保存本地节目版本号
    fun savePlayListVersion(version: String) {
        preference.edit()
            .putString(Constant.SP_KEY_PLAYLIST_VERSION, version)
            .apply()
    }

    // 获取本地节目版本号
    fun getPlayListVersion(): String? {
        return preference.getString(Constant.SP_KEY_PLAYLIST_VERSION, "—")
    }

    fun saveLocation(lon: Float, lat: Float) {
        preference.edit()
            .putFloat(Constant.SP_KEY_LON, lon)
            .putFloat(Constant.SP_KEY_LAT, lat)
            .apply()
    }

    fun getLocation(): LatLng {
        val lng = preference.getFloat(Constant.SP_KEY_LON, 0f).toDouble()
        val lat = preference.getFloat(Constant.SP_KEY_LAT, 0f).toDouble()
        return LatLng(lat, lng)
    }

    fun saveCityName(name: String) {
        preference.edit()
            .putString(Constant.SP_KEY_CITY, name)
            .apply()
    }

    fun getCityName(): String? {
        return preference.getString(Constant.SP_KEY_CITY, "—")
    }


    fun saveTemp(name: String) {
        preference.edit()
            .putString(Constant.SP_KEY_TEMP, name)
            .apply()
    }

    fun getTemp(): String? {
        return preference.getString(Constant.SP_KEY_TEMP, "—")
    }


    fun saveCityCode(code: String) {
        preference.edit()
            .putString(Constant.SP_KEY_CITY_CODE, code)
            .apply()
    }

    fun getCityCode(): String? {
        return preference.getString(Constant.SP_KEY_CITY_CODE, "—")
    }

    fun saveVolume(volume: Int) {
        preference.edit()
            .putInt(Constant.SP_KEY_VOLUME, volume)
            .apply()
    }

    fun getVolume(): Int {
        return preference.getInt(Constant.SP_KEY_VOLUME, 30)
    }

    fun savePhone(phone: String?) {
        preference.edit()
            .putString(Constant.SP_KEY_PHONE, phone)
            .apply()
    }

    fun saveMacAddress(macAddress: String) {
        preference.edit()
            .putString(Constant.SP_KEY_MAC, macAddress)
            .apply()
    }

    fun getMacAddress(): String? {
        return preference
            .getString(Constant.SP_KEY_MAC, null)
    }

    fun getPhone(): String? {
        return preference.getString(Constant.SP_KEY_PHONE, "—")
    }

    fun saveCopyright(copyright: String?) {
        preference.edit()
            .putString(Constant.SP_KEY_COPYRIGHT, copyright)
            .apply()
    }

    fun getCopyright(): String? {
        return preference.getString(Constant.SP_KEY_COPYRIGHT, "—")
    }

    fun updateLaunchTime() {
        val current = getLaunchTimes()
        preference.edit()
            .putInt(Constant.SP_KEY_LAUNCH_TIMES, current + 1)
            .apply()
    }

    fun getLaunchTimes(): Int {
        return preference.getInt(Constant.SP_KEY_LAUNCH_TIMES, 0)
    }

}