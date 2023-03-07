// package com.example;

// import org.datavec.api.records.reader.RecordReader;
// import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
// import org.datavec.api.records.reader.impl.transform.TransformProcessRecordReader;
// import org.datavec.api.split.FileSplit;
// import org.datavec.api.transform.TransformProcess;
// import org.datavec.api.transform.schema.Schema;
// import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
// import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
// import org.deeplearning4j.util.ModelSerializer;
// import org.nd4j.common.io.ClassPathResource;
// import org.nd4j.linalg.api.ndarray.INDArray;
// import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
// import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
// // import org.nd4j.linalg.io.ClassPathResource;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import java.io.File;
// import java.io.IOException;
// import java.util.Arrays;

// public class Serving {

//     // private static final Logger log = LoggerFactory.getLogger("com.javadeeplearningcookbook.examples.CustomerLossPrediction.class");

//     // private static Schema generateSchema(){
//     //     final Schema schema = new Schema.Builder()
//     //             .addColumnString("RowNumber")
//     //             .addColumnInteger("CustomerId")
//     //             .addColumnString("Surname")
//     //             .addColumnInteger("CreditScore")
//     //             .addColumnCategorical("Geography", Arrays.asList("France","Germany","Spain"))
//     //             .addColumnCategorical("Gender", Arrays.asList("Male","Female"))
//     //             .addColumnsInteger("Age", "Tenure")
//     //             .addColumnDouble("Balance")
//     //             .addColumnsInteger("NumOfProducts","HasCrCard","IsActiveMember")
//     //             .addColumnDouble("EstimatedSalary")
//     //             .build();
//     //     return schema;

//     // }

//     // private static RecordReader applyTransform(RecordReader recordReader, Schema schema){
//     //     final TransformProcess transformProcess = new TransformProcess.Builder(schema)
//     //             .removeColumns("RowNumber","CustomerId","Surname")
//     //             .categoricalToInteger("Gender")
//     //             .categoricalToOneHot("Geography")
//     //             .removeColumns("Geography[France]")
//     //             .build();
//     //     final TransformProcessRecordReader transformProcessRecordReader = new TransformProcessRecordReader(recordReader,transformProcess);
//     //     return  transformProcessRecordReader;

//     // }
//     private static Schema generateSchema(){
//     final Schema schema = new Schema.Builder()
//                 // .addColumnString("RowNumber")
//                 // .addColumnInteger("CustomerId")
//                 // .addColumnString("Surname")
//                 // .addColumnInteger("CreditScore")
//                 // .addColumnCategorical("Geography", Arrays.asList("France","Germany","Spain"))
//                 // .addColumnCategorical("Gender", Arrays.asList("Male","Female"))
//                 // .addColumnsInteger("Age", "Tenure")
//                 // .addColumnDouble("Balance")
//                 // .addColumnsInteger("NumOfProducts","HasCrCard","IsActiveMember")
//                 // .addColumnDouble("EstimatedSalary")
//                 // .addColumnInteger("Exited")
//                 .addColumnInteger("sepal.length")
//                 .addColumnInteger("sepal.width")
//                 .addColumnInteger("petal.length")
//                 .addColumnInteger("petal.width")
//                 .build();
//         return schema;

//     }

//         private static RecordReader applyTransform(RecordReader recordReader, Schema schema){
//         final TransformProcess transformProcess = new TransformProcess.Builder(schema)
//         // .stringToCategorical("variety",Arrays.asList("Setosa","Versicolor","Virginica"))
//                 .build();
//         final TransformProcessRecordReader transformProcessRecordReader = new TransformProcessRecordReader(recordReader,transformProcess);
//         return  transformProcessRecordReader;

//     }

//     private static RecordReader generateReader(File file) throws IOException, InterruptedException {
//         final RecordReader recordReader = new CSVRecordReader(1,',');
//         recordReader.initialize(new FileSplit(file));
//         final RecordReader transformProcessRecordReader=applyTransform(recordReader,generateSchema());
//         return transformProcessRecordReader;
//     }

//     // public static INDArray generateOutput(File inputFile, String modelFilePath) throws IOException, InterruptedException {
//     //     final File modelFile = new File(modelFilePath);
//     //     final MultiLayerNetwork network = ModelSerializer.restoreMultiLayerNetwork(modelFile);
//     //     final RecordReader recordReader = generateReader(inputFile);
//     //     //final INDArray array = RecordConverter.toArray(recordReader.next());
//     //     final NormalizerStandardize normalizerStandardize = ModelSerializer.restoreNormalizerFromFile(modelFile);
//     //     //normalizerStandardize.transform(array);
//     //     final DataSetIterator dataSetIterator = new RecordReaderDataSetIterator.Builder(recordReader,1).build();
//     //     normalizerStandardize.fit(dataSetIterator);
//     //     dataSetIterator.setPreProcessor(normalizerStandardize);
//     //     return network.output(dataSetIterator);

//     // }

//     public static INDArray generateOutput(File inputFile, String modelFileLocation) throws IOException, InterruptedException {
//         //retrieve the saved model
//         final File modelFile = new File(modelFileLocation);
//         final MultiLayerNetwork model = ModelSerializer.restoreMultiLayerNetwork(modelFile);
//         final RecordReader recordReader = generateReader(inputFile);
//         final NormalizerStandardize normalizerStandardize = ModelSerializer.restoreNormalizerFromFile(modelFile);
//         final DataSetIterator dataSetIterator = new RecordReaderDataSetIterator.Builder(recordReader,1).build();
//         normalizerStandardize.fit(dataSetIterator);
//         dataSetIterator.setPreProcessor(normalizerStandardize);
//         return model.output(dataSetIterator);
//     }

