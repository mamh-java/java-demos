package com.cil.Task;

import com.cil.Global.GlobalCache;
import com.cil.Global.GlobalConstant;
import com.cil.Model.DeskSendCacheModel;
import com.cil.Model.DeskTermEntity;
import com.cil.Model.OutPushConfigEntity;
import com.cil.Model.OutSendCacheModel;
import com.cil.Repository.DeskFailedMsgRepository;
import com.cil.Repository.DeskTermRepository;
import com.cil.Repository.OutPushConfigRepository;
import com.cil.Tools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by bob on 2017.08.08.
 */
@Component
public class ScheduleMission {
    private static Logger logger = LoggerFactory.getLogger(ScheduleMission.class);
    @Autowired
    private DeskTermRepository deskTermRepository;
    @Autowired
    private OutPushConfigRepository outPushConfigRepository;
    @Autowired
    private DeskFailedMsgRepository deskFailedMsgRepository;

    /**
     * 配置信息重新加载
     */
    @Scheduled(cron = "0 */10 * * * ?")
    public void reloadMission() {
        // 加载商户信息
//        GlobalCache.merchantMap = MerchantColl.findMerMap();
//        logger.info("重新加载：" + GlobalCache.merchantMap.size() + "个商户信息！");
//        // 加载终端信息
//        GlobalCache.terminalMap = TerminalColl.findTermMsignMap();
//        // 加载打印模板信息
//        GlobalCache.printModelMap = PrintModelColl.findContentMap();

        List<OutPushConfigEntity> outPushConfigEntityList = outPushConfigRepository.findAllByStatus(GlobalConstant.OUT_CONFIG_OPEN);
        Map<String, OutPushConfigEntity> outPushConfigEntityTmpMap = new HashMap<String, OutPushConfigEntity>();
        for (int i = 0; i < outPushConfigEntityList.size(); i++) {
            OutPushConfigEntity outPushConfigEntity = outPushConfigEntityList.get(i);
            outPushConfigEntityTmpMap.put(outPushConfigEntity.getInsCode().trim() + ";" + outPushConfigEntity.getMerCode().trim(), outPushConfigEntity);
        }
        GlobalCache.outPushConfigMap = outPushConfigEntityTmpMap;
        logger.info("重新加载了" + outPushConfigEntityList.size() + "个对外配置信息！");
    }

    // 更新超时数据
    @Scheduled(cron = "0 0 23 * * ?")
    public void deleteDeskTimeoutParams() {
        Iterator<String> it = GlobalCache.deskTermMap.keySet().iterator();
        long zeroTimestamp = Tools.getZeroTimestamp();
        while (it.hasNext()) {
            String key = it.next();
            DeskTermEntity entity = GlobalCache.deskTermMap.get(key);
            if (entity.getUpdateTime().getTime() < zeroTimestamp) {
                entity.setStatus(GlobalConstant.DESK_TERM_TIMEOUT);
                GlobalCache.deskTermMap.put(key, entity);
            }
            deskTermRepository.saveAndFlush(entity);
        }
        logger.info("成功更新桌面版终端信息到数据库！");
    }

    // 去除缓存中超时数据
    @Scheduled(cron = "0 30 23 * * ?")
    public void updateDeskParams() {
        Iterator<String> it = GlobalCache.deskTermMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            DeskTermEntity entity = GlobalCache.deskTermMap.get(key);
            if (entity.getStatus().equals(GlobalConstant.DESK_TERM_TIMEOUT)) {
                GlobalCache.deskTermMap.remove(key);
            }
        }
        logger.info("成功清除缓存中桌面版终端超时数据！");
    }

    // 对缓存中的数据重新发送
    @Scheduled(cron = "*/5 * * * * ?")
    public void reSendDesk() {
        if (GlobalCache.deskSendModelMap.size() > 0) {
            Iterator<String> it = GlobalCache.deskSendModelMap.keySet().iterator();
            try {
                while (it.hasNext()) {
                    String key = it.next();
                    DeskSendCacheModel model = GlobalCache.deskSendModelMap.get(key);
                    if (model != null && !model.isRetry() && model.getSendTime().before(new Date())) {
                        Tools.reSendPrintMsg(model, key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (GlobalCache.outSendCacheModelMap.size() > 0) {
            Iterator<String> it = GlobalCache.outSendCacheModelMap.keySet().iterator();
            try {
                while (it.hasNext()) {
                    String key = it.next();
                    OutSendCacheModel model = GlobalCache.outSendCacheModelMap.get(key);
                    if (model != null && !model.isRetry() && model.getSendTime().before(new Date())) {
                        Tools.reSendOutMsg(model, key);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    // 删除超过7天的失败打印信息
    @Scheduled(cron = "0 0 5 * * ?")
    public void deleteDeskFailedMsgs() {
        logger.info("开始清理桌面版推送失败信息！");
        Timestamp ts = new Timestamp(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        int num = deskFailedMsgRepository.deleteByCreateTimeBefore(ts);
        logger.info("成功清理了" + num + "个桌面版推送失败信息！");
    }

}
