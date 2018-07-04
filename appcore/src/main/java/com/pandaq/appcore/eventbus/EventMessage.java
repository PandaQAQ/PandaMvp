package com.pandaq.appcore.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by huxinyu on 2018/5/24.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :EventBus传递数据类
 */
public class EventMessage {
    /**
     * targetClass recommend the targetClass Names
     * 可同时对多个目标发送事件
     */
    private Set<String> targetsName;
    private String message;
    private Bundle dataBundle;
    private int action;

    /**
     * 获取目标类集合
     *
     * @return 目标类
     */
    public Set<String> getTargetsName() {
        return targetsName;
    }

    /**
     * 设置接收此事件的目标类 SimpleName
     *
     * @param targetsName 目标类的 SimpleName
     */
    public void addTargetsName(String... targetsName) {
        if (targetsName == null) {
            return;
        }
        if (this.targetsName == null) {
            this.targetsName = new HashSet<>();
        }
        Collections.addAll(this.targetsName, targetsName);
    }

    /**
     * 获取附加消息内容（非必须）
     *
     * @return 消息内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置附加 String message（非必须）
     *
     * @param message 消息内容
     */
    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    /**
     * 获取附加 Bundle 数据（非必须）
     *
     * @return Bundle
     */
    public Bundle getDataBundle() {
        return dataBundle;
    }

    /**
     * 设置附加 Bundle 数据（非必须）
     *
     * @param dataBundle bundle 数据
     */
    public void setDataBundle(@Nullable Bundle dataBundle) {
        this.dataBundle = dataBundle;
    }

    /**
     * 获取事件对应 Action(必要)
     * 用于接收端判断事件类型进行相应处理
     *
     * @return 事件 ation 事件类型 {@link EventAction}
     */
    public int getAction() {
        return action;
    }

    /**
     * 获取事件对应 Action(必要)
     * 用于接收端判断事件类型进行相应处理
     *
     * @param action 事件类型 {@link EventAction}
     */
    public void setAction(int action) {
        this.action = action;
    }
}
