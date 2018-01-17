/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.spark.executor;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

/**
 * SparkExecutorAbstract.java
 *
 * @author yinyayun
 */
public abstract class SparkExecutorAbstract extends JCommanderExecutorAbstract<SparkExecutorAbstract>
        implements Serializable {
    private static final long serialVersionUID = 1L;
    private String appName;

    public SparkExecutorAbstract(String appName) {
        this.appName = appName;
    }

    public void executeSparkMain(String[] args) {
        super.executeMain(SparkExecutorAbstract.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<SparkExecutorAbstract> propertiesLoader, Logger logger, String[] args)
            throws Exception {
        String jarPath = propertiesLoader.getString("jars.path");
        String master = propertiesLoader.getString("spark.master");
        logger.info("jar path:{},mstaer:{}", jarPath, master);
        //
        SparkConf sparkConf = createSparkConf(master, jarPath);
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        try {
            executeSpark(sparkContext, propertiesLoader, logger);
        }
        finally {
            sparkContext.stop();
        }
    }

    public SparkConf createSparkConf(String master, String jarPath) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName(appName);
        // 设置jars
        if (StringUtils.isNotEmpty(jarPath)) {
            sparkConf.setJars(new String[]{jarPath});
        }
        sparkConf.setMaster(master);
        sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        return sparkConf;
    }

    protected abstract void executeSpark(JavaSparkContext sparkContext,
            PropertiesLoader<SparkExecutorAbstract> propertiesLoader, Logger logger) throws Exception;

}
