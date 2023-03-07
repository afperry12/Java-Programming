import java.util.Scanner;
import java.util.HashMap;

public class CountChars {
    public static void main(String[] args) {
        // Take Input
        Scanner scan = new Scanner(System.in);
        // Create HashMap to add character and increment number of that character
        HashMap<String, Integer> charCounts = new HashMap<String, Integer>();
        while (scan.hasNextLine()) {
            String nextLine = scan.nextLine();
            // Iterate through each character inputted
            for (int x = 0; x < nextLine.length(); x++) {
                int unicodeCheck = (int) nextLine.charAt(x);
                // Check if character is within range of Unicode values required by assignment
                if (unicodeCheck >= 0&&unicodeCheck <= 255) {
                    // If the character has been seen before, increment integer value by 1
                    if (charCounts.containsKey(Character.toString(nextLine.charAt(x)))) {
                        charCounts.put(Character.toString(nextLine.charAt(x)),
                                charCounts.get(Character.toString(nextLine.charAt(x))) + 1);
                    } 
                    // Otherwise, create a key with character value and set initial value to 1
                    else {
                        charCounts.put(Character.toString(nextLine.charAt(x)), 1);
                    }
                }
            }
        }
        // For each key value pair, print the character and how many there are from HashMap
        charCounts.forEach((key, value) -> System.out.println(key + " " + value));
    }
}