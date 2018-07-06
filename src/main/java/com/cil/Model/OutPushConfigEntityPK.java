package com.cil.Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by bob on 2017.10.27.
 */
public class OutPushConfigEntityPK implements Serializable {
    private String insCode;
    private String merCode;

    @Column(name = "ins_code")
    @Id
    public String getInsCode() {
        return insCode;
    }

    public void setInsCode(String insCode) {
        this.insCode = insCode;
    }

    @Column(name = "mer_code")
    @Id
    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutPushConfigEntityPK that = (OutPushConfigEntityPK) o;

        if (insCode != null ? !insCode.equals(that.insCode) : that.insCode != null) return false;
        if (merCode != null ? !merCode.equals(that.merCode) : that.merCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = insCode != null ? insCode.hashCode() : 0;
        result = 31 * result + (merCode != null ? merCode.hashCode() : 0);
        return result;
    }
}
