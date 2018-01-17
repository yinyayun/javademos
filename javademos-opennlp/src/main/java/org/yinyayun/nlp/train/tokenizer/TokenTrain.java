package org.yinyayun.nlp.train.tokenizer;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 * @author yinyayun
 */
public class TokenTrain {
    public static void main(String[] args) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        ObjectStream<String> lineStream = new PlainTextByLineStream(
                () -> new FileInputStream("C:/Users/yinyayun/Desktop/xyz/词法/lexical-corpus.txt"), charset);
        ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);
        TokenizerModel model;
        try {
            System.out.println("开始训练...");
            TrainingParameters parameters = TrainingParameters.defaultParams();
            parameters.put(TrainingParameters.THREADS_PARAM, Integer.toString(5));
            model = TokenizerME.train(sampleStream, new TokenizerFactory("cn", null, false, null), parameters);
            System.out.println("结束训练...");
        }
        finally {
            sampleStream.close();
        }
        OutputStream modelOut = null;
        try {
            System.out.println("保存模型...");
            modelOut = new BufferedOutputStream(new FileOutputStream("data/xyz-token.model"));
            model.serialize(modelOut);
        }
        finally {
            if (modelOut != null)
                modelOut.close();
        }
    }
}
