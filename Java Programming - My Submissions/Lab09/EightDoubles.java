import java.util.Scanner;

public class EightDoubles {

    public static void main(String[] args) {
        // Create variables for future print outs
        Double[] input = new Double[8];
        double minimum = 0;
        double maximum = 0;
        double total = 0;
        double average = 0;
        boolean hasNextLine = true;
        // For loop for the length of the input
        for (int x = 0; x < input.length; x++) {
            boolean incorrectValue = true;
            // While input has not been terminated and an incorrect value has been inserted, continue until one is not true
            while (incorrectValue && hasNextLine) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    // If input is terminated pre-emptively then exit with error message
                    if (scanner.hasNextLine() == false) {
                        System.err.println("ERROR: fewer than 8 input lines");
                        System.exit(0);
                    }
                    input[x] = scanner.nextDouble();
                    incorrectValue = false;
                } catch (Exception e) {
                    // Continue loop until 8 doubles added
                    incorrectValue = true;
                }
            }
            // Set min and max to the first number
            if (x == 0) {
                minimum = input[x];
                maximum = input[x];
            }
            // If new input is less than min or more then max, update to new value
            // respectively
            if (input[x] < minimum) {
                minimum = input[x];
            }
            if (input[x] > maximum) {
                maximum = input[x];
            }
            // Increase total to include new value
            total = total + input[x];
        }

        for (int y = 0; y < input.length; y++) {
            // Use rounding method for average
            double valueToBeRounded = (double) total / 8;
            average = ((int) (100 * valueToBeRounded)) * .01;
        }
        // Print final minimum, maximum, and average
        System.out.println("Minimum " + minimum);
        System.out.println("Maximum " + maximum);
        System.out.println("Average " + average);
    }
}