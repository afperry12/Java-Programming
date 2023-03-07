public class FixCapitalization {
    public static void main(String[] args) {
        // Create string that finalized pieces of the string will be appended to later
        String finalString = "";
        // Create boolean to fix capitalization
        boolean shouldBeCapital = true;
        // Ensure something is being passed through
        if (args.length > 0) {
            // Iterate through every character. If sentence ends with punctuation, make next
            // nonspace character capitalized
            // and add to final string. Otherwise, just append to final string.
            for (int t = 0; t < args[0].length(); t++) {
                char individualLetters = args[0].charAt(t);
                String convertIndLetters = String.valueOf(individualLetters).toLowerCase();
                if (shouldBeCapital == true && !convertIndLetters.equals(" ")) {
                    finalString += convertIndLetters.toUpperCase();
                    shouldBeCapital = false;
                } else {
                    finalString += convertIndLetters;
                }
                if (convertIndLetters.equals(".") || convertIndLetters.equals("!") || convertIndLetters.equals("?")) {
                    shouldBeCapital = true;
                }
            }
        }
        // Print final string
        System.out.print(finalString);
    }
}