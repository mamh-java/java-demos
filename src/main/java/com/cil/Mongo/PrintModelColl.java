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
public class PrintModelColl {

    private static Logger logger = LoggerFactory.getLogger(PrintModelColl.class.getName());

    /**
     * 获取对应数据库参数
     */
    private static MongoCollection<Document> coll = MongoDBUtil.getInstance().getCollection(GlobalConfig.getValue("mongo_config"), "config", "printModel");

    /**
     * @param machineCode,termModel
     * @return 打印模板文本
     */
    public static JSONObject findOne(String machineCode, String termModel) {
        final List<JSONObject> list = new ArrayList<JSONObject>();
        if (!Tools.isBlank(machineCode) && !Tools.isBlank(termModel)) {
            FindIterable<Document> documents = coll.find(new Document("machineCode", machineCode)
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
                logger.error("find by machineCode:{}, termModel{},counts:{}", machineCode, termModel, list.size());
            }
        }
        return null;
    }

    /**
     * 获取所有打印模板信息
     * @return Map key:machineCode+termModel,value:content
     */
    public static Map<String, String> findContentMap() {
        final Map<String, String> map = new HashMap<>();
        FindIterable<Document> iter = coll.find();
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                JSONObject jsonObject = JSONObject.parseObject(document.toJson());
                String machineCode = jsonObject.getString("machineCode");
                String termModel = jsonObject.getString("termModel");
                if ( !Tools.isBlank(machineCode) && !Tools.isBlank(termModel)) {
                    map.put(machineCode + termModel, jsonObject.getString("content"));
                }
            }
        });
        return map;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = PrintModelColl.findOne("bobtest1", "test");
        System.out.println(jsonObject);
        if (jsonObject != null) {
            System.out.println(jsonObject.get("content"));
        }

        Map<String, String> termMap = PrintModelColl.findContentMap();
        Iterator it = termMap.keySet().iterator();
        while (it.hasNext()){
            String key = (String)it.next();
            System.out.println(key);
            System.out.println(termMap.get(key));
        }
    }
}
