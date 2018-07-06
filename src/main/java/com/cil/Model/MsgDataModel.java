package com.cil.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 报文信息实体
 */
public class MsgDataModel {

    private String msgSource;
    private String msgType;
    private String orderAmt;
    private String merCode;
    private String merName;
    private String cardBrand;
    private String txnType;
    private String orderNum;
    private String transTime;
    private String transCurr;
    private String subOpenid;
    private String subAppid;
    private String settFlag;
    private String toClient;
    private String veriCode;
    private RecoModel recoModel;
    private String termCode;
    private String accountNum;
    private String chanOrderNum;

    public RecoModel getRecoModel() {
        return recoModel;
    }

    public void setRecoModel(RecoModel recoModel) {
        this.recoModel = recoModel;
    }

    public String getVeriCode() {
        return veriCode;
    }

    public void setVeriCode(String veriCode) {
        this.veriCode = veriCode;
    }

    private SubscribeListEntity entity;

    public void setEntity(SubscribeListEntity entity) {
        this.entity = entity;
    }

    public SubscribeListEntity getEntity() {
        return entity;
    }

    public void setToClient(String toClient) {
        this.toClient = toClient;
    }

    public String getToClient() {
        return toClient;
    }

    public String getSettFlag() {
        return settFlag;
    }

    public void setSettFlag(String settFlag) {
        this.settFlag = settFlag;
    }

    public void setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getTransCurr() {
        return transCurr;
    }

    public void setTransCurr(String transCurr) {
        this.transCurr = transCurr;
    }

    public void setMsgSource(String msgSource) {
        this.msgSource = msgSource;
    }

    public String getMsgSource() {
        return msgSource;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getOrderAmt() {
        return orderAmt;
    }

    public void setOrderAmt(String orderAmt) {
        this.orderAmt = orderAmt;
    }

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public String getMerName() {
        return merName;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        cardBrand = checkCardBrand(cardBrand);
        this.cardBrand = cardBrand;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        txnType = checkTxnType(txnType);
        this.txnType = txnType;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        transTime = checkTransTime(transTime);
        this.transTime = transTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getChanOrderNum() {
        return chanOrderNum;
    }

    public void setChanOrderNum(String chanOrderNum) {
        this.chanOrderNum = chanOrderNum;
    }

    private static String checkTxnType(String txnType) {

        Map<String, String> txnList = new HashMap<>();
        txnList.put("PER", "消费");
        txnList.put("PEC", "消费冲正");
        txnList.put("PVR", "消费撤销");
        txnList.put("PVC", "消费撤销冲正");
        txnList.put("PPA", "预授权");
        txnList.put("PPC", "预授权冲正");
        txnList.put("PNP", "预授权撤销");
        txnList.put("PMC", "预授权撤销冲正");
        txnList.put("ACR", "预授权完成");
        txnList.put("PCR", "预授权完成撤销");
        txnList.put("PCC", "预授权完成冲正");
        txnList.put("PRC", "预授权完成撤销冲正");
        txnList.put("CTH", "退货");
        txnList.put("PURC", "下单");
        txnList.put("PAUT", "预下单");
        txnList.put("JSZF", "公众号支付");
        txnList.put("WPAY", "H5支付");
        txnList.put("APPP", "app支付");
        txnList.put("QYZF", "企业支付");
        txnList.put("REFD", "退款");
        txnList.put("VOID", "撤销");
        txnList.put("CANC", "取消");

        return txnList.get(txnType);
    }

    private static String checkCardBrand(String cardBrand) {

        Map<String, String> cardBrandList = new HashMap<>();
        cardBrandList.put("CUP", "银联卡");
        cardBrandList.put("UPI", "银联外卡");
        cardBrandList.put("MCC", "万事达卡");
        cardBrandList.put("WXP", "微信支付");
        cardBrandList.put("ALP", "支付宝");

        return cardBrandList.get(cardBrand);
    }

    private static String checkOrderAmt(String orderAmt) {

        if (orderAmt == null || orderAmt.isEmpty()) {
            return "0";
        }

        long longMoney = Long.parseLong(orderAmt);
        String moneyStr = String.valueOf(longMoney);
        moneyStr = String.format("%03d", longMoney);

        StringBuilder strBuf = new StringBuilder(moneyStr);
        strBuf.insert(strBuf.length() - 2, ".");
        return strBuf.toString();
    }

    private static String checkTransTime(String transTime) {

        return getPushTransTime(transTime);
    }

    private static String getPushTransTime(String tradeTime) {

        if (tradeTime == null || tradeTime.isEmpty()) {
            return " ";
        }
        DateFormat sdf = new SimpleDateFormat("MMddHHmmss");
        DateFormat sdf2 = new SimpleDateFormat("MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(tradeTime);
            tradeTime = sdf2.format(date);
            return tradeTime;
        } catch (Exception e) {
            e.printStackTrace();
            return tradeTime;
        }
    }
}
