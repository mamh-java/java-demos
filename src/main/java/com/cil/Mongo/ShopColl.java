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
public class ShopColl {

    private static Logger logger = LoggerFactory.getLogger(ShopColl.class.getName());

    /**
     * 获取对应数据库参数
     */
    private static MongoCollection<Document> coll =  MongoDBUtil.getInstance().getCollection(GlobalConfig.getValue("mongo_tasty"),"tasty","shop");

    /**
     * @param shopId
     * @return 商户文本
     */
    public static JSONObject findOne(String shopId) {
        final List<JSONObject> list = new ArrayList<JSONObject>();
        if (!Tools.isBlank(shopId) ) {
            FindIterable<Document> documents =  coll.find(new Document("shopId", shopId));

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
                logger.error("shop find by shopId:{},counts:{}",shopId, list.size());
            }
        }
        return null;
    }

    /**
     * 获取所有商户信息
     * @return Map key:shopId,value:shop json对象
     */
    public static Map<String, JSONObject> findShopMap() {
        final Map<String,JSONObject> map = new HashMap<>();
        FindIterable<Document> iter = coll.find();
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                JSONObject jsonObject = JSONObject.parseObject(document.toJson());
                String shopId= jsonObject.getString("shopId");
                if (!Tools.isBlank(shopId)){
                    map.put(shopId,jsonObject);
                }
            }
        });
        return map;
    }

    public static long count() {
        return coll.count();
    }


    public static void main(String[] args) {
        JSONObject shop = ShopColl.findOne("bobTest");
        System.out.println(shop);
        Map<String, JSONObject> map = ShopColl.findShopMap();
        System.out.println(map.size());
        System.out.println(map.get("bobTest"));
        System.out.println(map.get("bobTest").get("signKey"));
        System.out.println(ShopColl.count());
    }

}
