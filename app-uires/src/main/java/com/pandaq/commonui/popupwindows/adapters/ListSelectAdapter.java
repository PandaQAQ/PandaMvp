package com.pandaq.commonui.popupwindows.adapters;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pandaq.commonui.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by huxinyu on 2018/6/13.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :
 */
public class ListSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private String selected;

    public ListSelectAdapter(@Nullable List<String> data) {
        super(R.layout.res_item_list_select_popup, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView textView = helper.getView(R.id.tv_item);
        textView.setText(item);
        if (selected.equals(item)) {
            textView.setTextColor(mContext.getResources().getColor(R.color.res_colorPrimary));
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.res_colorTextGray));
        }
        helper.addOnClickListener(R.id.tv_item);
    }

    public void setSelected(@NonNull String selected) {
        this.selected = selected;
        this.notifyDataSetChanged();
    }
}
