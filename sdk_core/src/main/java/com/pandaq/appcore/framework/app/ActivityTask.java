package com.pandaq.appcore.framework.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import com.pandaq.appcore.utils.system.AppUtils;

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
     * 判断指定activity是否存在
     */
    public boolean isExistSpecifiedActivity(Class<?> cls) {
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i) && mActivityStack.get(i).getClass() == cls) {
                return true;
            }
        }

        return false;
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
    public Activity currentActivity() {
        if (mActivityStack == null || mActivityStack.empty()) {
            return null;
        }
        return mActivityStack.lastElement();
    }

    /**
     * 获取前一个Activity（堆栈中最后一个压入的）
     */
    public Activity getSecondLastActivity() {
        if (mActivityStack == null || mActivityStack.size() < 2) {
            return null;
        }
        return mActivityStack.get(mActivityStack.size() - 2);
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        mActivityStack.lastElement().finish();
    }

    /**
     * 结束指定 BaseActivity
     */
    public void killActivity(Activity activity) {
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
     * 结束指定类名的Activity
     */
    public void finishActivityWithName(String str) {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            if (null != activity && activity.getClass().getSimpleName().startsWith(str)) {
                mActivityStack.remove(i);
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killOtherWithName(String str) {
        if (str == null) {
            return;
        }
        Activity keepActivity = null;
        for (int i = 0, size = mActivityStack.size(); i < size; i++) {
            if (null != mActivityStack.get(i) && !str.equals(mActivityStack.get(i).getClass().getSimpleName())) {
                mActivityStack.get(i).finish();
            } else {
                keepActivity = mActivityStack.get(i);
            }
        }
        mActivityStack.clear();
        mActivityStack.add(keepActivity);
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
     * 退出应用程序
     */
    @SuppressWarnings("deprecation")
    public void appExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * 结束除了当前的其他所有Activity
     *
     * @param activity 保留的 BaseActivity
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
     * 结束除了当前的其他所有Activity
     *
     * @param activityClass 保留的 Activity 类名
     */
    public void killOthersActivity(Class<?> activityClass) {
        if (activityClass == null) {
            return;
        }
        Activity keep = null;
        for (Activity activity : mActivityStack) {
            if (!activity.getClass().equals(activityClass)) {
                activity.finish();
            } else {
                keep = activity;
            }
        }
        mActivityStack.clear();
        // 将保留的 activity 重新入栈
        mActivityStack.add(keep);
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            killAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
