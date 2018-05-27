package com.pandaq.pandacore.http.transformer;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huxinyu on 2018/5/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :线程转换器，网络请求 subscribeOn io ，observeOn AndroidMain
 */
public class RxScheduler {

    /**
     * Observable 类型线程转换器
     *
     * @param <T> 数据类型
     * @return 指定了在 io 线程执行，UI 线程观察结果的观察对象
     */
    public static <T> ObservableTransformer<T, T> observableSync() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Flowable 类型线程转换器
     *
     * @param <T> 数据类型
     * @return 指定了在 io 线程执行，UI 线程观察结果的观察对象
     */
    public static <T> FlowableTransformer<T, T> flowableSycn() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
