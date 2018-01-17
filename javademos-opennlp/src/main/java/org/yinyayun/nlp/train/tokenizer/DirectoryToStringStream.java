/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.nlp.train.tokenizer;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import opennlp.tools.util.ObjectStream;

/**
 * DirectoryToStringStream.java
 *
 * @author yinyayun
 */
public class DirectoryToStringStream implements ObjectStream<String> {
    private String encode;
    private String[] dirs;
    private BufferedReader currentReader;
    private List<BufferedReader> readers;
    private Iterator<BufferedReader> iterator;

    public DirectoryToStringStream(String[] dirs, String encode) throws IOException {
        this.dirs = dirs;
        this.encode = encode;
        reset();
    }

    @Override
    public String read() throws IOException {
        if (currentReader == null) {
            if (iterator.hasNext()) {
                currentReader = iterator.next();
            }
            else {
                return null;
            }
        }
        String text = currentReader.readLine();
        if (text == null && iterator.hasNext()) {
            currentReader = iterator.next();
            return read();
        }
        if (text != null) {
            return text.replace(" ", "<SPLIT>");
        }
        else {
            return null;
        }
    }

    @Override
    public void reset() throws IOException, UnsupportedOperationException {
        currentReader = null;
        readers = new ArrayList<BufferedReader>();
        for (String dir : dirs) {
            for (File file : new File(dir).listFiles()) {
                readers.add(new BufferedReader(new InputStreamReader(new FileInputStream(file), encode)));
            }
        }
        iterator = readers.iterator();
    }

    @Override
    public void close() throws IOException {
        readers.forEach(x -> closeReader(x));
    }

    private void closeReader(Closeable close) {
        try {
            close.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
