package com.pandaq.uires.widget.recyclerview;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandaq.uires.R;
import com.pandaq.uires.widget.recyclerview.loadfooter.PandaFooter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.h@foxmail.com
 * Description :recyclerview 嵌套在 smartrefreshlayout 内的 刷新布局
 */
public class RefreshRecyclerView extends FrameLayout {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    public RefreshRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public RefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.res_refresh_recyclerview, this);
        mRefreshLayout = findViewById(R.id.srl_refresh);
        mRecyclerView = findViewById(R.id.rv_list);

        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeColors(
                getResources().getColor(R.color.res_color_refresh_header1),
                getResources().getColor(R.color.res_color_refresh_header2),
                getResources().getColor(R.color.res_color_refresh_header3));
        mRefreshLayout.setRefreshHeader(header);
        PandaFooter footer = new PandaFooter(getContext());
        mRefreshLayout.setRefreshFooter(footer);
        //禁用 smartrefreshlayout 的自动加载，交给外部监听处理
        mRefreshLayout.setEnableAutoLoadMore(false);
    }

    public void setOnRefreshListener(OnRefreshListener refreshListener) {
        mRefreshLayout.setOnRefreshListener(refreshListener);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        mRefreshLayout.setOnLoadMoreListener(loadMoreListener);
    }

    public void setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener refreshLoadMoreListener) {
        mRefreshLayout.setOnRefreshLoadMoreListener(refreshLoadMoreListener);
    }

    /**
     * after refresh finish the EmptyView will be visiable
     */
    public void finishRefresh(boolean isError) {
        mRefreshLayout.finishRefresh(500);
    }

    public void autoRefresh() {
        mRefreshLayout.autoRefresh();
    }

    public void setRefreshHeader(RefreshHeader header) {
        mRefreshLayout.setRefreshHeader(header);
    }

    public void setRefreshFooter(RefreshFooter footer) {
        mRefreshLayout.setRefreshFooter(footer);
    }

    public void setPrimaryColorsId(@ColorRes int... primaryColorId) {
        mRefreshLayout.setPrimaryColorsId(primaryColorId);
    }

    public void finishLoadMore(boolean noMore) {
        mRefreshLayout.finishLoadMore(500, true, noMore);
    }

    public SmartRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void setItemAnimator(@Nullable RecyclerView.ItemAnimator animator) {
        mRecyclerView.setItemAnimator(animator);
    }

    public void setAdapter(@NonNull BaseQuickAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration, int index) {
        mRecyclerView.addItemDecoration(itemDecoration, index);
    }

    public void setRefreshLayout(SmartRefreshLayout refreshLayout) {
        mRefreshLayout = refreshLayout;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    public void setEnableLoadMore(boolean enable) {
        mRefreshLayout.setEnableLoadMore(enable);
    }

}