//     public static void main(String[] args) throws IOException, InterruptedException {
        
//         // take inputs from socket instead?
//         // File file = new File("test");
//         INDArray indArray = Serving.generateOutput(new ClassPathResource("testfile.csv").getFile(),"C:\\Users\\arthu\\Desktop\\CS112-afperry12\\Project01\\BlackjackAI\\src\\main\\resources\\DL4J_Iris_Model.zip");
//         String message="";
//         for(int i=0; i<indArray.rows();i++){
//            if(indArray.getDouble(i,0)>indArray.getDouble(i,1)){
//               message+="Customer "+(i+1)+"-> Happy Customer\n";
//            }
//            else{
//                message+="Customer "+(i+1)+"-> Unhappy Customer\n";
//            }
//         }
//         System.out.println(message);

//     }
// }


package com.example;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.records.reader.impl.transform.TransformProcessRecordReader;
import org.datavec.api.split.FileSplit;
import org.datavec.api.transform.TransformProcess;
import org.datavec.api.transform.schema.Schema;
import org.datavec.api.util.ndarray.RecordConverter;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.common.io.ClassPathResource;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
// import org.nd4j.linalg.io.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class Serving {

    // private static final Logger log = LoggerFactory.getLogger("com.javadeeplearningcookbook.examples.CustomerLossPrediction.class");

    // private static Schema generateSchema(){
    //     final Schema schema = new Schema.Builder()
    //             .addColumnString("RowNumber")
    //             .addColumnInteger("CustomerId")
    //             .addColumnString("Surname")
    //             .addColumnInteger("CreditScore")
    //             .addColumnCategorical("Geography", Arrays.asList("France","Germany","Spain"))
    //             .addColumnCategorical("Gender", Arrays.asList("Male","Female"))
    //             .addColumnsInteger("Age", "Tenure")
    //             .addColumnDouble("Balance")
    //             .addColumnsInteger("NumOfProducts","HasCrCard","IsActiveMember")
    //             .addColumnDouble("EstimatedSalary")
    //             .build();
    //     return schema;

    // }

    private static final Logger log = LoggerFactory.getLogger("com.example.Serving.class");
            private static Schema generateSchema(){
        final Schema schema = new Schema.Builder()
                .addColumnInteger("sepal.length")
                .addColumnInteger("sepal.width")
                .addColumnInteger("petal.length")
                .addColumnInteger("petal.width")
                .build();
        return schema;

    }

    private static RecordReader applyTransform(RecordReader recordReader, Schema schema){
        final TransformProcess transformProcess = new TransformProcess.Builder(schema)
                .build();
        final TransformProcessRecordReader transformProcessRecordReader = new TransformProcessRecordReader(recordReader,transformProcess);
        return  transformProcessRecordReader;

    }

    private static RecordReader generateReader(File file) throws IOException, InterruptedException {
        final RecordReader recordReader = new CSVRecordReader(1,',');
        recordReader.initialize(new FileSplit(file));
        final RecordReader transformProcessRecordReader=applyTransform(recordReader,generateSchema());
        return transformProcessRecordReader;
    }

    public static INDArray generateOutput(File inputFile, String modelFilePath) throws IOException, InterruptedException {
        final File modelFile = new File(modelFilePath);
        // System.out.println(modelFile.exists());
        final MultiLayerNetwork network = ModelSerializer.restoreMultiLayerNetwork(modelFile,true);
        // System.out.println(network.paramTable());
        final RecordReader recordReader = generateReader(inputFile);
        final INDArray array = RecordConverter.toArray(recordReader.next());
        final NormalizerStandardize normalizerStandardize = ModelSerializer.restoreNormalizerFromFile(modelFile);
        // normalizerStandardize.transform(array);
        final DataSetIterator dataSetIterator = new RecordReaderDataSetIterator.Builder(recordReader,1).build();
        normalizerStandardize.fit(dataSetIterator);
        dataSetIterator.setPreProcessor(normalizerStandardize);
        // System.out.println(dataSetIterator.totalOutcomes());
        return network.output(dataSetIterator);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        
        // INDArray indArray = Serving.generateOutput(new ClassPathResource("testfile.csv").getFile(),"C:\\Users\\arthu\\Documents\\GitHub\\CS112\\MyWork\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\DL4J_Iris_Model.zip");
        INDArray indArray = Serving.generateOutput(new File("C:\\Users\\arthu\\Documents\\GitHub\\CS112\\MyWork\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\Iristest.csv"),"C:\\Users\\arthu\\Documents\\GitHub\\CS112\\MyWork\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\DL4J_Iris_Model.zip");
        String message="";
        // System.out.println(indArray.data().length());
        for(int i=0; i<indArray.rows();i++){
            // System.out.println(indArray.getRow(i)+"Hi");
           if(indArray.getDouble(i,0)>indArray.getDouble(i,1)&&indArray.getDouble(i,0)>indArray.getDouble(i,2)){
              message+=(i+1)+"-> is Setosa\n";
           }
           else if(indArray.getDouble(i,1)>indArray.getDouble(i,0)&&indArray.getDouble(i,1)>indArray.getDouble(i,2)){
            message+=(i+1)+"-> is Versicolor\n";
         } else if(indArray.getDouble(i,2)>indArray.getDouble(i,0)&&indArray.getDouble(i,2)>indArray.getDouble(i,1)){
            message+=(i+1)+"-> is Virginica\n";
         }
        }
        System.out.println(message);

    }
}