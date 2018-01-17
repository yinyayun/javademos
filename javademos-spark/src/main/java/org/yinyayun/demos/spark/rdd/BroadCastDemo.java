/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.spark.rdd;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.broadcast.Broadcast;
import org.slf4j.Logger;
import org.yinyayun.demos.common.PropertiesLoader;
import org.yinyayun.demos.spark.executor.SparkExecutorAbstract;

/**
 * BraodCastDemo.java
 *
 * @author yinyayun
 */
public class BroadCastDemo extends SparkExecutorAbstract implements Serializable {
    private static final long serialVersionUID = 1L;

    public BroadCastDemo(String appName) {
        super(appName);
    }

    public static void main(String[] args) {
        new BroadCastDemo("BroadCastDemo").executeSparkMain(args);
    }

    @Override
    protected void executeSpark(JavaSparkContext sparkContext, PropertiesLoader<SparkExecutorAbstract> propertiesLoader,
            Logger logger) throws Exception {
        Broadcast<String> broadcast = sparkContext.broadcast("java");
        List<String> lines = FileUtils.readLines(new File(propertiesLoader.getString("data.file")));
        List<String> res = sparkContext.parallelize(lines).map(new Function<String, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String call(String v1) throws Exception {
                String value = broadcast.getValue();
                return v1.replace(value, "");
            }
        }).collect();
        System.out.println(res);
    }
}
