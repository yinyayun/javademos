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
import org.apache.spark.api.java.function.FlatMapFunction;
import org.slf4j.Logger;
import org.yinyayun.demos.common.PropertiesLoader;
import org.yinyayun.demos.spark.executor.SparkExecutorAbstract;

/**
 * GroupByKeyDemo.java
 *
 * @author yinyayun
 */
public class GroupByKeyDemo extends SparkExecutorAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    public GroupByKeyDemo(String appName) {
        super(appName);
    }

    public static void main(String[] args) {
        new GroupByKeyDemo("GroupByKeyDemo").executeSparkMain(args);
    }

    @Override
    protected void executeSpark(JavaSparkContext sparkContext, PropertiesLoader<SparkExecutorAbstract> propertiesLoader,
            Logger logger) throws Exception {
        List<String> lines = FileUtils.readLines(new File(propertiesLoader.getString("data.file")));
    }

}
