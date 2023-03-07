import java.util.Scanner;

class ReadKeyboard {
	static public void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int i = 0;
		while (i < 6) {
			if (scan.hasNextInt()) {
				int readVal = scan.nextInt();
				System.out.println("Read an int: " + readVal);
				i++;
			} else {
				System.out.print("Unacceptable input, please try again: ");
				String line = scan.next();
				System.out.println(line);
			}
		}
	}
}