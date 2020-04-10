package com.pandaq.uires.popupwindows.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.pandaq.uires.R;
import com.pandaq.uires.popupwindows.ItemData;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Created by huxinyu on 2018/6/13.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :选择列表 Adapter 数据源为 String
 */
public class ListSelectAdapter extends AbsPopupSelectAdapter<ItemData, BaseViewHolder> {

    public ListSelectAdapter(@Nullable List<ItemData> data) {
        super(R.layout.res_item_list_select_popup, data);
        addChildClickViewIds(R.id.ll_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemData item) {
        TextView textView = helper.getView(R.id.tv_item);
        ImageView icon = helper.getView(R.id.iv_item_icon);
        textView.setText(item.getTitleStr());
        if (item.isChecked()) {
            textView.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        } else {
            textView.setTextColor(getContext().getResources().getColor(R.color.res_colorTextMain));
        }
        if (item.getDrawableRes() > 0) {
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(item.getDrawableRes());
        } else {
            icon.setVisibility(View.GONE);
        }
    }

}
