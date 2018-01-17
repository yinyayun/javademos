/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.nlp.tokenizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * TokenizerUseTrainModel.java
 *
 * @author yinyayun
 */
public class TokenizerUseTrainModel {
    public static void main(String[] args) {
        try (InputStream modelIn = new FileInputStream("data/token-corenlp-cn.model")) {
            TokenizerModel model = new TokenizerModel(modelIn);
            Tokenizer tokenizer = new TokenizerME(model);
            String tokens[] = tokenizer.tokenize(
                    "苏州利凯士得电动车有限公司是电动观光车，电动巡逻车，电动老爷车的优质生产制造厂家，竭诚为您提供全新优质的舟山电动游览观光车 14座电动观光车厂家，利凯士得四轮电动车游览，常熟地区8座电动观光车");
            System.out.println(Arrays.toString(tokens));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
