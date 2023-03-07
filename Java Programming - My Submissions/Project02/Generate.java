import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.lang.Character;
import java.io.PrintWriter;

public class Generate {
    public static void main(String[] args) {
        File file = null;
        try{
        file = new File(args[0]);
    }catch (Exception exception) {
        System.err.println("ERROR: "+exception.getMessage());
    }
        HashMap<String, Integer> hashMap = HuffmanCountChars.main(file);
        List<Node> nodeList = new ArrayList<Node>();
        hashMap.forEach((character, count) -> {
            Node node = new Node();
            node.inputChar = character.toCharArray()[0];
            node.charCount = count;
            node.codedSymbol = null;
            nodeList.add(node);
        });
        sort(nodeList);
        while (nodeList.size() > 1) {
            Node node = new Node();
            Node node1 = nodeList.get(0);
            Node node2 = nodeList.get(1);
            nodeList.remove(node1);
            nodeList.remove(node2);
            node.charCount = node1.charCount + node2.charCount;
            node.leftNode = node1;
            node.rightNode = node2;
            nodeList.add(node);
            sort(nodeList);
        }
        nodeList.get(0).codedSymbol = "";
        Node rootNode = nodeList.get(0);
        updateSymbol(rootNode);
        // PrintMethods.printCodedSymbolTree(rootNode);
        // List<Node> tmpList = new ArrayList<>();
        // tmpList.add(rootNode);
        // PrintMethods.printSymbolsInTreeShape(tmpList);
        try{
        File saveCodebook = new File("codebook.txt");
        PrintWriter writer = new PrintWriter(saveCodebook);
        writer.print("");
        writer.close();
        updateCodebook(rootNode);
        }catch (Exception e) {
            System.err.println("ERROR: "+e.getMessage());
        }
    }

    public static void updateCodebook(Node currentNode) throws FileNotFoundException{
        try{
        File saveCodebook = new File("codebook.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(saveCodebook, true));
        if (currentNode.leftNode == null && currentNode.rightNode == null) {
            // bw.write(String.valueOf(currentNode.inputChar)+Integer.valueOf(Character.valueOf(currentNode.inputChar).charValue()) + ":" + currentNode.codedSymbol);
            bw.write((int) currentNode.inputChar+":"+currentNode.codedSymbol);
            bw.newLine();
        }
        if (currentNode.leftNode != null) {
            updateCodebook(currentNode.leftNode);
        }
        if (currentNode.leftNode != null) {
            updateCodebook(currentNode.rightNode);
        }
        bw.flush();
        bw.close();
    }catch(Exception e) {
        System.err.println("ERROR: "+e.getMessage());
    }
    }

    public static void updateSymbol(Node currentNode) {
        currentNode.setSymbol("");
    }

    public static void sort(List<Node> nodeList) {
        Collections.sort(nodeList);
    }
}