import java.util.Scanner;

public class EightLines {

    public static void main(String[] args) {
        // Setup scanner and input string variables
        Scanner scanner = new Scanner(System.in);
        String[] input = new String[8];
        for (int x = 0; x < input.length; x++) {
            // Check if scanner input has been pre-emptively terminated and exit with error if so
            if (scanner.hasNextLine() == false) {
                System.err.println("ERROR: fewer than 8 input lines");
                System.exit(0);
            }
            // Get next line
            input[x] = scanner.nextLine();
            // Create new String array to split up words in sentences
            String[] inputWords = new String[input[x].trim().split("\\s+").length];
            for (int y = 0; y < input[x].trim().split("\\s+").length; y++) {
                inputWords[y] = input[x].trim().split("\\s+")[y];
                // Print each word on new line
                System.out.println(inputWords[y]);
            }
        }
    }
}