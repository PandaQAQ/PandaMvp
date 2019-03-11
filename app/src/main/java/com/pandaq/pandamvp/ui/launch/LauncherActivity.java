package com.pandaq.pandamvp.ui.launch;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;

import com.pandaq.appcore.eventbus.EventUtils;
import com.pandaq.appcore.permission.RtPermission;
import com.pandaq.appcore.utils.log.Logger;
import com.pandaq.commonui.msgwindow.Snacker;
import com.pandaq.commonui.msgwindow.ToastIconGravity;
import com.pandaq.commonui.msgwindow.Toaster;
import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.events.LaunchEvent;
import com.pandaq.pandamvp.framework.AppBaseActivity;
import com.pandaq.pandamvp.ui.home.HomeActivity;

import org.greenrobot.eventbus.Subscribe;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 启动页面 Activity
 */
public class LauncherActivity extends AppBaseActivity<LauncherPresenter> implements LauncherContract.View {

    @BindView(R.id.ll_parent)
    LinearLayout mLlParent;

    @Override
    protected LauncherPresenter injectPresenter() {
        return new LauncherPresenter(this);
    }

    @Override
    protected void initVariable() {
        EventUtils.getDefault().register(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindContentRes() {
        return R.layout.app_activity_launcher;
    }

    @Override
    protected void loadData() {
//        mPresenter.showErrorMsg();
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                RtPermission.with(this)
                        .runtime(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .rationale((context, permissions, executor) ->
                                executor.execute())
                        .onGranted(permissions -> {

                        })
                        .onDenied(permissions ->
                                Toaster.with(LauncherActivity.this)
                                        .msg("权限被拒绝！")
                                        .show())
                        .onAlwaysDenied(permissions ->
                                Toaster.with(LauncherActivity.this)
                                        .msg("权限被拒绝,且不再提醒！")
                                        .show())
                        .start();
                break;
            case R.id.btn2:
                Intent intent = new Intent(this, HomeActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn3:
                Snacker.with(mLlParent)
                        .msg("Default")
                        .action("Action")
                        .actionListener(v -> Toaster.with(LauncherActivity.this)
                                .msg("点击了 Action")
                                .show()
                        )
                        .show();
                break;
            case R.id.btn4:
                RtPermission.with(this)
                        .install(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) + "/app-common-release.apk")
                        .onDenied(permissions ->
                                Toaster.with(LauncherActivity.this)
                                        .msg("无安装应用权限")
                                        .show())
                        .start();
                break;
            case R.id.btn5:
                mPresenter.showErrorMsg();
                break;
        }
    }

    @Subscribe
    public void handleEvent(LaunchEvent event) {
        Toaster.with(this)
                .icon(R.drawable.ic_launcher_background, ToastIconGravity.END)
                .msg("LaunchEvent")
                .show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate--->Launch");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d("onStart-->Launch");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("onResume--->Launch");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d("onPause--->Launch");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.d("onStop--->Launch");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.d("onDestroy-->Launch");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.d("onReStart-->Launch");
    }
}
