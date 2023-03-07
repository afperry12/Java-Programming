import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.io.File;

public class HuffmanCountChars {
    public static HashMap<String, Integer> main(File file) {
        try{
        // BufferedReader br = new BufferedReader(new FileReader(file));
        // Take Input
        Scanner scan = new Scanner(file);
        // Create HashMap to add character and increment number of that character
        HashMap<String, Integer> charCounts = new HashMap<String, Integer>();
        while (scan.hasNextLine()) {
            String nextLine = scan.nextLine();
            // Iterate through each character inputted
            for (int x = 0; x < nextLine.length(); x++) {
                // int unicodeCheck = (int) nextLine.charAt(x);
                char c = nextLine.charAt(x);
                // System.out.println((int)c);
                    if((c>='\u0007'&&c<='\u00fe')||c=='\u0004') {
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
        charCounts.put(String.valueOf('\u0004'), 1);
        for(int x = 7;x<=254;x++) {
            if(charCounts.containsKey(Character.toString(Character.valueOf((char)x)))) {
                charCounts.put(Character.toString(Character.valueOf((char)x)),
                charCounts.get(Character.toString(Character.valueOf((char)x))) + 1);
            } else {
                charCounts.put(Character.toString(Character.valueOf((char)x)),1);
            }
        }
        // For each key value pair, print the character and how many there are from HashMap
        // charCounts.forEach((key, value) -> System.out.println(key + " " + value));
        return charCounts;
    }catch(Exception e) {
        System.err.println("ERROR:"+e.getMessage());
        System.exit(1);
        return null;
    }
    }
}