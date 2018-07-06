package com.cil.Model;

import java.util.Date;

/**
 * Created by bob on 2017.09.08.
 */
public class DeskSendCacheModel {
    private String merCode;
    private String termCode;
    private String printMsg;
    private int retryTimes;
    private Date sendTime;
    private boolean retry;

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getPrintMsg() {
        return printMsg;
    }

    public void setPrintMsg(String printMsg) {
        this.printMsg = printMsg;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isRetry() {
        return retry;
    }

    public void setRetry(boolean retry) {
        this.retry = retry;
    }
}
