package org.yinyayun.demos.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

/**
 * @author yinyayun 解析参数启动模板
 */
public abstract class JCommanderExecutorAbstract<T> {
    @Parameter(names = "-propertiesPath", description = "properties file path,default is resource/system.properties")
    private String propertiesPath;

    protected void executeMain(Class<T> clazz, String[] args) {
        PropertiesLoader<T> propertiesLoader = null;
        Logger logger = null;
        try {
            initJcommander(args);
            propertiesLoader = initProperties(clazz);
            logger = initLog4j(propertiesLoader, clazz);
        }
        catch (Throwable t) {
            System.err.println("init error：" + t.getMessage());
            System.exit(-1);
        }
        try {
            System.out.println("开始执行...");
            execute(propertiesLoader, logger, args);
            System.out.println("执行结束...");
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }

    protected abstract void execute(PropertiesLoader<T> propertiesLoader, Logger logger, String[] args)
            throws Exception;

    /**
     * 通过Jcommander加载参数
     * 
     * @param args
     */
    private void initJcommander(String[] args) {
        JCommander jcmdr = new JCommander(this);
        try {
            jcmdr.parse(args);
            System.out.println("propertiesPath is :" + propertiesPath);
        }
        catch (ParameterException e) {
            jcmdr.usage();
            try {
                Thread.sleep(500);
            }
            catch (Exception e1) {
            }
            throw e;
        }
    }

    private PropertiesLoader<T> initProperties(Class<T> clazz) throws FileNotFoundException, IOException {
        if (StringUtils.isNotEmpty(propertiesPath)) {
            return new PropertiesLoader<T>(propertiesPath);
        }
        else {
            return new PropertiesLoader<T>(clazz, "system.properties");
        }
    }

    private Logger initLog4j(PropertiesLoader<T> propertiesLoader, Class<T> clazz) throws IOException {
        boolean exist = propertiesLoader.checkKeyExist(PropertiesLoader.LOG_OUT_DIR);
        String outDir;
        if (exist) {
            outDir = propertiesLoader.getString(PropertiesLoader.LOG_OUT_DIR);
        }
        else {
            System.out.println("not exist params:" + PropertiesLoader.LOG_OUT_DIR);
            outDir = System.getProperty("user.dir").concat(File.separator).concat("logs");
        }

        Properties properties = new Properties();
        properties.load(JCommanderExecutorAbstract.class.getClassLoader().getResourceAsStream("log4j.properties"));
        properties.setProperty(PropertiesLoader.LOG_OUT_DIR, outDir);
        System.setProperty(PropertiesLoader.LOG_OUT_DIR, outDir);
        System.out.println("use log out dir:" + outDir);
        PropertyConfigurator.configure(properties);
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.info("log4j init ok!");
        return logger;
    }
}
