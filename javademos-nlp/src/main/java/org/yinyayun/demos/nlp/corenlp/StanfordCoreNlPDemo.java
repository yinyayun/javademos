/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.nlp.corenlp;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

/**
 * StanfordCoreNlPDemo.java
 *
 * @author yinyayun
 */
public class StanfordCoreNlPDemo extends JCommanderExecutorAbstract<StanfordCoreNlPDemo> {
    public static void main(String[] args) {
        StanfordCoreNlPDemo demo=new StanfordCoreNlPDemo();
        demo.executeMain(StanfordCoreNlPDemo.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<StanfordCoreNlPDemo> propertiesLoader, Logger logger, String[] args)
            throws Exception {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        //
        String text = "I hate you";
        Annotation document = new Annotation(text);
        pipeline.annotate(document);
        pipeline.prettyPrint(document, System.out);
    }

}
