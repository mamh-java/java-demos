package com.atguigu.mvcapp.utils;

import java.util.HashMap;
import java.util.Map;

public class FileUploadAppProperties {

    private Map<String, String> properties = new HashMap<>();

    private FileUploadAppProperties() {

    }

    private static FileUploadAppProperties instance = new FileUploadAppProperties();

    public static FileUploadAppProperties getInstance() {
        return instance;
    }


    public void addProperty(String key, String value) {
        properties.put(key, value);
    }


    public String getProperty(String key) {
        return properties.get(key);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}
