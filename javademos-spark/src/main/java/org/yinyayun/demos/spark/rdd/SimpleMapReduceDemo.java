/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.spark.rdd;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.yinyayun.demos.common.PropertiesLoader;
import org.yinyayun.demos.spark.executor.SparkExecutorAbstract;

/**
 * MapReduce.java 简单的map reduce
 * 
 * @author yinyayun
 */
public class SimpleMapReduceDemo extends SparkExecutorAbstract implements Serializable {

    private static final long serialVersionUID = 1L;

    public SimpleMapReduceDemo(String appName) {
        super(appName);
    }

    public static void main(String[] args) {
        new SimpleMapReduceDemo("SimpleMapReduce").executeSparkMain(args);
    }

    @Override
    protected void executeSpark(JavaSparkContext sparkContext, PropertiesLoader<SparkExecutorAbstract> propertiesLoader,
            Logger logger) throws Exception {
        List<String> lines = FileUtils.readLines(new File(propertiesLoader.getString("data.file")));
        // lambda表达式也可替换成 Function
        JavaRDD<Integer> lens = sparkContext.parallelize(lines).map(x -> x.length());
        int totalLen = lens.reduce((x, y) -> x + y);
        System.out.println("总计字符：" + totalLen);
    }
}
