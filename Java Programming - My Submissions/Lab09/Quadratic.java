import java.util.Scanner;

public class Quadratic {
    public static void main(String[] args) {
        // Setup variables
        Double[] input = new Double[3];
        int takeInThreeInputs = 0;
        Scanner scanner = new Scanner(System.in);
        // For loop for input length
        for (int x = 0; x < input.length; x++) {
            boolean incorrectValue = true;
            // While input has not been terminated and an incorrect value has been entered, 
            // keep looping until one of these conditions is false
            while (incorrectValue&&scanner.hasNextLine()) {
                // Use while loop and try/catch to repeatedly handle errors in user input
                // until the input is successfully converted to double
                try {
                    String[] arrayOfDoublesInLine = scanner.nextLine().trim().split(" ");
                    for (int p = 0; p<arrayOfDoublesInLine.length;p++) {
                        // Set input to take in doubles split by string and on different lines until 3 are taken in
                        input[takeInThreeInputs]=Double.parseDouble(arrayOfDoublesInLine[p]);
                        takeInThreeInputs++;
                        if(takeInThreeInputs==3) {
                            break;
                        } 
                    }
                    if (takeInThreeInputs==3) {
                        break;
                    }
                    incorrectValue = false;
                } catch (Exception e) {
                    incorrectValue = true;
                }
            }
            if (takeInThreeInputs==3) {
                break;
            } else
            if(scanner.hasNextLine()==false&&takeInThreeInputs!=3) {
                System.err.println("ERROR: Not enough inputs");
                System.exit(0);
            }
        }
        // Create part of equations to check if taking square root of negative number or
        // dividing by 0
        if ((Math.pow(input[1], 2) - 4 * input[0] * input[2]) < 0) {
            System.err.println("ERROR number under square root is negative");
            System.exit(0);
        } else if (input[0] == 0) {
            System.err.println("ERROR dividing by 0");
        } else {
            // Get x1 and x2 values using quadratic equation (switching + and -), then print
            // out results
            double x1 = (-input[1] + Math.sqrt(Math.pow(input[1], 2) - 4 * input[0] * input[2])) / (2 * input[0]);
            double x2 = (-input[1] - Math.sqrt(Math.pow(input[1], 2) - 4 * input[0] * input[2])) / (2 * input[0]);
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }
    }
}