package com.cil.Tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 资源加载器
 */
public class PropertiesLoader {

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    private static Properties properties = new Properties();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PropertiesLoader(String filePath) {
        properties = loadProperties(filePath);
    }

    private Properties loadProperties(String filePath) {
        Properties pt = new Properties();
        Resource resource = resourceLoader.getResource(filePath);
        InputStream in = null;
        try {
            in = resource.getInputStream();
            pt.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return pt;
    }

    public String get(String key) {
        return (String) properties.get(key);
    }

}
