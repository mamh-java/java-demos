package com.cil.Model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by bob on 2017.08.16.
 */
public class DeskTermEntityPK implements Serializable {
    private String merCode;
    private String termCode;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeskTermEntityPK that = (DeskTermEntityPK) o;

        if (merCode != null ? !merCode.equals(that.merCode) : that.merCode != null) return false;
        if (termCode != null ? !termCode.equals(that.termCode) : that.termCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = merCode != null ? merCode.hashCode() : 0;
        result = 31 * result + (termCode != null ? termCode.hashCode() : 0);
        return result;
    }
}
