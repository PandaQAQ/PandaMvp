
package com.pandaq.appcore.framework.app.lifecycle;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.pandaq.appcore.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by huxinyu on 2018/12/25.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :manifest scanner,find meta class witch value is "AppInjector"
 */
public final class ManifestParser {
    private String moduleValue;

    private final Context context;

    public ManifestParser(Context context) {
        this.context = context;
        moduleValue = context.getString(R.string.lifecycle_proxy);
    }

    public List<ILifecycleInjector> parse() {
        List<ILifecycleInjector> modules = new ArrayList<>();
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                for (String key : appInfo.metaData.keySet()) {
                    if (moduleValue.equals(appInfo.metaData.get(key))) {
                        modules.add(parseModule(key));
                    }
                }
            }
            // 根据 priority 对扫描结果顺序进行调整，确定先初始化什么后初始化什么
            Collections.sort(modules, (o1, o2) -> o1.priority() - o2.priority());
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Unable to find metadata to parse ConfigModule", e);
        }
        return modules;
    }

    private static ILifecycleInjector parseModule(String className) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to find ConfigModule implementation", e);
        }

        Object module;
        try {
            module = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Unable to instantiate ConfigModule implementation for " + clazz, e);
        }

        if (!(module instanceof ILifecycleInjector)) {
            throw new RuntimeException("Expected instanceof ConfigModule, but found: " + module);
        }
        return (ILifecycleInjector) module;
    }
}