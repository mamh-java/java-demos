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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bob on 2017.08.08.
 */
public class MerchantColl {

    private static Logger logger = LoggerFactory.getLogger(MerchantColl.class.getName());

    /**
     * 获取对应数据库参数
     */
    private static MongoCollection<Document> coll =  MongoDBUtil.getInstance().getCollection(GlobalConfig.getValue("mongo_config"),"config","merchant");

    /**
     * @param merId
     * @return 商户文本
     */
    public static JSONObject findOne(String merId) {
        final List<JSONObject> list = new ArrayList<JSONObject>();
        if (!Tools.isBlank(merId) ) {
            FindIterable<Document> documents =  coll.find(new Document("merId", merId));

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
                logger.error("merchant find by merId:{},counts:{}",merId, list.size());
            }
        }
        return null;
    }

    /**
     * 获取所有商户信息
     * @return Map key:merId,value:merchant json对象
     */
    public static Map<String, JSONObject> findMerMap() {
        final Map<String,JSONObject> map = new HashMap<>();
        FindIterable<Document> iter = coll.find();
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                JSONObject jsonObject = JSONObject.parseObject(document.toJson());
                String merId= jsonObject.getString("merId");
                if (!Tools.isBlank(merId)){
                    map.put(merId,jsonObject);
                }
            }
        });
        return map;
    }

    public static long count() {
        return coll.count();
    }


    public static void main(String[] args) {
//        FindIterable<Document> iter = merchant.findByMerCode("100000000000204");
//        iter.forEach(new Block<Document>() {
//            @Override
//            public void apply(Document document) {
//                JSONObject jsonObject = JSONObject.parseObject(document.toJson());
//                System.out.println(jsonObject.get("merId"));
//            }
//        });
        Map<String, JSONObject> map = MerchantColl.findMerMap();
        System.out.println(map.size());
        System.out.println(map.get("100000000000204"));
        System.out.println(map.get("100000000000204").get("agentCode"));
        System.out.println(map.get("100000000000204").get("aaa"));
        System.out.println(MerchantColl.count());
        System.out.println(MerchantColl.findOne("999290048160001"));
    }

}
