package com.cil.Model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by bob on 2017.10.27.
 */
@Entity
@Table(name = "out_push_config", schema = "Foxbill", catalog = "")
@IdClass(OutPushConfigEntityPK.class)
public class OutPushConfigEntity {
    private String insCode;
    private String merCode;
    private String postAddr;
    private String keyAddr;
    private String keyPsd;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String appKey;

    @Id
    @Column(name = "ins_code")
    public String getInsCode() {
        return insCode;
    }

    public void setInsCode(String insCode) {
        this.insCode = insCode;
    }

    @Id
    @Column(name = "mer_code")
    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    @Basic
    @Column(name = "post_addr")
    public String getPostAddr() {
        return postAddr;
    }

    public void setPostAddr(String postAddr) {
        this.postAddr = postAddr;
    }

    @Basic
    @Column(name = "key_addr")
    public String getKeyAddr() {
        return keyAddr;
    }

    public void setKeyAddr(String keyAddr) {
        this.keyAddr = keyAddr;
    }

    @Basic
    @Column(name = "key_psd")
    public String getKeyPsd() {
        return keyPsd;
    }

    public void setKeyPsd(String keyPsd) {
        this.keyPsd = keyPsd;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "app_key")
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutPushConfigEntity that = (OutPushConfigEntity) o;

        if (insCode != null ? !insCode.equals(that.insCode) : that.insCode != null) return false;
        if (merCode != null ? !merCode.equals(that.merCode) : that.merCode != null) return false;
        if (postAddr != null ? !postAddr.equals(that.postAddr) : that.postAddr != null) return false;
        if (keyAddr != null ? !keyAddr.equals(that.keyAddr) : that.keyAddr != null) return false;
        if (keyPsd != null ? !keyPsd.equals(that.keyPsd) : that.keyPsd != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (appKey != null ? !appKey.equals(that.appKey) : that.appKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = insCode != null ? insCode.hashCode() : 0;
        result = 31 * result + (merCode != null ? merCode.hashCode() : 0);
        result = 31 * result + (postAddr != null ? postAddr.hashCode() : 0);
        result = 31 * result + (keyAddr != null ? keyAddr.hashCode() : 0);
        result = 31 * result + (keyPsd != null ? keyPsd.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (appKey != null ? appKey.hashCode() : 0);
        return result;
    }
}
