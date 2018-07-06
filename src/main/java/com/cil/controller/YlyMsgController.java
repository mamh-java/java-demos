package com.cil.controller;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalCache;
import com.cil.Global.GlobalConfig;
import com.cil.Model.MsgDataModel;
import com.cil.Model.ResponseState;
import com.cil.Model.YlyRespModel;
import com.cil.Mongo.MerchantColl;
import com.cil.Tools.Tools;
import com.cil.Tools.YlyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bob on 2017.08.04.
 * 易联云交互逻辑
 */
@RestController
@RequestMapping("yilianyun")
public class YlyMsgController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param dataModel 通过kafka传入报文信息进行处理并推送至易联云
     */
    @PostMapping(value = "/kfkPush", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    void kfkPush(@RequestBody MsgDataModel dataModel) {

        if (dataModel == null) {
            logger.info("推送报文为空:" + dataModel);
            return;
        }

        JSONObject merchantInfo = null;
        // 获取商户信息
        if (!Tools.isBlank(dataModel.getMerCode())) {
            merchantInfo = GlobalCache.merchantMap.get(dataModel.getMerCode());
        } else {
            logger.info("推送报文商户号为空:" + dataModel);
            return;
        }
        if (merchantInfo == null) {
            merchantInfo = MerchantColl.findOne(dataModel.getMerCode());
            if (merchantInfo == null) {
                logger.info("查询商户信息失败,商户号:" + dataModel.getMerCode());
                return;
            }
        }
        String machineCode = merchantInfo.getString("machineCode");
        String termModel = merchantInfo.getString("termCode");
        if (Tools.isBlank(machineCode) || Tools.isBlank(termModel)) {
            return;
        }

        // 获取终端密钥
        String msign = GlobalCache.terminalMap.get(GlobalConfig.getValue("ylyAgentCode") + dataModel.getMerCode()
                + machineCode + termModel);

        // 获取打印模板
        String printContent = GlobalCache.printModelMap.get(machineCode + termModel);

        // 处理报文
        YlyUtil ylyUtil = new YlyUtil();
        ylyUtil.ProcessMessage(dataModel, merchantInfo, msign, printContent);
    }

    /**
     * @param respModel 获取易联云返回报文进行处理
     */
    @PostMapping(value = "/ylyPush", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    void ylyPush(@RequestBody YlyRespModel respModel) {

        if (respModel == null) {
            logger.info("推送报文为空:" + respModel);
            return;
        }

        // 根据dataId和返回结果更新推送信息状态
        YlyUtil ylyUtil = new YlyUtil();
        ylyUtil.ProcessResponse(respModel);
    }

    // 用于测试https请求
    @PostMapping(value = "/testPush", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String testPush(@RequestBody JSONObject obj) {
        JSONObject respJson = new JSONObject();

        if (obj == null) {
            logger.info("推送报文为空");
            respJson.put("responseCode","999");
        }else{
            logger.info("推送报文:" + JSONObject.toJSONString(obj));
            respJson.put("responseCode","000");
        }

        return JSONObject.toJSONString(respJson);
    }
}

