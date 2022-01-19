package com.pandaq.appcore.log.alisls

import android.os.Environment
import com.aliyun.sls.android.producer.Log
import com.aliyun.sls.android.producer.LogProducerClient
import com.aliyun.sls.android.producer.LogProducerConfig
import com.aliyun.sls.android.producer.LogProducerException
import com.pandaq.appcore.BuildConfig
import com.pandaq.appcore.log.PLogger
import com.pandaq.appcore.utils.format.FormatFactory
import com.pandaq.appcore.utils.format.formaters.DateFormatter
import com.pandaq.appcore.utils.system.AppUtils
import java.io.File
import java.util.concurrent.Executors


/**
 * Created by huxinyu on 2021/12/16.
 * Email : panda.h@foxmail.com
 * Description :阿里云移动运维监控 SLS 日志服务工具类
 */
class SLSLoger {

    companion object {
        private var slsLoger: SLSLoger? = null
        private var logClient: LogProducerClient? = null
        private const val PROJECT = "cc-aaa-sds"
        private const val LOG_STORE = "sdsandroid"
        private const val END_POINT = "https://cn-qingdao.log.aliyuncs.com"
        private const val AK = "LTAI5tPRWHZQJbbFSgJio6ri"
        private const val AKS = "keDVLIWAR78banzhABZqsb5vUThSgb"

        /**
         * 发送一条日志
         */
        fun sendLog(slsLog: SlsLog) {
            if (logClient == null) {
                return
            }
            Executors.defaultThreadFactory().newThread {
                val log = Log()
                slsLog.toMap().entries.forEach {
                    log.putContent(it.key, it.value)
                }
                log.putContent(
                    "appReportTime", FormatFactory.DATE.formatTime(
                        System.currentTimeMillis(),
                        DateFormatter.FORMAT_DHMS
                    )
                )
                log.putContent("appVersion", AppUtils.versionName())
                log.putContent("registerState", AppUtils.preferenceUtil.getRegisterStatusName())
                log.putContent("deviceMac", AppUtils.getMacAddress())
                log.putContent("environment", BuildConfig.ENV)
                log.putContent("launchIndex", AppUtils.preferenceUtil.getLaunchTimes().toString())
                val result = logClient?.addLog(log, 1)
                PLogger.d("SLSLog", "addLog $result")
            }.start()
        }


        fun init() {
            slsLoger = SLSLoger()
            slsLoger?.initProducer()
        }
    }

    private fun initProducer() {
        try {
            val config =
                LogProducerConfig(END_POINT, PROJECT, LOG_STORE, AK, AKS)
            // 设置tag信息，此tag会附加在每条日志上
            // config.addTag("test", "test_tag")
            // 每个缓存的日志包的大小上限，取值为1~5242880，单位为字节。默认为1024 * 1024
            config.setPacketLogBytes(1024 * 1024)
            // 每个缓存的日志包中包含日志数量的最大值，取值为1~4096，默认为1024
            config.setPacketLogCount(1024)
            // 被缓存日志的发送超时时间，如果缓存超时，则会被立即发送，单位为毫秒，默认为3000
            config.setPacketTimeout(3000)
            // 单个Producer Client实例可以使用的内存的上限，超出缓存时add_log接口会立即返回失败
            // 默认为64 * 1024 * 1024
            config.setMaxBufferLimit(64 * 1024 * 1024)
            // 发送线程数，默认为1
            config.setSendThreadCount(1)
            //网络连接超时时间，整数，单位秒，默认为10
            config.setConnectTimeoutSec(10)
            //日志发送超时时间，整数，单位秒，默认为15
            config.setSendTimeoutSec(10)
            //flusher线程销毁最大等待时间，整数，单位秒，默认为1
            config.setDestroyFlusherWaitSec(2)
            //sender线程池销毁最大等待时间，整数，单位秒，默认为1
            config.setDestroySenderWaitSec(2)
            //数据上传时的压缩类型，默认为LZ4压缩，0 不压缩，1 LZ4压缩，默认为1
            config.setCompressType(1)
            //设备时间与标准时间之差，值为标准时间-设备时间，一般此种情况用户客户端设备时间不同步的场景
            //整数，单位秒，默认为0；比如当前设备时间为1607064208, 标准时间为1607064308，则值设置为 1607064308 - 1607064208 = 10
            config.setNtpTimeOffset(3)
            //日志时间与本机时间之差，超过该大小后会根据 `drop_delay_log` 选项进行处理。
            //一般此种情况只会在设置persistent的情况下出现，即设备下线后，超过几天/数月启动，发送退出前未发出的日志
            //整数，单位秒，默认为7*24*3600，即7天
            config.setMaxLogDelayTime(7 * 24 * 3600)
            //对于超过 `max_log_delay_time` 日志的处理策略
            //0 不丢弃，把日志时间修改为当前时间; 1 丢弃，默认为 1 （丢弃）
            config.setDropDelayLog(0)
            //是否丢弃鉴权失败的日志，0 不丢弃，1丢弃
            //默认为 0，即不丢弃
            config.setDropUnauthorizedLog(0)
            /**
             * 以下为开启断点续传的配置, 按照如下配置开启断点续传功能后, 日志会先缓存到本地
             */
            // 1 开启断点续传功能， 0 关闭
            // 每次发送前会把日志保存到本地的binlog文件，只有发送成功才会删除，保证日志上传At Least Once
            config.setPersistent(1)
            // 持久化的文件名，需要保证文件所在的文件夹已创建。
            // !!!!!!!!!!!!!!!!!!!注意!!!!!!!!!!!!!!!!!!!
            // 配置多个客户端时，不应设置相同文件
            config.setPersistentFilePath(
                Environment.getExternalStorageDirectory().path + String.format(
                    "%slog_data.dat",
                    File.separator
                )
            )
            // 是否每次AddLog强制刷新，高可靠性场景建议打开
            config.setPersistentForceFlush(0)
            // 持久化文件滚动个数，建议设置成10。
            config.setPersistentMaxFileCount(10)
            // 每个持久化文件的大小，建议设置成1-10M
            config.setPersistentMaxFileSize(1024 * 1024)
            // 本地最多缓存的日志数，不建议超过1M，通常设置为65536即可
            config.setPersistentMaxLogCount(65536)

            // callback为可选配置, 如果不需要关注日志的发送成功或失败状态, 可以不注册 callback
            logClient = LogProducerClient(config)
        } catch (e: LogProducerException) {
            e.printStackTrace()
        }
    }
}