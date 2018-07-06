package com.cil.Model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by bob on 2017.08.18.
 */
public class DeskRespModel {
    private String msgSource;
    private String msgType;
    private String msgVersion;
    private String msgDir;
    private String msgSeqNum;
    private JSONObject PrintContent;

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

    public JSONObject getPrintContent() {
        return PrintContent;
    }

    public void setPrintContent(JSONObject printContent) {
        PrintContent = printContent;
    }

    @Override
    public String toString() {
        return "DeskRespModel{" +
                "msgSource='" + msgSource + '\'' +
                ", msgType='" + msgType + '\'' +
                ", msgVersion='" + msgVersion + '\'' +
                ", msgDir='" + msgDir + '\'' +
                ", msgSeqNum='" + msgSeqNum + '\'' +
                ", PrintContent=" + PrintContent +
                '}';
    }
}
