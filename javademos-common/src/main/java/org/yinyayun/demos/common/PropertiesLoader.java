package org.yinyayun.demos.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yinyayun
 */
public class PropertiesLoader<T> {
    // log输出路径
    public static final String LOG_OUT_DIR = "log4j.out.dir";
    public static final String INFO_OUT_DIR = "log4j.appender.errorFile.File";
    public static final String ERROR_OUT_DIR = "log4j.appender.infoFile.File";
    private Properties properties = new Properties();

    public PropertiesLoader(Class<T> clazz, String propertieFileName) throws IOException {
        properties.load(clazz.getClassLoader().getResourceAsStream(propertieFileName));
    }

    public PropertiesLoader(String propertiesPath) throws FileNotFoundException, IOException {
        properties.load(new FileInputStream(new File(propertiesPath)));
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.valueOf(getString(key));
    }

    public float getFloat(String key) {
        return Float.valueOf(getString(key));
    }

    /**
     * 检查配置项是否存在
     * 
     * @param key
     * @return
     */
    public boolean checkKeyExist(String key) {
        return properties.containsKey(key);
    }
}
