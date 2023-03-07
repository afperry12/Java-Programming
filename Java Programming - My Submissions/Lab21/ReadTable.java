import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class ReadTable {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        int lines = 0;
        Scanner scanner = new Scanner(file);
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        int integer = 0;
        String string = "";
        try {
            while (scanner.hasNextLine()) {
                String wholeLine = scanner.nextLine();
                integer = Integer.parseInt(wholeLine.split(":")[0]);
                if (wholeLine.charAt(wholeLine.length() - 1) == ','&&wholeLine.split(":").length==2) {
                    hashMap.put(integer, wholeLine.split(":")[1].replace(",", ""));
                } else {
                    System.err.println("ERROR: Bad Input");
                    System.exit(1);
                }
                lines++;
            }
        } catch (Exception e) {
            System.err.println("ERROR: "+e.getMessage());
            System.exit(1);
        }
        System.out.println(lines);
        for (int x = 1; x < args.length; x++) {
            try {
                int checkForIntInFile = Integer.parseInt(args[x]);
                if (!hashMap.containsKey(checkForIntInFile)) {
                    System.out.println("NOT FOUND");
                } else {
                    System.out.println(hashMap.get(checkForIntInFile));
                }
            } catch (Exception e) {
                System.out.println("ERROR: "+e.getMessage());
            }
        }
    }
}
