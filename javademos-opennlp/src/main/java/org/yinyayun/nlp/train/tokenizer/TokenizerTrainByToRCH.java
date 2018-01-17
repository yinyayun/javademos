/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.nlp.train.tokenizer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.TrainingParameters;

/**
 * TokenizerTrainByToRCH.java
 *
 * @author yinyayun
 */
public class TokenizerTrainByToRCH {
    public static void main(String[] args) throws IOException {
        ObjectStream<String> lineStream = new DirectoryToStringStream(new String[]{"D:/Data/ToRCH/ToRCH2014_SEG_UTF-8",
                "D:/Data/ToRCH/ToRCH2009_UTF-8--20140720", "D:/Data/人民日报"}, "UTF-8");
        ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);
        TokenizerModel model;
        try {
            System.out.println("开始训练...");
            model = TokenizerME.train(sampleStream, new TokenizerFactory("cn", null, false, null),
                    TrainingParameters.defaultParams());
            System.out.println("结束训练...");
        }
        finally {
            sampleStream.close();
        }
        OutputStream modelOut = null;
        try {
            System.out.println("保存模型...");
            modelOut = new BufferedOutputStream(new FileOutputStream("data/token-corenlp-cn.model"));
            model.serialize(modelOut);
        }
        finally {
            if (modelOut != null)
                modelOut.close();
        }
    }
}
