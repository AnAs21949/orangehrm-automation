package com.orangehrm.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    Properties properties;
    public ConfigReader() throws Exception {
        properties = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        properties.load(fis);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
