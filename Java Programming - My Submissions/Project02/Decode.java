import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Decode {
    public static HashMap<Integer, String> huffmanEncodings = new HashMap<>();
    public static void main(String[] args) {
        try{
            File compressedFile = new File(args[0]);
            Scanner compressedFileScanner = new Scanner(compressedFile);
    
            File saveDecompressedVersion = new File(args[1]);
            BufferedWriter bw = new BufferedWriter(new FileWriter(saveDecompressedVersion,false));

            File codebookFile = new File("codebook.txt");
            Scanner scanner = new Scanner(codebookFile);

            while (scanner.hasNextLine()) {
                String dictionaryHuffmanCoding = scanner.nextLine();
                int charValueAsInt = Integer.valueOf(dictionaryHuffmanCoding.split(":")[0]);
                String binarySymbol = dictionaryHuffmanCoding.split(":")[1];
                huffmanEncodings.put(charValueAsInt, binarySymbol);
            }

            Node rootNode = new Node();
            rootNode.codedSymbol = "";
            reconstructTree(rootNode);

            String compressedFormatOfInput = "";
            while(compressedFileScanner.hasNextLine()) {
                compressedFormatOfInput+=compressedFileScanner.nextLine();
            }

            bw.write(searchForTextInTree(compressedFormatOfInput, rootNode));
            bw.flush();
            bw.close();

        } catch(Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static String searchForTextInTree(String compressedFormatOfInput, Node rootNode) {
        String decodedMessage = "";
        Node currentNode = rootNode;
            for(int x = 0;x<compressedFormatOfInput.length();x++) {
                if(String.valueOf(compressedFormatOfInput.charAt(x)).equals("0")) {
                    currentNode = currentNode.leftNode;
                } else if(String.valueOf(compressedFormatOfInput.charAt(x)).equals("1")) {
                    currentNode = currentNode.rightNode;
                } else {
                    System.err.println("ERROR: non 0 or 1 value in compressed input");
                }
                if(currentNode.leftNode==null&&currentNode.rightNode==null) {
                    // Test to ensure it recognizes end of transmission
                    // if(decodedMessage.length()==3) {
                    //     currentNode.inputChar='\u0004';
                    // }
                    if(((char)currentNode.inputChar)=='\u0004') {
                        return decodedMessage;
                    } else{
                    decodedMessage+=String.valueOf(currentNode.inputChar);
                    currentNode = rootNode;
                    }
                }
        }
        return decodedMessage;
    }

    public static void reconstructTree(Node currentNode) {
        if(huffmanEncodings.containsValue(currentNode.codedSymbol+"0")) {
            Node n = new Node();
            currentNode.leftNode = n;
            String codedSymbol = currentNode.codedSymbol+"0";
            n.codedSymbol = codedSymbol;
            for(Map.Entry entry: huffmanEncodings.entrySet()) {
                if(codedSymbol.equals(entry.getValue())) {
                    int key = (int) entry.getKey();
                    n.inputChar = (char) key;
                    break;
                }
            }
        } else {
            Node n = new Node();
            currentNode.leftNode = n;
            String codedSymbol = currentNode.codedSymbol+"0";
            n.codedSymbol = codedSymbol;
            for(Map.Entry entry: huffmanEncodings.entrySet()) {
                if(codedSymbol.equals(entry.getValue())) {
                    int key = (int) entry.getKey();
                    n.inputChar = (char) key;
                    break;
                }
            }
            reconstructTree(n);
        }
        if(huffmanEncodings.containsValue(currentNode.codedSymbol+"1")) {
            Node n = new Node();
            currentNode.rightNode = n;
            String codedSymbol = currentNode.codedSymbol+"1";
            n.codedSymbol = codedSymbol;
            for(Map.Entry entry: huffmanEncodings.entrySet()) {
                if(codedSymbol.equals(entry.getValue())) {
                    int key = (int) entry.getKey();
                    n.inputChar = (char) key;
                    break;
                }
            }
        } else {
            Node n = new Node();
            currentNode.rightNode = n;
            String codedSymbol = currentNode.codedSymbol+"1";
            n.codedSymbol = codedSymbol;
            for(Map.Entry entry: huffmanEncodings.entrySet()) {
                if(codedSymbol.equals(entry.getValue())) {
                    int key = (int) entry.getKey();
                    n.inputChar = (char) key;
                    break;
                }
            }
            reconstructTree(n);
        }
    }
}
