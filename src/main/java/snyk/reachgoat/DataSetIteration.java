package io.snyk.reachgoat;

import org.datavec.api.records.reader.SequenceRecordReader;
import org.datavec.api.records.reader.impl.csv.CSVNLinesSequenceRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.SequenceRecordReaderDataSetIterator;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.File;

public class DataSetIteration {
  public static void iterate() throws Exception {
    int seriesSize = 32;
    SequenceRecordReader features = new CSVNLinesSequenceRecordReader(seriesSize);
    File featuresFile = new File("/tmp/features.csv");
    features.initialize(new FileSplit(featuresFile));

    SequenceRecordReader labels = new CSVNLinesSequenceRecordReader(1);
    File labelsFile = new File("/tmp/labels.csv");
    labels.initialize(new FileSplit(labelsFile));

    int miniBatchSize = 32;
    int numPossibleLabels = -1;
    boolean regression = true;

    DataSetIterator datasetIter = new SequenceRecordReaderDataSetIterator(features, labels, miniBatchSize, numPossibleLabels, regression, SequenceRecordReaderDataSetIterator.AlignmentMode.ALIGN_END);

    while (datasetIter.hasNext()) {
      DataSet t = datasetIter.next();
      System.out.println(t.id());
    }
  }
}
