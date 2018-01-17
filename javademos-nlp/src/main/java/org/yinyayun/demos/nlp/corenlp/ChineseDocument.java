/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.nlp.corenlp;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.simple.Document;

/**
 * ChineseDocument.java
 *
 * @author yinyayun
 */
public class ChineseDocument extends Document {
    static {
        EMPTY_PROPS.setProperty("customAnnotatorClass.segment", "edu.stanford.nlp.pipeline.ChineseSegmenterAnnotator");
        EMPTY_PROPS.setProperty("segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz");
        EMPTY_PROPS.setProperty("segment.sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese");
        EMPTY_PROPS.setProperty("segment.serDictionary",
                "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz");
        EMPTY_PROPS.setProperty("segment.sighanPostProcessing", "true");
        EMPTY_PROPS.setProperty("ssplit.boundaryTokenRegex", "[.]|[!?]+|[。]|[！？]+");
        EMPTY_PROPS.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/chinese-distsim/chinese-distsim.tagger");
    }

    public ChineseDocument(Annotation ann) {
        super(ann);
    }

    public ChineseDocument(String input) {
        super(input);
    }

}
