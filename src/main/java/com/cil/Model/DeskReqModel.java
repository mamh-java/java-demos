package com.cil.Model;

/**
 * Created by bob on 2017.08.17.
 */
public class DeskReqModel {
    private String msgSource;
    private String msgType;
    private String msgVersion;
    private String msgDir;
    private String msgSeqNum;
    private String merCode;
    private String termCode;
    private String termVersion;
    private String hashKey;
    private String respCode;

    public String getMsgSource() {
        return msgSource;
    }

    public void setMsgSource(String msgSource) {
        this.msgSource = msgSource;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgVersion() {
        return msgVersion;
    }

    public void setMsgVersion(String msgVersion) {
        this.msgVersion = msgVersion;
    }

    public String getMsgDir() {
        return msgDir;
    }

    public void setMsgDir(String msgDir) {
        this.msgDir = msgDir;
    }

    public String getMsgSeqNum() {
        return msgSeqNum;
    }

    public void setMsgSeqNum(String msgSeqNum) {
        this.msgSeqNum = msgSeqNum;
    }

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

    public String getTermVersion() {
        return termVersion;
    }

    public void setTermVersion(String termVersion) {
        this.termVersion = termVersion;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
}
