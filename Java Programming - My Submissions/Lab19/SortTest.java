import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class SortTest {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("pythonsort.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        // List<Integer> numbers = new ArrayList<>();
        String[] allNumbersSeparatedBySpaces = br.readLine().split(" ");
        int[] allNumbers = new int[allNumbersSeparatedBySpaces.length];
        for (int i = 0;i < allNumbersSeparatedBySpaces.length; i++) {
            allNumbers[i] = Integer.parseInt(allNumbersSeparatedBySpaces[i]);
        }
        int previousNumber = 0;
        int numberCount = 0;
        for (int i = 0; i < allNumbers.length; i++) {
            numberCount++;
            if(allNumbers[i]>=previousNumber) {
                previousNumber = allNumbers[i];
            } else {
                System.err.print("FAIL incorrect sort");
                System.exit(1);
            }
        }
        if(numberCount==10000) {
            System.out.print("PASS");
        } else {
            System.err.print("FAIL incorrect element count");
        }
    }
}