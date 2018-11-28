package com.pandaq.appcore.imageloader.core;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.io.File;

/**
 * Created by huxinyu on 2018/6/27.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :图片下载器，封装与第三方图片加载框架剥离。
 * 切换图片加载框架时只需要新的框架加载器实现 IExecutor 接口并设置进来即可
 */
public class PicLoader {

    public static Requester with(@NonNull Context context) {
        return new Requester(context);
    }

    public static Requester with(@NonNull Activity activity) {
        return new Requester(activity);
    }

    public static Requester with(Fragment fragment) {
        return new Requester(fragment.getContext());
    }

    /**
     * 约束下载 url 必须先配置
     */
    public static class Requester {

        private Context context;

        public Requester(Context context) {
            this.context = context;
        }

        /**
         * 根据 url 地址加载
         *
         * @param url 图片 url
         * @return request
         */
        public Request.Builder load(@NonNull String url) {
            return new Request.Builder(context).load(url);
        }

        /**
         * 根据资源 id 加载
         *
         * @param res 图片资源 id
         * @return request
         */
        public Request.Builder load(int res) {
            return new Request.Builder(context).load(res);
        }

        /**
         * 根据资源 uri 加载
         *
         * @param uri 图片资源 uri
         * @return request
         */
        public Request.Builder load(Uri uri) {
            return new Request.Builder(context).load(uri);
        }

        /**
         * 根据资源 id 加载
         *
         * @param file 图片资源 file对象
         * @return request
         */
        public Request.Builder load(File file) {
            return new Request.Builder(context).load(file);
        }
    }

}
