/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.carrot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.carrot2.clustering.kmeans.BisectingKMeansClusteringAlgorithm;
import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.stc.STCClusteringAlgorithm;
import org.carrot2.clustering.synthetic.PassthroughClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.SimpleProcessingComponentManager;
import org.carrot2.core.Document;
import org.carrot2.core.LanguageCode;
import org.carrot2.core.ProcessingResult;

/**
 * CarrotDemo.java
 *
 * @author yinyayun
 */
public class CarrotDemo {
    public static void main(String[] args) {
        try (Controller controller = new Controller(new SimpleProcessingComponentManager())) {
            List<String> lines = Files.readAllLines(new File("data/data.txt").toPath());
            List<Document> docs = new ArrayList<Document>();
            for (String line : lines) {
                docs.add(new Document(null, line, LanguageCode.CHINESE_SIMPLIFIED));
            }
            // 可以更改LingoClusteringAlgorithm.class、BisectingKMeansClusteringAlgorithm.class、STCClusteringAlgorithm.class、PassthroughClusteringAlgorithm
            ProcessingResult result = controller.process(docs, null, BisectingKMeansClusteringAlgorithm.class);
            List<Cluster> clusters = result.getClusters();
            for (Cluster cluster : clusters) {
                System.out.println(cluster.getLabel());
                cluster.getAllDocuments().forEach(x -> System.out.println("\t" + x.getSummary()));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
