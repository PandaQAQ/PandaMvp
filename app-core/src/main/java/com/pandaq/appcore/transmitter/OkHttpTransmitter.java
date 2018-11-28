package com.pandaq.appcore.transmitter;

import android.support.annotation.NonNull;

import com.pandaq.appcore.transmitter.download.DownloadInterceptor;
import com.pandaq.appcore.transmitter.upload.UploadInterceptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by huxinyu on 2018/6/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :okhttp 简单的上传下载文件方法封装
 */
public class OkHttpTransmitter {
    public static volatile OkHttpTransmitter instance;
    private ExecutorService mExecutorService;

    private OkHttpTransmitter() {
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static OkHttpTransmitter getInstance() {
        if (instance == null) {
            synchronized (OkHttpTransmitter.class) {
                if (instance == null) {
                    instance = new OkHttpTransmitter();
                }
            }
        }
        return instance;
    }

    /**
     * 上传文件
     *
     * @param url       上传
     * @param paramsMap 上传请求参数
     */
    public void uploadFile(String url, Map<String, Object> paramsMap, TransmitCallback callback) {
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        //form 表单上传
        multipartBody.setType(MultipartBody.FORM);
        //拼接参数
        if (paramsMap != null && !paramsMap.isEmpty()) {
            for (String key : paramsMap.keySet()) {
                Object object = paramsMap.get(key);
                if (object instanceof String) {
                    multipartBody.addFormDataPart(key, object.toString());
                } else if (object instanceof File) {
                    File file = (File) object;
                    multipartBody.addFormDataPart(key, file.getName(), MultipartBody.create(MediaType.parse("multipart/form-data"), file));
                }
            }
        }
        RequestBody requestBody = multipartBody.build();
        //创建Request对象
        Request request = new Request.Builder().url(url).post(requestBody).build();
        mExecutorService.execute(() -> {
            try {
                new OkHttpClient.Builder()
                        //设置最长读写时间
                        .readTimeout(100000, TimeUnit.SECONDS)
                        .writeTimeout(100000, TimeUnit.SECONDS)
                        .connectTimeout(100000, TimeUnit.SECONDS)
                        .addInterceptor(new UploadInterceptor(callback))
//                        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                        .newCall(request)
                        .execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 下载单个文件
     *
     * @param file 下载文件存放位置
     * @param url  下载地址
     */
    public void downloadFile(final File file, String url, TransmitCallback callback) {
        // 父目录是否存在
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdir();
        }
        // 文件是否存在
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Request request = new Request.Builder().url(url).build();
        mExecutorService.execute(() -> new OkHttpClient.Builder()
                .readTimeout(100000, TimeUnit.SECONDS)
                .writeTimeout(100000, TimeUnit.SECONDS)
                .connectTimeout(100000, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new DownloadInterceptor(callback))
                .build()
                .newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        ResponseBody body = response.body();
                        if (body == null) {
                            return;
                        }
                        InputStream is = body.byteStream();
                        FileOutputStream fos = new FileOutputStream(file);
                        int len;
                        byte[] buffer = new byte[2048];
                        while (-1 != (len = is.read(buffer))) {
                            fos.write(buffer, 0, len);
                        }
                        fos.flush();
                        fos.close();
                        is.close();
                    }
                }));
    }
}
