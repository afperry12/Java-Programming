// package com.example;

// import org.apache.commons.io.FilenameUtils;
// import org.datavec.api.records.metadata.RecordMetaData;
// import org.datavec.api.records.reader.RecordReader;
// import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
// import org.datavec.api.records.reader.impl.transform.TransformProcessRecordReader;
// import org.datavec.api.split.FileSplit;
// import org.datavec.api.transform.TransformProcess;
// import org.datavec.api.transform.schema.Schema;
// import org.deeplearning4j.core.storage.StatsStorage;
// import org.deeplearning4j.ui.model.storage.InMemoryStatsStorage;
// import org.deeplearning4j.ui.model.stats.StatsListener;
// import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
// import org.deeplearning4j.datasets.iterator.DataSetIteratorSplitter;
// import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
// import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
// import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
// import org.deeplearning4j.nn.conf.inputs.InputType;
// import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
// import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
// import org.deeplearning4j.nn.conf.layers.DenseLayer;
// import org.deeplearning4j.nn.conf.layers.OutputLayer;
// import org.deeplearning4j.nn.conf.layers.PoolingType;
// import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
// import org.deeplearning4j.nn.weights.WeightInit;
// import org.deeplearning4j.optimize.api.InvocationType;
// import org.deeplearning4j.optimize.listeners.EvaluativeListener;
// import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
// import org.deeplearning4j.util.ModelSerializer;
// import org.nd4j.linalg.dataset.DataSet;
// import org.nd4j.linalg.dataset.SplitTestAndTrain;
// import org.nd4j.common.io.ClassPathResource;
// import org.nd4j.evaluation.classification.Evaluation;
// import org.nd4j.evaluation.meta.Prediction;
// import org.nd4j.linalg.activations.Activation;
// import org.nd4j.linalg.api.ndarray.INDArray;
// import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
// import org.nd4j.linalg.dataset.api.iterator.DataSetIteratorFactory;
// import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
// import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
// import org.nd4j.linalg.factory.Nd4j;
// import org.nd4j.linalg.learning.config.Adam;
// import org.nd4j.linalg.learning.config.Sgd;
// import org.nd4j.linalg.lossfunctions.LossFunctions;
// import org.nd4j.linalg.lossfunctions.impl.LossMCXENT;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.io.File;
// import java.io.IOException;
// //import java.nio.file.Path;
// import java.util.Arrays;
// import java.util.List;

// public class Training {
//     private static final Logger log = LoggerFactory.getLogger("com.example.Training.class");
//             private static Schema generateSchema(){
//         final Schema schema = new Schema.Builder()
//                 .addColumnInteger("sepal.length")
//                 .addColumnInteger("sepal.width")
//                 .addColumnInteger("petal.length")
//                 .addColumnInteger("petal.width")
//                 .addColumnString("variety")
//                 .build();
//         return schema;

//     }

//         private static RecordReader applyTransform(RecordReader recordReader, Schema schema){
//         final TransformProcess transformProcess = new TransformProcess.Builder(schema)
//         .stringToCategorical("variety",Arrays.asList("Setosa","Versicolor","Virginica"))
//                 .build();
//         final TransformProcessRecordReader transformProcessRecordReader = new TransformProcessRecordReader(recordReader,transformProcess);
//         return  transformProcessRecordReader;

//     }

//     private static RecordReader generateReader(File file) throws IOException, InterruptedException {
//             final RecordReader recordReader = new CSVRecordReader(1,',');
//             recordReader.initialize(new FileSplit(file));
//             final RecordReader transformProcessRecordReader=applyTransform(recordReader,generateSchema());
//             return transformProcessRecordReader;
//         }
// public static void main(String[] args) throws Exception {

//     // First: get the dataset using the record reader. CSVRecordReader handles
//     // loading/parsing
// //     final INDArray weightsArray = Nd4j.create(new double[]{0.57, 0.75});
//     int numLinesToSkip = 1;
//     char delimiter = ',';
//     RecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
//     recordReader.initialize(new FileSplit(new ClassPathResource("iris.csv").getFile()));
//     // RecordReader recordReader = generateReader(new ClassPathResource("iris.csv").getFile());

//     // Second: the RecordReaderDataSetIterator handles conversion to DataSet
//     // objects, ready for use in neural network
//     int labelIndex = 4; // 5 values in each row of the iris.txt CSV: 4 input features followed by an
//                         // integer label (class) index. Labels are the 5th value (index 4) in each row
//     int numClasses = 3; // 3 classes (types of iris flowers) in the iris data set. Classes have integer
//                         // values 0, 1 or 2
//     int batchSize = 150; // Iris data set: 150 examples total. We are loading all of them into one
//                          // DataSet (not recommended for large data sets)

