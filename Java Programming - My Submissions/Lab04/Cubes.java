public class Cubes {
    public static void main(String[] args) {
        int cubedValue = 0;
        int valueToCube = 1;
        while (cubedValue<2000) {

            // Math for cubeing value
            cubedValue = (int) Math.pow(valueToCube,3);

            // Ensures the cubed value of a number is never printed if greater than or equal to 2000
            if (cubedValue>=2000) {
                break;
            }

            // Prints cubed value
            System.out.println(cubedValue);

            // Iterates to next number
            valueToCube++;
        }
    }
}
