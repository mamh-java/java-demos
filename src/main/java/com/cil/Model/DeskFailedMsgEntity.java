package com.cil.Model;

import com.cil.Global.GlobalConstant;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by bob on 2017.12.27.
 */
@Entity
@Table(name = "desk_failed_msg", schema = GlobalConstant.dbName, catalog = "")
@IdClass(DeskFailedMsgEntityPK.class)
public class DeskFailedMsgEntity {
    private String merCode;
    private String termCode;
    private String msgSeqNum;
    private String msgStr;
    private String uuid;
    private Timestamp createTime;

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

    @Id
    @Column(name = "msg_seq_num")
    public String getMsgSeqNum() {
        return msgSeqNum;
    }

    public void setMsgSeqNum(String msgSeqNum) {
        this.msgSeqNum = msgSeqNum;
    }

    @Basic
    @Column(name = "msg_str")
    public String getMsgStr() {
        return msgStr;
    }

    public void setMsgStr(String msgStr) {
        this.msgStr = msgStr;
    }

    @Basic
    @Column(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeskFailedMsgEntity that = (DeskFailedMsgEntity) o;

        if (merCode != null ? !merCode.equals(that.merCode) : that.merCode != null) return false;
        if (termCode != null ? !termCode.equals(that.termCode) : that.termCode != null) return false;
        if (msgSeqNum != null ? !msgSeqNum.equals(that.msgSeqNum) : that.msgSeqNum != null) return false;
        if (msgStr != null ? !msgStr.equals(that.msgStr) : that.msgStr != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = merCode != null ? merCode.hashCode() : 0;
        result = 31 * result + (termCode != null ? termCode.hashCode() : 0);
        result = 31 * result + (msgSeqNum != null ? msgSeqNum.hashCode() : 0);
        result = 31 * result + (msgStr != null ? msgStr.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        return result;
    }
}
