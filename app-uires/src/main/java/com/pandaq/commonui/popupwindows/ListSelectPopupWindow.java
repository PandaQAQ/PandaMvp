package com.pandaq.commonui.popupwindows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pandaq.commonui.R;
import com.pandaq.commonui.popupwindows.adapters.ListSelectAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by huxinyu on 2018/1/3.
 * Email : huxinyu@gouuse.cn
 * Description : 列表单选 PopupWindow
 */

public class ListSelectPopupWindow extends PopupWindow implements BaseQuickAdapter.OnItemChildClickListener {

    private RecyclerView dataList;

    private List<String> itemDatas;

    private Context mContext;

    private ListSelectAdapter mAdapter;

    private OnItemClickListener mItemClickListener;

    public ListSelectPopupWindow(Context context, int width, int height) {
        super(width, height);
        mContext = context;
        init();
    }

    public void setItemData(ArrayList<String> itemDatas, int index) {
        this.itemDatas = itemDatas;
        if (mAdapter != null) {
            mAdapter.setNewData(itemDatas);
        } else {
            mAdapter = new ListSelectAdapter(this.itemDatas);
            mAdapter.setOnItemChildClickListener(this);
        }
        if (index >= 0) {
            mAdapter.setSelected(itemDatas.get(index));
        } else {
            mAdapter.setSelected(itemDatas.get(0));
        }
        dataList.setLayoutManager(new LinearLayoutManager(this.mContext));
        DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
        divider.setDrawable(mContext.getResources().getDrawable(R.drawable.public_dividing_drawable));
        dataList.addItemDecoration(divider);
        dataList.setAdapter(mAdapter);
    }

    private void init() {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.public_popup_list_select, null);
        setContentView(contentView);
        dataList = contentView.findViewById(R.id.lv_item);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mAdapter.setSelected(itemDatas.get(position));
        if (mItemClickListener != null) {
            mItemClickListener.itemClicked(position, itemDatas.get(position));
        }
    }

    public interface OnItemClickListener {
        void itemClicked(int position, String itemString);
    }

    public void addOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }
}
