package com.pandaq.appcore.eventbus;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :EventBus 事件 Action 代码，每一个事件对应一个唯一代码
 * 方便将事件的发送和监听关联，方便编辑器内查找关联
 */
public class EventAction {

    // 0-999 为预留，禁止占用,module 中使用 Action 从 1000开始

    /**
     * 关闭所有 Activity 的 Event事件，在 BaseActivity 中统一接收处理
     */
    public static int CLOSE_ALL_ACTIVITY = 0;

    // 100 - 105 为支付相关
    /**
     * 支付成功
     */
    public static int PAY_SUCCESS = 100;
    /**
     * 支付失败
     */
    public static int PAY_FAIL = 101;
    /**
     * 支付取消
     */
    public static int PAY_CANCEL = 102;
}
