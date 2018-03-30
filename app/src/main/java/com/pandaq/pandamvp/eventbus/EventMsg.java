package com.pandaq.pandamvp.eventbus;

import android.os.Bundle;

/**
 * Created by huxinyu on 2018/2/22.
 * Email : panda.h@foxmail.com
 * Description : 公共 EventBus post 事件类型，方便查找事件发出和接收点
 */

public class EventMsg {

    // 接收目标类 flag,用于判断该谁处理此消息
    private String targetName;
    // 事件类型(同一个类接收几种不同类型的消息，分别处理)
    private EventAction mAction;
    // 传递的消息内容
    private Bundle dataBundle;

    public String getTargetName() {
        return targetName;
    }

    /**
     * 推荐使用接收消息目标类的类名作为标识 XXX.class.getName();
     *
     * @param targetName 接收消息目标类名
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public EventAction getAction() {
        return mAction;
    }

    /**
     * 接收消息后对应的行为事件
     *
     * @param action 行为事件
     */
    public void setAction(EventAction action) {
        mAction = action;
    }

    public Bundle getDataBundle() {
        return dataBundle;
    }

    /**
     * 消息传递的数据内容
     *
     * @param dataBundle 数据内容
     */
    public void setDataBundle(Bundle dataBundle) {
        this.dataBundle = dataBundle;
    }
}
