package com.pandaq.pandamvp.ui.home;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pandaq.appcore.eventbus.EventUtils;
import com.pandaq.appcore.utils.log.PLogger;
import com.pandaq.commonui.msgwindow.ToastIconGravity;
import com.pandaq.commonui.msgwindow.Toaster;
import com.pandaq.commonui.widget.recyclerview.RefreshRecyclerView;
import com.pandaq.pandamvp.R;
import com.pandaq.pandamvp.events.HomeEvent;
import com.pandaq.pandamvp.framework.AppBaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * By Template Create
 * <p>
 * Description :
 */

public class HomeActivity extends AppBaseActivity<HomePresenter> implements HomeContract.View {

    @BindView(R.id.cl_container)
    ConstraintLayout mClContainer;
    @BindView(R.id.refreshList)
    RefreshRecyclerView mRefreshList;

    @Override
    public HomePresenter injectPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initVariable() {
        EventUtils.getDefault().register(this);
    }

    @Override
    protected void initView() {
        mRefreshList.setLayoutManager(new LinearLayoutManager(this));
        mRefreshList.setAdapter(mBaseQuickAdapter);
        mRefreshList.setOnRefreshListener(refreshLayout -> {
            mClContainer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<String> testData = new ArrayList<>();
                    testData.add("数据1");
                    testData.add("数据2");
                    testData.add("数据3");
                    testData.add("数据4");
                    testData.add("数据5");
                    mBaseQuickAdapter.setNewData(testData);
                    mRefreshList.finishRefresh(false);
                }
            }, 4000);
        });
    }

    @Override
    protected int bindContentRes() {
        return R.layout.app_activity_home;
    }

    @Override
    protected void loadData() {

    }


    @Subscribe
    public void handleEvent(HomeEvent event) {
        Toaster.with(this)
                .icon(R.drawable.ic_launcher_background, ToastIconGravity.END)
                .msg("Home")
                .show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PLogger.d("onCreate--->Home");
    }

    private BaseQuickAdapter mBaseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.res_item_list_select_popup) {
        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_item, item);
        }
    };

}

