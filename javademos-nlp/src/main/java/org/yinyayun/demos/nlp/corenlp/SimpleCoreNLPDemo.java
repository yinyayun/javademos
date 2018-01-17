/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.nlp.corenlp;

import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

/**
 * SimpleCoreNLPDemo.java
 *
 * @author yinyayun
 */
public class SimpleCoreNLPDemo extends JCommanderExecutorAbstract<SimpleCoreNLPDemo> {
    public static void main(String[] args) {
        SimpleCoreNLPDemo simple = new SimpleCoreNLPDemo();
        simple.executeMain(SimpleCoreNLPDemo.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<SimpleCoreNLPDemo> propertiesLoader, Logger logger, String[] args)
            throws Exception {
        String pargraph = "请问怎么查询保单?";
        Document doc = new ChineseDocument(pargraph);
        for (Sentence sent : doc.sentences()) { // Will iterate over two sentences
            for (int i = 0; i < sent.length(); i++) {
                System.out.println(
                        String.format("word:%s\tlemma:%s\tpos:%s", sent.word(i), sent.lemma(i), sent.posTag(i)));
            }
        }
    }
}
