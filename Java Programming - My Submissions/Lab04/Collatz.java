public class Collatz {
    public static void main(String[] args) {
        // For loop to iterate through specified numbers
        for (int valueToConverge = 1; valueToConverge <= 200; valueToConverge++) {
            int stepsTaken = 0;
            boolean valueNotOne = true;
            int a = valueToConverge;
            while (valueNotOne) {
                // If already equals one then do not continue
                if (a == 1) {
                    valueNotOne = false;
                    break;
                }
                // If even then do specified equation and check if equals one. If equals one, break.
                if (a % 2 == 0) {
                    a = a / 2;
                    stepsTaken++;
                    if (a == 1) {
                        valueNotOne = false;
                        break;
                    }
                }
                // If odd then do specified equation and check if equals one. If equals one, break.
                if (a % 2 == 1) {
                    a = 3 * a + 1;
                    stepsTaken++;
                    if (a == 1) {
                        valueNotOne = false;
                        break;
                    }
                }
            }
            // After breaking print out value to converge and the number of steps taken to reach 1
            // System.out.println("Original number: " + valueToConverge + " Value reached: "
            // + a + " Steps taken: " + stepsTaken);
            System.out.println(valueToConverge + " " + stepsTaken);
        }
    }
}
