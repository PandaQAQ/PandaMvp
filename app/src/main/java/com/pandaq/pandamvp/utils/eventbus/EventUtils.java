package com.pandaq.pandamvp.utils.eventbus;

import com.pandaq.pandamvp.utils.Test;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by huxinyu on 2018/2/22.
 * Email : panda.h@foxmail.com
 * Description : EventBus 工具类,发布事件和注册事件都由此工具类统一实现
 */

public class EventUtils {


    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unRegister(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static void postTestAction() {
        EventMsg eventMsg = new EventMsg();
        eventMsg.setTargetName(Test.class.getName());
        eventMsg.setAction(EventAction.TEST_ACTION);
        EventBus.getDefault().post(eventMsg);
    }
}
