package com.cil.Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by bob on 2017.12.27.
 */
public class DeskFailedMsgEntityPK implements Serializable {
    private String merCode;
    private String termCode;
    private String msgSeqNum;

    @Column(name = "mer_code")
    @Id
    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    @Column(name = "term_code")
    @Id
    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    @Column(name = "msg_seq_num")
    @Id
    public String getMsgSeqNum() {
        return msgSeqNum;
    }

    public void setMsgSeqNum(String msgSeqNum) {
        this.msgSeqNum = msgSeqNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeskFailedMsgEntityPK that = (DeskFailedMsgEntityPK) o;

        if (merCode != null ? !merCode.equals(that.merCode) : that.merCode != null) return false;
        if (termCode != null ? !termCode.equals(that.termCode) : that.termCode != null) return false;
        if (msgSeqNum != null ? !msgSeqNum.equals(that.msgSeqNum) : that.msgSeqNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = merCode != null ? merCode.hashCode() : 0;
        result = 31 * result + (termCode != null ? termCode.hashCode() : 0);
        result = 31 * result + (msgSeqNum != null ? msgSeqNum.hashCode() : 0);
        return result;
    }
}
