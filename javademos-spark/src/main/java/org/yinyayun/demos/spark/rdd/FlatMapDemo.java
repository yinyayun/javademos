/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.spark.rdd;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.slf4j.Logger;
import org.yinyayun.demos.common.PropertiesLoader;
import org.yinyayun.demos.spark.executor.SparkExecutorAbstract;

/**
 * FlatMapDemo.java
 *
 * @author yinyayun
 */
public class FlatMapDemo extends SparkExecutorAbstract implements Serializable {
    private static final long serialVersionUID = 1L;

    public FlatMapDemo(String appName) {
        super(appName);
    }

    public static void main(String[] args) {
        new FlatMapDemo("FlatMapDemo").executeSparkMain(args);
    }

    @Override
    protected void executeSpark(JavaSparkContext sparkContext, PropertiesLoader<SparkExecutorAbstract> propertiesLoader,
            Logger logger) throws Exception {
        List<String> lines = FileUtils.readLines(new File(propertiesLoader.getString("data.file")));
        // lambda表达式也可替换成 Function
        List<String> collections = sparkContext.parallelize(lines).flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String t) throws Exception {
                List<String> words = new ArrayList<String>();
                for (String word : t.split(" ")) {
                    words.add(word);
                }
                return words.iterator();
            }
        }).collect();
         System.out.println(collections);
    }
}
