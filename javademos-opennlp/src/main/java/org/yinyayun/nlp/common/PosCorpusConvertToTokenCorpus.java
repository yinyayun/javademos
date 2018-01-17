package org.yinyayun.nlp.common;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

/**
 * @author yinyayun
 */
public class PosCorpusConvertToTokenCorpus {
    private Pattern pattern = Pattern.compile("/[a-z,A-Z]+");

    public static void main(String[] args) {
        new PosCorpusConvertToTokenCorpus().convert("C:/Users/yinyayun/Desktop/中文标注.txt",
                "C:/Users/yinyayun/Desktop/中文分词.txt");
    }

    /**
     * @param sourceFile
     * @param optSave
     */
    public void convert(String sourceFile, String optSave) {
        try {
            File optFile = new File(optSave);
            List<String> lines = Files.readAllLines(new File(sourceFile).toPath(), Charset.forName("utf-8"));
            List<String> optLines = new ArrayList<String>();
            StringBuilder builder = new StringBuilder();
            for (String line : lines) {
                String[] splits = line.split(" ");
                for (String split : splits) {
                    if (builder.length() > 0) {
                        builder.append(" ");
                    }
                    builder.append(pattern.matcher(split).replaceAll(""));
                }
                optLines.add(builder.toString());
                builder.setLength(0);
            }
            FileUtils.writeLines(optFile, optLines, "\n", false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
