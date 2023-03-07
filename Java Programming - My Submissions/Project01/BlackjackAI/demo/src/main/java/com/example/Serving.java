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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Serving {

    // private static final Logger log =
    // LoggerFactory.getLogger("com.javadeeplearningcookbook.examples.CustomerLossPrediction.class");

    // private static Schema generateSchema(){
    // final Schema schema = new Schema.Builder()
    // .addColumnString("RowNumber")
    // .addColumnInteger("CustomerId")
    // .addColumnString("Surname")
    // .addColumnInteger("CreditScore")
    // .addColumnCategorical("Geography", Arrays.asList("France","Germany","Spain"))
    // .addColumnCategorical("Gender", Arrays.asList("Male","Female"))
    // .addColumnsInteger("Age", "Tenure")
    // .addColumnDouble("Balance")
    // .addColumnsInteger("NumOfProducts","HasCrCard","IsActiveMember")
    // .addColumnDouble("EstimatedSalary")
    // .build();
    // return schema;

    // }

    // private static final Logger log = LoggerFactory.getLogger("com.example.Serving.class");

    private static Schema generateSchema() {
        final Schema schema = new Schema.Builder()
                .addColumnInteger("2Cards")
                .addColumnInteger("3Cards")
                .addColumnInteger("4Cards")
                .addColumnInteger("5Cards")
                .addColumnInteger("6Cards")
                .addColumnInteger("7Cards")
                .addColumnInteger("8Cards")
                .addColumnInteger("9Cards")
                .addColumnInteger("10Cards")
                .addColumnInteger("11Cards")
                .addColumnInteger("valueOfDealerCard")
                .addColumnInteger("valueOfFirstPlayerCard")
                .addColumnInteger("valueOfSecondPlayerCard")
                .addColumnInteger("numberOfSplits")
                .addColumnInteger("numberOfHits")
                .addColumnInteger("numberOfDoubles")
                .addColumnInteger("numberOfStands")
                .build();
        return schema;

    }

    private static RecordReader applyTransform(RecordReader recordReader, Schema schema) {
        final TransformProcess transformProcess = new TransformProcess.Builder(schema)
                .build();
        final TransformProcessRecordReader transformProcessRecordReader = new TransformProcessRecordReader(recordReader,
                transformProcess);
        return transformProcessRecordReader;

    }

    private static RecordReader generateReader(File file) throws IOException, InterruptedException {
        final RecordReader recordReader = new CSVRecordReader(1, ',');
        recordReader.initialize(new FileSplit(file));
        final RecordReader transformProcessRecordReader = applyTransform(recordReader, generateSchema());
        return transformProcessRecordReader;
    }

    public static INDArray generateOutput(File inputFile, String modelFilePath)
            throws IOException, InterruptedException {
        final File modelFile = new File(modelFilePath);
        // System.out.println(modelFile.exists());
        final MultiLayerNetwork network = ModelSerializer.restoreMultiLayerNetwork(modelFile, true);
        // System.out.println(network.paramTable());
        final RecordReader recordReader = generateReader(inputFile);
        final INDArray array = RecordConverter.toArray(recordReader.next());
        final NormalizerStandardize normalizerStandardize = ModelSerializer.restoreNormalizerFromFile(modelFile);
        normalizerStandardize.transform(array);
        final DataSetIterator dataSetIterator = new RecordReaderDataSetIterator.Builder(recordReader, 1).build();
        normalizerStandardize.fit(dataSetIterator);
        dataSetIterator.setPreProcessor(normalizerStandardize);
        // System.out.println(dataSetIterator.totalOutcomes());
        return network.output(dataSetIterator);

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        // int portNumber = 181;

        // ServerSocket serverSocket = new ServerSocket(portNumber);
        // Socket clientSocket = serverSocket.accept();
        // DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
        // DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
//         while(clientSocket.isConnected()) {
//             try{
        File inputFile = new ClassPathResource("BlackjackTest.csv").getFile();
//         FileWriter fw = new FileWriter(inputFile,false);
//         fw.write("2Cards,3Cards,4Cards,5Cards,6Cards,7Cards,8Cards,9Cards,10Cards,11Cards,valueOfDealerCard,valueOfFirstPlayerCard,valueOfSecondPlayerCard,numberOfSplits,numberOfHits,numberOfDoubles,numberOfStands\n");
//         String firstTry = dis.readUTF();
//         System.out.println("First: "+firstTry);
//         fw.write(firstTry+"\n");
//         String secondTry = dis.readUTF();
//         System.out.println("Second: "+secondTry);
//         fw.write(secondTry+"\n");
//         String thirdTry = dis.readUTF();
//         System.out.println("Third: "+thirdTry);
//         fw.write(thirdTry+"\n");
//         String fourthTry = dis.readUTF();
//         System.out.println("Fourth: "+fourthTry);
//         fw.write(fourthTry);
//         fw.flush();
//         fw.close();
//         // INDArray indArray = Serving.generateOutput(new
//         // ClassPathResource("testfile.csv").getFile(),"C:\\Users\\arthu\\Documents\\GitHub\\CS112\\MyWork\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\DL4J_Iris_Model.zip");
//         INDArray indArray = Serving.generateOutput(inputFile, new ClassPathResource("BlackjackModel.zip").getFile().getAbsolutePath());
//                 // "C:\\Users\\arthu\\Desktop\\CS112-afperry12\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\BlackjackModel.zip");
//         String message = "";
//         // System.out.println(indArray.data().length());
//         HashMap<Integer, Double> highestBlackjackWinPercentage = new HashMap<Integer, Double>();
//         HashMap<Integer, Double> highestWinPercentage = new HashMap<Integer, Double>();
//         for (int i = 0; i < indArray.rows(); i++) {
//             System.out.println(indArray.getRow(i) + "Hi");
//             highestBlackjackWinPercentage.put(i, indArray.getDouble(i,3));
//             highestWinPercentage.put(i, indArray.getDouble(i,1));
//         }
//         Map.Entry<Integer, Double> maxEntry = null;
// for (Map.Entry<Integer, Double> entry : highestWinPercentage.entrySet()) {
//   if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
//     maxEntry = entry;
//   }
// }
// Integer maxKey = maxEntry.getKey();
//     if(maxKey==0) {
//         message="split";
//     } else if(maxKey==1) {
//         message="hit";
//     } else if(maxKey==2) {
//         message="double";
//     } else if(maxKey==3) {
//         message="stand";
//     } else {
//         message="stand";
//     }

//         // highestWinPercentage.entrySet().stream().filter(entry -> entry.getValue() == Collections.max(highestWinPercentage.values()));
//         // Key key = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
//         System.out.println(message);
//         dos.writeUTF(message);
//         // System.exit(1);
//     }catch(Exception e) {
//         System.out.println(e);
//         e.printStackTrace(System.out);
//     dos.writeUTF("stand");
//     System.exit(1);
// }
// }

        // INDArray indArray = Serving.generateOutput(new
        // ClassPathResource("testfile.csv").getFile(),"C:\\Users\\arthu\\Documents\\GitHub\\CS112\\MyWork\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\DL4J_Iris_Model.zip");
        INDArray indArray = Serving.generateOutput(inputFile, new ClassPathResource("BlackjackModel.zip").getFile().getAbsolutePath());
                // "C:\\Users\\arthu\\Desktop\\CS112-afperry12\\Project01\\BlackjackAI\\demo\\src\\main\\resources\\BlackjackModel.zip");
        String message = "";
        for (int i = 0; i < indArray.rows(); i++) {
            System.out.println(indArray.getRow(i) + "Hi");
        }
    }
}