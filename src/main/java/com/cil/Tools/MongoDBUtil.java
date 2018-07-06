package com.cil.Tools;

import com.cil.Global.GlobalConfig;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;


/**
 * Created by bob on 2017.08.08.
 */
public class MongoDBUtil {

    private static MongoDBUtil instance = new MongoDBUtil();

    public static MongoDBUtil getInstance() {
        return instance;
    }

    /*
     * 通过URL建立mongo连接
     */
    public MongoClient createMongoDBClientWithURI(String url) {
        MongoClientURI connectionString = new MongoClientURI(url);
        return new MongoClient(connectionString);
    }

    /**
     * 获取collection对象 - 指定Collection
     * @param collName
     * @return
     */
    public MongoCollection<Document> getCollection(String url,String dbName, String collName) {
        if (null == collName || "".equals(collName)) {
            return null;
        }
        if (null == dbName || "".equals(dbName)) {
            return null;
        }
        MongoCollection<Document> collection = instance.createMongoDBClientWithURI(url).getDatabase(dbName).getCollection(collName);
        return collection;
    }

    public static void main(String[] args) {
        MongoCollection<Document> coll = MongoDBUtil.getInstance().getCollection(GlobalConfig.getValue("mongo_config"),"config","merchant");
        System.out.println(coll.count());
    }
}
