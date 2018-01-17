package org.yinyayun.demos.nlp.corenlp;

import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

import edu.stanford.nlp.trees.international.pennchinese.CHTBTokenizer;

public class TokenizerDemo extends JCommanderExecutorAbstract<TokenizerDemo> {

    public static void main(String[] args) throws IOException {
        TokenizerDemo demo = new TokenizerDemo();
        demo.executeMain(TokenizerDemo.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<TokenizerDemo> propertiesLoader, Logger logger, String[] args)
            throws Exception {
        String dataFile = propertiesLoader.getString("data.file");
        CHTBTokenizer ptbt = new CHTBTokenizer(new FileReader(dataFile));
        while (ptbt.hasNext()) {
            String label = ptbt.getNext();
            System.out.println(label);
        }
    }
}
