/** filename:  Border.java
 *
 * Contains classes for printing strings with a border around them.
 *
 * @author:  Paul Haskell
 */
 

/** BorderPrinter
  *
  * Class BorderPrinter prints strings with a border on all sides.
  * The user can specify the border width and the character used
  * to make up the border.
  */
class BorderPrinter {
	// Class variables
	private int borderWidth; // must be >= 1
	private char ch;

	/* Constructors
	 *
	 * Set the border width and use '*' as the default starting character.
	 */
	BorderPrinter(int w) {
		setWidth(w);
		setChar('*');
	}
	BorderPrinter(int w, char c) {
		setWidth(w);
		setChar(c);
	}
	
	// Member functions
	public void setWidth(int w) {
		if (w < 1) {
			borderWidth = 1;
		} else {
			borderWidth = w;
		}
	}
	
	public void setChar(char c) {
		ch = c;
	}

	/* printLine()
	 *
	 * Print a line of our border character 'ch', with given length.
	 */
	private void printLine(int strWidth) {
		int count = 0;
		while (count++ < strWidth) {
			System.out.print(ch);
		}
		System.out.println();
	}

	/* print()
	 *
	 * Print the given string 's' with a border on the top, bottom, and sides.
	 */
	public void print(String s) {
		// length is the length of the string plus the side borders, with spaces.
		final int length = s.length() + 2 + 2*borderWidth;

		// Print top border
		int count = 0;
		while (count++ < borderWidth) {
			printLine(length);
		}

		// Print string with side borders.
		// Left border
		count = 0;
		while (count++ < borderWidth) {
			System.out.print(ch);
		}
		// The actual string
		System.out.print(" " + s + " ");
		// Right border
		count = 0;
		while (count++ < borderWidth) {
			System.out.print(ch);
		}
		System.out.println();

		// Print bottom border
		count = 0;
		while (count++ < borderWidth) {
			printLine(length);
		}
		// Print blank line
		System.out.println();
	} // end print()

} // end class BorderPrinter


/** Border
 *
 * This class contains the main() method,
 * which shows off the features of BorderPrinter.
 */
public class Border {
	public static void main(String[] programInputs) {
		BorderPrinter myPrinter = new BorderPrinter(1);
		myPrinter.print("Happy Wednesday");
		myPrinter.print("How long till lunch?");
		myPrinter.setWidth(2);
		myPrinter.setChar('#');
		myPrinter.print("Wow, bigger border");

		BorderPrinter bigPrinter = new BorderPrinter(3, '\u00a5');
		bigPrinter.print("Go Dons!");
	}
} // end class Border