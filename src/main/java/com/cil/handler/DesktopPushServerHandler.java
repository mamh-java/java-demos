package com.cil.handler;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalCache;
import com.cil.Global.GlobalConstant;
import com.cil.Model.DeskReqModel;
import com.cil.Model.DeskTermEntity;
import com.cil.Mongo.MerchantColl;
import com.cil.Repository.DeskFailedMsgRepository;
import com.cil.Tools.ParamsSign;
import com.cil.Tools.Tools;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by bob on 2017.08.15.
 */
public class DesktopPushServerHandler extends IoHandlerAdapter {


    private static final Logger logger = LoggerFactory.getLogger(DesktopPushServerHandler.class);
    public static final CharsetDecoder decoder = (Charset.forName("UTF-8")).newDecoder();
    public static final CharsetEncoder encoder = (Charset.forName("UTF-8")).newEncoder();


    @Override
    public void messageReceived(final IoSession session, final Object message)
            throws Exception {

        // 读取收到的数据
        IoBuffer buffer = (IoBuffer) message;
        String body = buffer.getString(decoder);
        String remoteAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        int remotePort = ((InetSocketAddress) session.getRemoteAddress()).getPort();
        logger.debug("客户端" + remoteAddress + ":" + remotePort + "连接成功！");
        session.setAttribute("type", body);
        session.setAttribute("ip", remoteAddress);
        logger.info("客户端" + remoteAddress + ":" + remotePort + "连接成功！心跳内容：" + body);

        // 处理逻辑
        byte[] res;
        if (resolveMessage(remoteAddress, remotePort, body)) {
            res = GlobalConstant.DESK_RESPONSE_SUCCESS.getBytes("UTF-8");
            GlobalCache.sessionMap.put(remoteAddress + ":" + remotePort, session);
        } else {
            res = GlobalConstant.DESK_RESPONSE_FAILED.getBytes("UTF-8");
        }


        // 向客户端写数据
        IoBuffer buf = IoBuffer.wrap(res);
        WriteFuture future = session.write(buf);
        // 在100毫秒超时间内等待写完成
        future.awaitUninterruptibly(100);
        // The message has been written successfully
        if (!future.isWritten()) {
            logger.warn("[IMCORE]回复给客户端的数据发送失败！");
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    }

    @Override
    public void sessionOpened(IoSession iosession) throws Exception {
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.error("[IMCORE]exceptionCaught捕获到错了，原因是：" + cause.getMessage(), cause);
        session.close(true);
    }

    private boolean resolveMessage(String ip, int port, String message) {
        if (message.length() <= 64) {
            return false;
        }
        // 验签：报文前64位为签名
        String sign = message.substring(0, 64);
        String realMsg = message.substring(64);
        DeskReqModel dr = JSONObject.parseObject(realMsg, DeskReqModel.class);
        if (dr.getMsgType() == null || dr.getMsgType().equals("")) {
            logger.error("ip:" + ip + ",port:" + port + " 消息类型不存在，报文：" + message);
            return false;
        }

        // 获取心跳信息记录在缓存，获取打印信息从缓存中获取打印信息，如果有，则重新打印
        if (dr.getMsgType().equals(GlobalConstant.DESK_MSG_TYPE_IDLE_PRINT) || dr.getMsgType().equals(GlobalConstant.DESK_MSG_TYPE_IDLE_TASTY)) {
            if (dr.getMerCode() == null || dr.getTermCode() == null) {
                logger.error("ip:" + ip + ",port:" + port + " 商户号或终端号不存在，报文：" + message);
                return false;
            }

            // 商户密钥解密并验签
            String signKey = null;
            if (dr.getMsgType().equals(GlobalConstant.DESK_MSG_TYPE_IDLE_PRINT)) {
                signKey = Tools.getMerCacheKey(dr.getMerCode(), false);
                if (signKey == null) {
                    signKey = Tools.getMerCacheKey(dr.getMerCode(), true);
                }
            } else {
                signKey = Tools.getShopCacheKey(dr.getMerCode(), false);
                if (signKey == null) {
                    signKey = Tools.getShopCacheKey(dr.getMerCode(), true);
                }
            }

            if (signKey == null) {
                logger.error("ip:" + ip + ",port:" + port + ",商户号:" + dr.getMerCode() + " 获取商户密钥失败！");
                return false;
            }
            if (!checkSign(realMsg, signKey, sign)) {
                if (dr.getMsgType().equals(GlobalConstant.DESK_MSG_TYPE_IDLE_PRINT)) {
                    logger.error("ip:" + ip + ",port:" + port + ",商户号：" + dr.getMerCode() + " 验证签名失败，需重新加载商户信息后验签！");
                    signKey = Tools.getMerCacheKey(dr.getMerCode(), true);
                } else if (dr.getMsgType().equals(GlobalConstant.DESK_MSG_TYPE_IDLE_TASTY)) {
                    logger.error("ip:" + ip + ",port:" + port + ",门店号：" + dr.getMerCode() + " 验证签名失败，需重新加载门店信息后验签！");
                    signKey = Tools.getShopCacheKey(dr.getMerCode(), true);
                } else {
                    logger.error("不支持的消息类型：" + dr.getMsgType());
                    return false;
                }

                if (!checkSign(realMsg, signKey, sign)) {
                    logger.error("ip:" + ip + ",port:" + port + ",商户号：" + dr.getMerCode() + " 重新验签失败");
                    return false;
                }
            }

            // 通过key从缓存中获取，并更新参数，如果没有获取到，则新建对象放入缓存
            String key = dr.getMerCode() + ";" + dr.getTermCode();
            DeskTermEntity cacheEntity = GlobalCache.deskTermMap.get(key);
            cacheEntity = Tools.transferReqToDesk(cacheEntity, ip, port, dr);
            GlobalCache.deskTermMap.put(key, cacheEntity);
        } else if (dr.getMsgType().equals(GlobalConstant.DESK_MSG_TYPE_PRINT) || dr.getMsgType().equals(GlobalConstant.DESK_MSG_TYPE_TASTY)) {
            if (dr.getRespCode() == null || dr.getRespCode().equals("")) {
                logger.error("ip:" + ip + ",port:" + port + " 应答码不存在，报文：" + message);
                return false;
            }
            if (dr.getMsgSeqNum() == null || dr.getMsgSeqNum().equals("")) {
                logger.error("ip:" + ip + ",port:" + port + " 消息流水号不存在，报文：" + message);
                return false;
            }
            // 应答为“00”则删除缓存和数据库信息
            if (dr.getRespCode().equals(GlobalConstant.DESK_RESPONSE_SUCCESS)) {
                GlobalCache.deskSendModelMap.remove(dr.getMsgSeqNum());
                GlobalCache.deskFailedMsgRepository.deleteByMsgSeqNum(dr.getMsgSeqNum());
            }
        } else {
            logger.error("ip:" + ip + ",port:" + port + " 消息类型不支持，报文：" + message);
            return false;
        }
        return true;
    }

    private static boolean checkSign(String message, String key, String sign) {
        return StringUtils.upperCase(ParamsSign.Encrypt(message + key, "")).equals(sign);
    }

    public static void main(String[] args) {
        String msg = "{\"msgDir\":\"Q\",\"msgSeqNum\":\"66610004121003017082520372269286\",\"msgType\":\"PrintMsg\",\"msgVersion\":\"1.0\",\"printContent\":{\"merCode\":\"666100041210030\",\"transTime\":\"0825203723\",\"chanOrderNum\":\"4005592001201708258231143924\",\"orderNum\":\"17082520372269286\",\"txnType\":\"JSZF\",\"orderAmt\":\"0.01\",\"consumerAccount\":\"\",\"cardBrand\":\"WXP\",\"termCode\":\"10000001\"}}";
        String merId = "666100041210030";
        JSONObject merObj = MerchantColl.findOne(merId);
        System.out.println(merObj.get("signKeyC"));
        GlobalCache.merchantMap.put(merId, merObj);
        String signKey = Tools.getMerCacheKey(merId, false);
        System.out.println("signKey:" + signKey);
        //556B713F254CFF414EDBEFCE5CDDE9475FD209AE285B5C9E6E39BEB2E92E9212
        System.out.println(StringUtils.upperCase(ParamsSign.Encrypt(msg + signKey, "")));
//        System.out.println(DesktopPushServerHandler.resolveMessage("", 0, msg));
//
//        Map<String, String> map = new HashMap<>();
//        System.out.println(map.get("123"));
    }

}
