package com.pandaq.pandamvp.entites;

import androidx.annotation.Keep;

/**
 * Created by huxinyu on 2018/7/17.
 * Email : panda.h@foxmail.com
 * <p>
 * Description :用户信息对象
 */
@Keep
public class UserInfo {
    @Keep
    private String account;
    private String userName;
    private String token;

    @Keep
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
