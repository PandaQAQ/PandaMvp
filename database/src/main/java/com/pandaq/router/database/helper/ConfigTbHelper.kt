package com.pandaq.router.database.helper

import com.pandaq.appcore.utils.system.AppUtils
import com.pandaq.router.database.dao.ConfigDataDao
import com.pandaq.router.database.dao.DaoMaster
import com.pandaq.router.database.dao.DaoSession
import com.pandaq.router.database.entity.ConfigData

/**
 * Created by huxinyu on 5/25/21.
 * Email : panda.h@foxmail.com
 * Description :
 */
class ConfigTbHelper {

    private val openHelper = DaoMaster.DevOpenHelper(AppUtils.getContext(), "scaaa.db", null)
    private val daoMaster: DaoMaster = DaoMaster(openHelper.readableDatabase)
    private val daoSession: DaoSession = daoMaster.newSession()
    private val configDao: ConfigDataDao = daoSession.configDataDao

    companion object {

        private val AD_HELPER: ConfigTbHelper by lazy {
            ConfigTbHelper()
        }

        fun get() = AD_HELPER
    }

    fun insertOrReplace(configs: MutableList<ConfigData>) {
        // 删除旧列表
        configDao.deleteAll()
        configDao.detachAll()
        daoSession.clear()
        // 存储新的节目单
        configDao.insertOrReplaceInTx(configs)
    }


    fun listAll(): List<ConfigData> {
        val result = configDao.queryBuilder()
            .build()
            .list()
        return if (result.isNullOrEmpty()) {
            arrayListOf()
        } else {
            result
        }
    }
}