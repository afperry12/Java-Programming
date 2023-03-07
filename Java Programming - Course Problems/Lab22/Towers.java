/*************************************************
 * filename: Towers.java
 *
 * Set up and solve the Towers of Hanoi problem,
 * using recursion.
 ************************************************/


////// Imports
import java.util.ArrayList;
import java.util.Stack;


/** class Towers
 *
 * A class that sets up the Towers of Hanoi game and solves it.
 */
public class Towers {
	// Data structure for the three towers:  an ArrayList that holds three "Stacks".
	//
	// A "stack" is a data structure where you can push elements onto the "top"
	// and can "pop" them off the top.  But you only access the elements through
	// the "top".
	ArrayList<Stack<Character>> towers;
	final static char BottomChar = 'G';
	final static int Size = BottomChar - 'A' + 1;


	/** Towers Constructor
	 *
	 * Create the three towers and populate tower#0.
	 */
	Towers() {
		// Create the ArrayList that stores the three Stacks.
		towers = new ArrayList<Stack<Character>>();
		
		// Now create the three towers:  Stacks onto which we "push" and "pop" characters.
		towers.add(new Stack<Character>());
		towers.add(new Stack<Character>());
		towers.add(new Stack<Character>());
		
		// Fill tower#0 with the initial blocks: BottomChar at the bottom, 'A' at the top.
		for(char c = BottomChar; c >= 'A'; --c) {
			towers.get(0).push(c);
		}
	}

	/** Move()
	 *
	 * Solve the problem using recursion!
	 */
	void Move(int numToMove, int from, int to, int temp)
	{
		// Base case:  only one element to move, so just move it.
		if (numToMove <= 1) {
			char movedChar = towers.get(from).pop();
			towers.get(to).push(movedChar);
			System.out.printf("Move %c from %d to %d\n", movedChar, from, to);

		} else {
			// Recursive case:
			this.Move(numToMove-1, from, temp, to); // move a subset from 'from' to 'temp'
			this.Move(1, from, to, temp); // move 1 bottom block from 'from' to 'to'.
			this.Move(numToMove-1, temp, to, from); // finally move subset from 'temp' to 'to'.
		}
	}

	// main()
	public static void main(String[] args) {
		// Create the solver with its three stacks.
		Towers solver = new Towers();

		// Print out the initial tower contents.
		solver.PrintTowers();

		// Move the blocks from tower 0 to tower 1, recursively.  2 is temporary storage
		solver.Move(Size, 0, 1, 2);
		
		// Print out the final tower contents.
		solver.PrintTowers();
	} // end main()

	
	// Print the contents of all the towers.
	void PrintTowers() {
		// For each tower...
		for (int n = 0; n < towers.size(); n++) {
			System.out.print("tower "+  n + ": ");
			// For each element in the current tower...
			for (int elem = 0; elem < towers.get(n).size(); elem++) {
				int indx = towers.get(n).size() - elem - 1;
				System.out.print(towers.get(n).elementAt(indx));
			}
			System.out.println();
		}
	}

} // end class Towers