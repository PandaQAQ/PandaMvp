package com.pandaq.pandamvp.ui.launch;

import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.pandaq.appcore.eventbus.EventUtils;
import com.pandaq.appcore.permission.RtPermission;
import com.pandaq.appcore.utils.logutils.PLogger;
import com.pandaq.commonui.msgwindow.Snacker;
import com.pandaq.commonui.msgwindow.ToastIconGravity;
import com.pandaq.commonui.msgwindow.Toaster;
import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.events.LaunchEvent;
import com.pandaq.pandamvp.framework.basemvp.BaseMvpActivity;
import com.pandaq.pandamvp.ui.home.HomeActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 启动页面 Activity
 */
public class LauncherActivity extends BaseMvpActivity<LauncherPresenter> implements LauncherContract.View {

    @BindView(R.id.ll_parent)
    LinearLayout mLlParent;

    @Override
    protected LauncherPresenter injectPresenter() {
        return new LauncherPresenter(this);
    }

    @Override
    protected void initVariable() {
        super.initVariable();
        swipeEnable = false;
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

    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    @Override
    public void onError(int errCode, String errMsg) {
        super.onError(errCode, errMsg);
    }

    @Override
    public void onLoadFinish() {
        super.onLoadFinish();
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Snacker.with(mLlParent)
                        .duration(Snackbar.LENGTH_LONG)
                        .msg("SnackMsg")
                        .msgColor(Color.RED)
//                        .msgFont(DisplayUtils.sp2px(this, 20))
                        .action("Action")
                        .actionListener(v -> Toaster.with(LauncherActivity.this)
                                .msg("点击了 Action")
                                .show()
                        )
                        .actionColor(Color.YELLOW)
                        .backgroundColor(Color.GREEN)
                        .show();
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
                        .install()
                        .file(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) + "/app-common-release.apk")
                        .onDenied(permissions ->
                                Toaster.with(LauncherActivity.this)
                                        .msg("无安装应用权限")
                                        .show())
                        .start();
                break;
            case R.id.btn5:
                PLogger.w("我是打印内容啊");
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
    protected void onDestroy() {
        super.onDestroy();
        EventUtils.getDefault().unregister(this);
    }
}
