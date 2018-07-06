package com.cil.Mongo;

import com.alibaba.fastjson.JSONObject;
import com.cil.Global.GlobalConfig;
import com.cil.Tools.MongoDBUtil;
import com.cil.Tools.Tools;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by bob on 2017.08.09.
 */
public class TerminalColl {

    private static Logger logger = LoggerFactory.getLogger(TerminalColl.class.getName());

    /**
     * 获取对应数据库参数
     */
    private static MongoCollection<Document> coll = MongoDBUtil.getInstance().getCollection(GlobalConfig.getValue("mongo_config"), "config", "terminal");

    /**
     * @param agentCode,merCode,machineCode,termModel
     * @return 终端文本
     */
    public static JSONObject findOne(String agentCode, String merCode, String machineCode, String termModel) {
        final List<JSONObject> list = new ArrayList<JSONObject>();
        if (!Tools.isBlank(agentCode) && !Tools.isBlank(merCode) && !Tools.isBlank(machineCode) && !Tools.isBlank(termModel)) {
            FindIterable<Document> documents = coll.find(new Document("agentCode", agentCode)
                    .append("merCode", merCode)
                    .append("machineCode", machineCode)
                    .append("termModel", termModel));

            documents.forEach(new Block<Document>() {
                @Override
                public void apply(Document document) {
                    JSONObject jsonObject = JSONObject.parseObject(document.toJson());
                    list.add(jsonObject);
                }
            });
            if (list.size() == 1) {
                return list.get(0);
            } else {
                logger.error("find by agentCode:{}, merCode:{}, machineCode:{}, termModel{},counts:{}", agentCode, merCode, machineCode, termModel, list.size());
            }
        }
        return null;
    }

    /**
     * 获取所有终端密钥信息
     * @return Map key:agentCode+merCode+machineCode+termModel,value:msign
     */
    public static Map<String, String> findTermMsignMap() {
        final Map<String, String> map = new HashMap<>();
        FindIterable<Document> iter = coll.find();
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                JSONObject jsonObject = JSONObject.parseObject(document.toJson());
                String agentCode = jsonObject.getString("agentCode");
                String merCode = jsonObject.getString("merCode");
                String machineCode = jsonObject.getString("machineCode");
                String termModel = jsonObject.getString("termModel");
                if (!Tools.isBlank(agentCode) && !Tools.isBlank(merCode) && !Tools.isBlank(machineCode) && !Tools.isBlank(termModel)) {
                    map.put(agentCode + merCode + machineCode + termModel, jsonObject.getString("msign"));
                }
            }
        });
        return map;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = TerminalColl.findOne("123456", "bobtest1", "bobtest1", "test");
        if (jsonObject != null) {
            System.out.println(jsonObject.get("msign"));
        }

        Map<String, String> termMap = TerminalColl.findTermMsignMap();
        Iterator it = termMap.keySet().iterator();
        while (it.hasNext()){
            String key = (String)it.next();
            System.out.println(key);
            System.out.println(termMap.get(key));
        }
    }
}
