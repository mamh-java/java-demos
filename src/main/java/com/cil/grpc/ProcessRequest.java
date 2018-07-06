package com.cil.grpc;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalCache;
import com.cil.Global.GlobalConstant;
import com.cil.Model.*;
import com.cil.Repository.DeskFailedMsgRepository;
import com.cil.Tools.HttpsTools;
import com.cil.Tools.ParamsSign;
import com.cil.Tools.Tools;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by bob on 2017.10.20.
 */
public class ProcessRequest {

    private static final Logger logger = LoggerFactory.getLogger(ProcessRequest.class.getName());


    // requestFilter 将获取的请求进行筛选和处理
    public void requestFilterAndProcess(SendRequest req) {
        if (req.getMsgSrc() == null) {
            return;
        }
        switch (req.getMsgSrc()) {
            case GlobalConstant.MSG_SOURCE_KAFKA:
                processKafkaMsg(req);
            case GlobalConstant.MSG_SOURCE_TASTY:
                processTastyMsg(req);
        }
    }

    // processKafkaMsg 处理kafka消息
    public void processKafkaMsg(SendRequest req) {
        // 发送到打印机
        sendToDeskPrint(req);
        // 对外发送
        try {
            sendToOut(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // processTastyMsg 处理智慧点餐消息
    public void processTastyMsg(SendRequest req) {
        if (req.getMerCode() == null || req.getMerCode().equals("")) {
            return;
        }
        if (req.getTermCode() == null || req.getTermCode().equals("")) {
            return;
        }
        if (req.getUserDef() == null || req.getUserDef().equals("")) {
            return;
        }
        DeskTermEntity deskTerm = GlobalCache.deskTermMap.get(req.getMerCode() + ";" + req.getTermCode());
        if (deskTerm != null) {
            // 获取请求对象
            DeskRespModel deskRespModel = Tools.tastyReqToDesk(req);
            if (deskRespModel == null) {
                logger.error("转换请求失败，请求内容：" + req.getUserDef());
                return;
            }
            String reqStr = JSONObject.toJSONString(deskRespModel);
            String appKey = Tools.getShopCacheKey(req.getMerCode(), false);
            if (appKey == null) {
                appKey = Tools.getShopCacheKey(req.getMerCode(), true);
            }
            if (appKey != null) {
                logger.info("获取请求报文:" + reqStr);
                reqStr = StringUtils.upperCase(ParamsSign.Encrypt(reqStr + appKey, "")) + reqStr;
                // 发送至桌面版,首次连续发送3个包以减少丢包率
                for (int i = 0; i < 3; i++) {
                    Tools.minaUDPSend(deskTerm.getIpAdd(), deskTerm.getPort(), reqStr);
                }
                // 记录打印信息到缓存
                Tools.PrintMsgToCache(deskTerm.getMerCode(), deskTerm.getTermCode(), reqStr, deskRespModel.getMsgSeqNum());
                // 记录到数据库
                DeskFailedMsgEntity deskFailedMsgEntity = new DeskFailedMsgEntity();
                deskFailedMsgEntity.setMerCode(deskTerm.getMerCode());
                deskFailedMsgEntity.setTermCode(deskTerm.getTermCode());
                deskFailedMsgEntity.setMsgSeqNum(deskRespModel.getMsgSeqNum());
                deskFailedMsgEntity.setMsgStr(reqStr);
                deskFailedMsgEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
                GlobalCache.deskFailedMsgRepository.saveAndFlush(deskFailedMsgEntity);
            } else {
                logger.info("获取门店密钥失败，门店号：" + req.getMerCode());
            }
        } else {
            logger.info("没有找到终端配置,门店号：" + req.getMerCode() + "终端号：" + req.getTermCode() + "信息：" + req.getUserDef());
        }
    }

    // sendToDeskPrint 发送至桌面版打印
    public void sendToDeskPrint(SendRequest req) {
        // 筛选需要打印的信息
        if (req.getTxnType() == null || (!req.getTxnType().equals("JSZF") && !req.getTxnType().equals("REFD"))) {
            logger.info("交易类型不支持，商户号：" + req.getMerCode() + "，订单号：" + req.getOrderNum());
            return;
        }
        if (req.getTxnType().equals("REFD") && (req.getOrigTxnType() == null || !req.getOrigTxnType().equals("JSZF"))) {
            logger.info("交易类型不支持，商户号：" + req.getMerCode() + "，订单号：" + req.getOrderNum());
            return;
        }

        // 从缓存中获取终端配置
        DeskTermEntity deskTerm = GlobalCache.deskTermMap.get(req.getMerCode() + ";" + req.getTermCode());
        if (deskTerm != null) {
            String reqStr = JSONObject.toJSONString(Tools.reqModelToDesk(req));
            String appKey = Tools.getMerCacheKey(req.getMerCode(), false);
            if (appKey == null) {
                appKey = Tools.getMerCacheKey(req.getMerCode(), true);
            }
            if (appKey != null) {
                logger.info("获取请求报文:" + reqStr);
                reqStr = StringUtils.upperCase(ParamsSign.Encrypt(reqStr + appKey, "")) + reqStr;
                // 发送至桌面版,首次连续发送3个包以减少丢包率
                for (int i = 0; i < 3; i++) {
                    Tools.minaUDPSend(deskTerm.getIpAdd(), deskTerm.getPort(), reqStr);
                }
                // 记录打印信息到缓存
                Tools.PrintMsgToCache(deskTerm.getMerCode(), deskTerm.getTermCode(), reqStr, req.getMerCode() + req.getOrderNum());
                // 记录到数据库
                DeskFailedMsgEntity deskFailedMsgEntity = new DeskFailedMsgEntity();
                deskFailedMsgEntity.setMerCode(deskTerm.getMerCode());
                deskFailedMsgEntity.setTermCode(deskTerm.getTermCode());
                deskFailedMsgEntity.setMsgSeqNum(req.getMerCode() + req.getOrderNum());
                deskFailedMsgEntity.setMsgStr(reqStr);
                deskFailedMsgEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
                DeskFailedMsgRepository deskFailedMsgRepository = (DeskFailedMsgRepository) GlobalCache.getBean("deskFailedMsgRepository");
                deskFailedMsgRepository.saveAndFlush(deskFailedMsgEntity);
            } else {
                logger.info("获取商户密钥失败，商户号：" + req.getMerCode());
            }
        } else {
            logger.info("没有找到终端配置,商户号：" + req.getMerCode() + "终端号：" + req.getTermCode() + "订单号：" + req.getOrderNum());
        }
    }

    // sendToOut 将请求发送至外部
    public void sendToOut(SendRequest req) {
        // 筛选需要发送的报文
        if (req.getInscd() == null || req.getInscd().equals("") || req.getMerCode() == null || req.getMerCode().equals("")) {
            return;
        }
        // 获取机构配置，如果获取成功向外部发送
        OutPushConfigEntity insEntity = GlobalCache.outPushConfigMap.get(req.getInscd().trim() + ";");
        if (insEntity != null) {
            logger.info("向机构发送请求，机构号：" + req.getInscd() + "，订单号：" + req.getOrderNum());
            sendPost(req, insEntity);
        }

        // 获取商户配置，如果获取成功向外部发送
        OutPushConfigEntity merEntity = GlobalCache.outPushConfigMap.get(req.getInscd().trim() + ";" + req.getMerCode().trim());
        if (merEntity != null) {
            logger.info("向商户发送请求，机构号：" + req.getInscd() + "，商户号：" + req.getInscd() + "，订单号：" + req.getOrderNum());
            sendPost(req, merEntity);
        }
    }

    // 根据配置将请求发送
    private void sendPost(SendRequest req, OutPushConfigEntity entity) {
        // 签名
        String sign = StringUtils.upperCase(ParamsSign.Encrypt(req.getUserDef() + entity.getAppKey(), ""));

        // 发送post
        String reqStr = reqToJsonString(req);
        String result = HttpsTools.sslPost(entity.getPostAddr(), reqStr, sign);
        // 检测发送结果，如果失败记录至缓存
        if (!Tools.checkResponse(result)) {
            logger.info("发送失败，等待重试，机构号：" + req.getInscd() + "商户号：" + req.getMerCode() + "，订单号：" + req.getOrderNum() + "，发送结果：" + result);
            OutSendCacheModel outSendCacheModel = new OutSendCacheModel();
            outSendCacheModel.setSign(sign);
            outSendCacheModel.setRetryTimes(0);
            Date date = new Date();
            outSendCacheModel.setSendTime((new Date(date.getTime() + GlobalConstant.OUT_RESEND_INTERVAL[0] * 1000)));
            outSendCacheModel.setSendMsg(reqStr);
            outSendCacheModel.setRetry(false);
            outSendCacheModel.setOutEntity(entity);
            GlobalCache.outSendCacheModelMap.put(req.getMerCode() + req.getOrderNum(), outSendCacheModel);
        } else {
            logger.info("发送成功：机构号：" + req.getInscd() + "商户号：" + req.getMerCode() + "，订单号：" + req.getOrderNum());
        }
    }

    private String reqToJsonString(SendRequest req) {
        JSONObject obj = new JSONObject();
        if (req.getInscd() != null && !req.getInscd().equals("")) {
            obj.put("inscd", req.getInscd());
        }
        if (req.getGroupCode() != null && !req.getGroupCode().equals("")) {
            obj.put("groupCode", req.getGroupCode());
        }
        if (req.getMsgType() != null && !req.getMsgType().equals("")) {
            obj.put("msgType", req.getMsgType());
        }
        if (req.getOrderAmt() != null && !req.getOrderAmt().equals("")) {
            obj.put("orderAmt", req.getOrderAmt());
        }
        if (req.getDiscountAmt() != null && !req.getDiscountAmt().equals("")) {
            obj.put("discountAmt", req.getDiscountAmt());
        }
        if (req.getMerCode() != null && !req.getMerCode().equals("")) {
            obj.put("merCode", req.getMerCode());
        }
        if (req.getMerName() != null && !req.getMerName().equals("")) {
            obj.put("merName", req.getMerName());
        }
        if (req.getCardBrand() != null && !req.getCardBrand().equals("")) {
            obj.put("cardBrand", req.getCardBrand());
        }
        if (req.getTxnType() != null && !req.getTxnType().equals("")) {
            obj.put("txnType", req.getTxnType());
        }
        if (req.getOrderNum() != null && !req.getOrderNum().equals("")) {
            obj.put("orderNum", req.getOrderNum());
        }
        if (req.getTransTime() != null && !req.getTransTime().equals("")) {
            obj.put("transTime", req.getTransTime());
        }
        if (req.getTransCurr() != null && !req.getTransCurr().equals("")) {
            obj.put("transCurr", req.getTransCurr());
        }
        if (req.getSubOpenid() != null && !req.getSubOpenid().equals("")) {
            obj.put("subOpenid", req.getSubOpenid());
        }
        if (req.getSubAppid() != null && !req.getSubAppid().equals("")) {
            obj.put("subAppid", req.getSubAppid());
        }
        if (req.getSettFlag() != null && !req.getSettFlag().equals("")) {
            obj.put("settFlag", req.getSettFlag());
        }
        if (req.getCheckCode() != null && !req.getCheckCode().equals("")) {
            obj.put("checkCode", req.getCheckCode());
        }
        if (req.getOrigTxnType() != null && !req.getOrigTxnType().equals("")) {
            obj.put("origTxnType", req.getOrigTxnType());
        }
        if (req.getTermCode() != null && !req.getTermCode().equals("")) {
            obj.put("termCode", req.getTermCode());
        }
        if (req.getChanOrderNum() != null && !req.getChanOrderNum().equals("")) {
            obj.put("chanOrderNum", req.getChanOrderNum());
        }
        if (req.getOrigOrderNum() != null && !req.getOrigOrderNum().equals("")) {
            obj.put("origOrderNum", req.getOrigOrderNum());
        }
        if (req.getAttach() != null && !req.getAttach().equals("")) {
            obj.put("attach", req.getAttach());
        }
        if (req.getMerDiscount() != null && !req.getMerDiscount().equals("")) {
            obj.put("merDiscount", req.getMerDiscount());
        }
        if (req.getChcdDiscount() != null && !req.getChcdDiscount().equals("")) {
            obj.put("chcdDiscount", req.getChcdDiscount());
        }
        if (req.getOutOrderNum() != null && !req.getOutOrderNum().equals("")) {
            obj.put("outOrderNum", req.getOutOrderNum());
        }
        if (req.getRespcd() != null && !req.getRespcd().equals("")) {
            obj.put("respcd", req.getRespcd());
        }
        if (req.getRecvInsName() != null && !req.getRecvInsName().equals("")) {
            obj.put("recvInsName", req.getRecvInsName());
        }
        if (req.getRetrivlRefNum() != null && !req.getRetrivlRefNum().equals("")) {
            obj.put("retrivlRefNum", req.getRetrivlRefNum());
        }
        if (req.getOrigretrivlRefNum() != null && !req.getOrigretrivlRefNum().equals("")) {
            obj.put("origretrivlRefNum", req.getOrigretrivlRefNum());
        }
        if (req.getRespAuthCode() != null && !req.getRespAuthCode().equals("")) {
            obj.put("respAuthCode", req.getRespAuthCode());
        }
        if (req.getConsumerAccount() != null && !req.getConsumerAccount().equals("")) {
            obj.put("consumerAccount", req.getConsumerAccount());
        }
        return JSONObject.toJSONString(obj);
    }

}
