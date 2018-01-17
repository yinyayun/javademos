/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.nlp.corenlp;

import java.util.Properties;

import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * CoreNLPChineseDemo.java
 *
 * @author yinyayun
 */
public class CoreNLPChineseDemo extends JCommanderExecutorAbstract<CoreNLPChineseDemo> {
    public static void main(String[] args) {
        CoreNLPChineseDemo demo = new CoreNLPChineseDemo();
        demo.executeMain(CoreNLPChineseDemo.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<CoreNLPChineseDemo> propertiesLoader, Logger logger, String[] args)
            throws Exception {
        String text = "hello,are you speak spanish.视频也只能上传10个啊";
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("StanfordCoreNLP-chinese.properties"));
        properties.setProperty("annotators", "segment, ssplit");
        StanfordCoreNLP corenlp = new StanfordCoreNLP(properties);
        Annotation annotation = new Annotation(text);
        corenlp.annotate(annotation);
        corenlp.prettyPrint(annotation, System.out);
    }

}
