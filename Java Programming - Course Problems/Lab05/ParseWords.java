/** class ParseWords
 *
 * Parse first argument to the program.
 * Find all words between colons (':') and print them out.
 * Print "BLANK" if a colon starts or ends the input,
 * or for consecutive colons in the input.
 */
public class ParseWords {
	static public void main(String[] args) {
		if (args.length < 1) { return; }

		String input = args[0];
		final int Length = input.length();
		if (Length < 1) { return; }

		boolean printBlank = true; // if first char is colon, print BLANK
		int curIndx = 0;

		while (curIndx < Length) {
			if (input.charAt(curIndx) == ':') {
				if (printBlank) {
					System.out.println("BLANK");
				}
				curIndx++;
				printBlank = true; // just handled a ':'
			} else {
				// find next colon, if any, starting to look at 'curIndx'.
				int nextColon = input.indexOf(":", curIndx);
				if (nextColon < 0) {
					 // no more colons in input
					 nextColon = Length;
				}
				// Print from 'curIndx' up to the next colon (or end of the string).
				System.out.println(input.substring(curIndx, nextColon));
				curIndx = nextColon;
				printBlank = false;
			}
		}

		// If last char is colon, need to print BLANK one more time.
		if (input.charAt(Length-1) == ':') {
			System.out.println("BLANK");
		}
	} // end main()
} // end class ParseWords