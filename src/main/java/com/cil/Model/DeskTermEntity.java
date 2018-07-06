package com.cil.Model;

import com.cil.Global.GlobalConstant;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by bob on 2017.08.16.
 */
@Entity
@Table(name = "desk_term", schema = GlobalConstant.dbName, catalog = "")
@IdClass(DeskTermEntityPK.class)
public class DeskTermEntity {
    private String merCode;
    private String termCode;
    private String ipAdd;
    private Integer port;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "mer_code")
    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    @Id
    @Column(name = "term_code")
    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    @Basic
    @Column(name = "ip_add")
    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    @Basic
    @Column(name = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeskTermEntity that = (DeskTermEntity) o;

        if (merCode != null ? !merCode.equals(that.merCode) : that.merCode != null) return false;
        if (termCode != null ? !termCode.equals(that.termCode) : that.termCode != null) return false;
        if (ipAdd != null ? !ipAdd.equals(that.ipAdd) : that.ipAdd != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = merCode != null ? merCode.hashCode() : 0;
        result = 31 * result + (termCode != null ? termCode.hashCode() : 0);
        result = 31 * result + (ipAdd != null ? ipAdd.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
