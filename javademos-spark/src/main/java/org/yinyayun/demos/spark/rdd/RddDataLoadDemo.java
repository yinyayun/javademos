/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.spark.rdd;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.spark.SparkFiles;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.yinyayun.demos.common.PropertiesLoader;
import org.yinyayun.demos.spark.executor.SparkExecutorAbstract;

/**
 * RddDataLoad.java 加载数据到RDD
 * 
 * @author yinyayun
 */
public class RddDataLoadDemo extends SparkExecutorAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    public RddDataLoadDemo(String appName) {
        super(appName);
    }

    public static void main(String[] args) {
        new RddDataLoadDemo("LoadDataToRDD").executeSparkMain(args);
    }

    @Override
    protected void executeSpark(JavaSparkContext sparkContext, PropertiesLoader<SparkExecutorAbstract> propertiesLoader,
            Logger logger) throws Exception {
        // 方式一：从spark的master节点上加载数据，因为master不存在，结果肯定为找不到文件
        JavaRDD<String> rdd1 = sparkContext.textFile(propertiesLoader.getString("data.file"));
        // 方式二：如果想spark每一个节点上都存储该文件可以：addFile,然后通过SparkFiles.get读取
        sparkContext.addFile(propertiesLoader.getString("data.file"));
        JavaRDD<String> rdd2 = sparkContext.textFile(SparkFiles.get("filename"));
        // 方式三：从驱动机上加载
        List<String> lines = FileUtils.readLines(new File(propertiesLoader.getString("data.file")));
        JavaRDD<String> rdd3 = sparkContext.parallelize(lines);
    }
}
