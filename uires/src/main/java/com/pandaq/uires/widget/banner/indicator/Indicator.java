package com.pandaq.uires.widget.banner.indicator;

import android.view.View;

import androidx.annotation.NonNull;

import com.pandaq.uires.widget.banner.config.IndicatorConfig;
import com.pandaq.uires.widget.banner.listener.OnPageChangeListener;

public interface Indicator extends OnPageChangeListener {
    @NonNull
    View getIndicatorView();

    IndicatorConfig getIndicatorConfig();

    void onPageChanged(int count, int currentPosition);

}
