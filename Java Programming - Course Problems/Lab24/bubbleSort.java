/****************************************************************
 * filename: bubbleSort.java
 * 
 * Methods to perform a bubble sort, selection sort,
 * and insertion sort.
 *
 * Main() method generates test data, sorts it, validates result.
 *
 * @author phaskell
 ****************************************************************/


//////
////// Imports
//////
import java.util.Random;


//////
////// Class definitions
//////
/** class bubbleSort
 *
 * A class that performs and validates several types of sorts.
 * @author phaskell
 */
public class bubbleSort {
	// Variables for counting how many operations are used
	// by different sort methods.
	static int numCompares = 0;
	static int numCopies = 0;

	/** SetValues()
	 *
	 * Assigns random integer values to the provided int array.
	 * @param values - the array to be filled in
	 */
	private static void SetValues(int[] values) {
		final int L = values.length;
		Random randGen = new Random();
		for(int i = 0; i < L; i++) {
			values[i] = randGen.nextInt(2*L);
		}
	}

	/** validate()
	 *
	 * Validates that the input values are sorted in increasing order.
	 * @param values - the data to validate
	 * @throws Exception - if the data are not in increasing order
	 */
	private static void validate(int[] values)
			throws
				Exception // if input array is not sorted properly
	{
		for(int i = 1; i < values.length; i++) {
			if (values[i-1] > values[i]) {
				throw(new Exception("Sort failed"));
			}
		}
	} // end validate()

	
	/** bubbleSort()
	 *
	 * Perform an in-place bubble sort on 'values'.
	 * @param values - the data to sort
	 */
	private static void bubbleSort(int[] values) {
		for (int i = values.length - 1; i > 0; --i) {
			for(int j = 1; j <= i; j++) {
				if (values[j-1] > values[j]) {
					// swap
					int tmp = values[j-1];
					values[j-1] = values[j];
					values[j] = tmp;
				}
			}
		}
	}

	/** selectionSort()
	 *
	 * Perform an in-place selection sort on 'values'.
	 * @param values - the data to sort
	 */
	private static void selectionSort(int[] values) {
		for (int i = 0; i < values.length; i++) {
			int minVal = values[i];
			int minIndx = i;
			for(int j = i+1; j < values.length; j++) {
				if (values[j] < minVal) {
					minVal = values[j];
					minIndx = j;
				}
			}
			int tmp = values[i];
			values[i] = values[minIndx];
			values[minIndx] = tmp;
		}
	}

	public static void main(String[] args) {
		// Create a list of integers
		final int NumValues = 50000;
		int values[] = new int[NumValues];

		// Assign random values to values[]
		SetValues(values);
		
		// Sort the data and print run-time info.
		final long startTime = System.currentTimeMillis();
		insertionSort(values);

		final long endTime = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (endTime - startTime)/1000.0 + " seconds");
		System.out.println(numCompares + " compares, " + numCopies + " copies");
		
		// Validate the sort worked properly
		try {
			validate(values);
		} catch (Exception e) { System.out.println(e); }
	} // end main()
	
	/** class Node
	 *
	 * Store an int in a linked list node.
	 */
	static class Node {
		int val;
		Node next;
		Node(int v) { val  = v; next = null; }
	}

	/** class IntList
	 *
	 * A singly-linked list of Nodes.
	 */
	static class IntList {
		Node head;
		
		/* add()
		 *
		 * Add integer 'v' to our list, in sorted order!
		 */
		void add(int v) {
			Node n = new Node(v); // the new Node for value 'v'.

			numCompares++;
			if (head == null) { // empty list
				numCopies++;
				head = n;
				return;
			}
			numCompares++;
			if (head.val > v) { // 'v' is smallest value so far
				numCopies += 2;
				n.next = head;
				head = n;
				return;
			}
			// Must stick 'n' somewhere in middle of list.
			numCopies++;
			Node curr = head;
			numCompares += 2;
			while (curr.next != null && curr.next.val < v) {
				numCompares += 2;
				numCopies++;
				curr = curr.next;
			}
			// Found where in list to insert 'n', so insert it.
			numCopies += 2;
			n.next = curr.next;
			curr.next = n;
		}
	} // end class IntList
	
	/** insertionSort()
	 *
	 * Do an insertion sort using a list, rather than sorting an array.
	 */
	private static void insertionSort(int[] values) {
		IntList list = new IntList();

		// Put each element of 'values' into the sorted 'list'.
		for(int i = 0; i < values.length; i++) {
			list.add(values[i]); // add to list in correct sorted order
		}

		// Now read values out of the list back into the array.
		Node head = list.head;
		int i = 0;
		while (head != null) {
			numCopies += 2;
			values[i++] = head.val;
			head = head.next;
		}
	}

} // end class bubbleSort


//for(int i = values.length; i > 0; --i) {
//	for(int j = 1; j < i; j++) {
//		numCompares++;
//		if (values[j-1] > values[j]) {
//			numCopies += 3;
//			final int tmp = values[j-1];
//			values[j-1] = values[j];
//			values[j] = tmp;
//		}
//	}
//}

//for(int i = 0; i < values.length-1; i++) {
//	numCopies += 2;
//	int minIndx = i;
//	int minVal = values[minIndx];
//	for(int j = i+1; j < values.length; j++) {
//		numCompares++;
//		if (values[j] < minVal) {
//			numCopies += 2;
//			minIndx = j;
//			minVal = values[minIndx];
//		}
//	}
//	numCopies += 3;
//	final int tmp = values[i];
//	values[i] = values[minIndx];
//	values[minIndx] = tmp;
//}
