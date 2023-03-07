public class TextTriangle {
    public static void main(String[] args) {
        // Create alphabet string and placeholder string variables
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String newString = "";
        // For loop that increments by two to skip two letters at a time
        for (int x = 0; x < alphabet.length(); x += 2) {
            // Clear placeholder between loop calls
            newString = "";
            // For loop that prints out all letters in each row,
            // starting with character at X value to skip beginning 
            // characters as needed
            for (int y = 0; y < alphabet.length() - x; y++) {
                newString+=alphabet.charAt(x+y);
            }
            // Print string after fully composed
            System.out.println(newString);
        }
    }
}