/****************************************************************************
 * file:  QuickSort.java
 *
 * Implement and test QuickSort.
 * QuickSort is O(n log n) in computation, and O(1) in storage.
 * And it's a cool algorithm!
 *
 * @author phaskell
 ****************************************************************************/


//////
////// Imports
//////
import java.util.Random;


/** class Sorter
 * 
 * Sort an array of ints, using QuickSort algorithm.
 */
class Sorter {
	// Variables to count the number of operations.
	static int numCompares = 0;
	static int numCopies = 0;

	/** Sort()
	 * 
	 * Sort an array of ints, in-place.
	 * @param in - the array of ints to be sorted
	 */
	public static void Sort(int[] in) {
		Sort(in, 0, in.length-1); // pass actual index of last element, not length
	}

	private static void Sort(int[] in, int first, int last) {
		int i, j; // indices for left- and right-side of array being partitioned
		int pivot; // element being used as a "pivot"
		int temp;

		numCompares++;
		if(first<last) { // otherwise nothing to do!
			numCopies+=3;
			pivot=first;
			// Move all array elements < in[pivot] "to the left".
			// Move all array elements > in[pivot] "to the right".
			i=first; // index for the left side
			j=last; // index for the right side

			while(i<j) {
				numCompares++;
				while(in[i]<=in[pivot] && i<j) {
					numCompares += 2;
					numCopies++;
					i++;
				}
				while(in[j]>=in[pivot] && j>=i) {
					numCompares += 2;
					numCopies++;
					j--;
				}
				// Now in[i] should be > in[pivot] and in[j] should be < in[pivot],
				// so swap them!
				// The other possibility is that we have processed the whole array
				// and i = j+1. Then exit the while(i<j) loop.
				if(i<j) {
					numCompares++;
					numCopies += 3;
					temp=in[i];
					in[i]=in[j];
					in[j]=temp;
				}
			}
			// Now put the pivot value "where it belongs" in the array,
			// which is in[i-1] i.e. in[j].
			numCopies += 3;
			temp=in[pivot];
			in[pivot]=in[j];
			in[j]=temp;

			// Now every element in the array before in[pivot] is <= in[pivot].
			// And every element in the array after in[pivot] is >= in[pivot].
			// So we can sort both halves!
			Sort(in, first, j-1);
			Sort(in, j+1, last);
		} // end if(i < j)
	} // end Sort()

	static public String print( ) { return "" + numCompares + " compares, " + numCopies + " copies"; }
} // end class Sorter


public class QuickSort {
	/** SetValues()
	 *
	 * Assigns random integer values to the provided int array.
	 * @param values - the array to be filled in
	 */
	private static void SetValues(int[] values) {
		final int L = values.length;
		Random randGen = new Random();
		for(int i = 0; i < L; i++) {
			values[i] = randGen.nextInt(L);
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

	public static void main(String[] args) {
		// Generate test data
		int NumVals = 50000;
		int testData[] = new int[NumVals];
		SetValues(testData);

		// Sort
		final long startTime = System.currentTimeMillis();
		Sorter.Sort(testData);
		final long endTime = System.currentTimeMillis();
		System.out.println("Elapsed time: " + (endTime - startTime)/1000.0 + " seconds");
		System.out.println(Sorter.print());

		// Verify
		try {
			validate(testData);
		}
		catch (Exception e) { System.err.println(e); }
	} // end main()

} // end class QuickSort
