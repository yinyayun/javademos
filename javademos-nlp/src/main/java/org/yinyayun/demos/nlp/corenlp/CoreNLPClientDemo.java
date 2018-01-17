/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.nlp.corenlp;

import java.util.Properties;

import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLPClient;

/**
 * CoreNLPClient.java
 *
 * @author yinyayun
 */
public class CoreNLPClientDemo extends JCommanderExecutorAbstract<CoreNLPClientDemo> {
    public static void main(String[] args) {
        CoreNLPClientDemo clientDemo = new CoreNLPClientDemo();
        clientDemo.executeMain(CoreNLPClientDemo.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<CoreNLPClientDemo> propertiesLoader, Logger logger, String[] args)
            throws Exception {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("StanfordCoreNLP-chinese.properties"));
        StanfordCoreNLPClient pipeline = new StanfordCoreNLPClient(properties, "localhost", 9000, 2);

        String text = "请问被保险人是哪位呢？";
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

    }

}
