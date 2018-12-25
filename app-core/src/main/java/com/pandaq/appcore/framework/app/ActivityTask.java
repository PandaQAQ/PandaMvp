package com.pandaq.appcore.framework.app;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by huxinyu on 2018/4/24.
 * Email : panda.h@foxmail.com
 * Description :
 */
public class ActivityTask {

    /**
     * Actvity 存储栈
     */
    private static Stack<Activity> mActivityStack;

    /**
     * ActivityTask 单例对象
     */
    private static ActivityTask mAppManager;

    private ActivityTask() {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
    }

    public static ActivityTask getInstance() {
        if (mAppManager == null) {
            mAppManager = new ActivityTask();
        }
        return mAppManager;
    }

    /**
     * 添加Activity到堆栈
     *
     * @param activity 入栈的 activity
     */
    public void addActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    /**
     * 从栈里删除activity
     *
     * @param activity 被删除的 activity
     */
    public void remove(Activity activity) {
        if (activity != null && mActivityStack != null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        return mActivityStack.lastElement();
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        mActivityStack.remove(mActivityStack.lastElement());
        mActivityStack.lastElement().finish();
    }

    /**
     * 结束指定 Activity
     */
    public void killActivity(Activity activity) {
        mActivityStack.remove(activity);
        activity.finish();
    }

    /**
     * 结束指定类名的Activity
     */
    public synchronized void killActivity(Class<?>... calsses) {
        if (mActivityStack == null || mActivityStack.isEmpty())
            return;
        List<Activity> activities = new ArrayList<>();
        for (Class cls : calsses) {
            for (Activity activity : mActivityStack) {
                if (activity.getClass().equals(cls)) {
                    activities.add(activity);
                }
            }
        }
        for (Activity activity : activities) {
            killActivity(activity);
        }

    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        if (mActivityStack == null || mActivityStack.isEmpty()) {
            return;
        }
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 结束除了当前的其他所有Activity
     *
     * @param activity 保留的 Activity
     */
    public void killOthersActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i) && activity != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
        mActivityStack.add(activity);
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            killAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
