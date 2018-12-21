
package com.pandaq.appcore.permission;

import android.app.Activity;
import android.content.Context;

import com.pandaq.appcore.permission.runtime.RuntimeRequest;
import com.pandaq.appcore.permission.source.ActivitySource;
import com.pandaq.appcore.permission.source.AndroidxFragmentSource;
import com.pandaq.appcore.permission.source.ContextSource;
import com.pandaq.appcore.permission.source.FragmentSource;
import com.pandaq.appcore.permission.source.Source;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * Created by huxinyu on 2018/12/18.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :enter class for request runtime permission
 */

public class RtPermission {

    private static final RequestBuilder builder = new RequestBuilder();

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param activity          {@link Activity}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull Activity activity,
            @NonNull List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(new ActivitySource(activity), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment          {@link Fragment}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull Fragment fragment,
            @NonNull List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(new AndroidxFragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment          {@link android.app.Fragment}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull android.app.Fragment fragment,
            @NonNull List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(new FragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param context           {@link Context}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull Context context,
            @NonNull List<String> deniedPermissions) {
        return hasAlwaysDeniedPermission(new ContextSource(context), deniedPermissions);
    }

    /**
     * Has always been denied permission.
     */
    private static boolean hasAlwaysDeniedPermission(
            @NonNull Source source,
            @NonNull List<String> deniedPermissions) {
        for (String permission : deniedPermissions) {
            if (!source.isShowRationalePermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param activity          {@link Activity}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull Activity activity,
            @NonNull String... deniedPermissions) {
        return hasAlwaysDeniedPermission(new ActivitySource(activity), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment          {@link Fragment}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull Fragment fragment,
            @NonNull String... deniedPermissions) {
        return hasAlwaysDeniedPermission(new AndroidxFragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param fragment          {@link android.app.Fragment}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull android.app.Fragment fragment,
            @NonNull String... deniedPermissions) {
        return hasAlwaysDeniedPermission(new FragmentSource(fragment), deniedPermissions);
    }

    /**
     * Some privileges permanently disabled, may need to set up in the execute.
     *
     * @param context           {@link Context}.
     * @param deniedPermissions one or more permissions.
     * @return true, other wise is false.
     */
    public static boolean hasAlwaysDeniedPermission(
            @NonNull Context context,
            @NonNull String... deniedPermissions) {
        return hasAlwaysDeniedPermission(new ContextSource(context), deniedPermissions);
    }

    /**
     * Has always been denied permission.
     */
    private static boolean hasAlwaysDeniedPermission(
            @NonNull Source source,
            @NonNull String... deniedPermissions) {
        for (String permission : deniedPermissions) {
            if (!source.isShowRationalePermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create a service that opens the permission setting page.
     *
     * @param activity {@link Activity}.
     * @return {@link ISettingAction}.
     */
    @NonNull
    public static ISettingAction permissionSetting(@NonNull Activity activity) {
        return new SettingActionImp(new ActivitySource(activity));
    }

    /**
     * Create a service that opens the permission setting page.
     *
     * @param fragment {@link Fragment}.
     * @return {@link ISettingAction}.
     */
    @NonNull
    public static ISettingAction permissionSetting(@NonNull Fragment fragment) {
        return new SettingActionImp(new AndroidxFragmentSource(fragment));
    }

    /**
     * Create a service that opens the permission setting page.
     *
     * @param fragment {@link android.app.Fragment}.
     * @return {@link ISettingAction}.
     */
    @NonNull
    public static ISettingAction permissionSetting(@NonNull android.app.Fragment fragment) {
        return new SettingActionImp(new FragmentSource(fragment));
    }

    /**
     * Create a service that opens the permission setting page.
     *
     * @param context {@link android.app.Fragment}.
     * @return {@link ISettingAction}.
     */
    @NonNull
    public static ISettingAction permissionSetting(@NonNull Context context) {
        return new SettingActionImp(new ContextSource(context));
    }

    /**
     * With Activity.
     *
     * @param activity {@link Activity}.
     * @return {@link RuntimeRequest}.
     */
    @NonNull
    public static RequestBuilder with(@NonNull Activity activity) {
        return builder.source(new ActivitySource(activity));
    }

    /**
     * With androidx.Fragment.
     *
     * @param fragment {@link Fragment}.
     * @return {@link RuntimeRequest}.
     */
    @NonNull
    public static RequestBuilder with(@NonNull Fragment fragment) {
        return builder.source(new AndroidxFragmentSource(fragment));
    }

    /**
     * With android.app.Fragment.
     *
     * @param fragment {@link android.app.Fragment}.
     * @return {@link RuntimeRequest}.
     */
    @NonNull
    public static RequestBuilder with(@NonNull android.app.Fragment fragment) {
        return builder.source(new FragmentSource(fragment));
    }

    /**
     * With context.
     *
     * @param context {@link Context}.
     * @return {@link RuntimeRequest}.
     */
    @NonNull
    public static RequestBuilder with(@NonNull Context context) {
        return builder.source(new ContextSource(context));
    }

    private RtPermission() {
    }

}