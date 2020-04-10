package com.pandaq.uires.widget.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by huxinyu on 2019/3/25.
 * Email : panda.h@foxmail.com
 * Description :recyclerview 嵌套在 smartrefreshlayout 内的 刷新布局
 */
public class RefreshRecyclerView extends FrameLayout {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private boolean showHolderView; // 是否在加载失败后显示占位视图
    private View emptyView;
    private View errorView;

    private BaseQuickAdapter mQuickAdapter;

    public RefreshRecyclerView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public RefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RefreshRecyclerView);
        showHolderView = ta.getBoolean(R.styleable.RefreshRecyclerView_showHolder, true);
        int emptyLayout = ta.getResourceId(R.styleable.RefreshRecyclerView_emptyLayout, R.layout.res_empty_view);
        int errorLayout = ta.getResourceId(R.styleable.RefreshRecyclerView_emptyLayout, R.layout.res_error_view);
        ta.recycle();
        inflate(getContext(), R.layout.res_refresh_recyclerview, this);
        mRefreshLayout = findViewById(R.id.srl_refresh);
        mRecyclerView = findViewById(R.id.rv_recycle_list);

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

        emptyView = LayoutInflater.from(getContext()).inflate(emptyLayout, null);
        errorView = LayoutInflater.from(getContext()).inflate(errorLayout, null);
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
    public void finishRefresh(boolean success) {
        mRefreshLayout.finishRefresh(500);
        if (this.showHolderView && mQuickAdapter != null) {
            if (success) {
                mQuickAdapter.setEmptyView(emptyView);
            } else {
                mQuickAdapter.setEmptyView(errorView);
            }
        }
    }

    @Nullable
    public View getEmptyView() {
        return emptyView;
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
        if (noMore) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            mRefreshLayout.finishLoadMore(500);
        }
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

    public void setAdapter(@NonNull BaseQuickAdapter adapter, boolean showEmpty) {
        this.showHolderView = showEmpty;
        mQuickAdapter = adapter;
        mRecyclerView.setAdapter(mQuickAdapter);
    }


    public void setAdapter(@NonNull BaseQuickAdapter adapter) {
        setAdapter(adapter, true);
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

    public void setCanRefresh(boolean enable) {
        mRefreshLayout.setEnableRefresh(enable);
    }

}
