import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
public class PrintMethods {
    public static void printCodedSymbolTree(Node currentNode) {
        if (currentNode.leftNode != null) {
            System.out.println(currentNode.leftNode.codedSymbol);
            printCodedSymbolTree(currentNode.leftNode);
        }
        if (currentNode.rightNode != null) {
            System.out.println(currentNode.rightNode.codedSymbol);
            printCodedSymbolTree(currentNode.rightNode);
        }
    }

    public static HashMap<Integer, List<String>> printMapOfTree = new HashMap<Integer, List<String>>();
    public static void printSymbolsInTreeShape(List<Node> nodeList) {
        // Node currentNode = nodeList.get(0);
        // if(currentNode.leftNode==null&&currentNode.rightNode==null) {
        // System.out.println(currentNode.codedSymbol);
        // }
        // if(currentNode.leftNode!=null) {
        //     List<Node> tmpList = new ArrayList<>();
        //     tmpList.add(currentNode.leftNode);
        //     printSymbolsInTreeShape(tmpList);
        // }
        // if(currentNode.leftNode!=null) {
        //     List<Node> tmpList = new ArrayList<>();
        //     tmpList.add(currentNode.leftNode);
        //     printSymbolsInTreeShape(tmpList);
        // }

        boolean allNull = true;
        List<Node> tmpList = new ArrayList<>();
        int lineLength = 0;
        List<String> symbols = new ArrayList<>();
        for (int x = 0; x < nodeList.size(); x++) {
            symbols.add(nodeList.get(x).codedSymbol);
            lineLength++;
            if (nodeList.get(x).leftNode != null) {
                tmpList.add(nodeList.get(x).leftNode);
                allNull = false;
            } else {
                symbols.add("*");
            }
            if (nodeList.get(x).rightNode != null) {
                tmpList.add(nodeList.get(x).rightNode);
                allNull = false;
            } else {
                symbols.add("*");
            }
        }
        printMapOfTree.put(printMapOfTree.size(), symbols);
        if (allNull == false) {
            printSymbolsInTreeShape(tmpList);
        }else {
            int maxNumberOfCharacters = Collections.max(printMapOfTree.keySet());

            printMapOfTree.forEach((integer,listOfIntegers) -> {
                for(int x = 0;x<(maxNumberOfCharacters-integer)/2;x++) {
                    System.out.print(" ");
                }
                for(int x = 0;x<listOfIntegers.size();x++) {
                    System.out.print(listOfIntegers.get(x));
                        System.out.print(" ");
                }
                for(int x = 0;x<(maxNumberOfCharacters-integer)/2;x++) {
                    System.out.print(" ");
                }
                System.out.println();
            });
        }
    }
}
