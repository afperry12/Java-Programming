import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

public class Encode {
    public static void main(String[] args) {
        try{
        File uncompressedFile = new File(args[0]);
        Scanner uncompressedFileScanner = new Scanner(uncompressedFile);
        double uncompressedSize = Files.size(uncompressedFile.toPath());
        // System.out.println("Initial size: "+uncompressedSize);
        double compressedSize = 0;

        File saveCompressedVersion = new File(args[1]);
        BufferedWriter bw = new BufferedWriter(new FileWriter(saveCompressedVersion,false));

        File codebookFile = new File("codebook.txt");
        Scanner scanner = new Scanner(codebookFile);

        HashMap<Integer, String> huffmanEncodings = new HashMap<>();
        while (scanner.hasNextLine()) {
            String dictionaryHuffmanCoding = scanner.nextLine();
            int charValueAsInt = Integer.valueOf(dictionaryHuffmanCoding.split(":")[0]);
            String binarySymbol = dictionaryHuffmanCoding.split(":")[1];
            huffmanEncodings.put(charValueAsInt, binarySymbol);
        }

        while (uncompressedFileScanner.hasNextLine()) {
            String nextLine = uncompressedFileScanner.nextLine();
            for (int x = 0; x < nextLine.length(); x++) {
                int unicodeCheck = (int) nextLine.charAt(x);
                if(!(unicodeCheck>='\u0007'&&unicodeCheck<='\u00fe')||unicodeCheck=='\u0004') {
                } else {
                try {
                String encodedValue = huffmanEncodings.get(unicodeCheck);
                bw.write(encodedValue);
                compressedSize+=((double)encodedValue.length()/8.0);
                } catch(Exception e) {
                    System.err.println("ERROR: "+e.getMessage());
                    continue;
                }
            }
            }
            if(uncompressedFileScanner.hasNextLine()) {
                bw.write(huffmanEncodings.get(10));
            }
        }
        bw.flush();
        bw.close();
        // System.out.println("Final size: "+compressedSize);
        double percentCompression = (compressedSize/uncompressedSize);
        // System.out.println(percentCompression);
        } catch(Exception e){
            System.err.println("ERROR: "+e.getMessage());
        }
    }
}
