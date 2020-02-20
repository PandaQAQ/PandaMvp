
package com.pandaq.appcore.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


/**
 * Created by huxinyu on 2018/12/20.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 申请权限的 BaseActivity 透明界面
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public final class PermissionActivity extends Activity {
    private static final String KEY_INPUT_OPERATION = "KEY_INPUT_OPERATION";
    private static final String KEY_INPUT_PERMISSIONS = "KEY_INPUT_PERMISSIONS";
    private static final int VALUE_INPUT_PERMISSIONS = 0;
    private static final int VALUE_INPUT_INSTALL = 1;

    private static PermissionListener sPermissionListener;
    private static RequestListener sRequestListener;

    /**
     * RuntimeRequest for permissions.
     */
    public static void requestPermission(Context context, String[] permissions, PermissionListener listener) {
        sPermissionListener = listener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(KEY_INPUT_PERMISSIONS, permissions);
        intent.putExtra(KEY_INPUT_OPERATION, VALUE_INPUT_PERMISSIONS);
        context.startActivity(intent);
    }

    /**
     * Request for package install.
     */
    public static void requestInstall(Context context, RequestListener requestListener) {
        PermissionActivity.sRequestListener = requestListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(KEY_INPUT_OPERATION, VALUE_INPUT_INSTALL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invasionStatusBar(this);

        Intent intent = getIntent();
        int operation = intent.getIntExtra(KEY_INPUT_OPERATION, 0);
        switch (operation) {
            case VALUE_INPUT_PERMISSIONS:
                String[] permissions = intent.getStringArrayExtra(KEY_INPUT_PERMISSIONS);
                if (permissions != null && sPermissionListener != null) {
                    requestPermissions(permissions, VALUE_INPUT_PERMISSIONS);
                } else {
                    finish();
                }
                break;
            case VALUE_INPUT_INSTALL:
                if (sRequestListener != null) {
                    Intent manageIntent;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        manageIntent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                        manageIntent.setData(Uri.fromParts("package", getPackageName(), null));
                        startActivityForResult(manageIntent, VALUE_INPUT_INSTALL);
                    }
                } else {
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (sPermissionListener != null)
            sPermissionListener.onRequestPermissionsResult(permissions);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (sRequestListener != null) {
            sRequestListener.onRequestCallback();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sPermissionListener = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * permission callback.
     */
    public interface PermissionListener {
        void onRequestPermissionsResult(@NonNull String[] permissions);
    }

    /**
     * permission callback.
     */
    public interface RequestListener {
        void onRequestCallback();
    }

    /**
     * Set the content layout full the StatusBar, but do not hide StatusBar.
     */
    private static void invasionStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility()
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
