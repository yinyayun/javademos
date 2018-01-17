/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.demos.nlp.corenlp;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;

/**
 * AnnationPrint.java
 *
 * @author yinyayun
 */
public class AnnationPrint {
    public static void printAnnation(Annotation annotation, PrintWriter pw) {
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        // Display docid if available
        String docId = annotation.get(CoreAnnotations.DocIDAnnotation.class);
        if (docId != null) {
            List<CoreLabel> tokens = annotation.get(CoreAnnotations.TokensAnnotation.class);
            int nSentences = (sentences != null) ? sentences.size() : 0;
            int nTokens = (tokens != null) ? tokens.size() : 0;
            pw.printf("Document: ID=%s (%d sentences, %d tokens)%n", docId, nSentences, nTokens);
        }

        // Display doctitle if available
        String docTitle = annotation.get(CoreAnnotations.DocTitleAnnotation.class);
        if (docTitle != null) {
            pw.printf("Document Title: %s%n", docTitle);
        }

        // Display docdate if available
        String docDate = annotation.get(CoreAnnotations.DocDateAnnotation.class);
        if (docDate != null) {
            pw.printf("Document Date: %s%n", docDate);
        }

        // Display doctype if available
        String docType = annotation.get(CoreAnnotations.DocTypeAnnotation.class);
        if (docType != null) {
            pw.printf("Document Type: %s%n", docType);
        }
        // Display docsourcetype if available
        String docSourceType = annotation.get(CoreAnnotations.DocSourceTypeAnnotation.class);
        if (docSourceType != null) {
            pw.printf("Document Source Type: %s%n", docSourceType);
        }

        // display the new-style coreference graph
        Map<Integer, CorefChain> corefChains = annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class);
        if (corefChains != null && sentences != null) {
            for (CorefChain chain : corefChains.values()) {
                CorefChain.CorefMention representative = chain.getRepresentativeMention();
                boolean outputHeading = false;
                for (CorefChain.CorefMention mention : chain.getMentionsInTextualOrder()) {
                    if (mention == representative)
                        continue;
                    if (!outputHeading) {
                        outputHeading = true;
                        pw.println("Coreference set:");
                    }
                    // all offsets start at 1!
                    pw.printf("\t(%d,%d,[%d,%d]) -> (%d,%d,[%d,%d]), that is: \"%s\" -> \"%s\"%n", mention.sentNum,
                            mention.headIndex, mention.startIndex, mention.endIndex, representative.sentNum,
                            representative.headIndex, representative.startIndex, representative.endIndex,
                            mention.mentionSpan, representative.mentionSpan);
                }
            }
        }
        pw.flush();
    }
}
