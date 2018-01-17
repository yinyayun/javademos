/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.nlp.corenlp;

import java.util.Properties;

import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

import edu.stanford.nlp.pipeline.StanfordCoreNLPServer;

/**
 * CoreNLPServerDemo.java 启动CoreNLP服务
 * 
 * @author yinyayun
 */
public class CoreNLPServerDemo extends JCommanderExecutorAbstract<CoreNLPServerDemo> {
    public static void main(String[] args) {
        CoreNLPServerDemo demo = new CoreNLPServerDemo();
        demo.executeMain(CoreNLPServerDemo.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<CoreNLPServerDemo> propertiesLoader, Logger logger, String[] args)
            throws Exception {
//        Properties properties = new Properties();
//        properties.load(this.getClass().getClassLoader().getResourceAsStream("StanfordCoreNLP-chinese.properties"));
        StanfordCoreNLPServer server = new StanfordCoreNLPServer(9000, 30000, true);
        server.run();
    }

}
