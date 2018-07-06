package com.cil.Global;


import com.cil.Tools.PropertiesLoader;
import com.cil.Tools.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局参数配置
 */
public class GlobalConfig {

    private static Map<String, String> propertyMap = new HashMap<>();

    //private static PropertiesLoader propertiesLoader = new PropertiesLoader("params_" + Tools.getEnv("FOXBILL_ENV") + ".properties");
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("params_testing.properties");

    public static String getValue(String key) {
        String value = propertyMap.get(key);
        if (value == null) {
            value = propertiesLoader.get(key);
            propertyMap.put(key, value);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(GlobalConfig.getValue("mongo_config"));
    }

}
