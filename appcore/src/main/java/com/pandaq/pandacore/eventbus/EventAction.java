package com.pandaq.pandacore.eventbus;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :EventBus 事件 Action 代码，每一个事件对应一个唯一代码
 * 方便将事件的发送和监听关联，方便编辑器内查找关联
 */
public interface EventAction {
    // 0-999 为预留，禁止占用,module 中使用 Action 从 1000开始
}
