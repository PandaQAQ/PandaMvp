package com.pandaq.uires.beans

import com.flyco.tablayout.listener.CustomTabEntity

/**
 * Created by huxinyu on 2019/7/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
data class TabEntity(
    val unSelectIcon: Int,
    val selectIcon: Int,
    val title: String
) : CustomTabEntity {
    override fun getTabUnselectedIcon(): Int = unSelectIcon

    override fun getTabSelectedIcon(): Int = selectIcon

    override fun getTabTitle(): String = title
}