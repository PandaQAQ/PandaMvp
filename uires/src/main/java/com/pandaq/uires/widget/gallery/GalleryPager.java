package com.pandaq.uires.widget.gallery;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.pandaq.appcore.utils.log.PLogger;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by huxinyu on 2019/4/3.
 * Email : panda.h@foxmail.com
 * Description :画廊 ViewPager
 */
public class GalleryPager extends ViewPager implements LifecycleObserver {

    private boolean canRecycle = false;
    private GalleryPageAdapter mPageAdapter;
    private int currentPosition = 0;

    public GalleryPager(@NonNull Context context) {
        super(context);
        init();
    }

    public GalleryPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public boolean isCanRecycle() {
        return canRecycle;
    }

    public void setCanRecycle(boolean canRecycle) {
        this.canRecycle = canRecycle;
    }

    private void init() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedScroller scroller = new FixedScroller(getContext());
            scroller.setmDuration(1000);
            field.set(this, scroller);
            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Observable.interval(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (shouldContinue.get()) {
                            currentPosition++;
                            setCurrentItem(currentPosition, true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initAfterSetData() {
        if (mPageAdapter == null || mPageAdapter.getPages() == null) {
            throw new RuntimeException("make sure adapter.getPages() is not null");
        }
        setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0) {
                    currentPosition = position;
                    PLogger.d("currentPosition:: "+currentPosition);
                    if (!canRecycle) return; // 不需要循环则不做处理
                    if (currentPosition == mPageAdapter.getPages().size() - 2) {
                        // 倒数第二页，页面重置为第0页
                        setCurrentItem(2, false);
                    } else if (currentPosition == 1) {
                        // 第一页后页面重置为最后页
                        setCurrentItem(mPageAdapter.getPages().size() - 3, false);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false && super.onInterceptTouchEvent(ev);
    }


    public void setGalleryAdapter(GalleryPageAdapter adapter) {
        mPageAdapter = adapter;
        setAdapter(mPageAdapter);
        initAfterSetData();
    }

    public void startPlay() {
        shouldContinue.set(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        shouldContinue.set(true);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        shouldContinue.set(false);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void stopPlay() {
        shouldContinue.set(false);
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    private Disposable mDisposable;
    AtomicBoolean shouldContinue = new AtomicBoolean(false);

}
