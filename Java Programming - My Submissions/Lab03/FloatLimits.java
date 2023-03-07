import java.math.BigDecimal;

public class FloatLimits {
    public static void main(String[] args) {
        // Initialize float variables to 0 and 1. Divide the float initialized to 1 as many times as
        // possible before the decimal specifity forces Java to recognize the number as 0. Save
        // and print previous number before floats are equal to each other.
        float initialNumberToDivide = 1;
        float testV = 0;
        boolean flag = true;
        float previousValue = 0;
        while (flag) {
            initialNumberToDivide = (float) (initialNumberToDivide / 2);
            // System.out.println(initialNumberToDivide);
            if (testV == initialNumberToDivide) {
                System.out.println("Smallest positive float is " + previousValue);
                System.out.println(new BigDecimal(previousValue).toPlainString());
                flag = false;
            }
            previousValue = initialNumberToDivide;
        }

        // Initialize double variables to 0 and 1. Divide the double initialized to 1 as many times as
        // possible before the decimal specifity forces Java to recognize the number as 0. Save
        // and print previous number before doubles are equal to each other.
        double initialDoubleToDivide = 1;
        double testValue = 0;
        flag = true;
        double previousDoubleValue = 0;
        while (flag) {
            initialDoubleToDivide = (double) (initialDoubleToDivide / 2);
            // System.out.println(initialDoubleToDivide);
            if (testValue == initialDoubleToDivide) {
                System.out.println("Smallest positive double is " + previousDoubleValue);
                System.out.println(new BigDecimal(previousDoubleValue).toPlainString());
                flag = false;
            }
            previousDoubleValue = initialDoubleToDivide;
        }
    }
}
