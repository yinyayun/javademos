/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.spark.rdd;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.spark.Accumulator;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.broadcast.Broadcast;
import org.slf4j.Logger;
import org.yinyayun.demos.common.PropertiesLoader;
import org.yinyayun.demos.spark.executor.SparkExecutorAbstract;

/**
 * AccumulatorDemo.java
 *
 * @author yinyayun
 */
public class AccumulatorDemo extends SparkExecutorAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    public AccumulatorDemo(String appName) {
        super(appName);
    }

    public static void main(String[] args) {
        new AccumulatorDemo("AccumulatorDemo").executeSparkMain(args);
    }

    @Override
    protected void executeSpark(JavaSparkContext sparkContext, PropertiesLoader<SparkExecutorAbstract> propertiesLoader,
            Logger logger) throws Exception {
        Accumulator<Integer> accumulator = sparkContext.accumulator(0);
        List<String> lines = FileUtils.readLines(new File(propertiesLoader.getString("data.file")));
        List<String> res = sparkContext.parallelize(lines).map(new Function<String, String>() {
            private static final long serialVersionUID = 1L;

            @Override
            public String call(String v1) throws Exception {
                if (v1.toLowerCase().indexOf("hello") >= 0) {
                    accumulator.add(1);
                }
                return v1;
            }
        }).collect();
        System.out.println(accumulator.toString());
    }

}