//     DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);

//     DataSet allData = iterator.next();
//     allData.shuffle();
//     SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.65); // Use 65% of data for training

//     DataSet trainingData = testAndTrain.getTrain();
//     DataSet testData = testAndTrain.getTest();

//     // We need to normalize our data. We'll use NormalizeStandardize (which gives us
//     // mean 0, unit variance):

//     DataNormalization normalizer = new NormalizerStandardize();
//     normalizer.fit(trainingData); // Collect the statistics (mean/stdev) from the training data. This does not
//                                   // modify the input data
//     normalizer.transform(trainingData); // Apply normalization to the training data
//     normalizer.transform(testData); // Apply normalization to the test data. This is using statistics calculated
//                                     // from the *training* set

//     final int numInputs = 4;
//     int outputNum = 3;
//     long seed = 6;

//     log.info("Build model....");
//             // final MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//             //                                                         .weightInit(WeightInit.RELU_UNIFORM)
//             //                                                         .updater(new Adam(0.015D))
//             //                                                         .list()
//             //                                                         .layer(new DenseLayer.Builder().nIn(11).nOut(6).activation(Activation.RELU).dropOut(0.9).build())
//             //                                                         .layer(new DenseLayer.Builder().nIn(6).nOut(6).activation(Activation.RELU).dropOut(0.9).build())
//             //                                                         .layer(new DenseLayer.Builder().nIn(6).nOut(4).activation(Activation.RELU).dropOut(0.9).build())
//             //                                                         .layer(new OutputLayer.Builder(new LossMCXENT(weightsArray)).nIn(4).nOut(2).activation(Activation.SOFTMAX).build())
//             //                                                         .build();
//             MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().seed(seed).activation(Activation.TANH)
//                 .weightInit(WeightInit.XAVIER).updater(new Sgd(0.1)).l2(1e-4).list()
//                 .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(3).build())
//                 .layer(1, new DenseLayer.Builder().nIn(3).nOut(3).build())
//                 .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
//                         .activation(Activation.SOFTMAX).nIn(3).nOut(outputNum).build())
//                 .build();

//     // run the model
//     MultiLayerNetwork model = new MultiLayerNetwork(conf);
//     model.init();
//     model.setListeners(new ScoreIterationListener(100));

//     for (int i = 0; i < 1000; i++) {
//         model.fit(trainingData);
//     }

//     // evaluate the model on the test set
//     Evaluation eval = new Evaluation(3);
//     INDArray input = testData.getFeatures();
//     INDArray output = model.output(input);
//     System.out.println("INPUT:" + input.toString());
//     eval.eval(testData.getLabels(), output);
//     log.info(eval.stats());

//     // Save the model
//     File locationToSave = new File("C:\\Users\\arthu\\Documents\\GitHub\\CS112\\MyWork\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\DL4J_Iris_Model.zip"); // Where to save
//     // the network.
//     // Note: the file
//     // is in .zip
//     // format - can
//     // be opened
//     // externally
//     boolean saveUpdater = true; // Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you
//     // want to train your network more in the future
//     ModelSerializer.writeModel(model, locationToSave, saveUpdater);
//     ModelSerializer.addNormalizerToModel(locationToSave, normalizer);

//     // Load the model
//     MultiLayerNetwork restored = ModelSerializer.restoreMultiLayerNetwork(locationToSave);

//     System.out.println("Saved and loaded parameters are equal:      " + model.params().equals(restored.params()));
//     System.out.println("Saved and loaded configurations are equal:  "
//             + model.getLayerWiseConfigurations().equals(restored.getLayerWiseConfigurations()));
//     System.out.println(model.summary());

// }
// }

package com.example;

