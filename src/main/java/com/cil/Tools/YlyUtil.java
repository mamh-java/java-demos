package com.cil.Tools;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalConfig;
import com.cil.Global.GlobalConstant;
import com.cil.Model.MsgDataModel;
import com.cil.Model.YlyRespModel;
import com.cil.Mongo.MerchantColl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by bob on 2017.08.04.
 */
public class YlyUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void ProcessMessage(MsgDataModel dataModel, JSONObject merchantInfo, String msign, String modelStr) {

        // 获取打印文本
        String content = processYlyModel(dataModel, modelStr);

        // 发送打印请求
        String result = sendRequest(content, merchantInfo.getString("machineCode"), msign, GlobalConfig.getValue("apiKey"), GlobalConfig.getValue("partner"));
        JSONObject resultJson = JSONObject.parseObject(result);
        String resultStatus = resultJson.getString("status");
        if (resultStatus.equals(GlobalConstant.PRINT_STATUS_SUCCESS)) {
            // 记录在数据库（print_result）
        } else if (resultStatus.equals(GlobalConstant.PRINT_STATUS_TIMEOUT)) {
            // 直接重新发送
        } else {
            // sendEmail
        }
        System.out.println(result);
    }

    // TODO
    public void ProcessResponse(YlyRespModel respModel) {
        // 签名验证:失败忽略
        // 根据dataId和machine_code获取打印结果信息，如果不存在则输出日志
        // 如果存在，并且状态=1，则更新数据库为成功，其他状态更新重试次数+1，并重新发送
    }

    private String processYlyModel(MsgDataModel dataModel, String modelStr) {
        String content = "";
        String tmpModelStr = modelStr;
        while (tmpModelStr.indexOf("{$") != -1) {
            content += tmpModelStr.substring(0, tmpModelStr.indexOf("{$"));
            tmpModelStr = tmpModelStr.substring(tmpModelStr.indexOf("{$") + 2);
            String methodName = tmpModelStr.substring(0, tmpModelStr.indexOf("}"));
            try {
                Method m = dataModel.getClass().getDeclaredMethod(methodName);
                String result = (String) m.invoke(dataModel);
                content += result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            if (tmpModelStr.indexOf("}") == -1) {
                logger.error("模板格式错误：" + modelStr);
                return null;
            }
            tmpModelStr = tmpModelStr.substring(tmpModelStr.indexOf("}") + 1);
        }
        content += tmpModelStr;
        return content;
    }

    //打印机打印消息
    private String sendRequest(String content, String machineCode, String msign, String apiKey, String partner) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("partner", partner);
        params.put("machine_code", machineCode);
        String time = String.valueOf(System.currentTimeMillis());
        params.put("time", time);
        String sign = signRequest(params, apiKey, msign);
        params.put("sign", sign);
        String param = "partner=" + partner + "&machine_code=" + machineCode + "&content=" + content + "&sign=" + sign + "&time=" + time;
        return HttpTools.sendPost("http://open.10ss.net:8888", param);
    }


    /**
     * 打印签名
     *
     * @param params
     * @return
     */
    private String signRequest(Map<String, String> params, String apiKey, String mKey) {
        Map<String, String> sortedParams = new TreeMap<String, String>();
        sortedParams.putAll(params);
        Set<Map.Entry<String, String>> paramSet = sortedParams.entrySet();
        StringBuilder query = new StringBuilder();
        query.append(apiKey);
        for (Map.Entry<String, String> param : paramSet) {
            query.append(param.getKey());
            query.append(param.getValue());
        }
        query.append(mKey);
        String encryptStr = MD5.MD5Encode(query.toString()).toUpperCase();
        return encryptStr;
    }

    public static void main(String[] args) throws Exception {

//        MsgDataModel msg = new MsgDataModel();
//        msg.setMerCode("111");
//        msg.setCardBrand("WXP");
//        String methodName = "getMerCode";
//        //获取方法
//        Method m = msg.getClass().getDeclaredMethod(methodName);
//        //调用方法
//        String result = (String) m.invoke(msg);
//        System.out.println(result);

//        YlyUtil util = new YlyUtil();
//        MsgDataModel msg = new MsgDataModel();
//        msg.setOrderAmt("1.00");
//        msg.setTransTime("2017-08-10");
//        msg.setMerName("123");
//        String model = "<FH2><FB>签购单\\n</FB></FH2>用户联\\n支付\\n********************\\n金额：<FH2>{$getOrderAmt}</FH2>\\n--------------------\\n交易时间：{$getTransTime}\\n商户名称：{$getMerName}\\n";
//        System.out.println(util.processYlyModel(msg, model));

        YlyUtil util = new YlyUtil();
        MsgDataModel msg = new MsgDataModel();
        msg.setOrderAmt("1.00");
        msg.setTransTime("2017-08-10");
        msg.setMerName("123");
        msg.setMerCode("999290053110001");
        msg.setTermCode("98764");
        msg.setCardBrand("支付宝");
        msg.setAccountNum("123***456@qq.com");
        msg.setChanOrderNum("12345678");
        msg.setOrderNum("7654321");
        JSONObject merchantInfo = MerchantColl.findOne("999290053110001");
        String modelStr = "<FH2><FB><center>签购单</center></FB></FH2>\\r<center>用户联</center>\\r<center>支付</center>\\r*************************\\r金额：<FH2>{$getOrderAmt}</FH2>\\r-------------------------\\r交易时间：{$getTransTime}\\r商户名称：{$getMerName}\\r商户号：{$getMerCode}\\r终端号：{$getTermCode}\\r-------------------------\\r支付类型：{$getCardBrand}\\r交易账号{$getAccountNum}\\r-------------------------\\r渠道订单号{$getChanOrderNum}\\r商户订单号{$getOrderNum}\\r\\r";
        util.ProcessMessage(msg, merchantInfo, "6rpqppzdhv2e", modelStr);


    }
}

