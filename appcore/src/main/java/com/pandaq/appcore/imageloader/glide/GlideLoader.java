package com.pandaq.appcore.imageloader.glide;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.pandaq.appcore.imageloader.core.IExecutor;
import com.pandaq.appcore.imageloader.core.Request;


/**
 * Created by huxinyu on 2018/7/4.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :默认的 Glide 加载器
 */
public class GlideLoader implements IExecutor {

    @Override
    public void execute(Request request) {
        GlideRequests glideRequests = GlideApp.with(request.getContext());
        GlideRequest<Drawable> glideRequest;
        if (request.getFile() != null) {
            glideRequest = glideRequests.load(request.getFile());
        } else if (request.getRes() != 0) {
            glideRequest = glideRequests.load(request.getRes());
        } else if (request.getUri() != null) {
            glideRequest = glideRequests.load(request.getUri());
        } else {
            glideRequest = glideRequests.load(request.getUrl());
        }
        switch (request.getScaleType()) {
            case FIT_CENTER:
                glideRequest.fitCenter();
                break;
            case CENTER_CROP:
                glideRequest.centerCrop();
                break;
            case CIRCLE_CROP:
                glideRequest.circleCrop();
                break;
            case CENTER_INSIDE:
                glideRequest.centerInside();
                break;
        }
        glideRequest = glideRequest.error(request.getErrorRes());
        glideRequest = glideRequest.placeholder(request.getHolderRes());
        if (request.getTargetView() != null) {
            glideRequest.into(request.getTargetView());
        } else if (request.getCallBack() != null) {
            glideRequest.into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    request.getCallBack().loaded(resource);
                }
            });
        } else {
            Log.e("PandaImageLoader", "target is illegal !!!");
        }
    }
}