import org.apache.commons.io.FilenameUtils;
import org.datavec.api.records.metadata.RecordMetaData;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.records.reader.impl.transform.TransformProcessRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.split.InputStreamInputSplit;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.schema.Schema;
import org.deeplearning4j.core.storage.StatsStorage;
import org.deeplearning4j.ui.model.storage.InMemoryStatsStorage;
import org.deeplearning4j.ui.model.stats.StatsListener;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.datasets.iterator.DataSetIteratorSplitter;
import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.inputs.InputType;
import org.deeplearning4j.nn.conf.layers.ConvolutionLayer;
import org.deeplearning4j.nn.conf.layers.SubsamplingLayer;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.PoolingType;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.api.InvocationType;
import org.deeplearning4j.optimize.listeners.EvaluativeListener;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.evaluation.meta.Prediction;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.iterator.DataSetIteratorFactory;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.lossfunctions.impl.LossMCXENT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
//import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Training {
        public static void main(String[] args) throws IOException, InterruptedException {
                // URL dataUrl = new URL(
                //                 "https://gist.githubusercontent.com/netj/8836201/raw/6f9306ad21398ea43cba4f7d537619d0e07d5ae3/iris.csv");

                // int numLinesToSkip = 1;
                // char delimiter = ',';
                // CSVRecordReader csvReader = new CSVRecordReader(numLinesToSkip, delimiter);

                    int numLinesToSkip = 1;
    char delimiter = ',';
    RecordReader recordReader = new CSVRecordReader(numLinesToSkip, delimiter);
    recordReader.initialize(new FileSplit(new ClassPathResource("IrisFull.csv").getFile()));

                final Schema schema = new Schema.Builder()
                                .addColumnInteger("Id")
                                .addColumnInteger("sepal.length")
                                .addColumnInteger("sepal.width")
                                .addColumnInteger("petal.length")
                                .addColumnInteger("petal.width")
                                .addColumnCategorical("variety", Arrays.asList("Iris-setosa", "Iris-versicolor", "Iris-virginica"))
                                .build();

                final TransformProcess transformProcess = new TransformProcess.Builder(schema)
                                .removeColumns("Id")
                                .categoricalToInteger("variety")
                                .build();

                // InputStream inputStream = dataUrl.openStream();

                // csvReader.initialize(new InputStreamInputSplit(inputStream));

                TransformProcessRecordReader tpReader = new TransformProcessRecordReader(recordReader, transformProcess);

                int labelIndex = 4;
                int numClasses = 3;
                int batchSize = 150;

                RecordReaderDataSetIterator iterator = new RecordReaderDataSetIterator(tpReader, batchSize, labelIndex,
                                numClasses);

                DataSet allData = iterator.next();

                allData.shuffle();

                SplitTestAndTrain testAndTrain = allData.splitTestAndTrain(0.90);

                DataSet trainingData = testAndTrain.getTrain();
                DataSet testData = testAndTrain.getTest();

                DataNormalization normalizer = new NormalizerStandardize();
                normalizer.fit(trainingData);
                normalizer.transform(trainingData); // Apply normalization to the training data
                normalizer.transform(testData);

                int numInputs = 4;
                int outputNum = 3;
                int seed = 6;

                MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                                .seed(seed)
                                .activation(Activation.TANH)
                                .weightInit(WeightInit.XAVIER)
                                .updater(new Sgd(0.1))
                                .l2(1e-4)
                                .list()
                                .layer(0, new DenseLayer.Builder().nIn(numInputs).nOut(3)
                                                .build())
                                .layer(1, new DenseLayer.Builder().nIn(3).nOut(3)
                                                .build())
                                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                                                .activation(Activation.SOFTMAX)
                                                .nIn(3).nOut(outputNum).build())
                                .build();

                MultiLayerNetwork model = new MultiLayerNetwork(conf);
                model.init();
                model.setListeners(new ScoreIterationListener(100));

                int epochs = 15000;
                for (int i = 0; i < epochs; i++) {
                        model.fit(trainingData);
                }

                Evaluation eval = new Evaluation(3);
                INDArray output = model.output(testData.getFeatures());
                eval.eval(testData.getLabels(), output);

                System.out.println(eval.stats());

                // Save the model
                File locationToSave = new File(
                                "C:\\Users\\arthu\\Documents\\GitHub\\CS112\\MyWork\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\DL4J_Iris_Model.zip"); // Where
                                                                                                                                                                // to
                                                                                                                                                                // save
                // the network.
                // Note: the file
                // is in .zip
                // format - can
                // be opened
                // externally
                boolean saveUpdater = true; // Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if
                                            // you
                // want to train your network more in the future
                ModelSerializer.writeModel(model, locationToSave, saveUpdater);
                ModelSerializer.addNormalizerToModel(locationToSave, normalizer);

                // Load the model
                MultiLayerNetwork restored = ModelSerializer.restoreMultiLayerNetwork(locationToSave);

                System.out.println("Saved and loaded parameters are equal:      "
                                + model.params().equals(restored.params()));
                System.out.println("Saved and loaded configurations are equal:  "
                                + model.getLayerWiseConfigurations().equals(restored.getLayerWiseConfigurations()));
                System.out.println(model.summary());

        }
}