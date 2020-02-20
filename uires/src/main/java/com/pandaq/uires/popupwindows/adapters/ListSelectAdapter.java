package com.pandaq.uires.popupwindows.adapters;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pandaq.uires.R;
import com.pandaq.uires.popupwindows.ItemData;

import java.util.List;

import static com.pandaq.appcore.utils.system.AppUtils.getContext;

/**
 * Created by huxinyu on 2018/6/13.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :选择列表 Adapter 数据源为 String
 */
public class ListSelectAdapter extends BaseQuickAdapter<ItemData, BaseViewHolder> {

    private int lastCheck = -1;

    public ListSelectAdapter(@Nullable List<ItemData> data) {
        super(R.layout.res_item_list_select_popup, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemData item) {
        TextView textView = helper.getView(R.id.tv_item);
        textView.setText(item.getKey());
        if (item.isChecked()) {
            textView.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        } else {
            textView.setTextColor(getContext().getResources().getColor(R.color.res_colorTextMain));
        }

    }

    public void setSelected(int index) {
        if (lastCheck != -1) {
            getData().get(lastCheck).setChecked(false);
            this.notifyItemChanged(lastCheck);
        }
        getData().get(index).setChecked(true);
        this.notifyItemChanged(index);
        lastCheck = index;
    }
}
