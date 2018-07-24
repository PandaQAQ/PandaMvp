package com.pandaq.pandamvp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.pandaq.appcore.utils.msgwindow.SnackBarTool;
import com.pandaq.appcore.utils.msgwindow.ToastIconGravity;
import com.pandaq.appcore.utils.msgwindow.ToastTool;
import com.pandaq.pandamvp.framework.basemvp.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huxinyu on 2018/1/26.
 * Email : panda.h@foxmail.com
 * Description : 启动页面 Activity
 */
public class LauncherActivity extends BaseMvpActivity<LauncherPresenter> implements LauncherContract.View {



    @BindView(R.id.ll_parent)
    LinearLayout mLlParent;

    @Override
    public LauncherPresenter injectPresenter() {
        return new LauncherPresenter(this);
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int bindContentRes() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(int errCode, String errMsg) {

    }

    @Override
    public void onLoadFinish() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                SnackBarTool.with(mLlParent)
                        .duration(Snackbar.LENGTH_INDEFINITE)
                        .msg("SnackMsg")
                        .msgColor(Color.RED)
//                        .msgFont(DisplayUtils.sp2px(this, 20))
                        .action("Action")
                        .actionListener(v -> ToastTool.with(LauncherActivity.this)
                                .msg("点击了 Action")
                                .msgColor(Color.YELLOW)
                                .duration(Toast.LENGTH_LONG)
                                .backgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher_background))
                                .icon(getResources().getDrawable(R.mipmap.ic_launcher_round), ToastIconGravity.LEFT)
                                .backgroundColor(Color.CYAN)
                                .show()
                        )
                        .actionColor(Color.YELLOW)
                        .backgroundColor(Color.GREEN)
                        .show();
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
        }
    }
}
