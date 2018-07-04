package com.pandaq.appcore.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :EventBus 的注册发送工具类，做了重复注册及取消注册的判断
 */
public class EventUtil {

    private static EventUtil sEventUtil;

    public synchronized static EventUtil getDefault() {
        if (sEventUtil == null) {
            sEventUtil = new EventUtil();
        }
        return sEventUtil;
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

    /**
     * 发送事件
     *
     * @param message 事件对象
     */
    public void postEvent(EventMessage message) {
        EventBus.getDefault().post(message);
    }

    /**
     * 发送迟滞事件
     *
     * @param message 事件对象
     */
    public void postSticky(EventMessage message) {
        EventBus.getDefault().postSticky(message);
    }

    /**
     * 取消迟滞事件
     *
     * @param message 被取消的迟滞事件
     */
    public void cancelSticky(EventMessage message) {
        EventBus.getDefault().removeStickyEvent(message);
    }
}
