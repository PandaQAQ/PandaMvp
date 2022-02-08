package com.pandaq.router.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by huxinyu on 7/29/21.
 * Email : panda.h@foxmail.com
 * Description :配置文件
 */
@Entity
public class ConfigData {
    public boolean active;
    public int appId;
    public String configKey;
    public String configValue;
    public int deleted;
    public String type;
    public int version;
    @Generated(hash = 509497765)
    public ConfigData(boolean active, int appId, String configKey,
            String configValue, int deleted, String type, int version) {
        this.active = active;
        this.appId = appId;
        this.configKey = configKey;
        this.configValue = configValue;
        this.deleted = deleted;
        this.type = type;
        this.version = version;
    }
    @Generated(hash = 2100648308)
    public ConfigData() {
    }
    public boolean getActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getAppId() {
        return this.appId;
    }
    public void setAppId(int appId) {
        this.appId = appId;
    }
    public String getConfigKey() {
        return this.configKey;
    }
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }
    public String getConfigValue() {
        return this.configValue;
    }
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
    public int getDeleted() {
        return this.deleted;
    }
    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getVersion() {
        return this.version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
}
