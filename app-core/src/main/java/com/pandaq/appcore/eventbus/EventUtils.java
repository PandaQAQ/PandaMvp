package com.pandaq.appcore.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :EventBus 的注册发送工具类，做了重复注册及取消注册的判断
 */
public class EventUtils {

    private static EventUtils sEventUtils;

    public synchronized static EventUtils getDefault() {
        if (sEventUtils == null) {
            sEventUtils = new EventUtils();
        }
        return sEventUtils;
    }

    /**
     * 注册方法，加判断避免重复注册
     *
     * @param subscriber 观察者
     */
    public void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    /**
     * 取消注册方法，加判断避免取消未注册观察这
     *
     * @param subscriber 观察者
     */
    public void unregister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }
}
