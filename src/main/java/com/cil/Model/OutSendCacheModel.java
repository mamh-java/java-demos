package com.cil.Model;

import java.util.Date;

/**
 * Created by bob on 2017.09.08.
 */
public class OutSendCacheModel {

    private String sendMsg;
    private String sign;
    private int retryTimes;
    private Date sendTime;
    private boolean retry;
    private OutPushConfigEntity outEntity;

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public OutPushConfigEntity getOutEntity() {
        return outEntity;
    }

    public void setOutEntity(OutPushConfigEntity outEntity) {
        this.outEntity = outEntity;
    }
}
