package com.cil.Global;

import com.alibaba.fastjson.JSONObject;
import com.cil.Model.DeskSendCacheModel;
import com.cil.Model.DeskTermEntity;
import com.cil.Model.OutPushConfigEntity;
import com.cil.Model.OutSendCacheModel;
import com.cil.Repository.DeskFailedMsgRepository;
import com.cil.Repository.DeskTermRepository;
import com.cil.Repository.OutPushConfigRepository;
import com.cil.grpc.SendServer;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bob on 2017.08.16.
 */
@Service
public class GlobalCache implements ApplicationContextAware {

    @Autowired
    private DeskTermRepository deskTermRepository;
    @Autowired
    private OutPushConfigRepository outPushConfigRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public static ApplicationContext applicationContext = null;

    public static DeskFailedMsgRepository deskFailedMsgRepository = null;
    /**
     * 静态参数
     */
    // key:merId,value:merchant json对象
    public static Map<String, JSONObject> merchantMap = new HashMap<String, JSONObject>();
    // key:agentCode+merCode+machineCode+termModel,value:msign
    public static Map<String, String> terminalMap = new HashMap<String, String>();
    // key:machineCode+termModel,value:content
    public static Map<String, String> printModelMap = new HashMap<String, String>();
    // key:merCode+';'+termCode,value:DeskTermEntity
    public static Map<String, DeskTermEntity> deskTermMap = new HashMap<String, DeskTermEntity>();
    // key:ip+':'+port value:IoSession
    public static Map<String, IoSession> sessionMap = new HashMap<String, IoSession>();
    // key:merCode+orderNum,value:DeskSendCacheModel
    public static Map<String, DeskSendCacheModel> deskSendModelMap = new HashMap<String, DeskSendCacheModel>();
    // key:shopId,value:shop json对象
    public static Map<String, JSONObject> shopMap = new HashMap<String, JSONObject>();
    // key:insCode+';'+merCode,value:OutPushConfigEntity
    public static Map<String, OutPushConfigEntity> outPushConfigMap = new HashMap<String, OutPushConfigEntity>();
    // key:merCode+orderNum,value:OutSendCacheModel
    public static Map<String, OutSendCacheModel> outSendCacheModelMap = new HashMap<String, OutSendCacheModel>();
    // key:merCode+';'+termCode,value:ExpireDate
    public static Map<String,Date> deskFailedMsgExpireMap = new HashMap<String,Date>();
    @PostConstruct
    public void init() throws Exception {
        // 加载商户信息
//        GlobalCache.merchantMap = MerchantColl.findMerMap();
//        logger.info("成功加载：" + GlobalCache.merchantMap.size() + "个商户信息！");
//        // 加载终端信息
//        GlobalCache.terminalMap = TerminalColl.findTermMsignMap();
//        // 加载打印模板信息
//        GlobalCache.printModelMap = PrintModelColl.findContentMap();

        // 加载桌面版终端信息
        List<DeskTermEntity> deskTermEntityList = deskTermRepository.findAllByStatus(GlobalConstant.DESK_TERM_NORMAL);
        for (int i = 0; i < deskTermEntityList.size(); i++) {
            DeskTermEntity deskTermEntity = deskTermEntityList.get(i);
            deskTermMap.put(deskTermEntity.getMerCode() + ";" + deskTermEntity.getTermCode(), deskTermEntity);
            deskTermEntity.setStatus("1");
        }
        logger.info("成功加载：" + deskTermEntityList.size() + "个桌面版终端信息！");

        // 加载对外发送信息
        List<OutPushConfigEntity> outPushConfigEntityList = outPushConfigRepository.findAllByStatus(GlobalConstant.OUT_CONFIG_OPEN);
        for (int i = 0; i < outPushConfigEntityList.size(); i++) {
            OutPushConfigEntity outPushConfigEntity = outPushConfigEntityList.get(i);
            outPushConfigMap.put(outPushConfigEntity.getInsCode().trim() + ";" + outPushConfigEntity.getMerCode().trim(), outPushConfigEntity);
        }
        logger.info("成功加载：" + outPushConfigEntityList.size() + "个对外配置信息！");

        // 开启接收kafka信息
        SendServer server = new SendServer();
        server.start();

        deskFailedMsgRepository = (DeskFailedMsgRepository) GlobalCache.getBean("deskFailedMsgRepository");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        GlobalCache.applicationContext = applicationContext;
    }

    // 对于重新实例化的bean，不能自动装载，需要直接从spring中获取
    public static Object getBean(String classType) {
        try {
            //return (RiskProcessor) applicationContext.getBean(Class.forName(classType));
            return applicationContext.getBean(classType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
