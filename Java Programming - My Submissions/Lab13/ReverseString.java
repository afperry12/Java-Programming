public class ReverseString {
	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				// Print array of chars
				System.out.println(StringToCharsReverse(args[0]));
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	// Method that takes an inputted String and converts to array of chars
	static char[] StringToCharsReverse(String input) {
		// Create array of chars the same length as the length of the inputted String
		char[] output = new char[input.length()];
		int i = input.length() - 1;
		int t = 0;
		// While i is greater than or equal to 0, set the highest index of the char
		// output array to the value
		// of the lowest index of the String, in the process converting the string to a
		// char and reducing the
		// value of the variable for the next output index and increasing the variable
		// for the next String index
		while (i >= 0) {
			output[i--] = input.charAt(t++);
		}
		// Return the array of chars
		return output;
	}
}