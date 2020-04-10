package com.pandaq.uires.popupwindows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pandaq.uires.R;
import com.pandaq.uires.popupwindows.adapters.AbsPopupSelectAdapter;
import com.pandaq.uires.popupwindows.adapters.ListSelectAdapter;

import java.util.ArrayList;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by huxinyu on 2018/1/3.
 * Email : huxinyu@gouuse.cn
 * Description : 列表单选 PopupWindow
 */

public class ListSelectPopupWindow extends PopupWindow implements OnItemChildClickListener {

    private RecyclerView dataList;

    private ArrayList<ItemData> itemData;

    private Context mContext;

    private AbsPopupSelectAdapter<ItemData, BaseViewHolder> mAdapter;

    private OnItemClickListener mItemClickListener;

    public ListSelectPopupWindow(Context context, int width, int height) {
        super(width, height);
        mContext = context;
        init();
    }

    /**
     * 设置数据
     *
     * @param itemData 数据源
     */
    public void setItemData(ArrayList<ItemData> itemData) {
        setItemData(itemData, 0);
    }

    /**
     * 设置数据
     */
    public void setItemData(AbsPopupSelectAdapter<ItemData, BaseViewHolder> adapter, RecyclerView.LayoutManager manager) {
        setItemData(0, adapter, manager);
    }

    /**
     * 设置数据
     *
     * @param itemData 数据源
     * @param index    默认选中 index
     */
    public void setItemData(ArrayList<ItemData> itemData, int index) {
        this.itemData = itemData;
        if (mAdapter != null) {
            mAdapter.setNewData(itemData);
        } else {
            mAdapter = new ListSelectAdapter(this.itemData);
            mAdapter.setOnItemChildClickListener(this);
        }
        dataList.setLayoutManager(new LinearLayoutManager(this.mContext));
        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        divider.setDrawable(mContext.getResources().getDrawable(R.drawable.res_dividing_drawable));
        dataList.addItemDecoration(divider);
        dataList.setAdapter(mAdapter);
        if (!mAdapter.getData().isEmpty()) {
            mAdapter.setSelected(Math.max(index, 0));
        }
    }

    /**
     * 设置数据
     *
     * @param index 默认选中 index
     */
    public void setItemData(int index, AbsPopupSelectAdapter<ItemData,
            BaseViewHolder> adapter, RecyclerView.LayoutManager manager) {
        this.itemData = (ArrayList<ItemData>) adapter.getData();
        mAdapter = adapter;
        mAdapter.setOnItemChildClickListener(this);
        dataList.setLayoutManager(manager);
        dataList.setAdapter(mAdapter);
        if (!mAdapter.getData().isEmpty()) {
            mAdapter.setSelected(Math.max(index, 0));
        }
    }

    private void init() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.res_popup_list_select, null);
        setContentView(contentView);
        setOutsideTouchable(true);
        dataList = contentView.findViewById(R.id.lv_item);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mAdapter.setSelected(position);
        if (mItemClickListener != null) {
            mItemClickListener.itemClicked(position, itemData.get(position));
        }
    }

    public interface OnItemClickListener {
        void itemClicked(int position, ItemData item);
    }

    public void addOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
