public class Taylor {
    // Takes in counter to run equation certain number of epochs. Uses for loop to continue adding 
    // the answer from the next iteration to the past total sum.
    public static double calculateEquation(int counter) {
        double sumOfEquation = 0;
        int alternatePlusMinus = 1;
        for (int i = 1; i < counter + 1; i++) {
            sumOfEquation += (alternatePlusMinus) * ((Math.pow(1, i)) / i);
            alternatePlusMinus = alternatePlusMinus * (-1);
        }
        return sumOfEquation;
    }

    // Compares and prints my calculateEquation method with different epoch values to Java's Math.log
    public static void main(String[] args) {
        double x = Math.log(2);
        System.out.println("ln(2) = "+x);

        double result1 = calculateEquation(1);
        double result2 = calculateEquation(10);
        double result3 = calculateEquation(100);
        double result4 = calculateEquation(1000);
        double result5 = calculateEquation(10000);
        System.out.println("One epoch: " + result1);
        System.out.println("Taylor(10) = " + result2);
        System.out.println("Taylor(100) = " + result3);
        System.out.println("Taylor(1000) = " + result4);
        System.out.println("Ten thousand epochs  :" + result5);
    }
}
