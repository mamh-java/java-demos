package com.cil.controller;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalCache;
import com.cil.Global.GlobalConstant;
import com.cil.Model.DeskFailedMsgEntity;
import com.cil.Repository.DeskFailedMsgRepository;
import com.cil.Tools.ParamsSign;
import com.cil.Tools.Tools;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by bob on 2017.08.04.
 * 桌面版失败信息重发接口
 */
@RestController
@RequestMapping("desktop")
public class DeskResendController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 每次最多获取20笔信息
    private Pageable maxPageable = new PageRequest(0, 20);

    @Autowired
    private DeskFailedMsgRepository deskFailedMsgRepository;

    /**
     * @param dataModel 桌面版调用重试发送接口
     */
    @PostMapping(value = "/findMsg", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String msgResend(@RequestBody String dataModel) {
        // 应答json
        JSONObject respJson = new JSONObject();

        if (dataModel == null || dataModel.length() < 64) {
            logger.error("请求格式错误：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_FORMAT_ERROR, "请求格式错误", null, null);
        }


        String sign = dataModel.substring(0, 64);
        String realMsg = dataModel.substring(64);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(realMsg);

        String merCode = jsonObject.getString("merCode");
        if (merCode == null || "".equals(merCode)) {
            logger.error("请求商户号不存在：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_NO_MERCODE, "商户号不存在", null, null);
        }

        String termCode = jsonObject.getString("termCode");
        if (termCode == null || "".equals(termCode)) {
            logger.error("请求终端号不存在：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_NO_TERMCODE, "终端号不存在", null, null);
        }

        Date expired = GlobalCache.deskFailedMsgExpireMap.get(merCode + ";" + termCode);
        Date now = new Date();
        if (expired != null && expired.after(now)) {
            logger.error("请求过于频繁，稍后后再试：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_FREQUENT, "请求过于频繁，请稍后再试", null, null);
        }
        // 设置一分钟内不受理请求
        GlobalCache.deskFailedMsgExpireMap.put(merCode + ";" + termCode, new Date(now.getTime() + 60 * 1000));

        // 根据商户密钥验证签名，如果密钥失效需要重新加载密钥后验证
        String signKey = Tools.getMerCacheKey(merCode, false);
        if (signKey == null) {
            signKey = Tools.getMerCacheKey(merCode, true);
        }
        if (signKey == null) {
            logger.error("商户号:" + merCode + " 获取商户密钥失败！");
            return getRespCode(GlobalConstant.DESK_RESEND_SIGN_ERROR, "验签失败", null, null);
        }
        if (!checkSign(realMsg, signKey, sign)) {
            logger.error("商户号:" + merCode + " 验签失败，重新加载密钥后验签！");
            signKey = Tools.getMerCacheKey(merCode, true);
            if (!checkSign(realMsg, signKey, sign)) {
                logger.error("商户号：" + merCode + " 重新验签失败");
                return getRespCode(GlobalConstant.DESK_RESEND_SIGN_ERROR, "验签失败", null, null);
            }
        }

        List<DeskFailedMsgEntity> list = deskFailedMsgRepository.findAllByMerCodeAndTermCode(merCode, termCode, maxPageable);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String msgStr = "";
        for (int i = 0; i < list.size(); i++) {
            msgStr = msgStr + list.get(i).getMsgStr() + ";";
            list.get(i).setUuid(uuid);
        }
        if ("".equals(msgStr)) {
            return getRespCode(GlobalConstant.DESK_RESEND_NO_MSG, "获取打印信息失败", null, null);
        }
        try {
            deskFailedMsgRepository.save(list);
        } catch (Exception e) {
            e.printStackTrace();
            return getRespCode(GlobalConstant.DESK_RESEND_PROC_ERROR, "处理异常", null, null);
        }

        return getRespCode(GlobalConstant.DESK_RESEND_SUCCESS, null, msgStr, uuid);

    }

    /**
     * @param dataModel 桌面版调用删除接口
     */
    @PostMapping(value = "/delMsg", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String delMsg(@RequestBody String dataModel) {
        // 应答json
        JSONObject respJson = new JSONObject();

        if (dataModel == null || dataModel.length() < 64) {
            logger.error("请求格式错误：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_FORMAT_ERROR, "请求格式错误", null, null);
        }


        String sign = dataModel.substring(0, 64);
        String realMsg = dataModel.substring(64);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(realMsg);

        String merCode = jsonObject.getString("merCode");
        if (merCode == null || "".equals(merCode)) {
            logger.error("请求商户号不存在：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_NO_MERCODE, "商户号不存在", null, null);
        }

        String termCode = jsonObject.getString("termCode");
        if (termCode == null || "".equals(termCode)) {
            logger.error("请求终端号不存在：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_NO_TERMCODE, "终端号不存在", null, null);
        }

        String uuid = jsonObject.getString("uuid");
        if (uuid == null || "".equals(uuid)) {
            logger.error("请求uuid不存在：" + dataModel);
            return getRespCode(GlobalConstant.DESK_RESEND_FORMAT_ERROR, "uuid不存在", null, null);
        }

        // 根据商户密钥验证签名，如果密钥失效需要重新加载密钥后验证
        String signKey = Tools.getMerCacheKey(merCode, false);
        if (signKey == null) {
            signKey = Tools.getMerCacheKey(merCode, true);
        }
        if (signKey == null) {
            logger.error("商户号:" + merCode + " 获取商户密钥失败！");
            return getRespCode(GlobalConstant.DESK_RESEND_SIGN_ERROR, "验签失败", null, null);
        }
        if (!checkSign(realMsg, signKey, sign)) {
            logger.error("商户号:" + merCode + " 验签失败，重新加载密钥后验签！");
            signKey = Tools.getMerCacheKey(merCode, true);
            if (!checkSign(realMsg, signKey, sign)) {
                logger.error("商户号：" + merCode + " 重新验签失败");
                return getRespCode(GlobalConstant.DESK_RESEND_SIGN_ERROR, "验签失败", null, null);
            }
        }

        int count = 0;
        // 先查询再删除，如果没有数据，直接返回正常
        try {
            count = deskFailedMsgRepository.countAllByMerCodeAndTermCodeAndUuid(merCode, termCode, uuid);
            if (count == 0) {
                return getRespCode(GlobalConstant.DESK_RESEND_SUCCESS, null, null, uuid);
            }
            count = deskFailedMsgRepository.deleteAllByMerCodeAndTermCodeAndUuid(merCode, termCode, uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return getRespCode(GlobalConstant.DESK_RESEND_PROC_ERROR, "处理异常", null, null);
        }

        logger.info("merCode:" + merCode + "termCode:" + termCode + ",delete:" + count);

        return getRespCode(GlobalConstant.DESK_RESEND_SUCCESS, null, null, uuid);

    }


    // 校验签名
    private static boolean checkSign(String message, String key, String sign) {
        return StringUtils.upperCase(ParamsSign.Encrypt(message + key, "")).equals(sign);
    }

    private static String getRespCode(String respCode, String errorDetail, String printContent, String uuid) {
        JSONObject jsonObject = new JSONObject();
        if (respCode != null) {
            jsonObject.put("respCode", respCode);
        }
        if (errorDetail != null) {
            jsonObject.put("errorDetail", errorDetail);
        }
        if (printContent != null) {
            jsonObject.put("printContent", printContent);
        }
        if (uuid != null) {
            jsonObject.put("uuid", uuid);
        }
        return JSONObject.toJSONString(jsonObject);
    }
}

