package com.cil.Tools;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalCache;
import com.cil.Global.GlobalConfig;
import com.cil.Global.GlobalConstant;
import com.cil.Model.*;
import com.cil.Mongo.MerchantColl;
import com.cil.Mongo.ShopColl;
import com.cil.grpc.SendRequest;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by bob on 2017.08.09.
 */
public class Tools {

    private static final Logger logger = LoggerFactory.getLogger(Tools.class);

    public static boolean isBlank(String str) {
        return str == null || str.equals("");
    }

    //     如果缓存实体存在，则将返回参数更新至实体，如果不存在，创建实体，并更新参数
    public static DeskTermEntity transferReqToDesk(DeskTermEntity cacheEntity, String ip, int port, DeskReqModel model) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (cacheEntity == null) {
            cacheEntity = new DeskTermEntity();
            cacheEntity.setMerCode(model.getMerCode());
            cacheEntity.setIpAdd(ip);
            cacheEntity.setPort(port);
            cacheEntity.setTermCode(model.getTermCode());
            cacheEntity.setStatus(GlobalConstant.DESK_TERM_NORMAL);
            cacheEntity.setCreateTime(now);
            cacheEntity.setUpdateTime(now);
        } else {
            cacheEntity.setStatus(GlobalConstant.DESK_TERM_NORMAL);
            cacheEntity.setIpAdd(ip);
            cacheEntity.setPort(port);
            cacheEntity.setUpdateTime(now);
        }
        return cacheEntity;
    }

    // 获取当日0点时间戳
    public static long getZeroTimestamp() {
        long current = System.currentTimeMillis();
        return current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
    }

    // UDP send
    public static void UDPSend(String ip, int port, String requestStr) {

        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendBuf;
            sendBuf = requestStr.getBytes("UTF-8");
            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName(ip), port);
            socket.setSoTimeout(2000);
            socket.send(sendPacket);


            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void minaUDPSend(String ip, int port, String requestStr) {
        IoSession session = GlobalCache.sessionMap.get(ip + ":" + port);
        if (session != null) {
            try {
                IoBuffer buf = IoBuffer.wrap(requestStr.getBytes("UTF-8"));
                session.write(buf);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 将kafka请求信息转换为桌面版所需对象
    public static DeskRespModel reqModelToDesk(SendRequest req) {
        DeskRespModel respModel = new DeskRespModel();
        respModel.setMsgDir(GlobalConstant.MSG_DIR_PUSH);
        respModel.setMsgType(GlobalConstant.DESK_MSG_TYPE_PRINT);
        respModel.setMsgVersion(GlobalConstant.MSG_VERSION);
        respModel.setMsgDir("Q");
        respModel.setMsgSeqNum(req.getMerCode() + req.getOrderNum());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderAmt", req.getOrderAmt());
        jsonObject.put("transTime", req.getTransTime());
//        jsonObject.put("merName", req.getMerName());
        jsonObject.put("merCode", req.getMerCode());
        jsonObject.put("termCode", req.getTermCode());
        jsonObject.put("txnType", req.getTxnType());
        jsonObject.put("consumerAccount", req.getConsumerAccount());
        jsonObject.put("chanOrderNum", req.getChanOrderNum());
        jsonObject.put("orderNum", req.getOrderNum());
        jsonObject.put("cardBrand", req.getCardBrand());
        if (req.getTxnType() != null && req.getTxnType().equals("REFD")) {
            jsonObject.put("origOrderNum", req.getOrigOrderNum());
        }
        respModel.setPrintContent(jsonObject);
        return respModel;
    }

    // 将智慧点餐请求信息转换为桌面版所需对象
    public static DeskRespModel tastyReqToDesk(SendRequest req) {
        DeskRespModel respModel = new DeskRespModel();
        respModel.setMsgDir(GlobalConstant.MSG_DIR_PUSH);
        respModel.setMsgType(GlobalConstant.DESK_MSG_TYPE_TASTY);
        respModel.setMsgVersion(GlobalConstant.MSG_VERSION);
        respModel.setMsgDir("Q");
        JSONObject userDefObj = (JSONObject) JSONObject.parse(req.getUserDef());
        if (userDefObj == null) {
            return null;
        }
        respModel.setMsgSeqNum(req.getMerCode() + userDefObj.get("orderNum").toString());
        respModel.setPrintContent((JSONObject) JSONObject.parse(req.getUserDef()));
        return respModel;
    }

    // 先从缓存中获取商户，如果商户不存在，从数据库中加载商户信息
    public static String getMerCacheKey(String merCode, boolean reload) {
        JSONObject merObj = null;

        if (reload) {
            merObj = MerchantColl.findOne(merCode);
        } else {
            merObj = GlobalCache.merchantMap.get(merCode);
        }

        if (merObj == null || merObj.get("signKeyC") == null) {
            merObj = MerchantColl.findOne(merCode);
            if (merObj == null || merObj.get("signKeyC") == null) {
                return null;
            }
        }

        String signKeyC = merObj.get("signKeyC").toString();
        AES aes = new AES();
        byte[] signByte = HexUtil.hexToBytes(signKeyC);
        byte[] iv = new byte[16];
        String signKey = null;
        try {
            signKey = aes.decrypt(signByte, GlobalConfig.getValue("mongoHexKey"), "CFB", iv);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        GlobalCache.merchantMap.put(merCode, merObj);
        return signKey;
    }

    // 先从缓存中获取门店密钥，如果门店密钥不存在，从数据库中加载门店信息
    public static String getShopCacheKey(String shopId, boolean reload) {
        JSONObject shopObj = null;

        if (reload) {
            shopObj = ShopColl.findOne(shopId);
        } else {
            shopObj = GlobalCache.shopMap.get(shopId);
        }

        if (shopObj == null || shopObj.get("signKey") == null) {
            shopObj = ShopColl.findOne(shopId);
            if (shopObj == null || shopObj.get("signKey") == null) {
                return null;
            }
        }

        String signKey = shopObj.get("signKey").toString();
        GlobalCache.shopMap.put(shopId, shopObj);
        return signKey;
    }

    public static String getEnv(String key) {
        Map<String, String> map = System.getenv();
        return map.get(key);
    }

    // 重新发送打印信息
    public static void reSendPrintMsg(DeskSendCacheModel model, String msgSeqNum) {
        int retryTimes = model.getRetryTimes();
        model.setRetry(true);
        GlobalCache.deskSendModelMap.put(msgSeqNum, model);

        if (model.getRetryTimes() >= GlobalConstant.DESK_RESEND_INTERVAL.length) {
            GlobalCache.deskSendModelMap.remove(msgSeqNum);
            logger.info("对外发送，重试：" + model.getRetryTimes() + "次全部失败，发送信息：" + model.getPrintMsg());
        } else {
            DeskTermEntity deskTermEntity = GlobalCache.deskTermMap.get(model.getMerCode() + ";" + model.getTermCode());
            if (deskTermEntity == null) {
                logger.info("未找到缓存，商户号：" + model.getMerCode() + "，终端号：" + model.getTermCode() + "，检索号：" + msgSeqNum);
                return;
            }

            retryTimes++;

            logger.info("ip:" + deskTermEntity.getIpAdd() + ",port:" + deskTermEntity.getPort() + "第" + retryTimes + "次重试发送，打印信息：" + model.getPrintMsg());
            Tools.minaUDPSend(deskTermEntity.getIpAdd(), deskTermEntity.getPort(), model.getPrintMsg());

            model.setRetryTimes(retryTimes);
            Date date = new Date();
            model.setSendTime(new Date(date.getTime() + GlobalConstant.DESK_RESEND_INTERVAL[retryTimes] * 1000));
            model.setRetry(false);
            GlobalCache.deskSendModelMap.put(msgSeqNum, model);
        }
    }

    // 重新发送对外信息
    public static void reSendOutMsg(OutSendCacheModel model, String msgSeqNum) throws Exception {
        model.setRetry(true);
        GlobalCache.outSendCacheModelMap.put(msgSeqNum, model);
        int retryTimes = model.getRetryTimes();
        if (model.getRetryTimes() >= GlobalConstant.OUT_RESEND_INTERVAL.length) {
            GlobalCache.outSendCacheModelMap.remove(msgSeqNum);
            logger.info("对外发送，重试：" + model.getRetryTimes() + "次全部失败，发送信息：" + model.getSendMsg());
        } else {
            retryTimes++;
            // 发送post
            String result = HttpsTools.sslPost(model.getOutEntity().getPostAddr(), model.getSendMsg(), model.getSign());
            if (checkResponse(result)) {
                logger.info("对外发送，第" + retryTimes + "次，重试成功，机构号：" + model.getOutEntity().getInsCode() + "，商户号：" + model.getOutEntity().getMerCode() + "，检索号：" + msgSeqNum);
                GlobalCache.outSendCacheModelMap.remove(msgSeqNum);
            } else {
                logger.info("对外发送，第" + retryTimes + "次，重试失败，机构号：" + model.getOutEntity().getInsCode() + "，商户号：" + model.getOutEntity().getMerCode() +
                        "，检索号：" + msgSeqNum + "，发送结果：" + result);
                model.setRetryTimes(retryTimes);
                Date date = new Date();
                model.setSendTime((new Date(date.getTime() + GlobalConstant.OUT_RESEND_INTERVAL[retryTimes] * 1000)));
                model.setRetry(false);
                GlobalCache.outSendCacheModelMap.put(msgSeqNum, model);
            }
        }
    }

    public static boolean checkResponse(String respStr) {
        if (respStr == null) {
            return false;
        }
        JSONObject obj = (JSONObject) JSONObject.parse(respStr);
        if (obj == null || obj.get(GlobalConstant.OUT_RESP_CODE) == null || !obj.get(GlobalConstant.OUT_RESP_CODE).equals(GlobalConstant.OUT_RESP_CODE_SUCCESS)) {
            return false;
        }
        return true;
    }

    public static void PrintMsgToCache(String merCode, String termCode, String requestStr, String msgSeqNum) {
        DeskSendCacheModel model = new DeskSendCacheModel();
        model.setMerCode(merCode);
        model.setTermCode(termCode);
        model.setPrintMsg(requestStr);
        model.setRetry(false);
        model.setRetryTimes(0);
        Date date = new Date();
        model.setSendTime((new Date(date.getTime() + GlobalConstant.DESK_RESEND_INTERVAL[0] * 1000)));
        GlobalCache.deskSendModelMap.put(msgSeqNum, model);
    }

    public static void main(String[] args) {
//        DeskRespModel respModel = new DeskRespModel();
//        respModel.setMsgDir("PushSystem");
//        respModel.setMsgType("PrintMsg");
//        respModel.setMsgVersion("1.0");
//        respModel.setMsgDir("Q");
//        respModel.setMsgSeqNum("");
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("orderAmt", "1.0");
//        jsonObject.put("transTime", "08-18 17:25:01");
////        jsonObject.put("merName", "测试");
//        jsonObject.put("merCode", "00000000001");
//        jsonObject.put("termCode", "10000001");
//        jsonObject.put("txnType", "JSZF");
//        jsonObject.put("txnType", "JSZF");
//        jsonObject.put("consumerAccount", "123456***789@qq.com");
//        jsonObject.put("chanOrderNum", "1234");
//        jsonObject.put("orderNum", "789");
//        jsonObject.put("cardBrand", "WXP");
//        respModel.setPrintContent(jsonObject);
//        String reqStr = JSONObject.toJSONString(respModel);
//        reqStr = StringUtils.upperCase(ParamsSign.Encrypt(reqStr + GlobalConfig.getValue("mongoHexKey"), "")) + reqStr;
//        System.out.println(reqStr);
////        Tools.UDPSend("192.168.1.187", 39999, reqStr);
//        //116.236.215.18:61988
//        Tools.UDPSend("121.40.86.222", 39999, reqStr);
//        System.out.println(getEnv("FOXBILL_ENV"));
//        System.out.println(getMerCacheKey("100000000000204", true));
//        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        System.out.println(checkResponse(""));
    }

}
