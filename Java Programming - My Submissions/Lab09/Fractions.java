import java.util.Scanner;

public class Fractions {

    public static void main(String[] args) {
        // Can use scanner to take keyboard inputs, too
        // Scanner scanner = new Scanner(System.in);
        String input = new String();
        boolean isPositive = true;
        // Run until manually exited if using Scanner
        while (true) {
            // Use boolean to continously check if input is valid
            boolean incorrectValue = true;
            while (incorrectValue) {
                try {
                    // Use scanner
                    // input = scanner.nextLine().trim().replaceAll(" +", " ");
                    // Takes single command line argument and manages multiple spaces
                    input = args[0].toString().trim().replaceAll(" +", " ");
                    // Manages space, plus, minus, and /
                    String input2 = input.replaceAll(" |/[+]/g|/[-]/g|/", "");
                    Double inputAsDouble = Double.parseDouble(input2);
                    incorrectValue = false;
                } catch (Exception e) {
                    // If input is not valid
                    System.err.println("ERROR invalid input");
                    incorrectValue = true;
                    System.exit(0);
                }
            }
            // If input contains a negative sign, then note that final response will be
            // negative
            if (input.contains("-")) {
                input = input.replaceAll("-", "");
                isPositive = false;
            } else {
                isPositive = true;
            }
            // Split all inputs by space and put in array of Strings
            String[] array = input.split(" ");
            String[] array2;
            double division;
            // Initiate doubles to add and then keep adding every number split between
            // spaces until length of String has been traversed
            double doublesToAdd = 0.0;
            // Will set dividebyzero to true later if any fraction is dividedbyzero which
            // can then always return error
            // no matter which index of the String array this fraction is
            boolean divideByZero = false;
            for (int p = 0; p < array.length; p++) {
                divideByZero = false;
                // If split String contains a /, then split where the / is
                if (array[p].contains("/")) {
                    array2 = array[p].split("/");
                    // For the length, check if divides by zero and if it does not then add the
                    // division of the two numbers to total sum
                    for (int t = 0; t < array2.length; t += 2) {
                        if (Double.parseDouble(array2[t + 1]) == 0.0) {
                            divideByZero = true;
                            System.err.println("ERROR dividing by Zero");
                            break;
                        } else {
                            division = (Double.parseDouble(array2[t]) / Double.parseDouble(array2[t + 1]));
                            doublesToAdd = doublesToAdd + division;
                        }
                    }
                    // If there was a divide by zero break again
                    if (divideByZero == true) {
                        break;
                    }
                } else {
                    // If there is no fraction just add whole number to total value normally
                    doublesToAdd = doublesToAdd + Double.parseDouble(array[p]);
                }
            }
            // If there was a divide by zero break again
            if (divideByZero == true) {
                break;
            }
            // If positive then print the final value
            else if (isPositive == true) {
                System.out.println(doublesToAdd);
                break;
            }
            // If positive then print the final value with a negative sign in front
            else if (isPositive == false) {
                System.out.println("-" + String.valueOf(doublesToAdd));
                break;
            }
        }
    }
}