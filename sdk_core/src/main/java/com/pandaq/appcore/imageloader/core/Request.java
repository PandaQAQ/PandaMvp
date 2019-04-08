package com.pandaq.appcore.imageloader.core;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.pandaq.appcore.imageloader.glide.GlideLoader;

import java.io.File;

/**
 * Created by huxinyu on 2018/7/4.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :图片加载请求类
 */
public class Request {

    private Context context;
    private IExecutor executor;

    private String url;
    private int res;
    private File file;
    private Uri uri;

    //对应图片显示的 Type
    private ScaleType mScaleType;
    //对应 override
    private int mWidth;
    private int mHeight;
    //对应 placeHolder
    private int holderRes;
    //对应 error;
    private int errorRes;
    //目标 image
    private ImageView targetView;
    //回调接口
    private ILoadCallBack mCallBack;

    Request(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setExecutor(IExecutor executor) {
        this.executor = executor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public ScaleType getScaleType() {
        return mScaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType == null) {
            //default type is fitCenter
            scaleType = ScaleType.FIT_CENTER;
        }
        mScaleType = scaleType;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getHolderRes() {
        return holderRes;
    }

    public void setHolderRes(int holderRes) {
        this.holderRes = holderRes;
    }

    public int getErrorRes() {
        return errorRes;
    }

    public void setErrorRes(int errorRes) {
        this.errorRes = errorRes;
    }

    public ImageView getTargetView() {
        return targetView;
    }

    public void setTargetView(ImageView targetView) {
        this.targetView = targetView;
    }

    public void setCallBack(ILoadCallBack callBack) {
        mCallBack = callBack;
    }

    public ILoadCallBack getCallBack() {
        return mCallBack;
    }

    public static class Builder {
        private Context context;
        private IExecutor executor;

        private String url;
        private int res;
        private File file;
        private Uri uri;

        //对应图片显示的 Type
        private ScaleType mScaleType;
        //对应 override
        private int mWidth;
        private int mHeight;
        //对应 placeHolder
        private int holderRes;
        //对应 error;
        private int errorRes;
        //显示目标 ImageView
        private ImageView targetView;
        //加载回调接口
        private ILoadCallBack mCallBack;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * 根据 url 地址加载
         *
         * @param url 图片 url
         * @return request
         */
        public Builder load(String url) {
            this.url = url;
            return this;
        }

        public Builder executor(IExecutor executor) {
            this.executor = executor;
            return this;
        }

        /**
         * 根据资源 id 加载
         *
         * @param res 图片资源 id
         * @return request
         */
        public Builder load(int res) {
            this.res = res;
            return this;
        }

        /**
         * 根据资源 uri 加载
         *
         * @param uri 图片资源 uri
         * @return request
         */
        public Builder load(Uri uri) {
            this.uri = uri;
            return this;
        }

        /**
         * 根据资源 id 加载
         *
         * @param file 图片资源 file对象
         * @return request
         */
        public Builder load(File file) {
            this.file = file;
            return this;
        }

        public Builder scale(ScaleType type) {
            this.mScaleType = type;
            return this;
        }

        public Builder reSize(int width, int height) {
            this.mWidth = width;
            this.mHeight = height;
            return this;
        }

        public Builder holder(int holderRes) {
            this.holderRes = holderRes;
            return this;
        }

        public Builder error(int errorRes) {
            this.errorRes = errorRes;
            return this;
        }

        private Request build() {
            Request request = new Request(context);

            request.setFile(this.file);
            request.setUri(this.uri);
            request.setUrl(this.url);
            request.setRes(this.res);

            request.setExecutor(this.executor);
            request.setHeight(this.mHeight);
            request.setWidth(this.mWidth);
            request.setHolderRes(this.holderRes);
            request.setErrorRes(this.errorRes);
            request.setScaleType(this.mScaleType);
            request.setTargetView(this.targetView);
            request.setCallBack(this.mCallBack);
            return request;
        }

        public void into(ImageView imageView) {
            this.targetView = imageView;
            this.build().execute();
        }

        public void into(ILoadCallBack callBack) {
            mCallBack = callBack;
            this.build().execute();
        }
    }

    public void execute() {
        if (executor == null) {
            executor = new GlideLoader();
        }
        executor.execute(this);
    }

}
