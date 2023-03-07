import java.io.*;
import java.util.*;
import java.util.Map.*;

public class Merge {
    public static void main(String[] args) {
        try{
        // Create arraylist that converts each file inputted to an arraylist of integers
        ArrayList<ArrayList<Integer>> allLists = new ArrayList<ArrayList<Integer>>();
        // Create an arraylist to add final integers to in order of smallest to largest
        ArrayList<Integer> mergedList = new ArrayList<Integer>();
        // Scan each file for all integers and add each integer to the separate arraylist for each file in "allLists"
        for (int x = 0; x < args.length; x++) {
            Scanner scanner = new Scanner(new File(args[x]));
            ArrayList<Integer> list = new ArrayList<>();
            allLists.add(list);
            int y = 0;
            while (scanner.hasNextInt()) {
                y++;
                allLists.get(x).add(scanner.nextInt());
            }
            // Ignore empty files
            if(y==0) {
                System.err.println("ERROR: File is empty, sorting without this file");
                allLists.remove(x);
            } else{
            // Sort to ensure integers from each file are in proper order
            Collections.sort(allLists.get(x));
            }
        }
        // Create arraylist to keep track of which index each arraylist is on in traversing through ordered integers
        // i.e. the first arraylist might be on the 5th index because it had many small values
        // while the second arraylist could still be on the 0th index because its first value is larger
        List<Integer> currentIndexForEachArrayListInAllLists = new ArrayList<Integer>(
                Collections.nCopies(allLists.size(), 0));
        // Until break is called
        while (true) {
            // Create a hashmap that stores all the values of each arraylist at each arraylist's current index
            // according to currentIndexForEachArrayListInAllLists
            HashMap<Integer, Integer> allListsIndexAndValueOfNumber = new HashMap<>();
            for (int x = 0; x < allLists.size(); x++) {
                allListsIndexAndValueOfNumber.put(x,
                        allLists.get(x).get(currentIndexForEachArrayListInAllLists.get(x)));
            }
            // Find the smallest integer out of the values of the hashmap
            int min = Collections.min(allListsIndexAndValueOfNumber.values());
            // Retrieve all the keys where this value occurs in case two arraylists
            // share the next smallest integer's value
            for (Entry<Integer, Integer> entry : allListsIndexAndValueOfNumber.entrySet()) {
                if (Objects.equals(entry.getValue(), min)) {
                    // Add each value to the final arraylist of values in order
                    mergedList.add(entry.getValue());
                    // If the key had the smallest value out of all the keys, increase its index by 1 for next round
                    currentIndexForEachArrayListInAllLists.set(entry.getKey(),
                            currentIndexForEachArrayListInAllLists.get(entry.getKey()) + 1);
                }
            }
            // If the value of currentIndexForEachArrayListInAllLists becomes greater than the size of the allLists arraylist minus 1
            // remove both the arraylists for keeping track of the index and keeping track of the integers left, as every integer within
            // the arraylist has already been traversed.
            for (int x = 0; x < allLists.size(); x++) {
                if (allLists.get(x).size() - 1 < currentIndexForEachArrayListInAllLists.get(x)) {
                    currentIndexForEachArrayListInAllLists.remove(x);
                    allLists.remove(x);
                }
            }
            // If there are no arraylists left, break from while loop since every integer in every (file) arraylist has been traversed
            if (allLists.isEmpty()) {
                break;
            }
        }
        // Print out final arraylist with merged, ordered values
        for (int x = 0; x < mergedList.size(); x++) {
            System.out.println(mergedList.get(x));
        }
    } catch(Exception e) {
        // Catch any error and print it out (mainly for filenotfound)
        System.err.println("ERROR: "+e.getMessage());
    }
    }
}