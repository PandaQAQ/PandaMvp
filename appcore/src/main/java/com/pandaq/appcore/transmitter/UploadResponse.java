package com.pandaq.appcore.transmitter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huxinyu on 2018/6/8.
 * Email : panda.h@foxmail.com
 * <p>
 * Description : 上传返回数据实体
 */
public class UploadResponse {
    /**
     * code : 200
     * message : 上传成功
     * data : {"id":"710","path":"http://api.hengqindongli.com:8080/Uploads/2018-06-08/5b1a4c4dae6e6.jpg"}
     */

    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 710
         * path : http://api.hengqindongli.com:8080/Uploads/2018-06-08/5b1a4c4dae6e6.jpg
         */

        @SerializedName("id")
        private String id;
        @SerializedName("path")
        private String path;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
